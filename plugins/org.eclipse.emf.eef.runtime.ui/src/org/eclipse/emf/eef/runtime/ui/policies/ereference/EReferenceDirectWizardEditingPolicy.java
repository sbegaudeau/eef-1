/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.policies.ereference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.eef.runtime.internal.context.SemanticPropertiesEditingContext;
import org.eclipse.emf.eef.runtime.policies.EditingPolicyProcessor;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EReferenceDirectWizardEditingPolicy extends EReferenceWizardEditingPolicy {

	/**
	 * @param context
	 */
	public EReferenceDirectWizardEditingPolicy(SemanticPropertiesEditingContext context) {
		super(context);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.EditingPolicyWithProcessor#getProcessor()
	 */
	public EditingPolicyProcessor getProcessor() {
		return new org.eclipse.emf.eef.runtime.policies.processors.DirectEditingPolicyProcessor();
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.policies.ereference.EReferenceWizardEditingPolicy#attachToResource(org.eclipse.emf.ecore.resource.Resource, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void attachToResource(Resource resource, EObject createdEObject) {
		resource.getContents().equals(createdEObject);
	}

}
