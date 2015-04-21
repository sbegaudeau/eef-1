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
package org.eclipse.emf.eef.runtime.ui.view.propertyeditors;

import java.util.Collection;

import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorFactory.PropertyEditorContext;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.EEFToolkitImpl;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 * 
 */
public interface EEFToolkitProvider {

	/**
	 * @param editorContext
	 *            The {@link PropertyEditorContext} to process.
	 * @return a {@link EEFToolkitImpl} able to process the given editor
	 *         context.
	 */
	<T> EEFToolkit<T> getToolkit(PropertyEditorContext editorContext);

	/**
	 * Returns all registered toolkits.
	 * 
	 * @return a {@link Collection} of all registered {@link EEFToolkit}.
	 */
	Collection<EEFToolkit<?>> getAllToolkits();

	// ToolkitHandler createHandler(ResourceSet resourceSet);
}
