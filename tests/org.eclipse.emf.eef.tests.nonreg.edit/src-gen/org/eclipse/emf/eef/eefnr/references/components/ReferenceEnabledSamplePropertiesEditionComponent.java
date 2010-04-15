/*******************************************************************************
 * Copyright (c) 2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.eefnr.references.components;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.eefnr.references.ReferenceEnabledSample;
import org.eclipse.emf.eef.eefnr.references.parts.ReferenceEnabledSamplePropertiesEditionPart;
import org.eclipse.emf.eef.eefnr.references.parts.ReferencesViewsRepository;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionProvider;
import org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionComponentService;


// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class ReferenceEnabledSamplePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The Base part
	 * 
	 */
	private ReferenceEnabledSamplePropertiesEditionPart basePart;

	/**
	 * The ReferenceEnabledSampleBasePropertiesEditionComponent sub component
	 * 
	 */
	protected ReferenceEnabledSampleBasePropertiesEditionComponent referenceEnabledSampleBasePropertiesEditionComponent;
	/**
	 * The AbstractEnabledSamplePropertiesEditionComponent sub component
	 * 
	 */
	protected AbstractEnabledSamplePropertiesEditionComponent abstractEnabledSamplePropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param referenceEnabledSample the EObject to edit
	 * 
	 */
	public ReferenceEnabledSamplePropertiesEditionComponent(EObject referenceEnabledSample, String editing_mode) {
		super(editing_mode);
		if (referenceEnabledSample instanceof ReferenceEnabledSample) {
			IPropertiesEditionProvider provider = null;
			provider = PropertiesEditionComponentService.getInstance().getProvider(referenceEnabledSample, ReferenceEnabledSampleBasePropertiesEditionComponent.class);
			referenceEnabledSampleBasePropertiesEditionComponent = (ReferenceEnabledSampleBasePropertiesEditionComponent)provider.getPropertiesEditionComponent(referenceEnabledSample, editing_mode, ReferenceEnabledSampleBasePropertiesEditionComponent.BASE_PART, ReferenceEnabledSampleBasePropertiesEditionComponent.class);
			addSubComponent(referenceEnabledSampleBasePropertiesEditionComponent);
			provider = PropertiesEditionComponentService.getInstance().getProvider(referenceEnabledSample, AbstractEnabledSamplePropertiesEditionComponent.class);
			abstractEnabledSamplePropertiesEditionComponent = (AbstractEnabledSamplePropertiesEditionComponent)provider.getPropertiesEditionComponent(referenceEnabledSample, editing_mode, AbstractEnabledSampleBasePropertiesEditionComponent.BASE_PART, AbstractEnabledSamplePropertiesEditionComponent.class);
			addSubComponent(abstractEnabledSamplePropertiesEditionComponent);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      getPropertiesEditionPart(int, java.lang.String)
	 * 
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (ReferenceEnabledSampleBasePropertiesEditionComponent.BASE_PART.equals(key)) {
			basePart = (ReferenceEnabledSamplePropertiesEditionPart)referenceEnabledSampleBasePropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)basePart;
		}
		return super.getPropertiesEditionPart(kind, key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      setPropertiesEditionPart(java.lang.Class, int,
	 *      org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart)
	 * 
	 */
	public void setPropertiesEditionPart(java.lang.Class key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (ReferencesViewsRepository.ReferenceEnabledSample.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			basePart = (ReferenceEnabledSamplePropertiesEditionPart)propertiesEditionPart;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      initPart(java.lang.Class, int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(java.lang.Class key, int kind, EObject element, ResourceSet allResource) {
		if (key == ReferencesViewsRepository.ReferenceEnabledSample.class) {
			super.initPart(key, kind, element, allResource);
			abstractEnabledSamplePropertiesEditionComponent.setPropertiesEditionPart(ReferencesViewsRepository.AbstractEnabledSample.class, kind, basePart.getAbstractEnabledSampleReferencedView());
			abstractEnabledSamplePropertiesEditionComponent.initPart(ReferencesViewsRepository.AbstractEnabledSample.class, kind, element, allResource);
		}
		if (key == ReferencesViewsRepository.AbstractEnabledSample.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
