/**
 *  Copyright (c) 2008 - 2010 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *      Obeo - initial API and implementation
 *
 */
package org.eclipse.emf.eef.mapping.components;

// Start of user code for imports

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.mapping.navigation.NavigationPackage;
import org.eclipse.emf.eef.mapping.navigation.SimpleModelNavigation;
import org.eclipse.emf.eef.mapping.parts.MappingViewsRepository;
import org.eclipse.emf.eef.mapping.parts.SimpleModelNavigationPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class SimpleModelNavigationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for feature EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings featureSettings;
	
	/**
	 * Settings for discriminatorType EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings discriminatorTypeSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public SimpleModelNavigationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject simpleModelNavigation, String editing_mode) {
		super(editingContext, simpleModelNavigation, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = MappingViewsRepository.class;
		partKey = MappingViewsRepository.SimpleModelNavigation.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Class, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(java.lang.Class key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			final SimpleModelNavigation simpleModelNavigation = (SimpleModelNavigation)elt;
			final SimpleModelNavigationPropertiesEditionPart basePart = (SimpleModelNavigationPropertiesEditionPart)editingPart;
			// init values
			basePart.setIndex(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEInt(), simpleModelNavigation.getIndex()));
			
			// init part
			featureSettings = new EObjectFlatComboSettings(simpleModelNavigation, NavigationPackage.eINSTANCE.getSimpleModelNavigation_Feature());
			basePart.initFeature(featureSettings);
			// set the button mode
			basePart.setFeatureButtonMode(ButtonsModeEnum.BROWSE);
			// init part
			discriminatorTypeSettings = new EObjectFlatComboSettings(simpleModelNavigation, NavigationPackage.eINSTANCE.getSimpleModelNavigation_DiscriminatorType());
			basePart.initDiscriminatorType(discriminatorTypeSettings);
			// set the button mode
			basePart.setDiscriminatorTypeButtonMode(ButtonsModeEnum.BROWSE);
			// init filters
			
			basePart.addFilterToFeature(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof EReference);
				}
			
			});
			// Start of user code for additional businessfilters for feature
																																																			
																																																			// End of user code
			
			basePart.addFilterToDiscriminatorType(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof EClassifier); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for discriminatorType
																																																			
																																																			// End of user code
			
		}
		// init values for referenced views
		
		// init filters for referenced views
		
		setInitializing(false);
	}






	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		SimpleModelNavigation simpleModelNavigation = (SimpleModelNavigation)semanticObject;
		if (MappingViewsRepository.SimpleModelNavigation.Properties.index == event.getAffectedEditor()) {
			simpleModelNavigation.setIndex((EEFConverterUtil.createIntFromString(EcorePackage.eINSTANCE.getEInt(), (String)event.getNewValue())));
		}
		if (MappingViewsRepository.SimpleModelNavigation.Properties.feature == event.getAffectedEditor()) {
			featureSettings.setToReference((EReference)event.getNewValue());
		}
		if (MappingViewsRepository.SimpleModelNavigation.Properties.discriminatorType == event.getAffectedEditor()) {
			discriminatorTypeSettings.setToReference((EClassifier)event.getNewValue());
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		SimpleModelNavigationPropertiesEditionPart basePart = (SimpleModelNavigationPropertiesEditionPart)editingPart;
		if (NavigationPackage.eINSTANCE.getSimpleModelNavigation_Index().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setIndex(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEInt(), msg.getNewValue()));
			} else {
				basePart.setIndex("");
			}
		}
		if (NavigationPackage.eINSTANCE.getSimpleModelNavigation_Feature().equals(msg.getFeature()) && basePart != null)
			basePart.setFeature((EObject)msg.getNewValue());
		if (NavigationPackage.eINSTANCE.getSimpleModelNavigation_DiscriminatorType().equals(msg.getFeature()) && basePart != null)
			basePart.setDiscriminatorType((EObject)msg.getNewValue());
		
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.String, int)
	 * 
	 */
	public boolean isRequired(String key, int kind) {
		return key == MappingViewsRepository.SimpleModelNavigation.Properties.feature;
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
			String newStringValue = event.getNewValue().toString();
			try {
				if (MappingViewsRepository.SimpleModelNavigation.Properties.index == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(NavigationPackage.eINSTANCE.getSimpleModelNavigation_Index().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(NavigationPackage.eINSTANCE.getSimpleModelNavigation_Index().getEAttributeType(), newValue);
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
