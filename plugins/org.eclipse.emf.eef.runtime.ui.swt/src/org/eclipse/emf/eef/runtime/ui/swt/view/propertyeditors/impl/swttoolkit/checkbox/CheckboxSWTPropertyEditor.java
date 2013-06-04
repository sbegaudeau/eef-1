/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.impl.swttoolkit.checkbox;

import org.eclipse.emf.eef.runtime.ui.services.view.ViewService;
import org.eclipse.emf.eef.runtime.ui.swt.internal.services.propertyeditors.util.EEFControlWrapperViewer;
import org.eclipse.emf.eef.runtime.ui.swt.services.view.SWTViewService;
import org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.SWTPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class CheckboxSWTPropertyEditor implements SWTPropertyEditor<EEFControlWrapperViewer<Button>> {

	protected PropertiesEditingView<Composite> view;
	protected ElementEditor elementEditor;
	protected Button checkbox;
	
	private EEFControlWrapperViewer<Button> wrapperViewer;

	/**
	 * @param view
	 * @param viewElement
	 */
	public CheckboxSWTPropertyEditor(PropertiesEditingView<Composite> view, ElementEditor elementEditor) {
		this.view = view;
		this.elementEditor = elementEditor;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#getViewer()
	 */
	public EEFControlWrapperViewer<Button> getViewer() {
		if (wrapperViewer == null) {
			wrapperViewer = new EEFControlWrapperViewer<Button>() {


				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.ui.services.propertyeditors.util.EEFControlWrapperViewer#getMainControl()
				 */
				@Override
				public Button getMainControl() {
					return checkbox;
				}


			};
		}
		return wrapperViewer;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.swt.view.propertyeditors.SWTPropertyEditor#build(org.eclipse.swt.widgets.Composite)
	 */
	public void build(Composite parent) {
		checkbox = new Button(parent, SWT.CHECK);
		ViewService viewService = view.getViewService();
		checkbox.setText(viewService.getDescription(elementEditor, elementEditor.getName()));
		GridData checkboxData = new GridData(GridData.FILL_HORIZONTAL);
		checkboxData.horizontalSpan = 2;
		checkbox.setLayoutData(checkboxData);
		if (viewService instanceof SWTViewService) {
			SWTViewService swtViewService = (SWTViewService)viewService;
			swtViewService.setID(checkbox, elementEditor.getQualifiedIdentifier());
			swtViewService.setEEFtype(checkbox, "eef::Checkbox"); //$NON-NLS-1$
			swtViewService.createHelpButton(parent, elementEditor);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#lock()
	 */
	public void lock() {
		checkbox.setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#unlock()
	 */
	public void unlock() {
		checkbox.setEnabled(true);
	}

}