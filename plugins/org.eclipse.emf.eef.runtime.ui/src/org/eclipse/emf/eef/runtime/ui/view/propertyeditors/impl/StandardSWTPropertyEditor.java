/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl;

import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.SWTPropertyEditor;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public abstract class StandardSWTPropertyEditor<VIEWER extends Viewer> implements SWTPropertyEditor<VIEWER> {
	
	protected PropertiesEditingView view;
	protected ElementEditor elementEditor;
	
	
	/**
	 * @param view {@link PropertiesEditingView} where the PropertyEditor is built.
	 * @param elementEditor {@link ElementEditor} specifying the Property Editor.
	 */
	public StandardSWTPropertyEditor(PropertiesEditingView view, ElementEditor elementEditor) {
		this.view = view;
		this.elementEditor = elementEditor;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor#build(org.eclipse.swt.widgets.Composite)
	 */
	public void build(Composite parent) {
		view.getViewHelper().createLabel(parent, elementEditor, elementEditor.getName());
		createEditorContents(parent);
		view.getViewHelper().createHelpButton(parent, elementEditor);
	}

	/**
	 * Create the contents of the property editor in the owning Composite.
	 * @param parent the owning {@link Composite}.
	 */
	protected abstract void createEditorContents(Composite parent);
	
}