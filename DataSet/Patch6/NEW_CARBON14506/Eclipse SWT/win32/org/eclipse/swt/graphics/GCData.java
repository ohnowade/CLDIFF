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
package org.eclipse.swt.graphics;

 
import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;

/**
 * Instances of this class are descriptions of GCs in terms
 * of unallocated platform-specific data fields.
 * <p>
 * <b>IMPORTANT:</b> This class is <em>not</em> part of the public
 * API for SWT. It is marked public only so that it can be shared
 * within the packages provided by SWT. It is not available on all
 * platforms, and should never be called from application code.
 * </p>
 */

public final class GCData {
	public Device device;
	public int style, state = -1;
	public int foreground = -1;
	public int background = -1;
	public int hFont;
	public Pattern foregroundPattern;
	public Pattern backgroundPattern;
	public int lineStyle = SWT.LINE_SOLID;
	public float lineWidth;
	public int lineCap = SWT.CAP_FLAT;
	public int lineJoin = SWT.JOIN_MITER;
	public float lineDashesOffset;
	public float[] lineDashes;
	public float lineMiterLimit = 10;
	public int alpha = 0xFF;

	public Image image;
	public int hPen, hOldPen;
	public int hBrush, hOldBrush;
	public int hNullBitmap;
	public int hwnd;
	public PAINTSTRUCT ps;
	public int layout = -1;
	public int gdipGraphics;
	public int gdipPen;
	public int gdipBrush;
	public int gdipFgBrush;
	public int gdipBgBrush;
	public int gdipFont;
}
