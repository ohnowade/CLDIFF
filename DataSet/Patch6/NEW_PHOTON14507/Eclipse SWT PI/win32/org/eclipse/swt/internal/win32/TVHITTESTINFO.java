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

public class TVHITTESTINFO {
//	POINT pt;
	public int x;
	public int y;
	public int flags;
	public int /*long*/ hItem;
	public static int sizeof = OS.TVHITTESTINFO_sizeof ();
}
