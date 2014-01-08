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
package org.eclipse.emf.eef.runtime.policies;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public interface EditingPolicyProcessorProvider {
	
	/**
	 * Returns a policy process able to handle the given context.
	 * @param editingContext the {@link PropertiesEditingContext} to handle.
	 * @return an {@link EditingPolicyProcessor} able to handle the context.
	 */
	EditingPolicyProcessor getProcessor(PropertiesEditingContext editingContext);

}