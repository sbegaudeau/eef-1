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
package org.eclipse.emf.eef.eefnr.components;

// Start of user code for imports

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.eef.eefnr.EefnrPackage;
import org.eclipse.emf.eef.eefnr.ReferencesTableSample;
import org.eclipse.emf.eef.eefnr.parts.EefnrViewsRepository;
import org.eclipse.emf.eef.eefnr.parts.ReferencesTableSamplePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;
import org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesValidationEditionEvent;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPartProviderService;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ReferencesTableSamplePropertiesEditionComponent extends StandardPropertiesEditionComponent {

	public static String BASE_PART = "Base"; //$NON-NLS-1$

	private String[] parts = {BASE_PART};

	/**
	 * The EObject to edit
	 */
	private ReferencesTableSample referencesTableSample;

	/**
	 * The Base part
	 */
	protected ReferencesTableSamplePropertiesEditionPart basePart;

	/**
	 * Default constructor
	 */
	public ReferencesTableSamplePropertiesEditionComponent(EObject referencesTableSample, String editing_mode) {
		if (referencesTableSample instanceof ReferencesTableSample) {
			this.referencesTableSample = (ReferencesTableSample)referencesTableSample;
			if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode)) {
				semanticAdapter = initializeSemanticAdapter();
				this.referencesTableSample.eAdapters().add(semanticAdapter);
			}
		}
		this.editing_mode = editing_mode;
	}

	/**
	 * Initialize the semantic model listener for live editing mode
	 * 
	 * @return the semantic model listener
	 */
	private AdapterImpl initializeSemanticAdapter() {
		return new EContentAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			public void notifyChanged(final Notification msg) {
				if (basePart == null)
					ReferencesTableSamplePropertiesEditionComponent.this.dispose();
				else {
					Runnable updateRunnable = new Runnable() {
						public void run() {
							runUpdateRunnable(msg);
						}
					};
					if (null == Display.getCurrent()) {
						PlatformUI.getWorkbench().getDisplay().syncExec(updateRunnable);
					} else {
						updateRunnable.run();
					}
				}
			}

		};
	}

	/**
	 * Used to update the views
	 */
	protected void runUpdateRunnable(final Notification msg) {
		if (EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty().equals(msg.getFeature()))
			basePart.updateReferencestableRequiredProperty(referencesTableSample);
		if (EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty().equals(msg.getFeature()))
			basePart.updateReferencestableOptionalProperty(referencesTableSample);


	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#translatePart(java.lang.String)
	 */
	public java.lang.Class translatePart(String key) {
		if (BASE_PART.equals(key))
			return EefnrViewsRepository.ReferencesTableSample.class;
		return super.translatePart(key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#partsList()
	 */
	public String[] partsList() {
		return parts;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionPart
	 *  (java.lang.String, java.lang.String)
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (referencesTableSample != null && BASE_PART.equals(key)) {
			if (basePart == null) {
				IPropertiesEditionPartProvider provider = PropertiesEditionPartProviderService.getInstance().getProvider(EefnrViewsRepository.class);
				if (provider != null) {
					basePart = (ReferencesTableSamplePropertiesEditionPart)provider.getPropertiesEditionPart(EefnrViewsRepository.ReferencesTableSample.class, kind, this);
					addListener((IPropertiesEditionListener)basePart);
				}
			}
			return (IPropertiesEditionPart)basePart;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#
	 *      setPropertiesEditionPart(java.lang.Class, int, org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart)
	 */
	public void setPropertiesEditionPart(java.lang.Class key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (key == EefnrViewsRepository.ReferencesTableSample.class)
			this.basePart = (ReferencesTableSamplePropertiesEditionPart) propertiesEditionPart;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Class, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public void initPart(java.lang.Class key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (basePart != null && key == EefnrViewsRepository.ReferencesTableSample.class) {
			((IPropertiesEditionPart)basePart).setContext(elt, allResource);
			final ReferencesTableSample referencesTableSample = (ReferencesTableSample)elt;
			// init values
			basePart.initReferencestableRequiredProperty(referencesTableSample, null, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty());
			basePart.initReferencestableOptionalProperty(referencesTableSample, null, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty());
			// init filters
			basePart.addFilterToReferencestableRequiredProperty(new ViewerFilter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInReferencestableRequiredPropertyTable((EObject)element));
					return element instanceof Resource;
				}

			});
			basePart.addFilterToReferencestableRequiredProperty(new EObjectFilter(EefnrPackage.eINSTANCE.getTotalSample()));
			// Start of user code for additional businessfilters for referencestableRequiredProperty
			
			// End of user code
			basePart.addFilterToReferencestableOptionalProperty(new ViewerFilter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInReferencestableOptionalPropertyTable((EObject)element));
					return element instanceof Resource;
				}

			});
			basePart.addFilterToReferencestableOptionalProperty(new EObjectFilter(EefnrPackage.eINSTANCE.getTotalSample()));
			// Start of user code for additional businessfilters for referencestableOptionalProperty
			
			// End of user code
		}
		// init values for referenced views

		// init filters for referenced views

		setInitializing(false);
	}






	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionCommand
	 *     (org.eclipse.emf.edit.domain.EditingDomain)
	 */
	public CompoundCommand getPropertiesEditionCommand(EditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand();
		if ((referencesTableSample != null) && (basePart != null)) { 
			List referencestableRequiredPropertyToAddFromReferencestableRequiredProperty = basePart.getReferencestableRequiredPropertyToAdd();
			for (Iterator iter = referencestableRequiredPropertyToAddFromReferencestableRequiredProperty.iterator(); iter.hasNext();)
				cc.append(AddCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty(), iter.next()));
			List referencestableRequiredPropertyToRemoveFromReferencestableRequiredProperty = basePart.getReferencestableRequiredPropertyToRemove();
			for (Iterator iter = referencestableRequiredPropertyToRemoveFromReferencestableRequiredProperty.iterator(); iter.hasNext();)
				cc.append(RemoveCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty(), iter.next()));
			//List referencestableRequiredPropertyToMoveFromReferencestableRequiredProperty = basePart.getReferencestableRequiredPropertyToMove();
			//for (Iterator iter = referencestableRequiredPropertyToMoveFromReferencestableRequiredProperty.iterator(); iter.hasNext();){
			//	org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
			//	cc.append(MoveCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getTotalSample(), moveElement.getElement(), moveElement.getIndex()));
			//}
			List referencestableOptionalPropertyToAddFromReferencestableOptionalProperty = basePart.getReferencestableOptionalPropertyToAdd();
			for (Iterator iter = referencestableOptionalPropertyToAddFromReferencestableOptionalProperty.iterator(); iter.hasNext();)
				cc.append(AddCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty(), iter.next()));
			List referencestableOptionalPropertyToRemoveFromReferencestableOptionalProperty = basePart.getReferencestableOptionalPropertyToRemove();
			for (Iterator iter = referencestableOptionalPropertyToRemoveFromReferencestableOptionalProperty.iterator(); iter.hasNext();)
				cc.append(RemoveCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty(), iter.next()));
			//List referencestableOptionalPropertyToMoveFromReferencestableOptionalProperty = basePart.getReferencestableOptionalPropertyToMove();
			//for (Iterator iter = referencestableOptionalPropertyToMoveFromReferencestableOptionalProperty.iterator(); iter.hasNext();){
			//	org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
			//	cc.append(MoveCommand.create(editingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getTotalSample(), moveElement.getElement(), moveElement.getIndex()));
			//}


		}
		if (!cc.isEmpty())
			return cc;
		cc.append(IdentityCommand.INSTANCE);
		return cc;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionObject()
	 */
	public EObject getPropertiesEditionObject(EObject source) {
		if (source instanceof ReferencesTableSample) {
			ReferencesTableSample referencesTableSampleToUpdate = (ReferencesTableSample)source;
			referencesTableSampleToUpdate.getReferencestableRequiredProperty().addAll(basePart.getReferencestableRequiredPropertyToAdd());
			referencesTableSampleToUpdate.getReferencestableOptionalProperty().addAll(basePart.getReferencestableOptionalPropertyToAdd());


			return referencesTableSampleToUpdate;
		}
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		if (!isInitializing()) {
			Diagnostic valueDiagnostic = validateValue(event);
			if (PropertiesEditionEvent.COMMIT == event.getState() && IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode) && valueDiagnostic.getSeverity() == Diagnostic.OK) {
				CompoundCommand command = new CompoundCommand();
			if (EefnrViewsRepository.ReferencesTableSample.referencestableRequiredProperty == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.ADD == event.getKind())
					command.append(AddCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty(), event.getNewValue()));
				if (PropertiesEditionEvent.REMOVE == event.getKind())
					command.append(RemoveCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty(), event.getNewValue()));
				if (PropertiesEditionEvent.MOVE == event.getKind())
					command.append(MoveCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableRequiredProperty(), event.getNewValue(), event.getNewIndex()));
			}
			if (EefnrViewsRepository.ReferencesTableSample.referencestableOptionalProperty == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.ADD == event.getKind())
					command.append(AddCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty(), event.getNewValue()));
				if (PropertiesEditionEvent.REMOVE == event.getKind())
					command.append(RemoveCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty(), event.getNewValue()));
				if (PropertiesEditionEvent.MOVE == event.getKind())
					command.append(MoveCommand.create(liveEditingDomain, referencesTableSample, EefnrPackage.eINSTANCE.getReferencesTableSample_ReferencestableOptionalProperty(), event.getNewValue(), event.getNewIndex()));
			}


				if (!command.isEmpty() && !command.canExecute()) {
					EEFRuntimePlugin.getDefault().logError("Cannot perform model change command.", null);
				} else {
					liveEditingDomain.getCommandStack().execute(command);
				}
			}
			if (valueDiagnostic.getSeverity() != Diagnostic.OK && valueDiagnostic instanceof BasicDiagnostic)
				super.firePropertiesChanged(new PropertiesValidationEditionEvent(event, valueDiagnostic));
			else {
				Diagnostic validate = validate();
				super.firePropertiesChanged(new PropertiesValidationEditionEvent(event, validate));
			}
			super.firePropertiesChanged(event);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.String, int)
	 */
	public boolean isRequired(String key, int kind) {
		return key == EefnrViewsRepository.ReferencesTableSample.referencestableRequiredProperty;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			String newStringValue = event.getNewValue().toString();
			try {

			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validate()
	 */
	public Diagnostic validate() {
		Diagnostic validate = Diagnostic.OK_INSTANCE;
		if (IPropertiesEditionComponent.BATCH_MODE.equals(editing_mode)) {
			EObject copy = EcoreUtil.copy(referencesTableSample);
			copy = getPropertiesEditionObject(copy);
			validate =  EEFRuntimePlugin.getEEFValidator().validate(copy);
		}
		else if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode))
			validate = EEFRuntimePlugin.getEEFValidator().validate(referencesTableSample);
		// Start of user code for custom validation check
		
		// End of user code
		return validate;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#dispose()
	 */
	public void dispose() {
		if (semanticAdapter != null)
			referencesTableSample.eAdapters().remove(semanticAdapter);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getTabText(java.lang.String)
	 */
	public String getTabText(String p_key) {
		return basePart.getTitle();
	}
}
