/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.platform.e4.parts;


import java.io.IOException;

import java.io.InputStream;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.eef.runtime.context.DomainAwarePropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContextFactory;
import org.eclipse.emf.eef.runtime.services.EEFServiceRegistry;
import org.eclipse.emf.eef.runtime.ui.platform.e4.E4EEFSupportConstants;
import org.eclipse.emf.eef.runtime.ui.platform.e4.services.PlatformRelatedUIUtils;
import org.eclipse.emf.eef.runtime.ui.platform.e4.utils.EditingInput;
import org.eclipse.emf.eef.runtime.ui.platform.e4.utils.impl.EditingContextEditingInput;
import org.eclipse.emf.eef.runtime.ui.platform.e4.utils.impl.URIEditingInput;
import org.eclipse.emf.eef.runtime.ui.viewer.IEEFViewer;
import org.eclipse.emf.eef.runtime.ui.viewer.filters.EEFViewerFilter;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
@SuppressWarnings("restriction")
public class E4EEFPart {

	private EditingDomain editingDomain;
	private IEEFViewer viewer;
	
	@Inject
	private MDirtyable dirty;

	@Inject
	private EEFServiceRegistry serviceRegistry;
	private MPart mPart;
	private UISynchronize uiSynchronize;
	private PlatformRelatedUIUtils uiUtils;


	/**
	 * @param serviceRegistry
	 * @param parent
	 */
	@Inject
	public E4EEFPart(MPart mPart, EEFServiceRegistry serviceRegistry, PlatformRelatedUIUtils uiUtils, UISynchronize uiSynchronize) {
		this.mPart = mPart;
		this.uiSynchronize = uiSynchronize;
		this.uiUtils = uiUtils;
	}
	
	@PostConstruct
	public void postConstruct() {
		Object widget = mPart.getWidget();
		this.viewer = uiUtils.createEEFViewer(widget);
	}
	
	/**
	 * @return the input {@link PropertiesEditingContext} of the nested {@link EEFViewer}.
	 */
	public Object getInput() {
		return viewer.getInput();
	}

	/**
	 * Defines the new input of the nested {@link EEFViewer}.
	 * @param input the new input.
	 */
	public void setInput(EditingInput input) {
		editingDomain = input.getEditingDomain();
		CommandStack commandStack = editingDomain.getCommandStack();
		if (commandStack instanceof BasicCommandStack) {
			commandStack.addCommandStackListener(new EEFCommandStackListener((BasicCommandStack) commandStack, uiSynchronize, dirty));
		}
		if (input instanceof EditingContextEditingInput) {
			DomainAwarePropertiesEditingContext context = ((EditingContextEditingInput) input).getEditingContext();
			context.getOptions().setOption(E4EEFSupportConstants.MODELPART_OPTION_KEY, mPart);
			viewer.setInput(context);
		} else if (input instanceof URIEditingInput) {
			Resource resource = editingDomain.getResourceSet().getResource(((URIEditingInput) input).getUri(), true);
			EObject root = resource.getContents().get(0);
			PropertiesEditingContextFactory contextFactory = serviceRegistry.getService(PropertiesEditingContextFactory.class, root);
			//TODO: is the ED always an AFED ?
			PropertiesEditingContext editingContext = contextFactory.createPropertiesEditingContext((AdapterFactoryEditingDomain)editingDomain, root);
			editingContext.getOptions().setOption(E4EEFSupportConstants.MODELPART_OPTION_KEY, mPart);
			viewer.setInput(editingContext);
		}
	}
	
	/**
	 * Adds a new filter in the nested {@link EEFViewer}.
	 * @param filter 
	 */
	public void addFilter(EEFViewerFilter filter) {
		viewer.addFilter(filter);
	}
	
	/**
	 * Removes a new filter in the nested {@link EEFViewer}.
	 * @param filter 
	 */
	public void removeFilter(EEFViewerFilter filter) {
		viewer.removeFilter(filter);
	}
	

	@Focus
	public void onFocus() {	}

	@Persist
	public void save() {
		if (editingDomain != null) {
			// Save only resources that have actually changed.
			//
			final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
			saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
			
			Job job = new Job("Model Saving") {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
				 */
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					// Save the resources to the file system.
					//
					boolean first = true;
					for (Resource resource : editingDomain.getResourceSet().getResources()) {
						if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
							try {
								resource.save(saveOptions);
							}
							catch (Exception exception) {
								return new Status(IStatus.ERROR, "org.eclipse.emf.eef.runtime.ui.platform.e4.support", "An error occured during model saving.", exception);
							}
							first = false;
						}
					}
					dirty.setDirty(false);
					return Status.OK_STATUS;
				}
				
			};
			
			job.schedule();

		}
	}

	private static class EEFCommandStackListener implements CommandStackListener {

		private BasicCommandStack commandStack;
		private UISynchronize uiSynchronize;
		private MDirtyable dirty;


		/**
		 * @param commandStack2
		 * @param control
		 * @param dirty
		 */
		public EEFCommandStackListener(BasicCommandStack commandStack, UISynchronize uiSynchronize, MDirtyable dirty) {
			this.commandStack = commandStack;
			this.uiSynchronize = uiSynchronize;
			this.dirty = dirty;
		}




		/**
		 * {@inheritDoc}
		 * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
		 */
		public void commandStackChanged(EventObject event) {
			uiSynchronize.asyncExec(new Runnable() {
				public void run() {
					dirty.setDirty(commandStack.isSaveNeeded());
				}
			});
		}

	}


	/**
	 * This returns whether something has been persisted to the URI of the specified resource.
	 * The implementation uses the URI converter from the editor's resource set to try to open an input stream. 
	 */
	protected boolean isPersisted(Resource resource) {
		boolean result = false;
		try {
			InputStream stream = editingDomain.getResourceSet().getURIConverter().createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		}
		catch (IOException e) {
			// Ignore
		}
		return result;
	}
}
