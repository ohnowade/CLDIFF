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


public class GdkWindowAttr {
	public int /*long*/ title;
	public int event_mask;
	public int x, y;
	public int width;
	public int height;
	public int wclass;
	public int /*long*/ visual;
	public int /*long*/ colormap;
	public int window_type;
	public int /*long*/ cursor;
	public int /*long*/ wmclass_name;
	public int /*long*/ wmclass_class;
	public boolean override_redirect;
	public static final int sizeof = OS.GdkWindowAttr_sizeof();
}
