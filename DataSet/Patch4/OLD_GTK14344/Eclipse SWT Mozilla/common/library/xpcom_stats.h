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
extern int XPCOM_nativeFunctionCount;
extern int XPCOM_nativeFunctionCallCount[];
extern char* XPCOM_nativeFunctionNames[];
#define XPCOM_NATIVE_ENTER(env, that, func) XPCOM_nativeFunctionCallCount[func]++;
#define XPCOM_NATIVE_EXIT(env, that, func) 
#else
#define XPCOM_NATIVE_ENTER(env, that, func) 
#define XPCOM_NATIVE_EXIT(env, that, func) 
#endif

typedef enum {
	Call_FUNC,
	NS_1GetComponentManager_FUNC,
	NS_1GetServiceManager_FUNC,
	NS_1InitEmbedding_FUNC,
	NS_1NewLocalFile_FUNC,
	NS_1TermEmbedding_FUNC,
	PR_1Free_FUNC,
	PR_1Malloc_FUNC,
	VtblCall__II_FUNC,
	VtblCall__IIF_FUNC,
	VtblCall__III_FUNC,
	VtblCall__IIII_FUNC,
	VtblCall__IIIII_FUNC,
	VtblCall__IIIIII_FUNC,
	VtblCall__IIIIIII_FUNC,
	VtblCall__IIIIIIII_FUNC,
	VtblCall__IIIIIIJII_FUNC,
	VtblCall__IIIIIIZ_FUNC,
	VtblCall__IIIIIZ_FUNC,
	VtblCall__IIIIIZ_3CIIIIZ_3I_3I_FUNC,
	VtblCall__IIIII_3C_FUNC,
	VtblCall__IIIII_3I_FUNC,
	VtblCall__IIIIJJJJ_FUNC,
	VtblCall__IIIIJZ_FUNC,
	VtblCall__IIIIZ_FUNC,
	VtblCall__IIII_3C_FUNC,
	VtblCall__IIII_3CIJI_FUNC,
	VtblCall__IIII_3I_FUNC,
	VtblCall__IIII_3J_FUNC,
	VtblCall__IIIJJ_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_mozilla_nsID_2_FUNC,
	VtblCall__IIILorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__IIIZ_FUNC,
	VtblCall__IIIZZ_FUNC,
	VtblCall__IIIZZII_FUNC,
	VtblCall__IIIZZIIIIIIZZZZSI_FUNC,
	VtblCall__IIIZ_3Z_FUNC,
	VtblCall__III_3B_FUNC,
	VtblCall__III_3BI_3I_FUNC,
	VtblCall__III_3BZ_FUNC,
	VtblCall__III_3B_3B_3BI_3I_FUNC,
	VtblCall__III_3B_3C_FUNC,
	VtblCall__III_3B_3Z_FUNC,
	VtblCall__III_3C_FUNC,
	VtblCall__III_3CI_FUNC,
	VtblCall__III_3C_3C_FUNC,
	VtblCall__III_3C_3CI_3C_3C_3C_3C_3Z_3I_FUNC,
	VtblCall__III_3C_3CI_3I_3I_3Z_FUNC,
	VtblCall__III_3C_3C_3C_3Z_FUNC,
	VtblCall__III_3C_3C_3C_3Z_3Z_FUNC,
	VtblCall__III_3C_3C_3I_FUNC,
	VtblCall__III_3C_3C_3I_3C_3Z_3Z_FUNC,
	VtblCall__III_3C_3C_3I_3I_3C_3Z_3Z_FUNC,
	VtblCall__III_3C_3C_3Z_FUNC,
	VtblCall__III_3I_FUNC,
	VtblCall__III_3I_3I_3I_3I_FUNC,
	VtblCall__III_3Z_FUNC,
	VtblCall__IIJ_FUNC,
	VtblCall__IIJI_FUNC,
	VtblCall__IIJJ_FUNC,
	VtblCall__IIJJI_FUNC,
	VtblCall__IIJJJJJ_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2ILorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2Lorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2Lorg_eclipse_swt_internal_mozilla_nsID_2_3Z_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2_3B_3BI_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2_3B_3BI_3B_3B_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__IILorg_eclipse_swt_internal_mozilla_nsID_2_3Z_FUNC,
	VtblCall__IIZ_FUNC,
	VtblCall__IIZI_FUNC,
	VtblCall__IIZ_3Z_FUNC,
	VtblCall__II_3B_FUNC,
	VtblCall__II_3BI_FUNC,
	VtblCall__II_3BILorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__II_3BI_3I_FUNC,
	VtblCall__II_3BJ_FUNC,
	VtblCall__II_3BLorg_eclipse_swt_internal_mozilla_nsID_2I_FUNC,
	VtblCall__II_3BLorg_eclipse_swt_internal_mozilla_nsID_2_3I_FUNC,
	VtblCall__II_3BLorg_eclipse_swt_internal_mozilla_nsID_2_3Z_FUNC,
	VtblCall__II_3BZI_3I_3Z_FUNC,
	VtblCall__II_3BZ_3I_3Z_FUNC,
	VtblCall__II_3B_3B_FUNC,
	VtblCall__II_3B_3BZ_FUNC,
	VtblCall__II_3B_3B_3BZZ_3I_FUNC,
	VtblCall__II_3B_3B_3I_FUNC,
	VtblCall__II_3B_3I_FUNC,
	VtblCall__II_3B_3I_3I_FUNC,
	VtblCall__II_3B_3I_3Z_FUNC,
	VtblCall__II_3B_3J_FUNC,
	VtblCall__II_3B_3Z_FUNC,
	VtblCall__II_3B_3Z_3I_FUNC,
	VtblCall__II_3C_FUNC,
	VtblCall__II_3CIIII_FUNC,
	VtblCall__II_3CI_3I_FUNC,
	VtblCall__II_3CZ_FUNC,
	VtblCall__II_3C_3C_FUNC,
	VtblCall__II_3C_3C_3CZ_FUNC,
	VtblCall__II_3C_3Z_FUNC,
	VtblCall__II_3F_FUNC,
	VtblCall__II_3I_FUNC,
	VtblCall__II_3I_3I_FUNC,
	VtblCall__II_3I_3I_3I_FUNC,
	VtblCall__II_3I_3I_3I_3I_FUNC,
	VtblCall__II_3I_3J_FUNC,
	VtblCall__II_3I_3J_3I_FUNC,
	VtblCall__II_3J_FUNC,
	VtblCall__II_3S_FUNC,
	VtblCall__II_3Z_FUNC,
	memmove__ILorg_eclipse_swt_internal_mozilla_nsID_2I_FUNC,
	memmove__I_3BI_FUNC,
	memmove__I_3CI_FUNC,
	memmove__I_3II_FUNC,
	memmove__I_3JI_FUNC,
	memmove__Lorg_eclipse_swt_internal_mozilla_nsID_2II_FUNC,
	memmove___3BII_FUNC,
	memmove___3B_3CI_FUNC,
	memmove___3CII_FUNC,
	memmove___3III_FUNC,
	memmove___3JII_FUNC,
	nsEmbedCString_1Length_FUNC,
	nsEmbedCString_1delete_FUNC,
	nsEmbedCString_1get_FUNC,
	nsEmbedCString_1new___FUNC,
	nsEmbedCString_1new___3BI_FUNC,
	nsEmbedString_1Length_FUNC,
	nsEmbedString_1delete_FUNC,
	nsEmbedString_1get_FUNC,
	nsEmbedString_1new___FUNC,
	nsEmbedString_1new___3C_FUNC,
	nsID_1Equals_FUNC,
	nsID_1Parse_FUNC,
	nsID_1delete_FUNC,
	nsID_1new_FUNC,
	strlen_FUNC,
	strlen_1PRUnichar_FUNC,
} XPCOM_FUNCS;
