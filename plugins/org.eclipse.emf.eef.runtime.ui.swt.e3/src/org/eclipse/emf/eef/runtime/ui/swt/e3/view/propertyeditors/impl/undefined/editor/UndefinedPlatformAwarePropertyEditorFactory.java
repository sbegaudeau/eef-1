/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.e3.view.propertyeditors.impl.undefined.editor;

import org.eclipse.emf.eef.runtime.ui.swt.EEFSWTConstants;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.undefined.editor.UndefinedPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.undefined.editor.UndefinedPropertyEditorFactory;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 * 
 */
public class UndefinedPlatformAwarePropertyEditorFactory extends UndefinedPropertyEditorFactory {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.ecomboeditor.EComboPropertyEditorFactory#createPropertyEditor(org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorFactory.PropertyEditorContext)
	 */
	protected PropertyEditor createPropertyEditor(PropertyEditorContext editorContext) {
		FormToolkit toolkit = editorContext.view.getEditingComponent().getEditingContext().getOptions().getOption(EEFSWTConstants.FORM_TOOLKIT);
		if (toolkit != null) {
			return new UndefinedPropertyEditor(new UndefinedFormPropertyEditor(editorContext.viewElement));
		} else {
			return super.createPropertyEditor(editorContext);
		}
	}

}