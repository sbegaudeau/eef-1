/**
 *  Copyright (c) 2008-2010 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *      Obeo - initial API and implementation
 *
 */
package org.eclipse.emf.eef.components.providers;

import org.eclipse.emf.eef.runtime.impl.providers.ComposedPropertiesEditionProvider;

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ComponentsPackagePropertiesEditionProvider extends ComposedPropertiesEditionProvider {

	/**
	 * Default Constructor
	 */
	public ComponentsPackagePropertiesEditionProvider() {
		super();
		append(createPropertiesEditionContextPropertiesEditionProvider());
		append(createPropertiesEditionComponentPropertiesEditionProvider());
		append(createPropertiesEditionElementPropertiesEditionProvider());
		append(createPropertiesMultiEditionElementPropertiesEditionProvider());
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * PropertiesEditionContext instances.
	 */
	protected PropertiesEditionContextPropertiesEditionProvider propertiesEditionContextPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a PropertiesEditionContext
	 */
	public PropertiesEditionContextPropertiesEditionProvider createPropertiesEditionContextPropertiesEditionProvider() {
		if (propertiesEditionContextPropertiesEditionProvider == null)
			propertiesEditionContextPropertiesEditionProvider = new PropertiesEditionContextPropertiesEditionProvider();
		return propertiesEditionContextPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * PropertiesEditionComponent instances.
	 */
	protected PropertiesEditionComponentPropertiesEditionProvider propertiesEditionComponentPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a PropertiesEditionComponent
	 */
	public PropertiesEditionComponentPropertiesEditionProvider createPropertiesEditionComponentPropertiesEditionProvider() {
		if (propertiesEditionComponentPropertiesEditionProvider == null)
			propertiesEditionComponentPropertiesEditionProvider = new PropertiesEditionComponentPropertiesEditionProvider();
		return propertiesEditionComponentPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * PropertiesEditionElement instances.
	 */
	protected PropertiesEditionElementPropertiesEditionProvider propertiesEditionElementPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a PropertiesEditionElement
	 */
	public PropertiesEditionElementPropertiesEditionProvider createPropertiesEditionElementPropertiesEditionProvider() {
		if (propertiesEditionElementPropertiesEditionProvider == null)
			propertiesEditionElementPropertiesEditionProvider = new PropertiesEditionElementPropertiesEditionProvider();
		return propertiesEditionElementPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * PropertiesMultiEditionElement instances.
	 */
	protected PropertiesMultiEditionElementPropertiesEditionProvider propertiesMultiEditionElementPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a PropertiesMultiEditionElement
	 */
	public PropertiesMultiEditionElementPropertiesEditionProvider createPropertiesMultiEditionElementPropertiesEditionProvider() {
		if (propertiesMultiEditionElementPropertiesEditionProvider == null)
			propertiesMultiEditionElementPropertiesEditionProvider = new PropertiesMultiEditionElementPropertiesEditionProvider();
		return propertiesMultiEditionElementPropertiesEditionProvider;
	}

}