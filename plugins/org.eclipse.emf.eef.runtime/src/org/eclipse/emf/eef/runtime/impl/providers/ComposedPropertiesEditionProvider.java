/*******************************************************************************
 * Copyright (c) 2008-2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.impl.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionProvider;

/**
 * An implementation of {@link IPropertiesEditionProvider} composing several
 * {@link IPropertiesEditionProvider}.
 * 
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class ComposedPropertiesEditionProvider implements IPropertiesEditionProvider {

	/**
	 * The managed providers.
	 */
	private List editPropertiesProviders;

	/**
	 * Default constructor.
	 */
	public ComposedPropertiesEditionProvider() {
		editPropertiesProviders = new ArrayList();
	}

	/**
	 * Constructor with initialized providers.
	 * 
	 * @param editPropertiesProviders
	 *            the initialized providers
	 */
	public ComposedPropertiesEditionProvider(List editPropertiesProviders) {
		this.editPropertiesProviders = editPropertiesProviders;
	}

	/**
	 * Append a IEditPropertiesProvider to the list
	 * 
	 * @param editPropertiesProvider
	 *            the IEditPropertiesProvider to add
	 */
	public void append(IPropertiesEditionProvider editPropertiesProvider) {
		editPropertiesProviders.add(editPropertiesProvider);
	}

	/**
	 * @param clazz
	 * @param editPropertiesProvider
	 */
	public void replace(Class clazz, IPropertiesEditionProvider editPropertiesProvider) {
		for (int i = 0; i < editPropertiesProviders.size(); i++) {
			IPropertiesEditionProvider provider = (IPropertiesEditionProvider)editPropertiesProviders.get(i);
			if (clazz.isInstance(provider))
				editPropertiesProviders.set(i, editPropertiesProvider);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionProvider#provides(org.eclipse.emf.ecore.EObject)
	 */
	public boolean provides(EObject eObject) {
		for (Iterator iter = editPropertiesProviders.iterator(); iter.hasNext();) {
			IPropertiesEditionProvider editPropertiesProvider = (IPropertiesEditionProvider)iter.next();
			if (editPropertiesProvider.provides(eObject))
				return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionProvider#getPropertiesEditionComponent(org.eclipse.emf.ecore.EObject,
	 *      java.lang.String)
	 */
	public IPropertiesEditionComponent getPropertiesEditionComponent(EObject eObject, String mode) {
		for (Iterator iter = editPropertiesProviders.iterator(); iter.hasNext();) {
			IPropertiesEditionProvider editPropertiesProvider = (IPropertiesEditionProvider)iter.next();
			if (editPropertiesProvider.provides(eObject))
				return editPropertiesProvider.getPropertiesEditionComponent(eObject, mode);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionProvider#getPropertiesEditionComponent(org.eclipse.emf.ecore.EObject,
	 *      java.lang.String, java.lang.String)
	 */
	public IPropertiesEditionComponent getPropertiesEditionComponent(EObject eObject, String mode, String part) {
		for (Iterator iter = editPropertiesProviders.iterator(); iter.hasNext();) {
			IPropertiesEditionProvider editPropertiesProvider = (IPropertiesEditionProvider)iter.next();
			if (editPropertiesProvider.provides(eObject))
				return editPropertiesProvider.getPropertiesEditionComponent(eObject, mode, part);
		}
		return null;
	}

}
