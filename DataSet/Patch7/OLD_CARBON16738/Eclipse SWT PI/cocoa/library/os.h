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

#ifndef INC_os_H
#define INC_os_H

/*#define NDEBUG*/

#include <Cocoa/Cocoa.h>
#import <objc/objc-runtime.h>

#include "os_custom.h"

#ifndef __i386__
#define objc_msgSend_fpret objc_msgSend
#endif

#endif /* INC_os_H */
