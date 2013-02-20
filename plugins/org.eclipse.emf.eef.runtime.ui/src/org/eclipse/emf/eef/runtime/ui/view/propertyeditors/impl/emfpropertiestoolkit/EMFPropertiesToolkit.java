package org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit;
/**
 * 
 */

import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.ToolkitPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.ecomboeditor.EComboPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.econtainmenteditor.EContainmentPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.ereferenceeditor.EReferencePropertyEditorProvider;
import org.eclipse.emf.eef.views.toolkits.Toolkit;
import org.eclipse.emf.eef.views.toolkits.ToolkitsFactory;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EMFPropertiesToolkit extends ToolkitPropertyEditorProvider {

	/**
	 * EMFProperties toolkit name.
	 */
	public static final String EMF_PROPERTIES = "EMFProperties";
	
	private static final Toolkit toolkit = ToolkitsFactory.eINSTANCE.createToolkit(); 
	static {
		toolkit.setName(EMF_PROPERTIES);
	}
	
	/**
	 * 
	 */
	public EMFPropertiesToolkit() {
		addPropertyEditorProvider(new EReferencePropertyEditorProvider())
			.addPropertyEditorProvider(new EComboPropertyEditorProvider())
			.addPropertyEditorProvider(new EContainmentPropertyEditorProvider());
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.ModelPropertyEditorProvider#getModel()
	 */
	public Toolkit getModel() {
		return toolkit;
	}

}