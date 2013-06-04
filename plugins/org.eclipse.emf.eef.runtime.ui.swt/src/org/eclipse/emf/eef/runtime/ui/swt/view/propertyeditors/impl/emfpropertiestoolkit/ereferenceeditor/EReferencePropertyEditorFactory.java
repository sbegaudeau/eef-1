/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.ereferenceeditor;

import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.emfpropertiestoolkit.EMFPropertiesToolkit;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.WidgetPropertyEditorFactoryImpl;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.emf.eef.views.toolkits.ToolkitsFactory;
import org.eclipse.emf.eef.views.toolkits.Widget;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EReferencePropertyEditorFactory extends WidgetPropertyEditorFactoryImpl<Composite> {

	private static final Widget widget = ToolkitsFactory.eINSTANCE.createWidget();
	
	static {
		widget.setName("MultiLinePropertyViewer");
	}
	
	protected final EMFPropertiesToolkit emfPropertiesToolkit;
	
	/**
	 * @param emfPropertiesToolkit
	 */
	public EReferencePropertyEditorFactory(EMFPropertiesToolkit emfPropertiesToolkit) {
		this.emfPropertiesToolkit = emfPropertiesToolkit;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.ModelPropertyEditorFactory#getModel()
	 */
	public Widget getModel() {
		return widget;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorFactory#serviceFor(org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorFactory.PropertyEditorContext)
	 */
	public boolean serviceFor(PropertyEditorContext editorContext) {
		return getModel() == editorContext.viewElement.getRepresentation() && editorContext.view.getContents() instanceof Composite;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.WidgetPropertyEditorFactoryImpl#createPropertyEditor(org.eclipse.emf.eef.runtime.ui.services.propertyeditors.PropertyEditorFactory.PropertyEditorContext)
	 */
	@SuppressWarnings("unchecked")
	protected PropertyEditor createPropertyEditor(PropertyEditorContext editorContext) {
		return new EReferencePropertyEditor(emfPropertiesToolkit.getEditUIProvidersFactory(), (PropertiesEditingView<Composite>) editorContext.view, (ElementEditor) editorContext.viewElement, new EReferenceSWTPropertyEditor(emfPropertiesToolkit.getImageManager(), (PropertiesEditingView<Composite>) editorContext.view, (ElementEditor) editorContext.viewElement));
	}

}