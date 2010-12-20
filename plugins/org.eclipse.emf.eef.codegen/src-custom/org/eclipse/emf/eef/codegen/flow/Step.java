/**
 * 
 */
package org.eclipse.emf.eef.codegen.flow;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.eef.codegen.flow.var.WorkflowContext;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 * 
 */
public abstract class Step {

	/**
	 * ResourceSet where to work 
	 */
	protected ResourceSet resourceSet;

	/**
	 * Workflow context
	 */
	protected WorkflowContext context;
	
	/**
	 * Step name
	 */
	protected final String name;


	/**
	 * @param name StepName
	 */
	public Step(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the context
	 */
	public WorkflowContext getContext() {
		if (context == null) {
			context = new WorkflowContext();
		}
		return context;
	}

	/**
	 * @param context to set
	 */
	public void setContext(WorkflowContext context) {
		this.context = context;
	}
	
	/**
	 * @return the resourceSet
	 */
	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	/**
	 * @param resourceSet the resourceSet to set
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/**
	 * @param monitor monitor to use
	 * @return the execution result
	 */
	public abstract IStatus execute(Monitor monitor);

}
