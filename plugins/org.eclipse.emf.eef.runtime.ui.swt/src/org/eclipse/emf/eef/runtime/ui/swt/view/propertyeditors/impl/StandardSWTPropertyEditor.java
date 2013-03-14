/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl;

import org.eclipse.emf.eef.runtime.ui.services.view.ViewService;
import org.eclipse.emf.eef.runtime.ui.swt.services.view.SWTViewService;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.SWTPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public abstract class StandardSWTPropertyEditor<VIEWER extends Viewer> implements SWTPropertyEditor<VIEWER> {
	
	protected PropertiesEditingView<Composite> view;
	protected ElementEditor elementEditor;
	
	
	/**
	 * @param view {@link PropertiesEditingView} where the PropertyEditor is built.
	 * @param viewElement {@link ElementEditor} specifying the Property Editor.
	 */
	public StandardSWTPropertyEditor(PropertiesEditingView<Composite> view, ElementEditor elementEditor) {
		this.view = view;
		this.elementEditor = elementEditor;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor#build(org.eclipse.swt.widgets.Composite)
	 */
	public void build(Composite parent) {
		ViewService viewService = view.getViewService();
		SWTViewService swtViewService = null;
		if (viewService instanceof SWTViewService) {
			swtViewService = (SWTViewService) viewService;
		}
		if (swtViewService != null) {
			swtViewService.createLabel(parent, elementEditor, elementEditor.getName());
		}
		createEditorContents(parent);
		if (swtViewService != null) {
			swtViewService.createHelpButton(parent, elementEditor);
		}
	}

	/**
	 * Create the contents of the property editor in the owning Composite.
	 * @param parent the owning {@link Composite}.
	 */
	protected abstract void createEditorContents(Composite parent);
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#lock()
	 */
	public void lock() {
		getViewer().getControl().setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#unlock()
	 */
	public void unlock() {
		getViewer().getControl().setEnabled(true);		
	}
	
}