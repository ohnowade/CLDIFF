/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
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
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gtk.*;
import org.eclipse.swt.graphics.*;

public class ExpandItem extends Item {
	ExpandBar parent;
	Control control;
	ImageList imageList;
	int /*long*/ clientHandle, boxHandle, labelHandle, imageHandle;
	boolean expanded;
	int x, y, width, height;
	int imageHeight, imageWidth;
	static final int TEXT_INSET = 6;
	static final int BORDER = 1;
	static final int CHEVRON_SIZE = 24;

public ExpandItem (ExpandBar parent, int style) {
	super (parent, style);
	this.parent = parent;
	createWidget (parent.getItemCount ());
}

public ExpandItem (ExpandBar parent, int style, int index) {
	super (parent, style);
	this.parent = parent;
	createWidget (index);
}

protected void checkSubclass () {
	if (!isValidSubclass ()) error (SWT.ERROR_INVALID_SUBCLASS);
}

void createHandle (int index) {
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		state |= HANDLE;
		handle = OS.gtk_expander_new (null);
		if (handle == 0) error (SWT.ERROR_NO_HANDLES);
		clientHandle = OS.g_object_new (display.gtk_fixed_get_type (), 0);
		if (clientHandle == 0) error (SWT.ERROR_NO_HANDLES);
		OS.gtk_container_add (handle, clientHandle);	
		boxHandle = OS.gtk_hbox_new (false, 4);
		if (boxHandle == 0) error (SWT.ERROR_NO_HANDLES);
		labelHandle = OS.gtk_label_new (null);
		if (labelHandle == 0) error (SWT.ERROR_NO_HANDLES);
		imageHandle = OS.gtk_image_new ();
		if (imageHandle == 0) error (SWT.ERROR_NO_HANDLES);
		OS.gtk_container_add (boxHandle, imageHandle);
		OS.gtk_container_add (boxHandle, labelHandle);
		OS.gtk_expander_set_label_widget (handle, boxHandle);
		OS.GTK_WIDGET_SET_FLAGS (handle, OS.GTK_CAN_FOCUS);
	}
}

void createWidget (int index) {
	super.createWidget (index);
	showWidget (index);
	parent.createItem (this, style, index);
}

void deregister() {
	super.deregister();
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		display.removeWidget (clientHandle);
		display.removeWidget (boxHandle);
		display.removeWidget (labelHandle);
		display.removeWidget (imageHandle);
	}
}

void destroyWidget () {
	parent.destroyItem (this);
	super.destroyWidget ();
}

void drawChevron (GC gc, int x, int y) {
	int [] polyline1, polyline2;
	if (expanded) {
		int px = x + 4 + 5;
		int py = y + 4 + 7;
		polyline1 = new int [] {
				px,py, px+1,py, px+1,py-1, px+2,py-1, px+2,py-2, px+3,py-2, px+3,py-3,
				px+3,py-2, px+4,py-2, px+4,py-1, px+5,py-1, px+5,py, px+6,py};
		py += 4;
		polyline2 = new int [] {
				px,py, px+1,py, px+1,py-1, px+2,py-1, px+2,py-2, px+3,py-2, px+3,py-3,
				px+3,py-2, px+4,py-2, px+4,py-1,  px+5,py-1, px+5,py, px+6,py};
	} else {
		int px = x + 4 + 5;
		int py = y + 4 + 4;
		polyline1 = new int[] {
				px,py, px+1,py, px+1,py+1, px+2,py+1, px+2,py+2, px+3,py+2, px+3,py+3,
				px+3,py+2, px+4,py+2, px+4,py+1,  px+5,py+1, px+5,py, px+6,py};
		py += 4;
		polyline2 = new int [] {
				px,py, px+1,py, px+1,py+1, px+2,py+1, px+2,py+2, px+3,py+2, px+3,py+3,
				px+3,py+2, px+4,py+2, px+4,py+1,  px+5,py+1, px+5,py, px+6,py};
	}
	gc.setForeground (display.getSystemColor (SWT.COLOR_TITLE_FOREGROUND));
	gc.drawPolyline (polyline1);
	gc.drawPolyline (polyline2);
}

void drawItem (GC gc, boolean drawFocus) {
	int headerHeight = parent.getBandHeight ();
	Display display = getDisplay ();
	gc.setForeground (display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND));
	gc.setBackground (display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
	gc.fillGradientRectangle (x, y, width, headerHeight, true);
	if (expanded) {
		gc.setForeground (display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		gc.drawLine (x, y + headerHeight, x, y + headerHeight + height - 1);
		gc.drawLine (x, y + headerHeight + height - 1, x + width - 1, y + headerHeight + height - 1);
		gc.drawLine (x + width - 1, y + headerHeight + height - 1, x + width - 1, y + headerHeight);
	}
	int drawX = x;
	if (image != null) {
		drawX += ExpandItem.TEXT_INSET;
		if (imageHeight > headerHeight) {
			gc.drawImage (image, drawX, y + headerHeight - imageHeight);
		} else {
			gc.drawImage (image, drawX, y + (headerHeight - imageHeight) / 2);
		}
		drawX += imageWidth;
	}
	if (text.length() > 0) {
		drawX += ExpandItem.TEXT_INSET;
		Point size = gc.stringExtent (text);
		gc.setForeground (parent.getForeground ());
		gc.drawString (text, drawX, y + (headerHeight - size.y) / 2, true);
	}
	int chevronSize = ExpandItem.CHEVRON_SIZE;
	drawChevron (gc, x + width - chevronSize, y + (headerHeight - chevronSize) / 2);
	if (drawFocus) {
		gc.drawFocus (x + 1, y + 1, width - 2, headerHeight - 2);
	}
}

public Control getControl () {
	checkWidget ();
	return control;
}

public boolean getExpanded () {
	checkWidget ();
	return expanded;
}

public int getHeaderHeight () {
	checkWidget ();
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		int /*long*/ widget = OS.gtk_expander_get_label_widget (handle);
		return OS.GTK_WIDGET_HEIGHT (widget);
	}
	return Math.max (parent.getBandHeight (), imageHeight);
}

public int getHeight () {
	checkWidget ();
	return height;
}

public ExpandBar getParent () {
	checkWidget();
	return parent;
}

int getPreferredWidth (GC gc) {
	int width = ExpandItem.TEXT_INSET * 2 + ExpandItem.CHEVRON_SIZE;
	if (image != null) {
		width += ExpandItem.TEXT_INSET + imageWidth;
	}
	if (text.length() > 0) {
		width += gc.stringExtent (text).x;
	}
	return width;
}

int /*long*/ gtk_activate (int /*long*/ widget) {
	Event event = new Event ();
	event.item = this;
	int type = OS.gtk_expander_get_expanded (handle) ? SWT.Collapse : SWT.Expand;
	parent.sendEvent (type, event);
	return 0;
}

int /*long*/ gtk_button_press_event (int /*long*/ widget, int /*long*/ event) {
	setFocus ();
	return 0;
}

int /*long*/ gtk_focus_out_event (int /*long*/ widget, int /*long*/ event) {
	OS.GTK_WIDGET_UNSET_FLAGS (handle, OS.GTK_CAN_FOCUS);
	parent.lastFocus = this;
	return 0;
}

int /*long*/ gtk_size_allocate (int /*long*/ widget, int /*long*/ allocation) {
	parent.layoutItems (0, false);
	return 0;
}

boolean hasFocus () {
	return OS.GTK_WIDGET_HAS_FOCUS (handle);
}

void hookEvents () {
	super.hookEvents ();
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		OS.g_signal_connect_closure (handle, OS.activate, display.closures [ACTIVATE], false);
		OS.g_signal_connect_closure (handle, OS.activate, display.closures [ACTIVATE_INVERSE], true);
		OS.g_signal_connect_closure_by_id (handle, display.signalIds [BUTTON_PRESS_EVENT], 0, display.closures [BUTTON_PRESS_EVENT], false);
		OS.g_signal_connect_closure_by_id (handle, display.signalIds [FOCUS_OUT_EVENT], 0, display.closures [FOCUS_OUT_EVENT], false);
		OS.g_signal_connect_closure (clientHandle, OS.size_allocate, display.closures [SIZE_ALLOCATE], true);
	}
}

void redraw () {
	if (OS.GTK_VERSION < OS.VERSION (2, 4, 0)) {
		int headerHeight = parent.getBandHeight ();
		if (imageHeight > headerHeight) {
			parent.redraw (x + ExpandItem.TEXT_INSET, y + headerHeight - imageHeight, imageWidth, imageHeight, false);
		}
		parent.redraw (x, y, width, headerHeight + height, false);
	}
}

void register () {
	super.register ();
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		display.addWidget (clientHandle, this);
		display.addWidget (boxHandle, this);
		display.addWidget (labelHandle, this);
		display.addWidget (imageHandle, this);
	}
}

void releaseHandle () {
	super.releaseHandle ();
	clientHandle = boxHandle = labelHandle = imageHandle = 0;
	parent = null;
}

void releaseWidget () {
	super.releaseWidget ();
	if (imageList != null) imageList.dispose ();
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		if (parent.lastFocus == this) parent.lastFocus = null;
	}
	imageList = null;
	control = null;
}

void resizeControl (int yScroll) {
	if (control != null && !control.isDisposed ()) {
		boolean visible = OS.gtk_expander_get_expanded (handle);
		if (visible) {
			int x = OS.GTK_WIDGET_X (clientHandle);
			int y = OS.GTK_WIDGET_Y (clientHandle);
			if (x != -1 && y != -1) {
				int width = OS.GTK_WIDGET_WIDTH (clientHandle);
				int height = OS.GTK_WIDGET_HEIGHT (clientHandle);
				int [] property = new int [1];
				OS.gtk_widget_style_get (handle, OS.focus_line_width, property, 0);				
				y += property [0] * 2;
				height -= property [0] * 2;
				control.setBounds (x, y - yScroll, width, Math.max (0, height), true, true);
			}
		}
		control.setVisible (visible);
	}
}

void setBounds (int x, int y, int width, int height, boolean move, boolean size) {
	redraw ();
	int headerHeight = parent.getBandHeight ();
	if (move) {
		if (imageHeight > headerHeight) {
			y += (imageHeight - headerHeight);
		}
		this.x = x;
		this.y = y;
		redraw ();
	}
	if (size) {
		this.width = width;
		this.height = height;
		redraw ();
	}
	if (control != null && !control.isDisposed ()) {
		if (move) control.setLocation (x + BORDER, y + headerHeight);
		if (size) control.setSize (Math.max (0, width - 2 * BORDER), Math.max (0, height - BORDER));
	}
}

public void setControl (Control control) {
	checkWidget ();
	if (control != null) {
		if (control.isDisposed ()) error (SWT.ERROR_INVALID_ARGUMENT);
		if (control.parent != parent) error (SWT.ERROR_INVALID_PARENT);
	}
	if (this.control == control) return;
	this.control = control;
	if (control != null) {
		control.setVisible (expanded);
		if (OS.GTK_VERSION < OS.VERSION (2, 4, 0)) {
			int headerHeight = parent.getBandHeight ();
			control.setBounds (x + BORDER, y + headerHeight, Math.max (0, width - 2 * BORDER), Math.max (0, height - BORDER));
		}
	}
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		parent.layoutItems (0, true);
	}
}

public void setExpanded (boolean expanded) {
	checkWidget ();
	this.expanded = expanded;
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		OS.gtk_expander_set_expanded (handle, expanded);
		parent.layoutItems (0, true);
	} else {
		parent.showItem (this);
	}
}

boolean setFocus () {
	if (!OS.gtk_widget_get_child_visible (handle)) return false;
	OS.GTK_WIDGET_SET_FLAGS (handle, OS.GTK_CAN_FOCUS);
	OS.gtk_widget_grab_focus (handle);
	boolean result = OS.gtk_widget_is_focus (handle);
	if (!result) OS.GTK_WIDGET_UNSET_FLAGS (handle, OS.GTK_CAN_FOCUS);
	return result;
}

void setFontDescription (int /*long*/ font) {
	OS.gtk_widget_modify_font (handle, font);
	if (labelHandle != 0) OS.gtk_widget_modify_font (labelHandle, font);
	if (imageHandle != 0) OS.gtk_widget_modify_font (imageHandle, font);
}

void setForegroundColor (GdkColor color) {
	OS.gtk_widget_modify_fg (handle,  OS.GTK_STATE_NORMAL, color);
	if (labelHandle != 0) OS.gtk_widget_modify_fg (labelHandle,  OS.GTK_STATE_NORMAL, color);
	if (imageHandle != 0) OS.gtk_widget_modify_fg (imageHandle,  OS.GTK_STATE_NORMAL, color);
}

public void setHeight (int height) {
	checkWidget ();
	if (height < 0) return;
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		this.height = height;
		OS.gtk_widget_set_size_request (clientHandle, -1, height);
		parent.layoutItems (0, false);
	} else {
		setBounds (0, 0, width, height, false, true);
		if (expanded) parent.layoutItems (parent.indexOf (this) + 1, true);
	}
}

public void setImage (Image image) {
	super.setImage (image);
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		if (imageList != null) imageList.dispose ();
		imageList = null;
		if (image != null) {
			if (image.isDisposed ()) error (SWT.ERROR_INVALID_ARGUMENT);
			imageList = new ImageList ();
			int imageIndex = imageList.add (image);
			int /*long*/ pixbuf = imageList.getPixbuf (imageIndex);
			OS.gtk_image_set_from_pixbuf (imageHandle, pixbuf);
			if (text.length () == 0) OS.gtk_widget_hide (labelHandle);
			OS.gtk_widget_show (imageHandle);
		} else {
			OS.gtk_image_set_from_pixbuf (imageHandle, 0);
			OS.gtk_widget_show (labelHandle);
			OS.gtk_widget_hide (imageHandle);
		}
	} else {
		int oldImageHeight = imageHeight;
		if (image != null) {
			Rectangle bounds = image.getBounds ();
			imageHeight = bounds.height;
			imageWidth = bounds.width;
		} else {
			imageHeight = imageWidth = 0;
		}
		if (oldImageHeight != imageHeight) {
			parent.layoutItems (parent.indexOf (this), true);
		} else {
			redraw ();
		}
	}
}

public void setText (String string) {
	super.setText (string);
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		byte [] buffer = Converter.wcsToMbcs (null, string, true);
		OS.gtk_label_set_text (labelHandle, buffer);
	} else {
		redraw ();
	}
}

void showWidget (int index) {
	if (OS.GTK_VERSION >= OS.VERSION (2, 4, 0)) {
		OS.gtk_widget_show (handle);
		OS.gtk_widget_show (clientHandle);
		OS.gtk_container_add (parent.handle, handle);
		OS.gtk_box_set_child_packing (parent.handle, handle, false, false, 0, OS.GTK_PACK_START);
		if (boxHandle != 0) OS.gtk_widget_show (boxHandle);
		if (labelHandle != 0) OS.gtk_widget_show (labelHandle);
	}
}

int /*long*/ windowProc (int /*long*/ handle, int /*long*/ user_data) {
	switch ((int)/*64*/user_data) {
		case ACTIVATE_INVERSE: {
			expanded = OS.gtk_expander_get_expanded (handle);
			parent.layoutItems (0, false);
			return 0;
		}
	}
	return super.windowProc (handle, user_data);
}
}