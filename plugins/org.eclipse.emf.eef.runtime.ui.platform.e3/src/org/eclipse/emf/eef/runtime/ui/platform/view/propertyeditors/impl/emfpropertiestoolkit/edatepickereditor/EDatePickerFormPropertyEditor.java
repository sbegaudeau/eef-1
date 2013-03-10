/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.platform.view.propertyeditors.impl.emfpropertiestoolkit.edatepickereditor;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.services.EEFServiceRegistry;
import org.eclipse.emf.eef.runtime.ui.platform.view.propertyeditors.impl.StandardFormPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.platform.widgets.FormSingleLinePropertyViewer;
import org.eclipse.emf.eef.runtime.ui.services.resources.ImageManager;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.viewer.EditUIProvidersFactory;
import org.eclipse.emf.eef.runtime.ui.widgets.SingleLinePropertyViewer;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EDatePickerFormPropertyEditor extends StandardFormPropertyEditor<SingleLinePropertyViewer> {

	private FormSingleLinePropertyViewer eDatePickerEditor;

	/**
	 * @param view
	 * @param elementEditor
	 */
	public EDatePickerFormPropertyEditor(PropertiesEditingView view, ElementEditor elementEditor) {
		super(view, elementEditor);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer#getViewer()
	 */
	public FormSingleLinePropertyViewer getViewer() {
		return eDatePickerEditor;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.StandardFormPropertyEditor#createEditorContents(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createEditorContents(FormToolkit toolkit, Composite parent) {
		eDatePickerEditor = new FormSingleLinePropertyViewer(toolkit, parent, SWT.BORDER);
		PropertiesEditingContext editingContext = view.getEditingComponent().getEditingContext();
		EEFServiceRegistry serviceRegistry = editingContext.getServiceRegistry();
		EditUIProvidersFactory providersFactory = serviceRegistry.getService(EditUIProvidersFactory.class, this);
		eDatePickerEditor.setLabelProvider(providersFactory.createLabelProvider(editingContext.getAdapterFactory()));
		ImageManager imageManager = serviceRegistry.getService(ImageManager.class, this);
		eDatePickerEditor.setImageManager(imageManager);
		eDatePickerEditor.createContents();
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		eDatePickerEditor.getControl().setLayoutData(layoutData);
	}


}