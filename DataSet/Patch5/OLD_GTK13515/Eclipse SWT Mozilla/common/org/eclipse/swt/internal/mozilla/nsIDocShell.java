/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Mozilla Communicator client code, released March 31, 1998.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by Netscape are Copyright (C) 1998-1999
 * Netscape Communications Corporation.  All Rights Reserved.
 *
 * Contributor(s):
 *
 * IBM
 * -  Binding to permit interfacing between Mozilla and SWT
 * -  Copyright (C) 2003 IBM Corp.  All Rights Reserved.
 *
 * ***** END LICENSE BLOCK ***** */
package org.eclipse.swt.internal.mozilla;

public class nsIDocShell extends nsISupports {

	static final int LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 51;

	public static final String NS_IDOCSHELL_IID_STR =
		"69e5de00-7b8b-11d3-af61-00a024ffc08c";

	public static final nsID NS_IDOCSHELL_IID =
		new nsID(NS_IDOCSHELL_IID_STR);

	public nsIDocShell(int /*long*/ address) {
		super(address);
	}

	public int LoadURI(int /*long*/ uri, int /*long*/ loadInfo, int aLoadFlags, boolean firstParty) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), uri, loadInfo, aLoadFlags, firstParty);
	}

	public int LoadStream(int /*long*/ aStream, int /*long*/ aURI, int /*long*/ aContentType, int /*long*/ aContentCharset, int /*long*/ aLoadInfo) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aStream, aURI, aContentType, aContentCharset, aLoadInfo);
	}

	public int InternalLoad(int /*long*/ aURI, int /*long*/ aReferrer, int /*long*/ aOwner, boolean aInheritOwner, char[] aWindowTarget, int /*long*/ aPostDataStream, int /*long*/ aHeadersStream, int aLoadFlags, int /*long*/ aSHEntry, boolean firstParty, int /*long*/[] aDocShell, int /*long*/[] aRequest) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, getAddress(), aURI, aReferrer, aOwner, aInheritOwner, aWindowTarget, aPostDataStream, aHeadersStream, aLoadFlags, aSHEntry, firstParty, aDocShell, aRequest);
	}

	public int CreateLoadInfo(int /*long*/[] loadInfo) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), loadInfo);
	}

	public int PrepareForNewContentModel() {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress());
	}

	public int SetCurrentURI(int /*long*/ aURI) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, getAddress(), aURI);
	}

	public int FireUnloadNotification() {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress());
	}

	public int GetPresContext(int /*long*/[] aPresContext) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, getAddress(), aPresContext);
	}

	public int GetPresShell(int /*long*/[] aPresShell) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, getAddress(), aPresShell);
	}

	public int GetEldestPresShell(int /*long*/[] aEldestPresShell) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, getAddress(), aEldestPresShell);
	}

	public int GetContentViewer(int /*long*/[] aContentViewer) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, getAddress(), aContentViewer);
	}

	public int GetChromeEventHandler(int /*long*/[] aChromeEventHandler) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, getAddress(), aChromeEventHandler);
	}

	public int SetChromeEventHandler(int /*long*/ aChromeEventHandler) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, getAddress(), aChromeEventHandler);
	}

	public int GetParentURIContentListener(int /*long*/[] aParentURIContentListener) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, getAddress(), aParentURIContentListener);
	}

	public int SetParentURIContentListener(int /*long*/ aParentURIContentListener) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, getAddress(), aParentURIContentListener);
	}

	public int GetDocumentCharsetInfo(int /*long*/[] aDocumentCharsetInfo) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, getAddress(), aDocumentCharsetInfo);
	}

	public int SetDocumentCharsetInfo(int /*long*/ aDocumentCharsetInfo) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, getAddress(), aDocumentCharsetInfo);
	}

	public int GetAllowPlugins(boolean[] aAllowPlugins) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, getAddress(), aAllowPlugins);
	}

	public int SetAllowPlugins(boolean aAllowPlugins) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, getAddress(), aAllowPlugins);
	}

	public int GetAllowJavascript(boolean[] aAllowJavascript) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, getAddress(), aAllowJavascript);
	}

	public int SetAllowJavascript(boolean aAllowJavascript) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, getAddress(), aAllowJavascript);
	}

	public int GetAllowMetaRedirects(boolean[] aAllowMetaRedirects) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, getAddress(), aAllowMetaRedirects);
	}

	public int SetAllowMetaRedirects(boolean aAllowMetaRedirects) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, getAddress(), aAllowMetaRedirects);
	}

	public int GetAllowSubframes(boolean[] aAllowSubframes) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, getAddress(), aAllowSubframes);
	}

	public int SetAllowSubframes(boolean aAllowSubframes) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, getAddress(), aAllowSubframes);
	}

	public int GetAllowImages(boolean[] aAllowImages) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, getAddress(), aAllowImages);
	}

	public int SetAllowImages(boolean aAllowImages) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, getAddress(), aAllowImages);
	}

	public static final int ENUMERATE_FORWARDS = 0;

	public static final int ENUMERATE_BACKWARDS = 1;

	public int GetDocShellEnumerator(int aItemType, int aDirection, int /*long*/[] _retval) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 28, getAddress(), aItemType, aDirection, _retval);
	}

	public static final int APP_TYPE_UNKNOWN = 0;

	public static final int APP_TYPE_MAIL = 1;

	public int GetAppType(int[] aAppType) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 29, getAddress(), aAppType);
	}

	public int SetAppType(int aAppType) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 30, getAddress(), aAppType);
	}

	public int GetAllowAuth(boolean[] aAllowAuth) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 31, getAddress(), aAllowAuth);
	}

	public int SetAllowAuth(boolean aAllowAuth) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 32, getAddress(), aAllowAuth);
	}

	public int GetZoom(float[] aZoom) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 33, getAddress(), aZoom);
	}

	public int SetZoom(float aZoom) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 34, getAddress(), aZoom);
	}

	public int GetMarginWidth(int[] aMarginWidth) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 35, getAddress(), aMarginWidth);
	}

	public int SetMarginWidth(int aMarginWidth) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 36, getAddress(), aMarginWidth);
	}

	public int GetMarginHeight(int[] aMarginHeight) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 37, getAddress(), aMarginHeight);
	}

	public int SetMarginHeight(int aMarginHeight) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 38, getAddress(), aMarginHeight);
	}

	public int GetHasFocus(boolean[] aHasFocus) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 39, getAddress(), aHasFocus);
	}

	public int SetHasFocus(boolean aHasFocus) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 40, getAddress(), aHasFocus);
	}

	public int GetCanvasHasFocus(boolean[] aCanvasHasFocus) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 41, getAddress(), aCanvasHasFocus);
	}

	public int SetCanvasHasFocus(boolean aCanvasHasFocus) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 42, getAddress(), aCanvasHasFocus);
	}

	public int TabToTreeOwner(boolean forward, boolean[] tookFocus) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 43, getAddress(), forward, tookFocus);
	}

	public static final int BUSY_FLAGS_NONE = 0;

	public static final int BUSY_FLAGS_BUSY = 1;

	public static final int BUSY_FLAGS_BEFORE_PAGE_LOAD = 2;

	public static final int BUSY_FLAGS_PAGE_LOADING = 4;

	public static final int LOAD_CMD_NORMAL = 1;

	public static final int LOAD_CMD_RELOAD = 2;

	public static final int LOAD_CMD_HISTORY = 4;

	public int GetBusyFlags(int[] aBusyFlags) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 44, getAddress(), aBusyFlags);
	}

	public int GetLoadType(int[] aLoadType) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 45, getAddress(), aLoadType);
	}

	public int SetLoadType(int aLoadType) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 46, getAddress(), aLoadType);
	}

	public int IsBeingDestroyed(boolean[] _retval) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 47, getAddress(), _retval);
	}

	public int GetIsExecutingOnLoadHandler(boolean[] aIsExecutingOnLoadHandler) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 48, getAddress(), aIsExecutingOnLoadHandler);
	}

	public int GetLayoutHistoryState(int /*long*/[] aLayoutHistoryState) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 49, getAddress(), aLayoutHistoryState);
	}

	public int SetLayoutHistoryState(int /*long*/ aLayoutHistoryState) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 50, getAddress(), aLayoutHistoryState);
	}

	public int GetShouldSaveLayoutState(boolean[] aShouldSaveLayoutState) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 51, getAddress(), aShouldSaveLayoutState);
	}
}