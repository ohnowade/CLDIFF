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
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.CloneableCompatibility;

public class StyleRange extends TextStyle implements CloneableCompatibility {
	
	/**
	 * the start offset of the range, zero-based from the document start
	 */
	public int start;
	
	/**
	 * the length of the range
	 */
	public int length;

	/**
	 * the font style of the range. It may be a combination of
	 * SWT.NORMAL, SWT.ITALIC or SWT.BOLD
	 * 
	 * Note: the font style is not used if the <code>font</code> attribute
	 * is set
	 */
	public int fontStyle = SWT.NORMAL;
	
public StyleRange() {
	super(null, null, null);
}

/** 
 * Create a new style range.
 * <p>
 *
 * @param start start offset of the style
 * @param length length of the style 
 * @param foreground foreground color of the style, null if none 
 * @param background background color of the style, null if none
 */
public StyleRange(int start, int length, Color foreground, Color background) {
	super(null, foreground, background);
	this.start = start;
	this.length = length;
}

/** 
 * Create a new style range.
 * <p>
 *
 * @param start start offset of the style
 * @param length length of the style 
 * @param foreground foreground color of the style, null if none 
 * @param background background color of the style, null if none
 * @param fontStyle font style of the style, may be SWT.NORMAL, SWT.ITALIC or SWT.BOLD
 */
public StyleRange(int start, int length, Color foreground, Color background, int fontStyle) {
	this(start, length, foreground, background);
	this.fontStyle = fontStyle;
}

/**
 * Compares the argument to the receiver, and returns true
 * if they represent the <em>same</em> object using a class
 * specific comparison.
 *
 * @param object the object to compare with this object
 * @return <code>true</code> if the object is the same as this object and <code>false</code> otherwise
 *
 * @see #hashCode()
 */
public boolean equals(Object object) {
	if (object == this) return true;
	if (object instanceof StyleRange) {
		StyleRange style = (StyleRange)object;
		if (start != style.start) return false;
		if (length != style.length) return false;
		return similarTo(style);
	}
	return false;
}

/**
 * Returns an integer hash code for the receiver. Any two 
 * objects that return <code>true</code> when passed to 
 * <code>equals</code> must return the same value for this
 * method.
 *
 * @return the receiver's hash
 *
 * @see #equals(Object)
 */
public int hashCode() {
	return super.hashCode() ^ fontStyle;
}
boolean isVariableHeight() {
	return font != null || metrics != null || rise != 0;
}
/**
 * Returns whether or not the receiver is unstyled (i.e., does not have any 
 * style attributes specified).
 * <p>
 *
 * @return true if the receiver is unstyled, false otherwise.
 */
public boolean isUnstyled() {
	//BAD
	if (font != null) return false;
	if (rise != 0) return false;
	if (metrics != null) return false;
	if (foreground != null) return false;
	if (background != null) return false;
	if (fontStyle != SWT.NORMAL) return false;
	if (underline) return false;
	if (strikeout) return false;
	return true;
}

/**
 * Compares the specified object to this StyleRange and answer if the two 
 * are similar. The object must be an instance of StyleRange and have the
 * same field values for except for start and length.
 * <p>
 *
 * @param style the object to compare with this object
 * @return true if the objects are similar, false otherwise
 */
public boolean similarTo(StyleRange style) {
	if (!super.equals(style)) return false;
	if (fontStyle != style.fontStyle) return false;
	return true;
}

/**
 * Answers a new StyleRange with the same values as this StyleRange.
 * <p>
 *
 * @return a shallow copy of this StyleRange
 */	
public Object clone() {
	try {
		return super.clone();
	} catch (CloneNotSupportedException e) {
		return null;
	}
}

/**
 * Returns a string containing a concise, human-readable
 * description of the receiver.
 *
 * @return a string representation of the StyleRange
 */
public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("StyleRange {");
	buffer.append(start);
	buffer.append(", ");
	buffer.append(length);
	buffer.append(", fontStyle=");
	switch (fontStyle) {
		case SWT.BOLD:
			buffer.append("bold");
			break;
		case SWT.ITALIC:
			buffer.append("italic");
			break;
		case SWT.BOLD | SWT.ITALIC:
			buffer.append("bold-italic");
			break;
		default:
			buffer.append("normal");
	}
	String str = super.toString();
	int index = str.indexOf('{');
	str = str.substring(index + 1);
	if (str.length() > 1) buffer.append(", ");
	buffer.append(str);
	return buffer.toString();
}
}
