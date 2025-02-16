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
package org.eclipse.swt.custom;

import org.eclipse.swt.*;

/**
 * Instances of this class represent bullets in the <code>StyledText</code>.
 * <p>
 * The hashCode() method in this class uses the values of the public
 * fields to compute the hash value. When storing instances of the
 * class in hashed collections, do not modify these fields after the
 * object has been inserted.  
 * </p>
 * <p>
 * Application code does <em>not</em> need to explicitly release the
 * resources managed by each instance when those instances are no longer
 * required, and thus no <code>dispose()</code> method is provided.
 * </p>
 * 
 * @see StyledText#setLineBullet(int, int, Bullet)
 * 
 * @since 3.2
 */
public class Bullet {
	public int type;
	public StyleRange style;
	public String text;	
	int[] linesIndices;
	int count;

/** 
 * Create a new bullet the specified style, the type is set to ST.BULLET_DOT. 
 * The style must have a glyph metrics set.
 *
 * @param style the style 
 * 
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT when the style or the glyph metrics are null</li>
 * </ul> 
 */
public Bullet(StyleRange style) {
	this(ST.BULLET_DOT, style);
}
/** 
 * Create a new bullet the specified style and type. 
 * The style must have a glyph metrics set.
 *
 * @param style the style 
 * 
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT when the style or the glyph metrics are null</li>
 * </ul> 
 */
public Bullet(int type, StyleRange style) {
	if (style == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
	if (style.metrics == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
	this.type = type;
	this.style = style;
}	
void addIndices (int startLine, int lineCount) {
	if (linesIndices == null) {
		linesIndices = new int[lineCount];
		count = lineCount;
		for (int i = 0; i < lineCount; i++) linesIndices[i] = startLine + i;
	} else {
		int modifyStart = 0;
		while (modifyStart < count) {
			if (startLine <= linesIndices[modifyStart]) break;
			modifyStart++;
		}
		int modifyEnd = modifyStart;
		while (modifyEnd < count) {
			if (startLine + lineCount <= linesIndices[modifyEnd]) break;
			modifyEnd++;
		}
		int newSize = modifyStart + lineCount + count - modifyEnd;
		if (newSize > linesIndices.length) {
			int[] newLinesIndices = new int[newSize];
			System.arraycopy(linesIndices, 0, newLinesIndices, 0, count);
			linesIndices = newLinesIndices;
		}
		System.arraycopy(linesIndices, modifyEnd, linesIndices, modifyStart + lineCount, count - modifyEnd);
		for (int i = 0; i < lineCount; i++) linesIndices[modifyStart + i] = startLine + i;
		count = newSize;
	}
}
int indexOf (int lineIndex) {
	for (int i = 0; i < count; i++) {
		if (linesIndices[i] == lineIndex) return i;
	}
	return -1;
}
public int hashCode() {
	return style.hashCode() ^ type;
}
int[] removeIndices (int startLine, int replaceLineCount, int newLineCount, boolean update) {
	if (count == 0) return null;
	if (startLine > linesIndices[count - 1]) return null;
	int endLine = startLine + replaceLineCount;
	int delta = newLineCount - replaceLineCount;
	for (int i = 0; i < count; i++) {
		int index = linesIndices[i];
		if (startLine <= index) {
			int j = i;
			while (j < count) {
				if (linesIndices[j] >= endLine) break;
				j++;
			}
			if (update) {
				for (int k = j; k < count; k++) linesIndices[k] += delta;
			}
			int[] redrawLines = new int[count - j];
			System.arraycopy(linesIndices, j, redrawLines, 0, count - j);
			System.arraycopy(linesIndices, j, linesIndices, i, count - j);
			count -= (j - i);
			return redrawLines;
		}
	}
	for (int i = 0; i < count; i++) linesIndices[i] += delta;
	return null;
}
int size() {
	return count;
}
}
