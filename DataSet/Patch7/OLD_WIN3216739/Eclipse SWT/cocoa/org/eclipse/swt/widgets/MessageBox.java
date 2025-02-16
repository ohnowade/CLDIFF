/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.widgets;


import org.eclipse.swt.*;
import org.eclipse.swt.internal.cocoa.*;

/**
 * Instances of this class are used to inform or warn the user.
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>ICON_ERROR, ICON_INFORMATION, ICON_QUESTION, ICON_WARNING, ICON_WORKING</dd>
 * <dd>OK, OK | CANCEL</dd>
 * <dd>YES | NO, YES | NO | CANCEL</dd>
 * <dd>RETRY | CANCEL</dd>
 * <dd>ABORT | RETRY | IGNORE</dd>
 * <dt><b>Events:</b></dt>
 * <dd>(none)</dd>
 * </dl>
 * <p>
 * Note: Only one of the styles ICON_ERROR, ICON_INFORMATION, ICON_QUESTION,
 * ICON_WARNING and ICON_WORKING may be specified.
 * </p><p>
 * IMPORTANT: This class is intended to be subclassed <em>only</em>
 * within the SWT implementation.
 * </p>
 */
public  class MessageBox extends Dialog {
	String message = "";
	

/**
 * Constructs a new instance of this class given only its parent.
 *
 * @param parent a shell which will be the parent of the new instance
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 */
public MessageBox (Shell parent) {
	this (parent, SWT.OK | SWT.ICON_INFORMATION | SWT.APPLICATION_MODAL);
}

/**
 * Constructs a new instance of this class given its parent
 * and a style value describing its behavior and appearance.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together 
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * lists the style constants that are applicable to the class.
 * Style bits are also inherited from superclasses.
 *
 * @param parent a shell which will be the parent of the new instance
 * @param style the style of dialog to construct
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 */
public MessageBox (Shell parent, int style) {
	super (parent, checkStyle (style));
	checkSubclass ();
}

static int checkStyle (int style) {
	if ((style & (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL)) == 0) style |= SWT.APPLICATION_MODAL;
	int mask = (SWT.YES | SWT.NO | SWT.OK | SWT.CANCEL | SWT.ABORT | SWT.RETRY | SWT.IGNORE);
	int bits = style & mask;
	if (bits == SWT.OK || bits == SWT.CANCEL || bits == (SWT.OK | SWT.CANCEL)) return style;
	if (bits == SWT.YES || bits == SWT.NO || bits == (SWT.YES | SWT.NO) || bits == (SWT.YES | SWT.NO | SWT.CANCEL)) return style;
	if (bits == (SWT.RETRY | SWT.CANCEL) || bits == (SWT.ABORT | SWT.RETRY | SWT.IGNORE)) return style;
	style = (style & ~mask) | SWT.OK;
	return style;
}

/**
 * Returns the dialog's message, or an empty string if it does not have one.
 * The message is a description of the purpose for which the dialog was opened.
 * This message will be visible in the dialog while it is open.
 *
 * @return the message
 */
public String getMessage () {
	return message;
}

/**
 * Makes the dialog visible and brings it to the front
 * of the display.
 *
 * @return the ID of the button that was selected to dismiss the
 *         message box (e.g. SWT.OK, SWT.CANCEL, etc.)
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the dialog has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the dialog</li>
 * </ul>
 */
public int open () {
	int alertType = OS.NSInformationalAlertStyle;
	if ((style & SWT.ICON_ERROR) != 0) alertType = OS.NSCriticalAlertStyle;
	if ((style & SWT.ICON_INFORMATION) != 0) alertType = OS.NSInformationalAlertStyle;
	if ((style & SWT.ICON_QUESTION) != 0) alertType = OS.NSInformationalAlertStyle;
	if ((style & SWT.ICON_WARNING) != 0) alertType = OS.NSWarningAlertStyle;
	if ((style & SWT.ICON_WORKING) != 0) alertType = OS.NSInformationalAlertStyle;
	NSString defaultButton = null, alternateButton = null, otherButton = null;
	int mask = (SWT.YES | SWT.NO | SWT.OK | SWT.CANCEL | SWT.ABORT | SWT.RETRY | SWT.IGNORE);
	int bits = style & mask;
	switch (bits) {
		case SWT.OK:
			break;
		case SWT.CANCEL:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Cancel"));
			break;
		case SWT.OK | SWT.CANCEL:
			alternateButton = NSString.stringWith(SWT.getMessage("SWT_Cancel"));
			break;
		case SWT.YES:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Yes"));
			break;
		case SWT.NO:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_No"));
			break;
		case SWT.YES | SWT.NO:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Yes"));
			alternateButton = NSString.stringWith(SWT.getMessage("SWT_No"));
			break;
		case SWT.YES | SWT.NO | SWT.CANCEL:				
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Yes"));
			alternateButton = NSString.stringWith(SWT.getMessage("SWT_No"));
			otherButton = NSString.stringWith(SWT.getMessage("SWT_Cancel"));
			break;
		case SWT.RETRY | SWT.CANCEL:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Retry"));
			alternateButton = NSString.stringWith(SWT.getMessage("SWT_Cancel"));
			break;
		case SWT.ABORT | SWT.RETRY | SWT.IGNORE:
			defaultButton = NSString.stringWith(SWT.getMessage("SWT_Abort"));
			alternateButton = NSString.stringWith(SWT.getMessage("SWT_Retry"));
			otherButton = NSString.stringWith(SWT.getMessage("SWT_Ignore"));
			break;
	}
	NSString title = NSString.stringWith(this.title != null ? this.title : "");
	NSString message = NSString.stringWith(this.message != null ? this.message : "");
	NSAlert alert = NSAlert.alertWithMessageText(title, defaultButton, alternateButton, otherButton, message);
	alert.setAlertStyle(alertType);
	int response = alert.runModal();
	switch (bits) {
		case SWT.OK:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.OK;
			}
			break;
		case SWT.CANCEL:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.CANCEL;
			}
			break;
		case SWT.OK | SWT.CANCEL:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.OK;
				case OS.NSAlertAlternateReturn:
					return SWT.CANCEL;
			}
			break;
		case SWT.YES:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.YES;
			}
			break;
		case SWT.NO:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.NO;
			}
			break;
		case SWT.YES | SWT.NO:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.YES;
				case OS.NSAlertAlternateReturn:
					return SWT.NO;
			}
			break;
		case SWT.YES | SWT.NO | SWT.CANCEL:				
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.YES;
				case OS.NSAlertAlternateReturn:
					return SWT.NO;
				case OS.NSAlertOtherReturn:
					return SWT.CANCEL;
			}
			break;
		case SWT.RETRY | SWT.CANCEL:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.RETRY;
				case OS.NSAlertAlternateReturn:
					return SWT.CANCEL;
			}
			break;
		case SWT.ABORT | SWT.RETRY | SWT.IGNORE:
			switch (response) {
				case OS.NSAlertDefaultReturn:
					return SWT.ABORT;
				case OS.NSAlertAlternateReturn:
					return SWT.RETRY;
				case OS.NSAlertOtherReturn:
					return SWT.IGNORE;
			}
			break;
	}
	return SWT.CANCEL;
}

/**
 * Sets the dialog's message, which is a description of
 * the purpose for which it was opened. This message will be
 * visible on the dialog while it is open.
 *
 * @param string the message
 * 
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 * </ul>
 */
public void setMessage (String string) {
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	message = string;
}

}
