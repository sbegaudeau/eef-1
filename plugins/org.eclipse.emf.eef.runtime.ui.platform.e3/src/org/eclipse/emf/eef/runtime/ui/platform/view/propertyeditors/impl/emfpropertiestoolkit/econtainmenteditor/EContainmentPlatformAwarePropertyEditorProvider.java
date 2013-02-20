/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.platform.view.propertyeditors.impl.emfpropertiestoolkit.econtainmenteditor;

import org.eclipse.emf.eef.runtime.ui.UIConstants;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.econtainmenteditor.EContainmentPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.econtainmenteditor.EContainmentPropertyEditorProvider;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EContainmentPlatformAwarePropertyEditorProvider extends EContainmentPropertyEditorProvider {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorProvider#getPropertyEditor(org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView, org.eclipse.emf.eef.views.ElementEditor)
	 */
	public PropertyEditor getPropertyEditor(PropertyEditorContext editorContext) {
		FormToolkit toolkit = editorContext.view.getEditingComponent().getEditingContext().getOptions().getOption(UIConstants.FORM_TOOLKIT);
		if (toolkit != null) {
			return new EContainmentPropertyEditor(editorContext.view, (ElementEditor) editorContext.viewElement, new EEContainmentFormPropertyEditor(editorContext.view, (ElementEditor) editorContext.viewElement));			
		} else {
			return super.getPropertyEditor(editorContext);
		}
	}

}