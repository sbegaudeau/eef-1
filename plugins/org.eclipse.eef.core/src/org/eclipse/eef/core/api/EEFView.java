/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.core.api;

import java.util.List;

import org.eclipse.eef.EEFViewDescription;
import org.eclipse.emf.ecore.EObject;

/**
 * The view if the root concept of the EEF model.
 *
 * @author sbegaudeau
 */
public interface EEFView {

	/**
	 * Initializes the view by creating the pages and groups used to compute the tab and section descriptors.
	 */
	void initialize();

	/**
	 * Sets the input of the view.
	 * 
	 * @param eObject
	 *            The input
	 */
	void setInput(EObject eObject);

	/**
	 * Returns the description of the {@link EEFView}.
	 *
	 * @return The {@link EEFViewDescription}
	 */
	EEFViewDescription getDescription();

	/**
	 * Returns the {@link EEFPage} to display in the {@link EEFView}.
	 *
	 * @return The {@link EEFPage}
	 */
	List<EEFPage> getPages();
}