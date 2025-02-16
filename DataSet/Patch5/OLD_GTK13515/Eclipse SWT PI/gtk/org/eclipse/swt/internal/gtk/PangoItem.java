/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others. All rights reserved.
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 *******************************************************************************/
package org.eclipse.swt.internal.gtk;

public class PangoItem {
	public int offset;
	public int length;
	public int num_chars;
	public int /*long*/ analysis_shape_engine;
	public int /*long*/ analysis_lang_engine;
	public int /*long*/ analysis_font;
	public byte analysis_level;
	public int /*long*/ analysis_language;
	public int /*long*/ analysis_extra_attrs;
	public static final int sizeof = OS.PangoItem_sizeof();
}
