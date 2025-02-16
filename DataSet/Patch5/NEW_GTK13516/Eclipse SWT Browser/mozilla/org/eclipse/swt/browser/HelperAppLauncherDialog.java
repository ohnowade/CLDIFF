/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.browser;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.mozilla.*;
import org.eclipse.swt.internal.gtk.OS;
import org.eclipse.swt.widgets.*;

class HelperAppLauncherDialog {
	XPCOMObject supports;
	XPCOMObject helperAppLauncherDialog;
	int refCount = 0;

public HelperAppLauncherDialog() {
	createCOMInterfaces();
}

int AddRef() {
	refCount++;
	return refCount;
}

void createCOMInterfaces() {
	/* Create each of the interfaces that this object implements */
	supports = new XPCOMObject(new int[]{2, 0, 0}){
		public int /*long*/ method0(int /*long*/[] args) {return QueryInterface(args[0], args[1]);}
		public int /*long*/ method1(int /*long*/[] args) {return AddRef();}
		public int /*long*/ method2(int /*long*/[] args) {return Release();}
	};
	
	helperAppLauncherDialog = new XPCOMObject(new int[]{2, 0, 0, 3, 5}){
		public int /*long*/ method0(int /*long*/[] args) {return QueryInterface(args[0], args[1]);}
		public int /*long*/ method1(int /*long*/[] args) {return AddRef();}
		public int /*long*/ method2(int /*long*/[] args) {return Release();}
		public int /*long*/ method3(int /*long*/[] args) {return Show(args[0], args[1], args[2]);}
		public int /*long*/ method4(int /*long*/[] args) {return PromptForSaveToFile(args[0], args[1], args[2], args[3], args[4]);}
	};		
}

void disposeCOMInterfaces() {
	if (supports != null) {
		supports.dispose();
		supports = null;
	}	
	if (helperAppLauncherDialog != null) {
		helperAppLauncherDialog.dispose();
		helperAppLauncherDialog = null;	
	}
}

int /*long*/ getAddress() {
	return helperAppLauncherDialog.getAddress();
}

int /*long*/ QueryInterface(int /*long*/ riid, int /*long*/ ppvObject) {
	if (riid == 0 || ppvObject == 0) return XPCOM.NS_ERROR_NO_INTERFACE;
	nsID guid = new nsID();
	XPCOM.memmove(guid, riid, nsID.sizeof);
	
	if (guid.Equals(nsISupports.NS_ISUPPORTS_IID)) {
		XPCOM.memmove(ppvObject, new int /*long*/[] {supports.getAddress()}, OS.PTR_SIZEOF);
		AddRef();
		return XPCOM.NS_OK;
	}
	if (guid.Equals(nsIHelperAppLauncherDialog.NS_IHELPERAPPLAUNCHERDIALOG_IID)) {
		XPCOM.memmove(ppvObject, new int /*long*/[] {helperAppLauncherDialog.getAddress()}, OS.PTR_SIZEOF);
		AddRef();
		return XPCOM.NS_OK;
	}
	
	XPCOM.memmove(ppvObject, new int /*long*/[] {0}, OS.PTR_SIZEOF);
	return XPCOM.NS_ERROR_NO_INTERFACE;
}
        	
int Release() {
	refCount--;
	/*
	* Note.  This instance lives as long as the download it is binded to.
	* Its reference count is expected to go down to 0 when the download
	* has completed or when it has been cancelled. E.g. when the user
	* cancels the File Dialog, cancels or closes the Download Dialog
	* and when the Download Dialog goes away after the download is completed.
	*/
	if (refCount == 0) disposeCOMInterfaces();
	return refCount;
}

/* nsIHelperAppLauncherDialog */

public int /*long*/ Show(int /*long*/ aLauncher, int /*long*/ aContext, int /*long*/ aForced) {
	nsIHelperAppLauncher helperAppLauncher = new nsIHelperAppLauncher(aLauncher);
	return helperAppLauncher.SaveToDisk(0, false);
}

public int /*long*/ PromptForSaveToFile(int /*long*/ arg0, int /*long*/ arg1, int /*long*/ arg2, int /*long*/ arg3, int /*long*/ arg4) {
	nsIHelperAppLauncher helperAppLauncher = null;
	int /*long*/ aDefaultFile, aSuggestedFileExtension, _retval;
	/*
	* Feature in Mozilla.  The nsIHelperAppLauncherDialog interface is not frozen 
	* despite being the only way to download files when embedding Mozilla.  Starting 
	* with Mozilla 1.5, the method PromptForSaveToFile takes an extra argument and 
	* previous arguments are shifted by one position.  The workaround is to provide 
	* an XPCOMObject that fits the newer API.  In all cases the first argument is a 
	* nsISupports reference. In the newer versions, that argument is nsIHelperAppLauncher,
	* a subclass of nsISupports.  The ordering of the arguments is inferred from the 
	* type of the first argument. 
	*/
	nsISupports support = new nsISupports(arg0);
	int /*long*/[] result = new int /*long*/[1];
	int rc = support.QueryInterface(nsIHelperAppLauncher.NS_IHELPERAPPLAUNCHER_IID, result);
	if (rc != XPCOM.NS_OK || result[0] != arg0) { 
		aDefaultFile = arg1;
		aSuggestedFileExtension = arg2;
		_retval = arg3;
	}
	else {
		helperAppLauncher = new nsIHelperAppLauncher(arg0);
		aDefaultFile = arg2;
		aSuggestedFileExtension = arg3;
		_retval = arg4;
	}
	result[0] = 0;
	int length = XPCOM.strlen_PRUnichar(aDefaultFile);
	char[] dest = new char[length];
	XPCOM.memmove(dest, aDefaultFile, length * 2);
	String defaultFile = new String(dest);

	length = XPCOM.strlen_PRUnichar(aSuggestedFileExtension);
	dest = new char[length];
	XPCOM.memmove(dest, aSuggestedFileExtension, length * 2);
	String suggestedFileExtension = new String(dest);
	
	Shell shell = new Shell();
	FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
	fileDialog.setFileName(defaultFile);
	fileDialog.setFilterExtensions(new String[] {suggestedFileExtension});
	String name = fileDialog.open();
	shell.close();
	if (name == null) {
		if (helperAppLauncher != null) {
			rc = helperAppLauncher.Cancel();
			if (rc != XPCOM.NS_OK) Browser.error(rc);
			return XPCOM.NS_OK;
		}
		return XPCOM.NS_ERROR_FAILURE;
	}
	nsEmbedString path = new nsEmbedString(name);
	rc = XPCOM.NS_NewLocalFile(path.getAddress(), true, result);
	path.dispose();
	if (rc != XPCOM.NS_OK) Browser.error(rc);
	if (result[0] == 0) Browser.error(XPCOM.NS_ERROR_NULL_POINTER);
	/* Our own nsIDownload has been registered during the Browser initialization. It will be invoked by Mozilla. */
	XPCOM.memmove(_retval, result, OS.PTR_SIZEOF);	
	return XPCOM.NS_OK;
}

}
