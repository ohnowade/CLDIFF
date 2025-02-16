/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.motif.*;
 
/**
 * The class <code>HTMLTransfer</code> provides a platform specific mechanism 
 * for converting text in HTML format represented as a java <code>String</code> 
 * to a platform specific representation of the data and vice versa.  See 
 * <code>Transfer</code> for additional information.
 * 
 * <p>An example of a java <code>String</code> containing HTML text is shown 
 * below:</p>
 * 
 * <code><pre>
 *     String htmlData = "<p>This is a paragraph of text.</p>";
 * </code></pre>
 */
public class HTMLTransfer extends ByteArrayTransfer {

	static HTMLTransfer _instance = new HTMLTransfer();
	static final String TEXT_HTML = "text/html"; //$NON-NLS-1$
	static final int TEXT_HTML_ID = registerType(TEXT_HTML);
	static final String TEXT_HTML2 = "TEXT/HTML"; //$NON-NLS-1$
	static final int TEXT_HTML2_ID = registerType(TEXT_HTML2);

HTMLTransfer() {/*empty*/}

/**
 * Returns the singleton instance of the HTMLTransfer class.
 *
 * @return the singleton instance of the HTMLTransfer class
 */
public static HTMLTransfer getInstance () {
	return _instance;
}

/**
 * This implementation of <code>javaToNative</code> converts HTML-formatted text
 * represented by a java <code>String</code> to a platform specific representation.
 * For additional information see <code>Transfer#javaToNative</code>.
 * 
 * @param object a java <code>String</code> containing HTML text
 * @param transferData an empty <code>TransferData</code> object; this
 *  object will be filled in on return with the platform specific format of the data
 */
public void javaToNative (Object object, TransferData transferData){
	transferData.result = 0;
	if (!checkHTML(object) || !isSupportedType(transferData)) {
		DND.error(DND.ERROR_INVALID_DATA);
	}
	String string = (String)object;
	int charCount = string.length();
	char [] chars = new char[charCount +1];
	string.getChars(0, charCount , chars, 0);
	int byteCount = chars.length*2;
	int pValue = OS.XtMalloc(byteCount);
	if (pValue == 0) return;
	OS.memmove(pValue, chars, byteCount);
	transferData.length = byteCount;
	transferData.format = 8;
	transferData.pValue = pValue;
	transferData.result = 1;
}

/**
 * This implementation of <code>nativeToJava</code> converts a platform specific 
 * representation of HTML text to a java <code>String</code>.
 * For additional information see <code>Transfer#nativeToJava</code>.
 * 
 * @param transferData the platform specific representation of the data to be 
 * been converted
 * @return a java <code>String</code> containing HTML text if the 
 * conversion was successful; otherwise null
 */
public Object nativeToJava(TransferData transferData){
	if ( !isSupportedType(transferData) ||  transferData.pValue == 0 ) return null;
	/* Ensure byteCount is a multiple of 2 bytes */
	int size = (transferData.format * transferData.length / 8) / 2 * 2;
	if (size <= 0) return null;			
	char[] chars = new char [size/2];
	OS.memmove (chars, transferData.pValue, size);
	String string = new String (chars);
	int end = string.indexOf('\0');
	return (end == -1) ? string : string.substring(0, end);
}
protected int[] getTypeIds() {
	return new int[] {TEXT_HTML_ID, TEXT_HTML2_ID};
}

protected String[] getTypeNames() {
	return new String[] {TEXT_HTML, TEXT_HTML2};
}

boolean checkHTML(Object object) {
	return (object != null && object instanceof String && ((String)object).length() > 0);
}

protected boolean validate(Object object) {
	return checkHTML(object);
}
}
