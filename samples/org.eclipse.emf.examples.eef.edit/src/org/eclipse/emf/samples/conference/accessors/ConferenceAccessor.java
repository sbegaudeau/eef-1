/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.samples.conference.accessors;

import org.eclipse.emf.eef.runtime.util.EEFInvocationParameters;
import org.eclipse.emf.samples.conference.Conference;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class ConferenceAccessor {

	public String getConferenceName(EEFInvocationParameters parameters) {
		StringBuilder builder = new StringBuilder("Conference: ");
		builder.append(((Conference)parameters.getEditedObject()).getName());
		return builder.toString();
	}
	
	
}
