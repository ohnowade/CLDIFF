/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
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

int OS_nativeFunctionCount = 945;
int OS_nativeFunctionCallCount[945];
char * OS_nativeFunctionNames[] = {
	"AECountItems",
	"AEGetNthPtr",
	"AEInstallEventHandler",
	"AEProcessAppleEvent",
	"ATSFontActivateFromFileSpecification",
	"ATSFontDeactivate",
	"ATSFontFindFromName",
	"ATSFontGetHorizontalMetrics",
	"ATSFontGetName",
	"ATSFontGetPostScriptName",
	"ATSFontGetVerticalMetrics",
	"ATSFontIteratorCreate",
	"ATSFontIteratorNext",
	"ATSFontIteratorRelease",
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
	"AXUIElementCopyAttributeValue",
	"AXUIElementCreateWithDataBrowserAndItemInfo",
	"AXUIElementCreateWithHIObjectAndIdentifier",
	"AXUIElementGetDataBrowserItemInfo",
	"AXUIElementGetHIObject",
	"AXUIElementGetIdentifier",
	"AXValueCreate",
	"AXValueGetValue",
	"AcquireFirstMatchingEventInQueue",
	"ActivateTSMDocument",
	"ActiveNonFloatingWindow",
	"AddDataBrowserItems",
	"AddDataBrowserListViewColumn",
	"AddDragItemFlavor",
	"AppendMenuItemTextWithCFString",
	"AutoSizeDataBrowserListViewColumns",
	"BringToFront",
	"CFArrayAppendValue",
	"CFArrayCreateMutable",
	"CFArrayGetCount",
	"CFArrayGetValueAtIndex",
	"CFDataGetBytePtr",
	"CFDataGetBytes",
	"CFDataGetLength",
	"CFDictionaryGetValueIfPresent",
	"CFEqual",
	"CFLocaleCopyCurrent",
	"CFNumberFormatterCopyProperty",
	"CFNumberFormatterCreate",
	"CFRelease",
	"CFRetain",
	"CFRunLoopAddObserver",
	"CFRunLoopAddSource",
	"CFRunLoopObserverCreate",
	"CFRunLoopObserverInvalidate",
	"CFRunLoopRunInMode",
	"CFRunLoopSourceCreate",
	"CFRunLoopSourceInvalidate",
	"CFRunLoopSourceSignal",
	"CFRunLoopStop",
	"CFRunLoopWakeUp",
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
	"CGContextAddRect",
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
	"CGContextSetBlendMode",
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
	"CGImageGetDataProvider",
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
	"CGPathCreateMutableCopy",
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
	"CGPostScrollWheelEvent",
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
	"ChangeMenuItemAttributes",
	"ChangeWindowAttributes",
	"ClearCurrentScrap",
	"ClearKeyboardFocus",
	"ClearMenuBar",
	"ClipCGContextToRegion",
	"CloseDataBrowserContainer",
	"ClosePicture",
	"CloseRgn",
	"CollapseWindow",
	"ContextualMenuSelect",
	"ConvertEventRefToEventRecord",
	"ConvertFromPStringToUnicode",
	"ConvertFromUnicodeToPString",
	"CopyBits",
	"CopyControlTitleAsCFString",
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
	"DataBrowserChangeAttributes",
	"DataBrowserGetAttributes",
	"DataBrowserGetMetric",
	"DataBrowserSetMetric",
	"DeactivateTSMDocument",
	"DeleteGlobalRef",
	"DeleteMenu",
	"DeleteMenuItem",
	"DeleteMenuItems",
	"DeleteTSMDocument",
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
	"DrawPicture",
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
	"EraseRect",
	"FMGetATSFontRefFromFont",
	"FMGetFontFamilyFromName",
	"FMGetFontFamilyInstanceFromFont",
	"FMGetFontFromATSFontRef",
	"FMGetFontFromFontFamilyInstance",
	"FPIsFontPanelVisible",
	"FPShowHideFontPanel",
	"FSGetCatalogInfo",
	"FSpGetFInfo",
	"FSpMakeFSRef",
	"FindSpecificEventInQueue",
	"FindWindow",
	"Fix2Long",
	"Fix2X",
	"FixTSMDocument",
	"FrontWindow",
	"Gestalt",
	"GetApplicationEventTarget",
	"GetAvailableWindowAttributes",
	"GetAvailableWindowPositioningBounds",
	"GetBestControlRect",
	"GetCFRunLoopFromEventLoop",
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
	"GetDeviceList",
	"GetDragAllowableActions",
	"GetDragDropAction",
	"GetDragItemReferenceNumber",
	"GetDragModifiers",
	"GetDragMouse",
	"GetEventClass",
	"GetEventDispatcherTarget",
	"GetEventKind",
	"GetEventParameter__III_3II_3II",
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
	"GetPixDepth",
	"GetPort",
	"GetPortBitMapForCopyBits",
	"GetPortBounds",
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
	"GetSystemUIMode",
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
	"GetWindowActivationScope",
	"GetWindowAlpha",
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
	"HIComboBoxIsListVisible",
	"HIComboBoxRemoveItemAtIndex",
	"HIComboBoxSetListVisible",
	"HICopyAccessibilityRoleDescription",
	"HICreateTransformedCGImage",
	"HIObjectCopyClassID",
	"HIObjectCreate",
	"HIObjectRegisterSubclass",
	"HIObjectSetAccessibilityIgnored",
	"HIScrollViewCreate",
	"HIScrollViewSetScrollBarAutoHide",
	"HISearchFieldChangeAttributes",
	"HISearchFieldCopyDescriptiveText",
	"HISearchFieldCreate",
	"HISearchFieldGetAttributes",
	"HISearchFieldSetDescriptiveText",
	"HIShapeCreateWithQDRgn",
	"HIShapeReplacePathInCGContext",
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
	"HIThemeSetTextFill",
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
	"HIViewGetLayoutInfo",
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
	"HIViewSetLayoutInfo",
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
	"IsControlActive",
	"IsControlEnabled",
	"IsControlVisible",
	"IsDataBrowserItemSelected",
	"IsEventInQueue",
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
	"KillPicture",
	"LSCopyAllRoleHandlersForContentType",
	"LSCopyDisplayNameForRef",
	"LSFindApplicationForInfo",
	"LSGetApplicationForInfo",
	"LSOpenApplication",
	"LSOpenCFURLRef",
	"LSOpenURLsWithRole",
	"LineTo",
	"LoWord",
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
	"NewTSMDocument",
	"OffsetRect",
	"OffsetRgn",
	"OpenDataBrowserContainer",
	"OpenPicture",
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
	"QDPictCreateWithProvider",
	"QDPictDrawToCGContext",
	"QDPictGetBounds",
	"QDPictRelease",
	"QDRegionToRects",
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
	"RemoveEventFromQueue",
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
	"SetEventParameter__IIIILorg_eclipse_swt_internal_carbon_Point_2",
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
	"SetPort",
	"SetPt",
	"SetRect",
	"SetRectRgn",
	"SetRootMenu",
	"SetSystemUIMode",
	"SetThemeBackground",
	"SetThemeCursor",
	"SetThemeDrawingState",
	"SetThemeTextColor",
	"SetThemeWindowBackground",
	"SetUpControlBackground",
	"SetWindowActivationScope",
	"SetWindowAlpha",
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
	"SysBeep",
	"TXNCopy",
	"TXNCut",
	"TXNDataSize",
	"TXNDeleteObject",
	"TXNEchoMode",
	"TXNGetData",
	"TXNGetHIRect",
	"TXNGetLineCount",
	"TXNGetLineMetrics",
	"TXNGetSelection",
	"TXNGetTXNObjectControls",
	"TXNGetViewRect",
	"TXNHIPointToOffset",
	"TXNInitTextension",
	"TXNOffsetToHIPoint",
	"TXNPaste",
	"TXNSelectAll",
	"TXNSetBackground",
	"TXNSetData",
	"TXNSetFrameBounds",
	"TXNSetSelection",
	"TXNSetTXNObjectControls",
	"TXNSetTypeAttributes",
	"TXNShowSelection",
	"TextFace",
	"TextFont",
	"TextSize",
	"TrackDrag",
	"TrackMouseLocationWithOptions",
	"UTTypeCreateAllIdentifiersForTag",
	"UTTypeCreatePreferredIdentifierForTag",
	"UnionRect",
	"UnionRgn",
	"UpdateDataBrowserItems",
	"UpgradeScriptInfoToTextEncoding",
	"UseInputWindow",
	"WaitMouseMoved",
	"X2Fix",
	"ZoomWindowIdeal",
	"_1_1BIG_1ENDIAN_1_1",
	"getpid",
	"kCFNumberFormatterDecimalSeparator",
	"kCFRunLoopCommonModes",
	"kCFRunLoopDefaultMode",
	"kFontPanelAttributeSizesKey",
	"kFontPanelAttributeTagsKey",
	"kFontPanelAttributeValuesKey",
	"kFontPanelAttributesKey",
	"kHIViewWindowContentID",
	"kPMDocumentFormatPDF",
	"kPMGraphicsContextCoreGraphics",
	"kUTTagClassFilenameExtension",
	"memmove__ILorg_eclipse_swt_internal_carbon_ATSUTab_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_BitMap_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_Cursor_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_HMHelpContentRec_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_PixMap_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_RGBColor_2I",
	"memmove__ILorg_eclipse_swt_internal_carbon_Rect_2I",
	"memmove__Lorg_eclipse_swt_internal_carbon_ATSLayoutRecord_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_ATSTrapezoid_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_CGPathElement_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_GDevice_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_HMHelpContentRec_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_PixMap_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_Point_2_3II",
	"memmove__Lorg_eclipse_swt_internal_carbon_RGBColor_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_Rect_2II",
	"memmove__Lorg_eclipse_swt_internal_carbon_TextRange_2II",
	"memmove___3C_3BI",
	"memmove___3ILorg_eclipse_swt_internal_carbon_TXNTab_2I",
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
