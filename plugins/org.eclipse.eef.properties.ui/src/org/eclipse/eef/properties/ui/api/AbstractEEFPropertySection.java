/*******************************************************************************
 * Copyright (c) 2001, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Obeo - Contribution to the EEF project
 *******************************************************************************/
package org.eclipse.eef.properties.ui.api;

import org.eclipse.eef.common.ui.api.EEFWidgetFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An abstract implementation of a section in a tab in the tabbed property sheet page.
 *
 * @author Anthony Hunter
 * @author Stephane Begaudeau
 * @since 1.6.0
 */
public abstract class AbstractEEFPropertySection extends AbstractEEFSection {

	/**
	 * The tabbed property sheet page.
	 */
	private EEFTabbedPropertySheetPage tabbedPropertySheetPage;

	/**
	 * The current workbench selection.
	 */
	private ISelection selection;

	/**
	 * The current active workbench part.
	 */
	private IWorkbenchPart part;

	/**
	 * Get the widget factory for the property sheet page.
	 *
	 * @return the widget factory.
	 */
	public EEFWidgetFactory getWidgetFactory() {
		return tabbedPropertySheetPage.getWidgetFactory();
	}

	/**
	 * Get the current workbench selection.
	 *
	 * @return the current workbench selection.
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * Get the current workbench part.
	 *
	 * @return Returns the part.
	 */
	public IWorkbenchPart getPart() {
		return part;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.properties.ui.api.IEEFSection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, EEFTabbedPropertySheetPage aTabbedPropertySheetPage) {
		this.tabbedPropertySheetPage = aTabbedPropertySheetPage;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.properties.ui.api.IEEFSection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart workbenchPart, ISelection currentSelection) {
		this.selection = currentSelection;
		this.part = workbenchPart;
	}

}
