/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.events;


import org.eclipse.swt.widgets.Event;

/**
 * Instances of this class are sent as a result of
 * keys being pressed and released on the keyboard.
 * <p>
 * When a key listener is added to a control, the control
 * is expected to take part in widget traversal.  By default,
 * all traversal keys (such as the tab key and so on) are
 * delivered to the control.  In order for a control to take
 * part in traversal, it should listen for traversal events.
 * Otherwise, the user can traverse into a control but not
 * out.  Note that native controls such as table and tree
 * implement key traversal in the operating system.  It is
 * not necessary to add traversal listeners for these controls,
 * unless you want to override the default traversal.
 * </p>
 * @see KeyListener
 * @see TraverseListener
 */

public class KeyEvent extends TypedEvent {
	
 	/**
 	 * the character represented by the key that was typed.  
	 * This is the final character that results after all modifiers have been
 	 * applied.  For example, when the user types Ctrl+A, the character value
 	 * is 0x01.  It is important that applications do not attempt to modify the
 	 * character value based on a stateMask (such as SWT.CTRL) or the resulting
 	 * character will not be correct.
 	 */
	public char character;
	
	/**
	 * the key code of the key that was typed,
	 * as defined by the key code constants in class <code>SWT</code>.
	 * When the character field of the event is ambiguous, this field
	 * contains the unicode value of the original character.  For example,
	 * typing Ctrl+M or Return both result in the character '\r' but the
	 * keyCode field will also contain '\r' when Return was typed.
	 * 
	 * @see org.eclipse.swt.SWT
	 */
	public int keyCode;
	
	/**
	 * the state of the keyboard modifier keys at the time
	 * the event was generated, as defined by the key code
	 * constants in class <code>SWT</code>.
	 * 
	 * @see org.eclipse.swt.SWT
	 */
	public int stateMask;
	
	/**
	 * A flag indicating whether the operation should be allowed.
	 * Setting this field to <code>false</code> will cancel the operation.
	 */
	public boolean doit;

	static final long serialVersionUID = 3256442491011412789L;
	
/**
 * Constructs a new instance of this class based on the
 * information in the given untyped event.
 *
 * @param e the untyped event containing the information
 */
public KeyEvent(Event e) {
	super(e);
	this.character = e.character;
	this.keyCode = e.keyCode;
	this.stateMask = e.stateMask;
	this.doit = e.doit;
}

/**
 * Returns a string containing a concise, human-readable
 * description of the receiver.
 *
 * @return a string representation of the event
 */
public String toString() {
	String string = super.toString ();
	return string.substring (0, string.length() - 1) // remove trailing '}'
		+ " character='" + ((character == 0) ? "\\0" : "" + character) + "'"
		+ " keyCode=" + keyCode
		+ " stateMask=" + stateMask
		+ " doit=" + doit
		+ "}";
}
}
