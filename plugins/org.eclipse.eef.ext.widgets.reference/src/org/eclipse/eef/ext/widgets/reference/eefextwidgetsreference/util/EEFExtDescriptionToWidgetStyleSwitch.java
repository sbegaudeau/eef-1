/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.util;

import org.eclipse.eef.EEFWidgetStyle;
import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EEFExtReferenceDescription;

/**
 * Custom switch used to add support for the reference widget style.
 *
 * @author sbegaudeau
 */
public class EEFExtDescriptionToWidgetStyleSwitch extends EefExtWidgetsReferenceSwitch<EEFWidgetStyle> {
	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.util.EefExtWidgetsReferenceSwitch#caseEEFExtReferenceDescription(org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EEFExtReferenceDescription)
	 */
	@Override
	public EEFWidgetStyle caseEEFExtReferenceDescription(EEFExtReferenceDescription object) {
		return object.getStyle();
	}
}