/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.internal.image;


import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import java.io.*;

/**
 * Baseline TIFF decoder revision 6.0
 * Extension T4-encoding CCITT T.4 1D
 */
final class TIFFFileFormat extends FileFormat {

boolean isFileFormat(LEDataInputStream stream) {
	try {
		byte[] header = new byte[4];
		stream.read(header);
		stream.unread(header);
		if (header[0] != header[1]) return false;
		if (!(header[0] == 0x49 && header[2] == 42 && header[3] == 0) &&
			!(header[0] == 0x4d && header[2] == 0 && header[3] == 42)) {
			return false;
		} 	
		return true;
	} catch (Exception e) {
		return false;
	}
}

ImageData[] loadFromByteStream() {	
	byte[] header = new byte[8];
	boolean isLittleEndian;
	ImageData[] images = new ImageData[0];
	TIFFRandomFileAccess file = new TIFFRandomFileAccess(inputStream);
	try {
		file.read(header);
		if (header[0] != header[1]) SWT.error(SWT.ERROR_INVALID_IMAGE);
		if (!(header[0] == 0x49 && header[2] == 42 && header[3] == 0) &&
			!(header[0] == 0x4d && header[2] == 0 && header[3] == 42)) {
			SWT.error(SWT.ERROR_INVALID_IMAGE);
		} 
		isLittleEndian = header[0] == 0x49;	
		int offset = isLittleEndian ? 
			(header[4] & 0xFF) | ((header[5] & 0xFF) << 8) | ((header[6] & 0xFF) << 16) | ((header[7] & 0xFF) << 24) :
			(header[7] & 0xFF) | ((header[6] & 0xFF) << 8) | ((header[5] & 0xFF) << 16) | ((header[4] & 0xFF) << 24);
		file.seek(offset);
		TIFFDirectory directory = new TIFFDirectory(file, isLittleEndian, loader);
		ImageData image = directory.read();
		/* A baseline reader is only expected to read the first directory */
		images = new ImageData[] {image};
	} catch (IOException e) {
		SWT.error(SWT.ERROR_IO, e);
	}
	return images;
}

void unloadIntoByteStream(ImageLoader loader) {
	/* We do not currently support writing multi-page tiff,
	 * so we use the first image data in the loader's array. */
	ImageData image = loader.data[0];
	TIFFDirectory directory = new TIFFDirectory(image);
	try {
		directory.writeToStream(outputStream);
	} catch (IOException e) {
		SWT.error(SWT.ERROR_IO, e);
	}
}

}
