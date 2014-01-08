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
package org.eclipse.emf.eef.runtime.internal.policies;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.policies.EditingPolicyValidation;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

/**
 * A null implementation of the {@link PropertiesEditingPolicy} interface.
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class NullEditingPolicy implements PropertiesEditingPolicy {
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy#validateEditing(org.eclipse.emf.eef.runtime.policies.SemanticPropertiesEditingContext)
	 */
	public EditingPolicyValidation validateEditing(PropertiesEditingContext editingContext) {
		return new EditingPolicyValidation(this, true);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy#execute(org.eclipse.emf.eef.runtime.policies.SemanticPropertiesEditingContext)
	 */
	public void execute(PropertiesEditingContext editingContext) {
		// Do nothing
	}

}