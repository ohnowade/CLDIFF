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

public class BROWSEINFO {
	public int /*long*/ hwndOwner;
	public int /*long*/ pidlRoot;
	public int /*long*/ pszDisplayName;
	public int /*long*/ lpszTitle;
	public int ulFlags;
	public int /*long*/ lpfn;
	public int /*long*/ lParam;
	public int iImage;
	public static final int sizeof = OS.BROWSEINFO_sizeof ();
}
