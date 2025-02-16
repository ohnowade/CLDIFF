/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.image;


import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

final class PNGFileFormat extends FileFormat {
	static final int SIGNATURE_LENGTH = 8;
	static final int PRIME = 65521;
	PngDecodingDataStream decodingStream;
	PngIhdrChunk headerChunk;
	PngPlteChunk paletteChunk;
	ImageData imageData;
	byte[] data;
	byte[] alphaPalette;
	byte headerByte1;
	byte headerByte2;
	int adler;

/**
 * Skip over signature data. This has already been
 * verified in isFileFormat(). 
 */
void readSignature() throws IOException {
	byte[] signature = new byte[SIGNATURE_LENGTH];
	inputStream.read(signature);
}
/**
 * Load the PNG image from the byte stream.
 */
ImageData[] loadFromByteStream() {
	try {
		readSignature();
		PngChunkReader chunkReader = new PngChunkReader(inputStream);
		headerChunk = chunkReader.getIhdrChunk();
		int imageSize = getAlignedBytesPerRow() * headerChunk.getHeight();
		data = new byte[imageSize];		
		imageData = ImageData.internal_new(
			headerChunk.getWidth(),
			headerChunk.getHeight(),
			headerChunk.getSwtBitsPerPixel(),
			new PaletteData(0, 0, 0),
			4,
			data,
			0,
			null,
			null,
			-1,
			-1,
			SWT.IMAGE_PNG,
			0,
			0,
			0,
			0);		
			
		if (headerChunk.usesDirectColor()) {
			imageData.palette = headerChunk.getPaletteData();
		}
		
		// Read and process chunks until the IEND chunk is encountered.
		while (chunkReader.hasMoreChunks()) {
			readNextChunk(chunkReader);
		}
						
		return new ImageData[] {imageData};
	} catch (IOException e) {
		SWT.error(SWT.ERROR_INVALID_IMAGE);
		return null;
	}
}
/**
 * Read and handle the next chunk of data from the 
 * PNG file.
 */
void readNextChunk(PngChunkReader chunkReader) {
	PngChunk chunk = chunkReader.readNextChunk();
	switch (chunk.getChunkType()) {
		case PngChunk.CHUNK_IEND:
			break;
		case PngChunk.CHUNK_PLTE:
			if (!headerChunk.usesDirectColor()) {
				paletteChunk = (PngPlteChunk) chunk;
				imageData.palette = paletteChunk.getPaletteData();						
			}			
			break;
		case PngChunk.CHUNK_tRNS:
			PngTrnsChunk trnsChunk = (PngTrnsChunk) chunk;
			if (trnsChunk.getTransparencyType(headerChunk) == 
				PngTrnsChunk.TRANSPARENCY_TYPE_PIXEL) 
			{
				imageData.transparentPixel = 
					trnsChunk.getSwtTransparentPixel(headerChunk);
			} else {
				alphaPalette = trnsChunk.getAlphaValues(headerChunk, paletteChunk);
				int transparentCount = 0, transparentPixel = -1;
				for (int i = 0; i < alphaPalette.length; i++) {
					if ((alphaPalette[i] & 0xFF) != 255) {
						transparentCount++;
						transparentPixel = i;
					}
				}
				if (transparentCount == 0) {
					alphaPalette = null;
				} else if (transparentCount == 1 && alphaPalette[transparentPixel] == 0) {
					alphaPalette = null;
					imageData.transparentPixel = transparentPixel;
				}
			}
			break;
		case PngChunk.CHUNK_IDAT:
			if (chunkReader.readPixelData()) {
				// All IDAT chunks in an image file must be
				// sequential. If the pixel data has already
				// been read and another IDAT block is encountered,
				// then this is an invalid image.
				SWT.error(SWT.ERROR_INVALID_IMAGE);
			} else {
				// Read in the pixel data for the image. This should
				// go through all the image's IDAT chunks. 	
				PngIdatChunk dataChunk = (PngIdatChunk) chunk;
				readPixelData(dataChunk, chunkReader);				
			}
			break;
		default:
			if (chunk.isCritical()) {
				// All critical chunks must be supported.
				SWT.error(SWT.ERROR_NOT_IMPLEMENTED);
			}
	}
}
void unloadIntoByteStream(ImageLoader loader) {
	/* We do not currently support writing png. */
	SWT.error(SWT.ERROR_UNSUPPORTED_FORMAT);
	try {
		/* PNG only supports single image (use MNG for multi-image). */
		ImageData imageData = loader.data[0];
	
		/* Step 1: Write PNG signature. */
		writeSignature();
		
		/* Step 2: Write IHDR chunk. */
		int width = imageData.width;
		int height = imageData.height;
		PaletteData palette = imageData.palette;
		byte colorType = PngIhdrChunk.COLOR_TYPE_RGB;
		byte bitDepth = 8;
		if (!palette.isDirect) {
			colorType = PngIhdrChunk.COLOR_TYPE_PALETTE;
			bitDepth = (byte) Math.min(imageData.depth, 8);
		}
		if (imageData.getTransparencyType() == SWT.TRANSPARENCY_ALPHA) {
			colorType = PngIhdrChunk.COLOR_TYPE_RGB_WITH_ALPHA;
		}
		byte compressionMethod = 0; // must be zero
		byte filterMethod = 0; // must be zero
		byte interlaceMethod = PngIhdrChunk.INTERLACE_METHOD_NONE;
		outputStream.write(new PngIhdrChunk(width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod).getReference());
		
		/* Step 3: Write PLTE (palette) chunk, if any. */
		if (!palette.isDirect) {
			outputStream.write(new PngPlteChunk(palette).getReference());
		}
		
		/* Step 4: Write tRNS (transparency) chunk, if any. */
		if (imageData.transparentPixel != -1) {
			RGB transparentRGB = palette.getRGB(imageData.transparentPixel);
			outputStream.write(new PngTrnsChunk(transparentRGB).getReference());
		}
		
		/* Step 5: Write IDAT chunk. */
		compress(imageData);
		outputStream.write(new PngIdatChunk(headerByte1, headerByte2, data, adler).getReference());
		
		/* Step 6: Write IEND chunk. */
		outputStream.write(new PngIendChunk().getReference());
	} catch (IOException e) {
		SWT.error(SWT.ERROR_IO, e);
	}
}
/* Compress the image data, and store the results in:
 * headerByte1, headerByte2, data, and adler.
 */
void compress(ImageData imageData) {
	headerByte1 = (byte)0x78; // window size = 0x70 (32768), compression method = 0x08
	headerByte2 = (byte)0x9C; // compression level = 0x80 (default), check bits = 0x1C
	
	int [] pixels = new int [imageData.width];
	// TODO - determine the most efficient way to compress the data. For now, just compress by scanline.
	for (int row = 0; row < imageData.height; row++) {
		imageData.getPixels(0, row, imageData.width, pixels, 0);
		// FIXME - compress the data, compute the Adler
	}
	// temporary code:
	data = new byte[] {(byte) 0xC0}; // final compressed block, dynamic
}
void updateAdler(byte value) {
	int low = adler & 0xFFFF;
	int high = (adler >> 16) & 0xFFFF;
	int valueInt = value & 0xFF;
	low = (low + valueInt) % PRIME;
	high = (low + high) % PRIME;
	adler = (high << 16) | low;
}
void writeSignature() throws IOException {
	byte[] signature = new byte[SIGNATURE_LENGTH];
	signature[0] = (byte) 137; //constant
	signature[1] = 80; //P
	signature[2] = 78; //N
	signature[3] = 71; //G
	signature[4] = 13; //<RETURN>
	signature[5] = 10; //<LINEFEED>
	signature[6] = 26; //<CTRL/Z>
	signature[7] = 10; //<LINEFEED>
	outputStream.write(signature);
}
boolean isFileFormat(LEDataInputStream stream) {
	try {
		byte[] signature = new byte[SIGNATURE_LENGTH];
		stream.read(signature);
		stream.unread(signature);
		if ((signature[0] & 0xFF) != 137) return false; //137
		if ((signature[1] & 0xFF) != 80) return false; //P
		if ((signature[2] & 0xFF) != 78) return false; //N
		if ((signature[3] & 0xFF) != 71) return false; //G
		if ((signature[4] & 0xFF) != 13) return false; //<RETURN>
		if ((signature[5] & 0xFF) != 10) return false; //<LINEFEED>
		if ((signature[6] & 0xFF) != 26) return false; //<CTRL/Z>
		if ((signature[7] & 0xFF) != 10) return false; //<LINEFEED>		
		return true;
	} catch (Exception e) {
		return false;
	}
}
/**
 * SWT does not support 16-bit depths. If this image uses
 * 16-bit depths, convert the data to an 8-bit depth.
 */
byte[] validateBitDepth(byte[] data) {
	if (headerChunk.getBitDepth() > 8) {
		byte[] result = new byte[data.length / 2];
		compress16BitDepthTo8BitDepth(data, 0, result, 0, result.length);
		return result;
	} else {
		return data;
	}
}
/**
 * SWT does not support greyscale as a color type. For
 * plain grayscale, we create a palette. For Grayscale
 * with Alpha, however, we need to convert the pixels
 * to use RGB values.
 * Note: This method assumes that the bit depth of the
 * data has already been restricted to 8 or less.
 */
void setPixelData(byte[] data, ImageData imageData) {
	switch (headerChunk.getColorType()) {
		case PngIhdrChunk.COLOR_TYPE_GRAYSCALE_WITH_ALPHA:
		{
			int width = imageData.width;
			int height = imageData.height;
			int destBytesPerLine = imageData.bytesPerLine;
			/*
			* If the image uses 16-bit depth, it is converted
			* to an 8-bit depth image.
			*/
			int srcBytesPerLine = getAlignedBytesPerRow();
			if (headerChunk.getBitDepth() > 8) srcBytesPerLine /= 2;

			byte[] rgbData = new byte[destBytesPerLine * height];
			byte[] alphaData = new byte[width * height];
			for (int y = 0; y < height; y++) {
				int srcIndex = srcBytesPerLine * y;
				int destIndex = destBytesPerLine * y;
				int destAlphaIndex = width * y;
				for (int x = 0; x < width; x++) {
					byte grey = data[srcIndex];
					byte alpha = data[srcIndex + 1];
					rgbData[destIndex + 0] = grey;
					rgbData[destIndex + 1] = grey;
					rgbData[destIndex + 2] = grey;
					alphaData[destAlphaIndex] = alpha;
					srcIndex += 2;
					destIndex += 3;
					destAlphaIndex++;
				}
			}
			imageData.data = rgbData;
			imageData.alphaData = alphaData;
			break;
		}
		case PngIhdrChunk.COLOR_TYPE_RGB_WITH_ALPHA:
		{
			int width = imageData.width;
			int height = imageData.height;
			int destBytesPerLine = imageData.bytesPerLine;
			int srcBytesPerLine = getAlignedBytesPerRow();
			/*
			* If the image uses 16-bit depth, it is converted
			* to an 8-bit depth image.
			*/
			if (headerChunk.getBitDepth() > 8) srcBytesPerLine /= 2;

			byte[] rgbData = new byte[destBytesPerLine * height];
			byte[] alphaData = new byte[width * height];
			for (int y = 0; y < height; y++) {
				int srcIndex = srcBytesPerLine * y;
				int destIndex = destBytesPerLine * y;
				int destAlphaIndex = width * y;
				for (int x = 0; x < width; x++) {
					rgbData[destIndex + 0] = data[srcIndex + 0];
					rgbData[destIndex + 1] = data[srcIndex + 1];
					rgbData[destIndex + 2] = data[srcIndex + 2];
					alphaData[destAlphaIndex] = data[srcIndex + 3];
					srcIndex += 4;
					destIndex += 3;
					destAlphaIndex++;
				}
			}
			imageData.data = rgbData;
			imageData.alphaData = alphaData;
			break;
		}		
		case PngIhdrChunk.COLOR_TYPE_RGB:
			imageData.data = data;
			break;
		case PngIhdrChunk.COLOR_TYPE_PALETTE:
			imageData.data = data;
			if (alphaPalette != null) {
				int size = imageData.width * imageData.height;
				byte[] alphaData = new byte[size];
				byte[] pixelData = new byte[size];
				imageData.getPixels(0, 0, size, pixelData, 0);
				for (int i = 0; i < pixelData.length; i++) {
					alphaData[i] = alphaPalette[pixelData[i] & 0xFF];
				}
				imageData.alphaData = alphaData;
			}
			break;
		default:
			imageData.data = data;
			break;
	}
}
/**
 * PNG supports some color types and bit depths that are 
 * unsupported by SWT. If the image uses an unsupported
 * color type (either of the gray scale types) or bit
 * depth (16), convert the data to an SWT-supported
 * format. Then assign the data into the ImageData given.
 */
void setImageDataValues(byte[] data, ImageData imageData) {
	byte[] result = validateBitDepth(data);
	setPixelData(result, imageData);
}
/**
 * Read the image data from the data stream. This must handle
 * decoding the data, filtering, and interlacing.
 */
void readPixelData(PngIdatChunk chunk, PngChunkReader chunkReader) {
	decodingStream = new PngDecodingDataStream(chunk, chunkReader);
	int interlaceMethod = headerChunk.getInterlaceMethod();
	if (interlaceMethod == PngIhdrChunk.INTERLACE_METHOD_NONE) {
		readNonInterlacedImage();
	} else {
		readInterlacedImage();
	}
	decodingStream.assertImageDataAtEnd();
	decodingStream.checkAdler();
}
/**
 * Answer the number of bytes in a word-aligned row of pixel data.
 */
int getAlignedBytesPerRow() {
	return ((getBytesPerRow(headerChunk.getWidth()) + 3) / 4) * 4;
}
/**
 * Answer the number of bytes in each row of the image
 * data. Each PNG row is byte-aligned, so images with bit
 * depths less than a byte may have unused bits at the
 * end of each row. The value of these bits is undefined.
 */
int getBytesPerRow() {
	return getBytesPerRow(headerChunk.getWidth());
}
/**
 * Answer the number of bytes needed to represent a pixel.
 * This value depends on the image's color type and bit
 * depth. 
 * Note that this method rounds up if an image's pixel size
 * isn't byte-aligned.
 */
int getBytesPerPixel() {
	int bitsPerPixel = headerChunk.getBitsPerPixel();
	return (bitsPerPixel + 7) / 8;	
}
/**
 * Answer the number of bytes in a row of the given pixel
 * width. Each row is byte-aligned, so images with bit
 * depths less than a byte may have unused bits at the
 * end of each row. The value of these bits is undefined.
 */
int getBytesPerRow(int rowWidthInPixels) {
	int bitsPerPixel = headerChunk.getBitsPerPixel();
	int bitsPerRow = bitsPerPixel * rowWidthInPixels;
	int bitsPerByte = 8;
	return (bitsPerRow + (bitsPerByte - 1)) / bitsPerByte;
}
/**
 * 1. Read one of the seven frames of interlaced data.
 * 2. Update the imageData.
 * 3. Notify the image loader's listeners of the frame load.
 */
void readInterlaceFrame(
	int rowInterval,
	int columnInterval,
	int startRow,
	int startColumn,
	int frameCount) 
{
	int width = headerChunk.getWidth();
	int alignedBytesPerRow = getAlignedBytesPerRow();
	int height = headerChunk.getHeight();
	if (startRow >= height || startColumn >= width) return;
	
	int pixelsPerRow = (width - startColumn + columnInterval - 1) / columnInterval;
	int bytesPerRow = getBytesPerRow(pixelsPerRow);
	byte[] row1 = new byte[bytesPerRow];
	byte[] row2 = new byte[bytesPerRow];
	byte[] currentRow = row1;	
	byte[] lastRow = row2;	
	for (int row = startRow; row < height; row += rowInterval) {
		byte filterType = decodingStream.getNextDecodedByte();
		for (int col = 0; col < bytesPerRow; col++) {
			currentRow[col] = decodingStream.getNextDecodedByte();
		}
		filterRow(currentRow, lastRow, filterType);
		if (headerChunk.getBitDepth() >= 8) {
			int bytesPerPixel = getBytesPerPixel();
			int dataOffset = (row * alignedBytesPerRow) + (startColumn * bytesPerPixel);
			for (int rowOffset = 0; rowOffset < currentRow.length; rowOffset += bytesPerPixel) {
				for (int byteOffset = 0; byteOffset < bytesPerPixel; byteOffset++) {
					data[dataOffset + byteOffset] = currentRow[rowOffset + byteOffset];
				}
				dataOffset += (columnInterval * bytesPerPixel);
			}
		} else {
			int bitsPerPixel = headerChunk.getBitDepth();
			int pixelsPerByte = 8 / bitsPerPixel;
			int column = startColumn;
			int rowBase = row * alignedBytesPerRow;
			int valueMask = 0;
			for (int i = 0; i < bitsPerPixel; i++) {
				valueMask <<= 1;
				valueMask |= 1;
			}
			int maxShift = 8 - bitsPerPixel;
			for (int byteOffset = 0; byteOffset < currentRow.length; byteOffset++) {
				for (int bitOffset = maxShift; bitOffset >= 0; bitOffset -= bitsPerPixel) {
					if (column < width) {
						int dataOffset = rowBase + (column * bitsPerPixel / 8);							
						int value = (currentRow[byteOffset] >> bitOffset) & valueMask;
						int dataShift = maxShift - (bitsPerPixel * (column % pixelsPerByte));
						data[dataOffset] |= value << dataShift;
					}
					column += columnInterval;
				}
			}
		}
		currentRow = (currentRow == row1) ? row2 : row1;
		lastRow = (lastRow == row1) ? row2 : row1;
	}
	setImageDataValues(data, imageData);
	fireInterlacedFrameEvent(frameCount);
}
/**
 * Read the pixel data for an interlaced image from the
 * data stream.
 */
void readInterlacedImage() {
	readInterlaceFrame(8, 8, 0, 0, 0);
	readInterlaceFrame(8, 8, 0, 4, 1);	
	readInterlaceFrame(8, 4, 4, 0, 2);	
	readInterlaceFrame(4, 4, 0, 2, 3);
	readInterlaceFrame(4, 2, 2, 0, 4);
	readInterlaceFrame(2, 2, 0, 1, 5);	
	readInterlaceFrame(2, 1, 1, 0, 6);
}
/**
 * Fire an event to let listeners know that an interlaced
 * frame has been loaded.
 * finalFrame should be true if the image has finished
 * loading, false if there are more frames to come.
 */
void fireInterlacedFrameEvent(int frameCount) {
	if (loader.hasListeners()) {
		ImageData image = (ImageData) imageData.clone();
		boolean finalFrame = frameCount == 6;
		loader.notifyListeners(new ImageLoaderEvent(loader, image, frameCount, finalFrame));
	}
}
/**
 * Read the pixel data for a non-interlaced image from the
 * data stream.
 * Update the imageData to reflect the new data.
 */
void readNonInterlacedImage() {
	int dataOffset = 0;
	int alignedBytesPerRow = getAlignedBytesPerRow();
	int bytesPerRow = getBytesPerRow();
	byte[] row1 = new byte[bytesPerRow];
	byte[] row2 = new byte[bytesPerRow];
	byte[] currentRow = row1;	
	byte[] lastRow = row2;
	for (int row = 0; row < headerChunk.getHeight(); row++) {
		byte filterType = decodingStream.getNextDecodedByte();
		for (int col = 0; col < bytesPerRow; col++) {
			currentRow[col] = decodingStream.getNextDecodedByte();
		}
		filterRow(currentRow, lastRow, filterType);
		System.arraycopy(currentRow, 0, data, dataOffset, bytesPerRow);
		dataOffset += alignedBytesPerRow;
		currentRow = (currentRow == row1) ? row2 : row1;
		lastRow = (lastRow == row1) ? row2 : row1;
	}
	setImageDataValues(data, imageData);
}
/**
 * SWT does not support 16-bit depth color formats.
 * Convert the 16-bit data to 8-bit data.
 * The correct way to do this is to multiply each
 * 16 bit value by the value:
 * (2^8 - 1) / (2^16 - 1).
 * The fast way to do this is just to drop the low
 * byte of the 16-bit value.
 */
static void compress16BitDepthTo8BitDepth(
	byte[] source,
	int sourceOffset,
	byte[] destination, 
	int destinationOffset,
	int numberOfValues) 
{
	//double multiplier = (Compatibility.pow2(8) - 1) / (Compatibility.pow2(16) - 1);
	for (int i = 0; i < numberOfValues; i++) {
		int sourceIndex = sourceOffset + (2 * i);
		int destinationIndex = destinationOffset + i;
		//int value = (source[sourceIndex] << 8) | source[sourceIndex + 1];
		//byte compressedValue = (byte)(value * multiplier);
		byte compressedValue = source[sourceIndex];
		destination[destinationIndex] = compressedValue;
	}
}
/**
 * SWT does not support 16-bit depth color formats.
 * Convert the 16-bit data to 8-bit data.
 * The correct way to do this is to multiply each
 * 16 bit value by the value:
 * (2^8 - 1) / (2^16 - 1).
 * The fast way to do this is just to drop the low
 * byte of the 16-bit value.
 */
static int compress16BitDepthTo8BitDepth(int value) {
	//double multiplier = (Compatibility.pow2(8) - 1) / (Compatibility.pow2(16) - 1);
	//byte compressedValue = (byte)(value * multiplier);
	return value >> 8;
}
/**
 * PNG supports four filtering types. These types are applied
 * per row of image data. This method unfilters the given row
 * based on the filterType.
 */
void filterRow(byte[] row, byte[] previousRow, int filterType) {
	int byteOffset = headerChunk.getFilterByteOffset();
	switch (filterType) {
		case PngIhdrChunk.FILTER_NONE:
			break;
		case PngIhdrChunk.FILTER_SUB:
			for (int i = byteOffset; i < row.length; i++) {
				int current = row[i] & 0xFF;
				int left = row[i - byteOffset] & 0xFF;
				row[i] = (byte)((current + left) & 0xFF);
			}
			break;
		case PngIhdrChunk.FILTER_UP:
			for (int i = 0; i < row.length; i++) {
				int current = row[i] & 0xFF;
				int above = previousRow[i] & 0xFF;				
				row[i] = (byte)((current + above) & 0xFF);
			}
			break;
		case PngIhdrChunk.FILTER_AVERAGE:
			for (int i = 0; i < row.length; i++) {
				int left = (i < byteOffset) ? 0 : row[i - byteOffset] & 0xFF;
				int above = previousRow[i] & 0xFF;
				int current = row[i] & 0xFF;
				row[i] = (byte)((current + ((left + above) / 2)) & 0xFF);
			}
			break;
		case PngIhdrChunk.FILTER_PAETH:
			for (int i = 0; i < row.length; i++) {
				int left = (i < byteOffset) ? 0 : row[i - byteOffset] & 0xFF;
				int aboveLeft = (i < byteOffset) ? 0 : previousRow[i - byteOffset] & 0xFF;
				int above = previousRow[i] & 0xFF;
				
				int a = Math.abs(above - aboveLeft);
				int b = Math.abs(left - aboveLeft);
				int c = Math.abs(left - aboveLeft + above - aboveLeft);
				
				int preductor = 0;
				if (a <= b && a <= c) {
					preductor = left;
				} else if (b <= c) {
					preductor = above;
				} else {
					preductor = aboveLeft;
				}
				
				int currentValue = row[i] & 0xFF;
				row[i] = (byte) ((currentValue + preductor) & 0xFF);
			}
			break;
	}
}

}
