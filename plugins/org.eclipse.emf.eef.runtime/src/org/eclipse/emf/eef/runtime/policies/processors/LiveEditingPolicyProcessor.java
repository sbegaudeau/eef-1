/**
 * 
 */
package org.eclipse.emf.eef.runtime.policies.processors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.eef.runtime.context.DomainAwarePropertiesEditingContext;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class LiveEditingPolicyProcessor extends DomainEditingPolicyProcessor {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.processors.DomainEditingPolicyProcessor#executeCommand(org.eclipse.emf.eef.runtime.context.DomainAwarePropertiesEditingContext, org.eclipse.emf.common.command.Command)
	 */
	@Override
	protected void executeCommand(DomainAwarePropertiesEditingContext editingContext, Command command) {
		editingContext.getEditingDomain().getCommandStack().execute(command);
	}

}
