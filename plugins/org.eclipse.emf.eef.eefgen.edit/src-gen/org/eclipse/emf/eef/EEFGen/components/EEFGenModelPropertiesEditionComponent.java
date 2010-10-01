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
package org.eclipse.emf.eef.EEFGen.components;

// Start of user code for imports

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.EEFGen.EEFGenModel;
import org.eclipse.emf.eef.EEFGen.EEFGenPackage;
import org.eclipse.emf.eef.EEFGen.parts.EEFGenModelPropertiesEditionPart;
import org.eclipse.emf.eef.EEFGen.parts.EEFGenViewsRepository;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;
import org.eclipse.emf.eef.runtime.impl.command.StandardEditingCommand;
import org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesValidationEditionEvent;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPartProviderService;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

// End of user code

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class EEFGenModelPropertiesEditionComponent extends StandardPropertiesEditionComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	private String[] parts = {BASE_PART};

	/**
	 * The EObject to edit
	 * 
	 */
	private EEFGenModel eEFGenModel;

	/**
	 * The Base part
	 * 
	 */
	protected EEFGenModelPropertiesEditionPart basePart;
	
	/**
	 * Default constructor
	 * 
	 */
	public EEFGenModelPropertiesEditionComponent(EObject eEFGenModel, String editing_mode) {
		if (eEFGenModel instanceof EEFGenModel) {
			this.eEFGenModel = (EEFGenModel)eEFGenModel;
			if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode)) {
				semanticAdapter = initializeSemanticAdapter();
				this.eEFGenModel.eAdapters().add(semanticAdapter);
			}
		}
		this.editing_mode = editing_mode;
	}

	/**
	 * Initialize the semantic model listener for live editing mode
	 * 
	 * @return the semantic model listener
	 * 
	 */
	private AdapterImpl initializeSemanticAdapter() {
		return new EContentAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 * 
			 */
			public void notifyChanged(final Notification msg) {
				if (basePart == null)
					EEFGenModelPropertiesEditionComponent.this.dispose();
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
	 * 
	 */
	protected void runUpdateRunnable(final Notification msg) {
		if (EEFGenPackage.eINSTANCE.getEEFGenModel_GenDirectory().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setGenerationDirectory(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setGenerationDirectory("");
			}
		}
		if (EEFGenPackage.eINSTANCE.getEEFGenModel_Author().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setAuthor(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setAuthor("");
			}
		}
		if (EEFGenPackage.eINSTANCE.getEEFGenModel_License().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setLicense(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setLicense("");
			}
		}
		if (EEFGenPackage.eINSTANCE.getEEFGenModel_TestsGenDirectory().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setTestsGenerationDirectory(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setTestsGenerationDirectory("");
			}
		}
		if (EEFGenPackage.eINSTANCE.getEEFGenModel_UseJMergeForUserCode().equals(msg.getFeature()) && basePart != null)
			basePart.setUseJMergeToManageUserCode((Boolean)msg.getNewValue());


	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#translatePart(java.lang.String)
	 * 
	 */
	public java.lang.Class translatePart(String key) {
		if (BASE_PART.equals(key))
			return EEFGenViewsRepository.EEFGenModel.class;
		return super.translatePart(key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#partsList()
	 * 
	 */
	public String[] partsList() {
		return parts;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionPart
	 *  (java.lang.String, java.lang.String)
	 * 
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (eEFGenModel != null && BASE_PART.equals(key)) {
			if (basePart == null) {
				IPropertiesEditionPartProvider provider = PropertiesEditionPartProviderService.getInstance().getProvider(EEFGenViewsRepository.class);
				if (provider != null) {
					basePart = (EEFGenModelPropertiesEditionPart)provider.getPropertiesEditionPart(EEFGenViewsRepository.EEFGenModel.class, kind, this);
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
	 * 
	 */
	public void setPropertiesEditionPart(java.lang.Class key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (key == EEFGenViewsRepository.EEFGenModel.class)
			this.basePart = (EEFGenModelPropertiesEditionPart) propertiesEditionPart;
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
		if (basePart != null && key == EEFGenViewsRepository.EEFGenModel.class) {
			((IPropertiesEditionPart)basePart).setContext(elt, allResource);
			final EEFGenModel eEFGenModel = (EEFGenModel)elt;
			// init values
			if (eEFGenModel.getGenDirectory() != null)
				basePart.setGenerationDirectory(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), eEFGenModel.getGenDirectory()));

			if (eEFGenModel.getAuthor() != null)
				basePart.setAuthor(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), eEFGenModel.getAuthor()));

			if (eEFGenModel.getLicense() != null)
				basePart.setLicense(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), eEFGenModel.getLicense()));
			if (eEFGenModel.getTestsGenDirectory() != null)
				basePart.setTestsGenerationDirectory(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), eEFGenModel.getTestsGenDirectory()));

			basePart.setUseJMergeToManageUserCode(eEFGenModel.isUseJMergeForUserCode());

			// init filters





		}
		// init values for referenced views

		// init filters for referenced views

		setInitializing(false);
	}








	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(final IPropertiesEditionEvent event) {
		if (!isInitializing()) {
			Diagnostic valueDiagnostic = validateValue(event);
			if (IPropertiesEditionComponent.BATCH_MODE.equals(editing_mode)) {			
				if (EEFGenViewsRepository.EEFGenModel.generationDirectory == event.getAffectedEditor()) {
					updateGenDirectory((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
				}
				if (EEFGenViewsRepository.EEFGenModel.author == event.getAffectedEditor()) {
					updateAuthor((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
				}
				if (EEFGenViewsRepository.EEFGenModel.license == event.getAffectedEditor()) {
					updateLicense((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
				}
				if (EEFGenViewsRepository.EEFGenModel.testsGenerationDirectory == event.getAffectedEditor()) {
					updateTestsGenDirectory((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
				}
				if (EEFGenViewsRepository.EEFGenModel.useJMergeToManageUserCode == event.getAffectedEditor()) {
					updateUseJMergeForUserCode((Boolean)event.getNewValue());
				}
			}
			else if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode)) {
				liveEditingDomain.getCommandStack().execute(new StandardEditingCommand() {
					
					public void execute() {
						if (EEFGenViewsRepository.EEFGenModel.generationDirectory == event.getAffectedEditor()) {
							updateGenDirectory((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
						}
						if (EEFGenViewsRepository.EEFGenModel.author == event.getAffectedEditor()) {
							updateAuthor((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
						}
						if (EEFGenViewsRepository.EEFGenModel.license == event.getAffectedEditor()) {
							updateLicense((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
						}
						if (EEFGenViewsRepository.EEFGenModel.testsGenerationDirectory == event.getAffectedEditor()) {
							updateTestsGenDirectory((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue()));
						}
						if (EEFGenViewsRepository.EEFGenModel.useJMergeToManageUserCode == event.getAffectedEditor()) {
							updateUseJMergeForUserCode((Boolean)event.getNewValue());
						}
					}
				});			
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

	private void updateGenDirectory(java.lang.String newValue) {
		eEFGenModel.setGenDirectory(newValue);	
	}

	private void updateAuthor(java.lang.String newValue) {
		eEFGenModel.setAuthor(newValue);	
	}

	private void updateLicense(java.lang.String newValue) {
		eEFGenModel.setLicense(newValue);	
	}

	private void updateTestsGenDirectory(java.lang.String newValue) {
		eEFGenModel.setTestsGenDirectory(newValue);	
	}

	private void updateUseJMergeForUserCode(Boolean newValue) {
		eEFGenModel.setUseJMergeForUserCode(newValue);	
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.String, int)
	 * 
	 */
	public boolean isRequired(String key, int kind) {
		return key == EEFGenViewsRepository.EEFGenModel.generationDirectory;
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
				if (EEFGenViewsRepository.EEFGenModel.generationDirectory == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(EEFGenPackage.eINSTANCE.getEEFGenModel_GenDirectory().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(EEFGenPackage.eINSTANCE.getEEFGenModel_GenDirectory().getEAttributeType(), newValue);
				}
				if (EEFGenViewsRepository.EEFGenModel.author == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(EEFGenPackage.eINSTANCE.getEEFGenModel_Author().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(EEFGenPackage.eINSTANCE.getEEFGenModel_Author().getEAttributeType(), newValue);
				}
				if (EEFGenViewsRepository.EEFGenModel.license == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(EEFGenPackage.eINSTANCE.getEEFGenModel_License().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(EEFGenPackage.eINSTANCE.getEEFGenModel_License().getEAttributeType(), newValue);
				}
				if (EEFGenViewsRepository.EEFGenModel.testsGenerationDirectory == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(EEFGenPackage.eINSTANCE.getEEFGenModel_TestsGenDirectory().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(EEFGenPackage.eINSTANCE.getEEFGenModel_TestsGenDirectory().getEAttributeType(), newValue);
				}
				if (EEFGenViewsRepository.EEFGenModel.useJMergeToManageUserCode == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(EEFGenPackage.eINSTANCE.getEEFGenModel_UseJMergeForUserCode().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(EEFGenPackage.eINSTANCE.getEEFGenModel_UseJMergeForUserCode().getEAttributeType(), newValue);
				}
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
	 * 
	 */
	public Diagnostic validate() {
		Diagnostic validate = Diagnostic.OK_INSTANCE;
		validate = EEFRuntimePlugin.getEEFValidator().validate(eEFGenModel);
		// Start of user code for custom validation check
		
		// End of user code
		return validate;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#dispose()
	 * 
	 */
	public void dispose() {
		if (semanticAdapter != null)
			eEFGenModel.eAdapters().remove(semanticAdapter);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getTabText(java.lang.String)
	 * 
	 */
	public String getTabText(String p_key) {
		return basePart.getTitle();
	}
}
