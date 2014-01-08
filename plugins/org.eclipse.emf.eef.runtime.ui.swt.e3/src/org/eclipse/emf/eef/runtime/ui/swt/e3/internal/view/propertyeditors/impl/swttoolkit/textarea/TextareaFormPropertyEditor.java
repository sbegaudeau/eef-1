/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.ui.swt.e3.internal.view.propertyeditors.impl.swttoolkit.textarea;

import org.eclipse.emf.eef.runtime.ui.swt.e3.internal.view.propertyeditors.impl.StandardFormControlPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.swt.util.SWTViewService;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class TextareaFormPropertyEditor extends StandardFormControlPropertyEditor<Text> {

	private Text text;
	
	/**
	 * @param view
	 * @param viewElement
	 */
	public TextareaFormPropertyEditor(PropertiesEditingView<Composite> view, ElementEditor elementEditor) {
		super(view, elementEditor);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.StandardFormPropertyEditor#createEditorContents(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 */
	protected void createEditorContents(FormToolkit toolkit, Composite parent) {
		if (view.getViewService() instanceof SWTViewService) {
			SWTViewService swtViewService = (SWTViewService) view.getViewService();
			text = swtViewService.createScrollableText(parent, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
			GridData textData = new GridData(GridData.FILL_HORIZONTAL);
			textData.heightHint = 80;
			textData.widthHint = 200;
			text.setLayoutData(textData);
			swtViewService.setID(text, elementEditor.getQualifiedIdentifier());
			swtViewService.setEEFtype(text, "eef::Textarea"); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.StandardFormControlPropertyEditor#getMainControl()
	 */
	public Text getMainControl() {
		return text;
	}

	
}