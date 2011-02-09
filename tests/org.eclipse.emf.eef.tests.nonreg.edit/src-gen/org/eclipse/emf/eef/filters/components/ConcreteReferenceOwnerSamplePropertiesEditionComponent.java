/*******************************************************************************
 * Copyright (c) 2009 - 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.filters.components;

// Start of user code for imports
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.eefnr.EefnrPackage;
import org.eclipse.emf.eef.eefnr.filters.AbstractReferenceTargetSample;
import org.eclipse.emf.eef.eefnr.filters.ConcreteReferenceOwnerSample;
import org.eclipse.emf.eef.eefnr.filters.FiltersPackage;
import org.eclipse.emf.eef.eefnr.filters.parts.ConcreteReferenceOwnerSamplePropertiesEditionPart;
import org.eclipse.emf.eef.eefnr.filters.parts.FiltersViewsRepository;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class ConcreteReferenceOwnerSamplePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for abstractTarget ReferencesTable
	 */
	private	ReferencesTableSettings abstractTargetSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ConcreteReferenceOwnerSamplePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject concreteReferenceOwnerSample, String editing_mode) {
		super(editingContext, concreteReferenceOwnerSample, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = FiltersViewsRepository.class;
		partKey = FiltersViewsRepository.ConcreteReferenceOwnerSample.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			final ConcreteReferenceOwnerSample concreteReferenceOwnerSample = (ConcreteReferenceOwnerSample)elt;
			final ConcreteReferenceOwnerSamplePropertiesEditionPart basePart = (ConcreteReferenceOwnerSamplePropertiesEditionPart)editingPart;
			// init values
			if (concreteReferenceOwnerSample.getName() != null)
				basePart.setName(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), concreteReferenceOwnerSample.getName()));
			
			abstractTargetSettings = new ReferencesTableSettings(concreteReferenceOwnerSample, FiltersPackage.eINSTANCE.getAbstractReferenceOwnerSample_AbstractTarget());
			basePart.initAbstractTarget(abstractTargetSettings);
			// init filters
			
			basePart.addFilterToAbstractTarget(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInAbstractTargetTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToAbstractTarget(new EObjectFilter(FiltersPackage.eINSTANCE.getAbstractReferenceTargetSample()));
						basePart.addFilterToAbstractTarget(new ViewerFilter() {
			
							/**
							 * {@inheritDoc}
							 * 
							 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
							 */
							public boolean select(Viewer viewer, Object parentElement, Object element) {
								Object result = EEFUtils.choiceOfValues(ConcreteReferenceOwnerSamplePropertiesEditionComponent.this.semanticObject, FiltersPackage.eINSTANCE.getConcreteReferenceOwnerSample_Target());
								if (result instanceof Collection) {
									return ((Collection)result).contains(element);
								} else if (result instanceof ResourceSet && element instanceof EObject) {
									return ((EObject)element).eResource() != null && ((EObject)element).eResource().getResourceSet() != null && ((EObject)element).eResource().getResourceSet().equals(result);
								}
								return element instanceof Resource;
							}
						});
			// Start of user code for additional businessfilters for abstractTarget
			
			// End of user code
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}





	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		ConcreteReferenceOwnerSample concreteReferenceOwnerSample = (ConcreteReferenceOwnerSample)semanticObject;
		if (FiltersViewsRepository.ConcreteReferenceOwnerSample.Properties.name == event.getAffectedEditor()) {
			concreteReferenceOwnerSample.setName((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
		}
		if (FiltersViewsRepository.ConcreteReferenceOwnerSample.Properties.abstractTarget == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof AbstractReferenceTargetSample) {
					abstractTargetSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					abstractTargetSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ConcreteReferenceOwnerSamplePropertiesEditionPart basePart = (ConcreteReferenceOwnerSamplePropertiesEditionPart)editingPart;
			if (EefnrPackage.eINSTANCE.getAbstractSample_Name().equals(msg.getFeature()) && basePart != null){
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (FiltersPackage.eINSTANCE.getAbstractReferenceOwnerSample_AbstractTarget().equals(msg.getFeature()))
				basePart.updateAbstractTarget();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (FiltersViewsRepository.ConcreteReferenceOwnerSample.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(EefnrPackage.eINSTANCE.getAbstractSample_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(EefnrPackage.eINSTANCE.getAbstractSample_Name().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

}