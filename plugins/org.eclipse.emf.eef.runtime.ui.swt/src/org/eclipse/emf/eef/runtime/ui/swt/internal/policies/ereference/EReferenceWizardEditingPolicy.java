/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.internal.policies.ereference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.policies.ereference.EReferenceEditingPolicy;
import org.eclipse.emf.eef.runtime.ui.swt.EEFSWTConstants;
import org.eclipse.emf.eef.runtime.ui.swt.wizard.EEFEditingWizard;
import org.eclipse.emf.eef.runtime.ui.swt.wizard.EEFWizardDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public abstract class EReferenceWizardEditingPolicy extends EReferenceEditingPolicy {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.ereference.EReferenceEditingPolicy#defineEObjectToSet(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, org.eclipse.emf.ecore.EReference)
	 */
	@Override
	protected EObject defineEObjectToSet(PropertiesEditingContext editingContext, EReference editedReference) {
		return createObjectAndOpenWizard(editingContext, editedReference);
	}

	private EObject createObjectAndOpenWizard(final PropertiesEditingContext editingContext, EReference editedReference) {
		editingContext.getOptions().setOption(EEFSWTConstants.FORM_TOOLKIT, null);
		EEFEditingWizard wizard = new EEFEditingWizard(editingContext) {

			/**
			 * {@inheritDoc}
			 * @see org.eclipse.emf.eef.runtime.ui.swt.wizard.EEFEditingWizard#attachToResource(org.eclipse.emf.ecore.resource.Resource, org.eclipse.emf.ecore.EObject)
			 */
			@Override
			protected void attachToResource(Resource resource, EObject eObject) {
				EReferenceWizardEditingPolicy.this.attachToResource(editingContext, resource, eObject);
			}

			/**
			 * {@inheritDoc}
			 * @see org.eclipse.emf.eef.runtime.ui.swt.wizard.EEFEditingWizard#detachFromResource(org.eclipse.emf.ecore.EObject)
			 */
			@Override
			protected void detachFromResource(EObject eObject) {
				EReferenceWizardEditingPolicy.this.detachFromResource(editingContext, eObject);
			}

		};
		//TODO: use a UI helper for providing the shell 
		EEFWizardDialog wDialog = new EEFWizardDialog(new Shell(), wizard);
		int open = wDialog.open();
		if (open == Window.CANCEL) {
			return null;
		}

		return wizard.getCreatedObject();
	}

	/**
	 * Attaches the created object to the given {@link Resource}.
	 * @param resource {@link Resource} to process.
	 * @param createdEObject the {@link EObject} to attach.
	 */
	protected abstract void attachToResource(PropertiesEditingContext editingContext, Resource resource, EObject createdEObject);

	/**
	 * Detaches the given object from its resource if it is contained as a root element.
	 * @param eObject the {@link EObject} to attach.
	 */
	protected abstract void detachFromResource(PropertiesEditingContext editingContext, EObject eObject);
}
