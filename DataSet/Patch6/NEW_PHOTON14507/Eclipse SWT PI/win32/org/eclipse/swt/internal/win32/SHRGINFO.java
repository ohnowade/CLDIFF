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
package org.eclipse.swt.internal.win32;

public class SHRGINFO {
	public int cbSize;
	public int /*long*/ hwndClient;
//	POINT ptDown
	public int ptDown_x;
	public int ptDown_y;
	public int dwFlags;
	public static final int sizeof = OS.SHRGINFO_sizeof ();
}
