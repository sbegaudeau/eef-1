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
package org.eclipse.emf.eef.mapping.components;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.mapping.filters.StrictTypingFilter;
import org.eclipse.emf.eef.mapping.parts.MappingViewsRepository;
import org.eclipse.emf.eef.mapping.parts.StrictTypingFilterPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.components.impl.ComposedPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.parts.PropertiesEditingPart;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;


// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class StrictTypingFilterFilterPropertiesEditionComponent extends ComposedPropertiesEditingComponent {

	/**
	 * The StrictTypingFilter part
	 * 
	 */
	private StrictTypingFilterPropertiesEditionPart strictTypingFilterPart;

	/**
	 * The StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent sub component
	 * 
	 */
	protected StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent strictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent;
	/**
	 * The DocumentedElementPropertiesEditionComponent sub component
	 * 
	 */
	protected DocumentedElementPropertiesEditionComponent documentedElementPropertiesEditionComponent;

	/**
	 * The FilterPropertiesPropertiesEditionComponent sub component
	 * 
	 */
	protected FilterPropertiesPropertiesEditionComponent filterPropertiesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param strictTypingFilter the EObject to edit
	 * 
	 */
	public StrictTypingFilterFilterPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject strictTypingFilter, String editing_mode) {
		super(editingContext, editing_mode);
		if (strictTypingFilter instanceof StrictTypingFilter) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(strictTypingFilter, PropertiesEditingProvider.class);
			strictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent = (StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent.STRICTTYPINGFILTER_PART, StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent.class);
			addSubComponent(strictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(strictTypingFilter, PropertiesEditingProvider.class);
			documentedElementPropertiesEditionComponent = (DocumentedElementPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, DocumentedElementPropertiesEditionComponent.DOCUMENTATION_PART, DocumentedElementPropertiesEditionComponent.class);
			addSubComponent(documentedElementPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(strictTypingFilter, PropertiesEditingProvider.class);
			filterPropertiesPropertiesEditionComponent = (FilterPropertiesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, FilterPropertiesPropertiesEditionComponent.FILTERPROPERTIES_PART, FilterPropertiesPropertiesEditionComponent.class);
			addSubComponent(filterPropertiesPropertiesEditionComponent);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.components.impl.ComposedPropertiesEditingComponent#
	 *      getPropertiesEditingPart(int, java.lang.String)
	 * 
	 */
	public PropertiesEditingPart getPropertiesEditingPart(int kind, String key) {
		if (StrictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent.STRICTTYPINGFILTER_PART.equals(key)) {
			strictTypingFilterPart = (StrictTypingFilterPropertiesEditionPart)strictTypingFilterFilterStrictTypingFilterPropertiesEditionComponent.getPropertiesEditingPart(kind, key);
			return (PropertiesEditingPart)strictTypingFilterPart;
		}
		return super.getPropertiesEditingPart(kind, key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.components.impl.ComposedPropertiesEditingComponent#
	 *      setPropertiesEditingPart(java.lang.Object, int,
	 *      org.eclipse.emf.eef.runtime.parts.PropertiesEditingPart)
	 * 
	 */
	public void setPropertiesEditingPart(java.lang.Object key, int kind, PropertiesEditingPart propertiesEditionPart) {
		if (MappingViewsRepository.StrictTypingFilter.class == key) {
			super.setPropertiesEditingPart(key, kind, propertiesEditionPart);
			strictTypingFilterPart = (StrictTypingFilterPropertiesEditionPart)propertiesEditionPart;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.components.impl.ComposedPropertiesEditingComponent#
	 *      initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(java.lang.Object key, int kind, EObject element, ResourceSet allResource) {
		if (key == MappingViewsRepository.StrictTypingFilter.class) {
			super.initPart(key, kind, element, allResource);
			filterPropertiesPropertiesEditionComponent.setPropertiesEditingPart(MappingViewsRepository.FilterProperties.class, kind, strictTypingFilterPart.getFilterPropertiesReferencedView());
			filterPropertiesPropertiesEditionComponent.initPart(MappingViewsRepository.FilterProperties.class, kind, element, allResource);
		}
		if (key == MappingViewsRepository.Documentation.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == MappingViewsRepository.FilterProperties.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}