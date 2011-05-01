/*******************************************************************************
 * Copyright (c) 2008, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.context.impl;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.eef.runtime.components.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.settings.EEFEditorSettings;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class EReferencePropertiesEditingContext extends EObjectPropertiesEditingContext {

	/**
	 * the EReference to edit
	 */
	protected EReference eReference;
	
	/**
	 * EEF editor settings to use.
	 */
	private EEFEditorSettings settings;

	/**
	 * @param propertiesEditingComponent
	 * @param eReference
	 * @param resourceSet
	 * @deprecated use EReferencePropertiesEditingContext(PropertiesEditingContext, PropertiesEditingComponent, ReferencesTableSettings settings, AdapterFactory adapterFactory) instead
	 */
	public EReferencePropertiesEditingContext(PropertiesEditingContext parentContext, PropertiesEditingComponent propertiesEditingComponent, EObject eObject, EReference eReference, AdapterFactory adapterFactory) {
		super(parentContext, propertiesEditingComponent, eObject, adapterFactory);
		this.eReference = eReference;
	}

	/**
	 * @param parentContext
	 * @param propertiesEditingComponent
	 * @param settings
	 * @param adapterFactory
	 */
	public EReferencePropertiesEditingContext(PropertiesEditingContext parentContext, PropertiesEditingComponent propertiesEditingComponent, EEFEditorSettings settings, AdapterFactory adapterFactory) {
		super(parentContext, propertiesEditingComponent, settings.getSource(), adapterFactory);
		this.settings = settings;
		this.eReference = settings.getLastReference();
	}

	/**
	 * @return the eReference
	 */
	public EReference getEReference() {
		return eReference;
	}

	/**
	 * @return the settings to use.
	 */
	public EEFEditorSettings getSettings() {
		return settings;
	}
	
}
