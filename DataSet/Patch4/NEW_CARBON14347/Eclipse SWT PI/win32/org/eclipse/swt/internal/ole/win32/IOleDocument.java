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
package org.eclipse.swt.internal.ole.win32;

public class IOleDocument extends IUnknown
{
public IOleDocument(int /*long*/ address) {
	super(address);
}
public int CreateView(int /*long*/ pIPSite,int /*long*/ pstm, int dwReserved, int /*long*/[] ppView) {
	return COM.VtblCall(3, address, pIPSite, pstm, dwReserved, ppView);
}
}
