/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.platform.application.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.eef.runtime.services.EEFServiceRegistry;
import org.eclipse.emf.eef.runtime.ui.platform.application.model.utils.ApplicationModelBuilder;
import org.eclipse.emf.eef.runtime.ui.platform.application.parts.E4EEFPart;
import org.eclipse.emf.eef.runtime.ui.platform.application.utils.EditingInput;
import org.eclipse.emf.eef.runtime.ui.platform.application.utils.impl.URIEditingInput;
import org.eclipse.swt.widgets.Shell;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public abstract class AbstractEEFOpenViewHandler {

	@Execute
	public void execute(IEclipseContext context, EModelService modelService, EPartService partService, EEFServiceRegistry serviceRegistry, MApplication applicationModel, @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) throws InvocationTargetException, InterruptedException {
		ApplicationModelBuilder builder = new ApplicationModelBuilder(applicationModel);
		builder.addPartDescriptorIfNeeded(ApplicationModelBuilder.EEF_PART_DESCRIPTOR);
		MElementContainer partStack = (MElementContainer) modelService.find(getElementContainerID(), applicationModel);
		preparePartCreation(modelService, applicationModel);
		MPart mPart = partService.createPart(ApplicationModelBuilder.EEF_PART_DESCRIPTOR);
		partStack.getChildren().add(mPart);
		partService.showPart(mPart, PartState.ACTIVATE);
		EditingInput editingInput = getEditingInput(mPart);
		E4EEFPart partImpl = (E4EEFPart) mPart.getObject();
		partImpl.setInput(editingInput);
		if (editingInput instanceof URIEditingInput) {
			mPart.setLabel(((URIEditingInput) editingInput).getUri().trimFileExtension().lastSegment());
		}
		configureCreatedPart(modelService, applicationModel, mPart);
	}

	/**
	 * @param mPart
	 * @return
	 */
	protected abstract EditingInput getEditingInput(MPart mPart);

	/**
	 * Defines the element where to open the EEF part
	 * @return the container ID.
	 */
	protected abstract String getElementContainerID();

	/**
	 * Allows sub classes to prepare the part creation.
	 * @param modelService
	 * @param applicationModel 
	 */
	protected void preparePartCreation(EModelService modelService, MApplication applicationModel) {
		// Do nothing
	}

	/**
	 * Allows sub classes to configure the created part.
	 * @param modelService
	 * @param applicationModel 
	 * @param mPart
	 */
	protected void configureCreatedPart(EModelService modelService, MApplication applicationModel, MPart mPart) {
		// Do nothing

	}

}
