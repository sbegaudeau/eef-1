/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.internal.view.impl;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.ui.UIConstants;
import org.eclipse.emf.eef.runtime.ui.view.FormPropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.ViewHelper;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.FormPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.section.SectionPropertiesEditingView;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.emf.eef.views.View;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class FormImplPropertiesEditingView extends AbstractPropertiesEditingView implements FormPropertiesEditingView {

	/**
	 * Non-parameterized constructor for {@link SectionPropertiesEditingView} purpose.
	 * Mustn't be use otherwise.
	 */
	public FormImplPropertiesEditingView() { }

	/**
	 * @param editingComponent
	 * @param viewDescriptor
	 */
	public FormImplPropertiesEditingView(PropertiesEditingComponent editingComponent, View viewDescriptor) {
		super(editingComponent, viewDescriptor);
		initToolkit();
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.FormPropertiesEditingView#createContents(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(FormToolkit toolkit, Composite composite) {
		contentsComposite = toolkit.createComposite(composite);
		contentsComposite.setLayout(new GridLayout(3, false));
		if (propertyEditorProvider != null) {
			TreeIterator<EObject> eAllContents = viewDescriptor.eAllContents();
			while (eAllContents.hasNext()) {
				EObject next = eAllContents.next();
				if (next instanceof ElementEditor) {
					ElementEditor elementEditor = (ElementEditor) next;
					if (propertyEditorProvider.canHandle(this, elementEditor)) {
						PropertyEditor propertyEditor = propertyEditorProvider.getPropertyEditor(this, elementEditor);
						if (propertyEditor.getPropertyEditorViewer() instanceof FormPropertyEditor) {
							((FormPropertyEditor<?>)propertyEditor.getPropertyEditorViewer()).build(toolkit, contentsComposite);
							this.propertyEditors.put(elementEditor, propertyEditor);
						}
					}
				}
			}
		}
	}

	/**
	 * Initialize the {@link FormToolkit} of the {@link ViewHelper}
	 */
	protected void initToolkit() {
		FormToolkit toolkit = getEditingComponent().getEditingContext().getOptions().getOption(UIConstants.FORM_TOOLKIT);
		if (toolkit != null) {
			getViewHelper().setToolkit(toolkit);
		}
	}
	
}