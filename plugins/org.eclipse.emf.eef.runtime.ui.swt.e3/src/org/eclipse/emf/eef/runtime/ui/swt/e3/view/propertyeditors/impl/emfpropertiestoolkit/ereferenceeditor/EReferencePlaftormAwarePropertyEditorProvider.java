/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.e3.view.propertyeditors.impl.emfpropertiestoolkit.ereferenceeditor;

import org.eclipse.emf.eef.runtime.ui.swt.EEFSWTConstants;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.EMFPropertiesToolkit;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.ereferenceeditor.EReferencePropertyEditor;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.ereferenceeditor.EReferencePropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 * 
 */
public class EReferencePlaftormAwarePropertyEditorProvider extends EReferencePropertyEditorProvider {

	/**
	 * @param emfPropertiesToolkit
	 */
	public EReferencePlaftormAwarePropertyEditorProvider(EMFPropertiesToolkit emfPropertiesToolkit) {
		super(emfPropertiesToolkit);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.ecomboeditor.EComboPropertyEditorProvider#createPropertyEditor(org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorProvider.PropertyEditorContext)
	 */
	@SuppressWarnings("unchecked")
	protected PropertyEditor createPropertyEditor(PropertyEditorContext editorContext) {
		FormToolkit toolkit = editorContext.view.getEditingComponent().getEditingContext().getOptions().getOption(EEFSWTConstants.FORM_TOOLKIT);
		if (toolkit != null) {
			return new EReferencePropertyEditor(emfPropertiesToolkit.getEditUIProvidersFactory(), (PropertiesEditingView<Composite>) editorContext.view, (ElementEditor) editorContext.viewElement, new EReferenceFormPropertyEditor(emfPropertiesToolkit.getImageManager(), (PropertiesEditingView<Composite>) editorContext.view, (ElementEditor) editorContext.viewElement));
		} else {
			return super.createPropertyEditor(editorContext);
		}
	}

}
