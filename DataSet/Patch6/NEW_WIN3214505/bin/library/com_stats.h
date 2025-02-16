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

#ifdef NATIVE_STATS
extern int COM_nativeFunctionCount;
extern int COM_nativeFunctionCallCount[];
extern char* COM_nativeFunctionNames[];
#define COM_NATIVE_ENTER(env, that, func) COM_nativeFunctionCallCount[func]++;
#define COM_NATIVE_EXIT(env, that, func) 
#else
#define COM_NATIVE_ENTER(env, that, func) 
#define COM_NATIVE_EXIT(env, that, func) 
#endif

typedef enum {
	CAUUID_1sizeof_FUNC,
	CLSIDFromProgID_FUNC,
	CLSIDFromString_FUNC,
	CONTROLINFO_1sizeof_FUNC,
	COSERVERINFO_1sizeof_FUNC,
	CoCreateInstance_FUNC,
	CoFreeUnusedLibraries_FUNC,
	CoGetClassObject_FUNC,
	CoLockObjectExternal_FUNC,
	CoTaskMemAlloc_FUNC,
	CoTaskMemFree_FUNC,
	CreateStdAccessibleObject_FUNC,
	DISPPARAMS_1sizeof_FUNC,
	DVTARGETDEVICE_1sizeof_FUNC,
	DoDragDrop_FUNC,
	EXCEPINFO_1sizeof_FUNC,
	FORMATETC_1sizeof_FUNC,
	FUNCDESC_1sizeof_FUNC,
	GUID_1sizeof_FUNC,
	GetClassFile_FUNC,
	IIDFromString_FUNC,
	IsEqualGUID_FUNC,
	LICINFO_1sizeof_FUNC,
	LresultFromObject_FUNC,
	MoveMemory__ILorg_eclipse_swt_internal_ole_win32_FORMATETC_2I_FUNC,
	MoveMemory__ILorg_eclipse_swt_internal_ole_win32_GUID_2I_FUNC,
	MoveMemory__ILorg_eclipse_swt_internal_ole_win32_OLEINPLACEFRAMEINFO_2I_FUNC,
	MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STATSTG_2I_FUNC,
	MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2I_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FORMATETC_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FUNCDESC_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_GUID_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STATSTG_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_TYPEATTR_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_VARDESC_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_ole_win32_VARIANT_2II_FUNC,
	MoveMemory__Lorg_eclipse_swt_internal_win32_RECT_2II_FUNC,
	OLECMD_1sizeof_FUNC,
	OLEINPLACEFRAMEINFO_1sizeof_FUNC,
	OleCreate_FUNC,
	OleCreateFromFile_FUNC,
	OleCreatePropertyFrame_FUNC,
	OleDraw_FUNC,
	OleFlushClipboard_FUNC,
	OleGetClipboard_FUNC,
	OleIsCurrentClipboard_FUNC,
	OleIsRunning_FUNC,
	OleLoad_FUNC,
	OleRun_FUNC,
	OleSave_FUNC,
	OleSetClipboard_FUNC,
	OleSetContainedObject_FUNC,
	OleSetMenuDescriptor_FUNC,
	OleTranslateColor_FUNC,
	ProgIDFromCLSID_FUNC,
	RegisterDragDrop_FUNC,
	ReleaseStgMedium_FUNC,
	RevokeDragDrop_FUNC,
	STATSTG_1sizeof_FUNC,
	STGMEDIUM_1sizeof_FUNC,
	StgCreateDocfile_FUNC,
	StgIsStorageFile_FUNC,
	StgOpenStorage_FUNC,
	StringFromCLSID_FUNC,
	SysAllocString_FUNC,
	SysFreeString_FUNC,
	SysStringByteLen_FUNC,
	TYPEATTR_1sizeof_FUNC,
	VARDESC_1sizeof_FUNC,
	VARIANT_1sizeof_FUNC,
	VariantChangeType_FUNC,
	VariantClear_FUNC,
	VariantInit_FUNC,
	VtblCall__IIII_FUNC,
	VtblCall__IIIII_FUNC,
	VtblCall__IIIIII_FUNC,
	VtblCall__IIIIIII_FUNC,
	VtblCall__IIIIIIII_FUNC,
	VtblCall__IIIIIIIIII_FUNC,
	VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_DVTARGETDEVICE_2Lorg_eclipse_swt_internal_win32_SIZE_2_FUNC,
	VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_GUID_2I_3I_FUNC,
	VtblCall__IIII_3I_FUNC,
	VtblCall__IIII_3J_FUNC,
	VtblCall__IIIJ_3I_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_ole_win32_FORMATETC_2_3I_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2II_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2IILorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2ILorg_eclipse_swt_internal_ole_win32_EXCEPINFO_2_3I_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_ole_win32_STATSTG_2_3I_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_win32_MSG_2IIILorg_eclipse_swt_internal_win32_RECT_2_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_win32_SIZE_2_FUNC,
	VtblCall__IIIZ_FUNC,
	VtblCall__III_3I_FUNC,
	VtblCall__III_3II_3I_FUNC,
	VtblCall__III_3I_3I_3I_3I_FUNC,
	VtblCall__III_3J_FUNC,
	VtblCall__IIJI_3I_FUNC,
	VtblCall__IIJ_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_CAUUID_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_CONTROLINFO_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2Z_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2IIII_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2III_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2ILorg_eclipse_swt_internal_ole_win32_OLECMD_2Lorg_eclipse_swt_internal_ole_win32_OLECMDTEXT_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_ole_win32_LICINFO_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_win32_MSG_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2IZ_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2Lorg_eclipse_swt_internal_win32_RECT_2_FUNC,
	VtblCall__II_3C_FUNC,
	VtblCall__II_3CI_FUNC,
	VtblCall__II_3CIIII_3I_FUNC,
	VtblCall__II_3CIII_3I_FUNC,
	VtblCall__II_3CIII_3J_FUNC,
	VtblCall__II_3CJII_3J_FUNC,
	VtblCall__II_3C_3C_FUNC,
	VtblCall__II_3I_FUNC,
	VtblCall__II_3J_FUNC,
	VtblCall__IJIIIIJ_FUNC,
	VtblCall__IJJIIIII_FUNC,
	VtblCall_1IVARIANT_FUNC,
	VtblCall_1IVARIANTP_FUNC,
	VtblCall_1PPPPVARIANT_FUNC,
	VtblCall_1PVARIANTP_FUNC,
	VtblCall_1VARIANT_FUNC,
	VtblCall_1VARIANTP_FUNC,
	WriteClassStg_FUNC,
	accDoDefaultAction_1CALLBACK_FUNC,
	accLocation_1CALLBACK_FUNC,
	accNavigate_1CALLBACK_FUNC,
	accSelect_1CALLBACK_FUNC,
	get_1accChild_1CALLBACK_FUNC,
	get_1accDefaultAction_1CALLBACK_FUNC,
	get_1accDescription_1CALLBACK_FUNC,
	get_1accHelpTopic_1CALLBACK_FUNC,
	get_1accHelp_1CALLBACK_FUNC,
	get_1accKeyboardShortcut_1CALLBACK_FUNC,
	get_1accName_1CALLBACK_FUNC,
	get_1accRole_1CALLBACK_FUNC,
	get_1accState_1CALLBACK_FUNC,
	get_1accValue_1CALLBACK_FUNC,
	put_1accName_1CALLBACK_FUNC,
	put_1accValue_1CALLBACK_FUNC,
} COM_FUNCS;
