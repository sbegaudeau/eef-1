/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.eefnr.providers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.eefnr.EefnrPackage;
import org.eclipse.emf.eef.eefnr.TextSampleWithTwoTabs;

import org.eclipse.emf.eef.eefnr.components.TextSampleWithTwoTabsPropertiesEditionComponent;
import org.eclipse.emf.eef.eefnr.components.TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent;
import org.eclipse.emf.eef.eefnr.components.TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.providers.impl.PropertiesEditingProviderImpl;

import org.eclipse.jface.viewers.IFilter;

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class TextSampleWithTwoTabsPropertiesEditionProvider extends PropertiesEditingProviderImpl {

	/**
	 * Constructor without provider for super types.
	 */
	public TextSampleWithTwoTabsPropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * @param superProviders providers to use for super types.
	 */
	public TextSampleWithTwoTabsPropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * 
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
		return (editingContext.getEObject() instanceof TextSampleWithTwoTabs) 
					&& (EefnrPackage.Literals.TEXT_SAMPLE_WITH_TWO_TABS == editingContext.getEObject().eClass());
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String)
	 * 
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
		return (editingContext.getEObject() instanceof TextSampleWithTwoTabs) && (TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.TEXTSAMPLEFIRSTTAB_PART.equals(part) || TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.TEXTSAMPLESECONDTAB_PART.equals(part));
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.Class)
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, java.lang.Class refinement) {
		return (editingContext.getEObject() instanceof TextSampleWithTwoTabs) && (refinement == TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.class || refinement == TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.class);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.Class)
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, String part, java.lang.Class refinement) {
		return (editingContext.getEObject() instanceof TextSampleWithTwoTabs) && ((TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.TEXTSAMPLEFIRSTTAB_PART.equals(part) && refinement == TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.class) || (TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.TEXTSAMPLESECONDTAB_PART.equals(part) && refinement == TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.class));
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String)
	 * 
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode) {
		if (editingContext.getEObject() instanceof TextSampleWithTwoTabs) {
			return new TextSampleWithTwoTabsPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.String)
	 * 
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part) {
		if (editingContext.getEObject() instanceof TextSampleWithTwoTabs) {
			if (TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.TEXTSAMPLEFIRSTTAB_PART.equals(part))
				return new TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
			if (TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.TEXTSAMPLESECONDTAB_PART.equals(part))
				return new TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part, java.lang.Class refinement) {
		if (editingContext.getEObject() instanceof TextSampleWithTwoTabs) {
			if (TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.TEXTSAMPLEFIRSTTAB_PART.equals(part)
				&& refinement == TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent.class)
				return new TextSampleWithTwoTabsTextSampleFirstTabPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
			if (TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.TEXTSAMPLESECONDTAB_PART.equals(part)
				&& refinement == TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent.class)
				return new TextSampleWithTwoTabsTextSampleSecondTabPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part, refinement);
	}

	/**
	 * Provides the filter used by the plugin.xml to assign part forms.
	 */
	public static class EditionFilter implements IFilter {
	
		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
		 */
		public boolean select(Object toTest) {
			EObject eObj = EEFUtils.resolveSemanticObject(toTest);
			return eObj != null && EefnrPackage.Literals.TEXT_SAMPLE_WITH_TWO_TABS == eObj.eClass();
		}
		
	}

}
