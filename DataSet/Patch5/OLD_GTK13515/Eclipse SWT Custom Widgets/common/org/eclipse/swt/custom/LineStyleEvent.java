/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

/**
 * This event is sent when a line is about to be drawn.
 */
public class LineStyleEvent extends TypedEvent {
	
	/**
	 * line start offset (input)
	 */
	public int lineOffset;
	
	/**
	 * line text (input)
	 */
	public String lineText;
	
	/**
	 * line ranges (output)
	 * 
	 * @since 3.2
	 */
	public int[] ranges;
	
	/**
	 * line styles (output)
	 * 
	 * Note: Because a StyleRange includes the start and length, the
	 * same instance cannot occur multiple times in the array of styles.
	 * If the same style attributes, such as font and color, occur in
	 * multiple StyleRanges, <code>ranges</code> can be used to share
	 * styles and reduce memory usage.
	 */
	public StyleRange[] styles;

	/** 
	 * line alignment (input, output)
	 * 
	 * @since 3.2
	 */
	public int alignment;

	/**
	 * line indent (input, output)
	 * 
	 * @since 3.2
	 */
	public int indent;

	/** 
	 * line justification (input, output)
	 * 
	 * @since 3.2
	 */
	public boolean justify;

	/**
	 * line bullet (output)
	 * @since 3.2
	 */
	public Bullet bullet;

	/**
	 * line bullet index (output)
	 * @since 3.2
	 */
	public int bulletIndex;
	
	static final long serialVersionUID = 3906081274027192884L;
	
public LineStyleEvent(StyledTextEvent e) {
	super(e);
	lineOffset = e.detail;
	lineText = e.text;
	alignment = e.alignment;
	justify = e.justify;
	indent = e.indent;
	bullet = e.bullet;
	bulletIndex = e.bulletIndex;
}
}
