/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/

#include "swt.h"
#include "os_stats.h"

#ifdef NATIVE_STATS

int OS_nativeFunctionCount = 947;
int OS_nativeFunctionCallCount[947];
char * OS_nativeFunctionNames[] = {
	"AECountItems",
	"AEGetNthPtr",
	"AEInstallEventHandler",
	"AEProcessAppleEvent",
	"ATSFontActivateFromFileSpecification",
	"ATSFontDeactivate",
	"ATSFontGetPostScriptName",
	"ATSUBatchBreakLines",
	"ATSUCreateStyle",
	"ATSUCreateTextLayout",
	"ATSUCreateTextLayoutWithTextPtr",
	"ATSUDirectGetLayoutDataArrayPtrFromTextLayout",
	"ATSUDirectReleaseLayoutDataArrayPtr",
	"ATSUDisposeStyle",
	"ATSUDisposeTextLayout",
	"ATSUDrawText",
	"ATSUFindFontFromName",
	"ATSUFindFontName",
	"ATSUGetFontIDs",
	"ATSUGetGlyphBounds__IIIIISII_3I",
	"ATSUGetGlyphBounds__IIIIISILorg_eclipse_swt_internal_carbon_ATSTrapezoid_2_3I",
	"ATSUGetLayoutControl",
	"ATSUGetLineControl",
	"ATSUGetSoftLineBreaks",
	"ATSUGetTextHighlight",
	"ATSUGetUnjustifiedBounds",
	"ATSUGlyphGetQuadraticPaths",
	"ATSUHighlightText",
	"ATSUNextCursorPosition",
	"ATSUOffsetToPosition",
	"ATSUPositionToOffset",
	"ATSUPreviousCursorPosition",
	"ATSUSetAttributes",
	"ATSUSetFontFeatures",
	"ATSUSetHighlightingMethod",
	"ATSUSetLayoutControls",
	"ATSUSetLineControls",
	"ATSUSetRunStyle",
	"ATSUSetSoftLineBreak",
	"ATSUSetTabArray",
	"ATSUSetTextPointerLocation",
	"ATSUSetTransientFontMatching",
	"ATSUTextDeleted",
	"ATSUTextInserted",
	"AXNotificationHIObjectNotify",
	"AXUIElementCreateWithHIObjectAndIdentifier",
	"AXUIElementGetIdentifier",
	"AXValueCreate",
	"AcquireFirstMatchingEventInQueue",
	"ActiveNonFloatingWindow",
	"AddDataBrowserItems",
	"AddDataBrowserListViewColumn",
	"AddDragItemFlavor",
	"AppendMenuItemTextWithCFString",
	"AutoSizeDataBrowserListViewColumns",
	"BeginUpdate",
	"BringToFront",
	"CFArrayAppendValue",
	"CFArrayCreateMutable",
	"CFArrayGetCount",
	"CFArrayGetValueAtIndex",
	"CFDataGetBytes",
	"CFDataGetLength",
	"CFEqual",
	"CFLocaleCopyCurrent",
	"CFNumberFormatterCopyProperty",
	"CFNumberFormatterCreate",
	"CFRelease",
	"CFRetain",
	"CFStringCreateWithBytes",
	"CFStringCreateWithCharacters__III",
	"CFStringCreateWithCharacters__I_3CI",
	"CFStringGetBytes",
	"CFStringGetCharacters",
	"CFStringGetLength",
	"CFStringGetSystemEncoding",
	"CFURLCopyFileSystemPath",
	"CFURLCopyLastPathComponent",
	"CFURLCopyPathExtension",
	"CFURLCreateCopyAppendingPathComponent",
	"CFURLCreateCopyDeletingLastPathComponent",
	"CFURLCreateData",
	"CFURLCreateFromFSRef",
	"CFURLCreateFromFileSystemRepresentation",
	"CFURLCreateStringByAddingPercentEscapes",
	"CFURLCreateWithBytes",
	"CFURLCreateWithFileSystemPath",
	"CFURLCreateWithString",
	"CFURLGetFSRef",
	"CGAffineTransformConcat",
	"CGAffineTransformInvert",
	"CGAffineTransformMake",
	"CGAffineTransformRotate",
	"CGAffineTransformScale",
	"CGAffineTransformTranslate",
	"CGBitmapContextCreate",
	"CGBitmapContextCreateImage",
	"CGColorCreate",
	"CGColorRelease",
	"CGColorSpaceCreateDeviceRGB",
	"CGColorSpaceCreatePattern",
	"CGColorSpaceRelease",
	"CGContextAddArc",
	"CGContextAddArcToPoint",
	"CGContextAddLineToPoint",
	"CGContextAddLines",
	"CGContextAddPath",
	"CGContextBeginPath",
	"CGContextClearRect",
	"CGContextClip",
	"CGContextClosePath",
	"CGContextConcatCTM",
	"CGContextDrawImage",
	"CGContextDrawShading",
	"CGContextEOClip",
	"CGContextEOFillPath",
	"CGContextFillPath",
	"CGContextFillRect",
	"CGContextFlush",
	"CGContextGetCTM",
	"CGContextGetInterpolationQuality",
	"CGContextGetPathBoundingBox",
	"CGContextGetTextPosition",
	"CGContextMoveToPoint",
	"CGContextRelease",
	"CGContextRestoreGState",
	"CGContextSaveGState",
	"CGContextScaleCTM",
	"CGContextSelectFont",
	"CGContextSetAlpha",
	"CGContextSetFillColor",
	"CGContextSetFillColorSpace",
	"CGContextSetFillPattern",
	"CGContextSetFont",
	"CGContextSetFontSize",
	"CGContextSetInterpolationQuality",
	"CGContextSetLineCap",
	"CGContextSetLineDash",
	"CGContextSetLineJoin",
	"CGContextSetLineWidth",
	"CGContextSetMiterLimit",
	"CGContextSetRGBFillColor",
	"CGContextSetRGBStrokeColor",
	"CGContextSetRenderingIntent",
	"CGContextSetShouldAntialias",
	"CGContextSetShouldSmoothFonts",
	"CGContextSetStrokeColor",
	"CGContextSetStrokeColorSpace",
	"CGContextSetStrokePattern",
	"CGContextSetTextDrawingMode",
	"CGContextSetTextMatrix",
	"CGContextSetTextPosition",
	"CGContextShowText",
	"CGContextShowTextAtPoint",
	"CGContextStrokePath",
	"CGContextStrokeRect",
	"CGContextSynchronize",
	"CGContextTranslateCTM",
	"CGDataProviderCreateWithData",
	"CGDataProviderCreateWithURL",
	"CGDataProviderRelease",
	"CGDisplayBaseAddress",
	"CGDisplayBitsPerPixel",
	"CGDisplayBitsPerSample",
	"CGDisplayBytesPerRow",
	"CGDisplayPixelsHigh",
	"CGDisplayPixelsWide",
	"CGFontCreateWithPlatformFont",
	"CGFontRelease",
	"CGFunctionCreate",
	"CGFunctionRelease",
	"CGGetDisplaysWithRect",
	"CGImageCreate",
	"CGImageCreateWithImageInRect",
	"CGImageCreateWithJPEGDataProvider",
	"CGImageCreateWithPNGDataProvider",
	"CGImageGetAlphaInfo",
	"CGImageGetBitsPerComponent",
	"CGImageGetBitsPerPixel",
	"CGImageGetBytesPerRow",
	"CGImageGetColorSpace",
	"CGImageGetHeight",
	"CGImageGetWidth",
	"CGImageRelease",
	"CGPathAddArc",
	"CGPathAddCurveToPoint",
	"CGPathAddLineToPoint",
	"CGPathAddPath",
	"CGPathAddQuadCurveToPoint",
	"CGPathAddRect",
	"CGPathApply",
	"CGPathCloseSubpath",
	"CGPathCreateMutable",
	"CGPathGetBoundingBox",
	"CGPathGetCurrentPoint",
	"CGPathIsEmpty",
	"CGPathMoveToPoint",
	"CGPathRelease",
	"CGPatternCreate",
	"CGPatternRelease",
	"CGPointApplyAffineTransform",
	"CGPostKeyboardEvent",
	"CGPostMouseEvent",
	"CGRectContainsPoint",
	"CGShadingCreateAxial",
	"CGShadingCreateRadial",
	"CGShadingRelease",
	"CGWarpMouseCursorPosition",
	"CPSEnableForegroundOperation",
	"CPSSetProcessName",
	"CalcMenuSize",
	"Call",
	"CallNextEventHandler",
	"CancelMenuTracking",
	"ChangeWindowAttributes",
	"CharWidth",
	"ClearCurrentScrap",
	"ClearKeyboardFocus",
	"ClearMenuBar",
	"ClipCGContextToRegion",
	"CloseDataBrowserContainer",
	"ClosePoly",
	"CloseRgn",
	"CollapseWindow",
	"ConvertEventRefToEventRecord",
	"ConvertFromPStringToUnicode",
	"ConvertFromUnicodeToPString",
	"CopyBits",
	"CopyControlTitleAsCFString",
	"CopyDeepMask",
	"CopyMenuItemTextAsCFString",
	"CopyRgn",
	"CountDragItemFlavors",
	"CountDragItems",
	"CountMenuItems",
	"CountSubControls",
	"CreateBevelButtonControl",
	"CreateCGContextForPort",
	"CreateCheckBoxControl",
	"CreateClockControl",
	"CreateDataBrowserControl",
	"CreateEditUnicodeTextControl",
	"CreateEvent",
	"CreateGroupBoxControl",
	"CreateIconControl",
	"CreateLittleArrowsControl",
	"CreateNewMenu",
	"CreateNewWindow",
	"CreatePopupArrowControl",
	"CreatePopupButtonControl",
	"CreateProgressBarControl",
	"CreatePushButtonControl",
	"CreatePushButtonWithIconControl",
	"CreateRadioButtonControl",
	"CreateRootControl",
	"CreateScrollBarControl",
	"CreateSeparatorControl",
	"CreateSliderControl",
	"CreateStandardAlert",
	"CreateStaticTextControl",
	"CreateTabsControl",
	"CreateTextToUnicodeInfoByEncoding",
	"CreateUnicodeToTextInfoByEncoding",
	"CreateUserPaneControl",
	"CreateWindowGroup",
	"DMGetFirstScreenDevice",
	"DMGetNextScreenDevice",
	"DataBrowserChangeAttributes",
	"DataBrowserGetAttributes",
	"DataBrowserGetMetric",
	"DataBrowserSetMetric",
	"DeleteGlobalRef",
	"DeleteMenu",
	"DeleteMenuItem",
	"DeleteMenuItems",
	"DiffRgn",
	"DisableControl",
	"DisableMenuCommand",
	"DisableMenuItem",
	"DisposeControl",
	"DisposeDrag",
	"DisposeGWorld",
	"DisposeHandle",
	"DisposeMenu",
	"DisposePtr",
	"DisposeRgn",
	"DisposeTextToUnicodeInfo",
	"DisposeUnicodeToTextInfo",
	"DisposeWindow",
	"DrawControlInCurrentPort",
	"DrawMenuBar",
	"DrawText",
	"DrawThemeButton",
	"DrawThemeEditTextFrame",
	"DrawThemeFocusRect",
	"DrawThemePopupArrow",
	"DrawThemeSeparator",
	"DrawThemeTextBox",
	"EmbedControl",
	"EmptyRect",
	"EmptyRgn",
	"EnableControl",
	"EnableMenuCommand",
	"EnableMenuItem",
	"EndUpdate",
	"EqualRect",
	"EraseRect",
	"EraseRgn",
	"FMCreateFontFamilyInstanceIterator",
	"FMCreateFontFamilyIterator",
	"FMDisposeFontFamilyInstanceIterator",
	"FMDisposeFontFamilyIterator",
	"FMGetATSFontRefFromFont",
	"FMGetFontFamilyFromName",
	"FMGetFontFamilyInstanceFromFont",
	"FMGetFontFamilyName",
	"FMGetFontFromFontFamilyInstance",
	"FMGetNextFontFamily",
	"FMGetNextFontFamilyInstance",
	"FPIsFontPanelVisible",
	"FPShowHideFontPanel",
	"FSGetCatalogInfo",
	"FSpGetFInfo",
	"FSpMakeFSRef",
	"FetchFontInfo",
	"FindSpecificEventInQueue",
	"FindWindow",
	"Fix2Long",
	"Fix2X",
	"FrameOval",
	"FramePoly",
	"FrameRect",
	"FrameRoundRect",
	"FrontWindow",
	"Gestalt",
	"GetAppFont",
	"GetApplicationEventTarget",
	"GetAvailableWindowAttributes",
	"GetAvailableWindowPositioningBounds",
	"GetBestControlRect",
	"GetCaretTime",
	"GetClip",
	"GetControl32BitMaximum",
	"GetControl32BitMinimum",
	"GetControl32BitValue",
	"GetControlAction",
	"GetControlBounds",
	"GetControlData__ISIILorg_eclipse_swt_internal_carbon_ControlEditTextSelectionRec_2_3I",
	"GetControlData__ISIILorg_eclipse_swt_internal_carbon_ControlFontStyleRec_2_3I",
	"GetControlData__ISIILorg_eclipse_swt_internal_carbon_LongDateRec_2_3I",
	"GetControlData__ISIILorg_eclipse_swt_internal_carbon_Rect_2_3I",
	"GetControlData__ISII_3B_3I",
	"GetControlData__ISII_3I_3I",
	"GetControlData__ISII_3S_3I",
	"GetControlEventTarget",
	"GetControlFeatures",
	"GetControlKind",
	"GetControlOwner",
	"GetControlProperty",
	"GetControlReference",
	"GetControlRegion",
	"GetControlValue",
	"GetControlViewSize",
	"GetCurrentEventButtonState",
	"GetCurrentEventKeyModifiers",
	"GetCurrentEventLoop",
	"GetCurrentEventQueue",
	"GetCurrentProcess",
	"GetCurrentScrap",
	"GetDataBrowserCallbacks",
	"GetDataBrowserItemCount",
	"GetDataBrowserItemDataButtonValue",
	"GetDataBrowserItemPartBounds",
	"GetDataBrowserItemState",
	"GetDataBrowserItems",
	"GetDataBrowserListViewDisclosureColumn",
	"GetDataBrowserListViewHeaderBtnHeight",
	"GetDataBrowserListViewHeaderDesc",
	"GetDataBrowserPropertyFlags",
	"GetDataBrowserScrollBarInset",
	"GetDataBrowserScrollPosition",
	"GetDataBrowserSelectionAnchor",
	"GetDataBrowserSelectionFlags",
	"GetDataBrowserSortProperty",
	"GetDataBrowserTableViewColumnPosition",
	"GetDataBrowserTableViewItemID",
	"GetDataBrowserTableViewItemRow",
	"GetDataBrowserTableViewNamedColumnWidth",
	"GetDataBrowserTableViewRowHeight",
	"GetDblTime",
	"GetDefFontSize",
	"GetDeviceList",
	"GetDragAllowableActions",
	"GetDragDropAction",
	"GetDragItemReferenceNumber",
	"GetDragModifiers",
	"GetDragMouse",
	"GetEventClass",
	"GetEventDispatcherTarget",
	"GetEventKind",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_CGPoint_2",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_CGRect_2",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_HICommand_2",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_Point_2",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_RGBColor_2",
	"GetEventParameter__III_3II_3ILorg_eclipse_swt_internal_carbon_Rect_2",
	"GetEventParameter__III_3II_3I_3B",
	"GetEventParameter__III_3II_3I_3C",
	"GetEventParameter__III_3II_3I_3I",
	"GetEventParameter__III_3II_3I_3S",
	"GetEventParameter__III_3II_3I_3Z",
	"GetEventTime",
	"GetFlavorData",
	"GetFlavorDataSize",
	"GetFlavorType",
	"GetFontInfo",
	"GetGDevice",
	"GetGWorld",
	"GetGlobalMouse",
	"GetHandleSize",
	"GetIconFamilyData",
	"GetIconRef",
	"GetIconRefFromFileInfo",
	"GetIconRefFromIconFamilyPtr",
	"GetIndMenuItemWithCommandID",
	"GetIndexedSubControl",
	"GetItemMark",
	"GetKeyboardFocus",
	"GetLastUserEventTime",
	"GetMBarHeight",
	"GetMainDevice",
	"GetMainEventQueue",
	"GetMenuCommandMark",
	"GetMenuEventTarget",
	"GetMenuFont",
	"GetMenuHeight",
	"GetMenuID",
	"GetMenuItemCommandID",
	"GetMenuItemHierarchicalMenu",
	"GetMenuItemRefCon",
	"GetMenuTrackingData",
	"GetMenuWidth",
	"GetMouse",
	"GetNextDevice",
	"GetNextWindow",
	"GetPixBounds",
	"GetPixDepth",
	"GetPort",
	"GetPortBitMapForCopyBits",
	"GetPortBounds",
	"GetPortClipRegion",
	"GetPortVisibleRegion",
	"GetPreviousWindow",
	"GetPtrSize",
	"GetRegionBounds",
	"GetRootControl",
	"GetScrapFlavorCount",
	"GetScrapFlavorData__II_3I_3B",
	"GetScrapFlavorData__II_3I_3C",
	"GetScrapFlavorInfoList",
	"GetScrapFlavorSize",
	"GetScriptManagerVariable",
	"GetSuperControl",
	"GetTabContentRect",
	"GetThemeBrushAsColor",
	"GetThemeButtonContentBounds",
	"GetThemeButtonRegion",
	"GetThemeDrawingState",
	"GetThemeFont",
	"GetThemeMenuItemExtra",
	"GetThemeMetric",
	"GetThemeTextColor",
	"GetThemeTextDimensions",
	"GetUserFocusEventTarget",
	"GetUserFocusWindow",
	"GetWRefCon",
	"GetWindowActivationScope",
	"GetWindowBounds",
	"GetWindowClass",
	"GetWindowDefaultButton",
	"GetWindowEventTarget",
	"GetWindowFromPort",
	"GetWindowGroupOfClass",
	"GetWindowList",
	"GetWindowModality",
	"GetWindowPort",
	"GetWindowRegion",
	"GetWindowResizeLimits",
	"GetWindowStructureWidths",
	"HIComboBoxAppendTextItem",
	"HIComboBoxCopyTextItemAtIndex",
	"HIComboBoxCreate",
	"HIComboBoxGetItemCount",
	"HIComboBoxInsertTextItemAtIndex",
	"HIComboBoxRemoveItemAtIndex",
	"HICopyAccessibilityRoleDescription",
	"HICreateTransformedCGImage",
	"HIObjectCopyClassID",
	"HIObjectCreate",
	"HIObjectRegisterSubclass",
	"HIScrollViewCreate",
	"HITextViewCreate",
	"HITextViewGetTXNObject",
	"HIThemeDrawBackground",
	"HIThemeDrawButton",
	"HIThemeDrawFocusRect",
	"HIThemeDrawFrame",
	"HIThemeDrawGenericWell",
	"HIThemeDrawGroupBox",
	"HIThemeDrawGrowBox",
	"HIThemeDrawPopupArrow",
	"HIThemeDrawSeparator",
	"HIThemeDrawTab",
	"HIThemeDrawTabPane",
	"HIThemeDrawTextBox",
	"HIThemeDrawTrack",
	"HIThemeGetButtonBackgroundBounds",
	"HIThemeGetButtonContentBounds",
	"HIThemeGetScrollBarTrackRect",
	"HIThemeGetTextDimensions",
	"HIThemeGetTrackBounds",
	"HIThemeGetTrackLiveValue",
	"HIThemeGetTrackPartBounds",
	"HIThemeGetTrackThumbPositionFromBounds",
	"HIThemeGetTrackThumbPositionFromOffset",
	"HIThemeHitTestScrollBarArrows",
	"HIThemeHitTestTrack",
	"HIThemeSetFill",
	"HIViewAddSubview",
	"HIViewChangeAttributes",
	"HIViewChangeFeatures",
	"HIViewClick",
	"HIViewConvertPoint",
	"HIViewConvertRect",
	"HIViewConvertRegion",
	"HIViewCreateOffscreenImage",
	"HIViewDrawCGImage",
	"HIViewFindByID",
	"HIViewGetBounds",
	"HIViewGetFeatures",
	"HIViewGetFirstSubview",
	"HIViewGetFrame",
	"HIViewGetLastSubview",
	"HIViewGetNeedsDisplay",
	"HIViewGetNextView",
	"HIViewGetRoot",
	"HIViewGetSizeConstraints",
	"HIViewGetSubviewHit",
	"HIViewGetSuperview",
	"HIViewGetViewForMouseEvent",
	"HIViewIsDrawingEnabled",
	"HIViewIsVisible",
	"HIViewRemoveFromSuperview",
	"HIViewRender",
	"HIViewScrollRect",
	"HIViewSetBoundsOrigin",
	"HIViewSetDrawingEnabled",
	"HIViewSetFrame",
	"HIViewSetNeedsDisplay",
	"HIViewSetNeedsDisplayInRegion",
	"HIViewSetVisible",
	"HIViewSetZOrder",
	"HIViewSimulateClick",
	"HLock",
	"HMDisplayTag",
	"HMGetTagDelay",
	"HMHideTag",
	"HMInstallControlContentCallback",
	"HMSetTagDelay",
	"HUnlock",
	"HandleControlClick",
	"HandleControlSetCursor",
	"HiWord",
	"HideWindow",
	"HiliteMenu",
	"IconRefToIconFamily",
	"InitContextualMenus",
	"InitCursor",
	"InitDataBrowserCallbacks",
	"InitDataBrowserCustomCallbacks",
	"InsertMenu",
	"InsertMenuItemTextWithCFString",
	"InstallEventHandler",
	"InstallEventLoopIdleTimer",
	"InstallEventLoopTimer",
	"InstallReceiveHandler",
	"InstallTrackingHandler",
	"InvalWindowRect",
	"InvalWindowRgn",
	"InvertRect",
	"InvertRgn",
	"IsControlActive",
	"IsControlEnabled",
	"IsControlVisible",
	"IsDataBrowserItemSelected",
	"IsMenuCommandEnabled",
	"IsMenuItemEnabled",
	"IsValidControlHandle",
	"IsValidMenu",
	"IsValidWindowPtr",
	"IsWindowActive",
	"IsWindowCollapsed",
	"IsWindowVisible",
	"JNIGetObject",
	"KeyTranslate",
	"KillPoly",
	"LSCopyAllRoleHandlersForContentType",
	"LSCopyDisplayNameForRef",
	"LSFindApplicationForInfo",
	"LSGetApplicationForInfo",
	"LSOpenApplication",
	"LSOpenCFURLRef",
	"LSOpenURLsWithRole",
	"LineTo",
	"LoWord",
	"LockPortBits",
	"Long2Fix",
	"MenuSelect",
	"MoveControl",
	"MoveTo",
	"MoveWindow",
	"NavCreateChooseFolderDialog",
	"NavCreateGetFileDialog",
	"NavCreatePutFileDialog",
	"NavDialogDispose",
	"NavDialogGetReply",
	"NavDialogGetSaveFileName",
	"NavDialogGetUserAction",
	"NavDialogRun",
	"NavDialogSetFilterTypeIdentifiers",
	"NavDialogSetSaveFileName",
	"NavGetDefaultDialogCreationOptions",
	"NewControl",
	"NewDrag",
	"NewGWorldFromPtr",
	"NewGlobalRef",
	"NewHandle",
	"NewHandleClear",
	"NewPtr",
	"NewPtrClear",
	"NewRgn",
	"OffsetRect",
	"OffsetRgn",
	"OpenDataBrowserContainer",
	"OpenPoly",
	"OpenRgn",
	"PMCreatePageFormat",
	"PMCreatePrintSettings",
	"PMCreateSession",
	"PMFlattenPageFormat",
	"PMFlattenPrintSettings",
	"PMGetAdjustedPageRect",
	"PMGetAdjustedPaperRect",
	"PMGetCollate",
	"PMGetCopies",
	"PMGetFirstPage",
	"PMGetJobNameCFString",
	"PMGetLastPage",
	"PMGetPageRange",
	"PMGetResolution",
	"PMRelease",
	"PMSessionBeginDocumentNoDialog",
	"PMSessionBeginPageNoDialog",
	"PMSessionCopyDestinationLocation",
	"PMSessionCreatePrinterList",
	"PMSessionDefaultPageFormat",
	"PMSessionDefaultPrintSettings",
	"PMSessionEndDocumentNoDialog",
	"PMSessionEndPageNoDialog",
	"PMSessionError",
	"PMSessionGetDestinationType",
	"PMSessionGetGraphicsContext",
	"PMSessionPageSetupDialog",
	"PMSessionPrintDialog",
	"PMSessionSetCurrentPrinter",
	"PMSessionSetDestination",
	"PMSessionSetDocumentFormatGeneration",
	"PMSessionSetError",
	"PMSessionUseSheets",
	"PMSessionValidatePageFormat",
	"PMSessionValidatePrintSettings",
	"PMSetCollate",
	"PMSetFirstPage",
	"PMSetJobNameCFString",
	"PMSetLastPage",
	"PMSetPageRange",
	"PMUnflattenPageFormat",
	"PMUnflattenPrintSettings",
	"PaintOval",
	"PaintPoly",
	"PaintRect",
	"PaintRoundRect",
	"PenSize",
	"PickColor",
	"PopUpMenuSelect",
	"PostEvent",
	"PostEventToQueue",
	"PtInRect",
	"PtInRgn",
	"PutScrapFlavor__IIII_3B",
	"PutScrapFlavor__IIII_3C",
	"QDBeginCGContext",
	"QDEndCGContext",
	"QDFlushPortBuffer",
	"QDGlobalToLocalPoint",
	"QDLocalToGlobalPoint",
	"QDRegionToRects",
	"QDSetDirtyRegion",
	"QDSetPatternOrigin",
	"QDSwapTextFlags",
	"RGBBackColor",
	"RGBForeColor",
	"ReadIconFile",
	"ReceiveNextEvent",
	"RectInRgn",
	"RectRgn",
	"RegisterAppearanceClient",
	"ReleaseEvent",
	"ReleaseIconRef",
	"ReleaseMenu",
	"ReleaseWindow",
	"ReleaseWindowGroup",
	"RemoveControlProperty",
	"RemoveDataBrowserItems",
	"RemoveDataBrowserTableViewColumn",
	"RemoveEventHandler",
	"RemoveEventLoopTimer",
	"RemoveReceiveHandler",
	"RemoveTrackingHandler",
	"RepositionWindow",
	"ReshapeCustomWindow",
	"RestoreApplicationDockTileImage",
	"RetainEvent",
	"RetainMenu",
	"RetainWindow",
	"RevealDataBrowserItem",
	"RunStandardAlert",
	"ScrollRect",
	"SectRect",
	"SectRgn",
	"SelectWindow",
	"SendBehind",
	"SendEventToEventTarget",
	"SendEventToEventTargetWithOptions",
	"SetApplicationDockTileImage",
	"SetAutomaticControlDragTrackingEnabledForWindow",
	"SetBevelButtonContentInfo",
	"SetClip",
	"SetControl32BitMaximum",
	"SetControl32BitMinimum",
	"SetControl32BitValue",
	"SetControlAction",
	"SetControlBounds",
	"SetControlColorProc",
	"SetControlData__IIIII",
	"SetControlData__IIIILorg_eclipse_swt_internal_carbon_ControlButtonContentInfo_2",
	"SetControlData__IIIILorg_eclipse_swt_internal_carbon_ControlEditTextSelectionRec_2",
	"SetControlData__IIIILorg_eclipse_swt_internal_carbon_ControlTabInfoRecV1_2",
	"SetControlData__IIIILorg_eclipse_swt_internal_carbon_LongDateRec_2",
	"SetControlData__IIIILorg_eclipse_swt_internal_carbon_Rect_2",
	"SetControlData__IIII_3B",
	"SetControlData__IIII_3I",
	"SetControlData__IIII_3S",
	"SetControlFontStyle",
	"SetControlPopupMenuHandle",
	"SetControlProperty",
	"SetControlReference",
	"SetControlTitleWithCFString",
	"SetControlViewSize",
	"SetControlVisibility",
	"SetCursor",
	"SetDataBrowserCallbacks",
	"SetDataBrowserCustomCallbacks",
	"SetDataBrowserHasScrollBars",
	"SetDataBrowserItemDataBooleanValue",
	"SetDataBrowserItemDataButtonValue",
	"SetDataBrowserItemDataIcon",
	"SetDataBrowserItemDataItemID",
	"SetDataBrowserItemDataText",
	"SetDataBrowserListViewDisclosureColumn",
	"SetDataBrowserListViewHeaderBtnHeight",
	"SetDataBrowserListViewHeaderDesc",
	"SetDataBrowserPropertyFlags",
	"SetDataBrowserScrollPosition",
	"SetDataBrowserSelectedItems",
	"SetDataBrowserSelectionFlags",
	"SetDataBrowserSortOrder",
	"SetDataBrowserSortProperty",
	"SetDataBrowserTableViewColumnPosition",
	"SetDataBrowserTableViewHiliteStyle",
	"SetDataBrowserTableViewItemRow",
	"SetDataBrowserTableViewNamedColumnWidth",
	"SetDataBrowserTableViewRowHeight",
	"SetDataBrowserTarget",
	"SetDragAllowableActions",
	"SetDragDropAction",
	"SetDragImageWithCGImage",
	"SetDragInputProc",
	"SetDragItemFlavorData",
	"SetDragSendProc",
	"SetEventLoopTimerNextFireTime",
	"SetEventParameter__IIIILorg_eclipse_swt_internal_carbon_CGPoint_2",
	"SetEventParameter__IIIILorg_eclipse_swt_internal_carbon_HICommand_2",
	"SetEventParameter__IIII_3C",
	"SetEventParameter__IIII_3I",
	"SetEventParameter__IIII_3S",
	"SetEventParameter__IIII_3Z",
	"SetFontInfoForSelection",
	"SetFrontProcess",
	"SetFrontProcessWithOptions",
	"SetGWorld",
	"SetHandleSize",
	"SetIconFamilyData",
	"SetItemMark",
	"SetKeyboardFocus",
	"SetMenuCommandMark",
	"SetMenuFont",
	"SetMenuItemCommandKey",
	"SetMenuItemHierarchicalMenu",
	"SetMenuItemIconHandle",
	"SetMenuItemKeyGlyph",
	"SetMenuItemModifiers",
	"SetMenuItemRefCon",
	"SetMenuItemTextWithCFString",
	"SetMenuTitleWithCFString",
	"SetOrigin",
	"SetPort",
	"SetPortBounds",
	"SetPortWindowPort",
	"SetPt",
	"SetRect",
	"SetRectRgn",
	"SetRootMenu",
	"SetThemeBackground",
	"SetThemeCursor",
	"SetThemeDrawingState",
	"SetThemeTextColor",
	"SetThemeWindowBackground",
	"SetUpControlBackground",
	"SetWRefCon",
	"SetWindowActivationScope",
	"SetWindowBounds",
	"SetWindowDefaultButton",
	"SetWindowGroup",
	"SetWindowGroupOwner",
	"SetWindowGroupParent",
	"SetWindowModality",
	"SetWindowResizeLimits",
	"SetWindowTitleWithCFString",
	"ShowWindow",
	"SizeControl",
	"SizeWindow",
	"StillDown",
	"SyncCGContextOriginWithPort",
	"SysBeep",
	"TXNActivate",
	"TXNAdjustCursor",
	"TXNClick",
	"TXNCopy",
	"TXNCut",
	"TXNDataSize",
	"TXNDeleteObject",
	"TXNDraw",
	"TXNEchoMode",
	"TXNFocus",
	"TXNGetData",
	"TXNGetHIRect",
	"TXNGetLineCount",
	"TXNGetLineMetrics",
	"TXNGetRectBounds",
	"TXNGetSelection",
	"TXNGetTXNObjectControls",
	"TXNGetViewRect",
	"TXNInitTextension",
	"TXNNewObject",
	"TXNOffsetToPoint",
	"TXNPaste",
	"TXNPointToOffset",
	"TXNSelectAll",
	"TXNSetBackground",
	"TXNSetData",
	"TXNSetFrameBounds",
	"TXNSetRectBounds",
	"TXNSetSelection",
	"TXNSetTXNObjectControls",
	"TXNSetTypeAttributes",
	"TXNShowSelection",
	"TestControl",
	"TextFace",
	"TextFont",
	"TextMode",
	"TextSize",
	"TextWidth",
	"TrackDrag",
	"TrackMouseLocationWithOptions",
	"UTTypeCreateAllIdentifiersForTag",
	"UTTypeCreatePreferredIdentifierForTag",
	"UnionRect",
	"UnionRgn",
	"UnlockPortBits",
	"UpdateControls",
	"UpdateDataBrowserItems",
	"UpgradeScriptInfoToTextEncoding",
	"WaitMouseMoved",
	"X2Fix",
	"ZoomWindowIdeal",
	"_1_1BIG_1ENDIAN_1_1",
	"getenv",
	"getpid",
	"kCFNumberFormatterDecimalSeparator",
	"kHIViewWindowContentID",
	"kPMDocumentFormatPDF",
	"kPMGraphicsContextCoreGraphics",
	"kUTTagClassFilenameExtension",
	"memcpy__III",
	"memcpy__ILorg_eclipse_swt_internal_carbon_ATSUTab_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_BitMap_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_Cursor_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_EventRecord_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_FontSelectionQDStyle_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_HMHelpContentRec_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_PixMap_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_RGBColor_2I",
	"memcpy__ILorg_eclipse_swt_internal_carbon_Rect_2I",
	"memcpy__I_3BI",
	"memcpy__I_3CI",
	"memcpy__I_3FI",
	"memcpy__I_3II",
	"memcpy__I_3SI",
	"memcpy__Lorg_eclipse_swt_internal_carbon_ATSLayoutRecord_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_ATSTrapezoid_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_CGPathElement_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_FontSelectionQDStyle_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_GDevice_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_HMHelpContentRec_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_PixMap_2II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_Point_2_3II",
	"memcpy__Lorg_eclipse_swt_internal_carbon_Rect_2II",
	"memcpy___3BII",
	"memcpy___3B_3CI",
	"memcpy___3CII",
	"memcpy___3C_3BI",
	"memcpy___3FII",
	"memcpy___3III",
	"memcpy___3ILorg_eclipse_swt_internal_carbon_TXNTab_2I",
	"memset",
	"strlen",
};

#define STATS_NATIVE(func) Java_org_eclipse_swt_tools_internal_NativeStats_##func

JNIEXPORT jint JNICALL STATS_NATIVE(OS_1GetFunctionCount)
	(JNIEnv *env, jclass that)
{
	return OS_nativeFunctionCount;
}

JNIEXPORT jstring JNICALL STATS_NATIVE(OS_1GetFunctionName)
	(JNIEnv *env, jclass that, jint index)
{
	return (*env)->NewStringUTF(env, OS_nativeFunctionNames[index]);
}

JNIEXPORT jint JNICALL STATS_NATIVE(OS_1GetFunctionCallCount)
	(JNIEnv *env, jclass that, jint index)
{
	return OS_nativeFunctionCallCount[index];
}

#endif
