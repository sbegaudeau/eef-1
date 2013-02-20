/**
 * 
 */
package org.eclipse.emf.eef.runtime.policies.processors;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.AbortExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.eef.runtime.context.DomainAwarePropertiesEditingContext;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class BatchEditingPolicyProcessor extends DomainEditingPolicyProcessor {

	/**
	 * @param editingContext
	 */
	public BatchEditingPolicyProcessor(DomainAwarePropertiesEditingContext editingContext) {
		super(editingContext);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.policies.processors.DomainEditingPolicyProcessor#executeCommand(org.eclipse.emf.common.command.Command)
	 */
	@Override
	protected void executeCommand(Command command) {
	    // If the command is executable, record and execute it.
	    //
	    if (command != null)
	    {
	      if (command.canExecute())
	      {
	        try
	        {
	          command.execute();
	        }
	        catch (AbortExecutionException exception)
	        {
	          command.dispose();
	        }
	        catch (RuntimeException exception)
	        {
	          handleError(exception);  
	          command.dispose();
	        }
	      }
	      else
	      {
	        command.dispose();
	      }
	    }				
	}


	/**
	 * Handles an exception thrown during command execution by logging it with the plugin.
	 */
	protected void handleError(Exception exception) {
		//TODO: remove dependency to CommonPlugin
		CommonPlugin.INSTANCE.log(new WrappedException(CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), exception).fillInStackTrace());
	}
}