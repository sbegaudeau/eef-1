/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.view.propertyeditors.swttoolkit;

import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.ToolkitPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.swttoolkit.checkbox.CheckboxPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.swttoolkit.text.TextPropertyEditorProvider;
import org.eclipse.emf.eef.views.toolkits.Toolkit;
import org.eclipse.emf.eef.views.toolkits.ToolkitsFactory;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class SWTToolkit extends ToolkitPropertyEditorProvider {

	/**
	 * SWT Toolkit name.
	 */
	public static final String SWT_TOOLKIT_NAME = "swt";
	
	private static final Toolkit toolkit = ToolkitsFactory.eINSTANCE.createToolkit();
	static {
		toolkit.setName(SWT_TOOLKIT_NAME);		
	}
	
	/**
	 * 
	 */
	public SWTToolkit() {
		addPropertyEditorProvider(new TextPropertyEditorProvider())
		.addPropertyEditorProvider(new CheckboxPropertyEditorProvider());
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.ModelPropertyEditorProvider#getModel()
	 */
	public Toolkit getModel() {
		return toolkit;
	}

}