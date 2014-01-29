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
package org.eclipse.emf.eef.runtime.ui.swt.e3.util;

import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.ui.util.ViewService;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public interface PlatformAwareViewService extends ViewService {
	
	/**
	 * Instantiates a new label to display the given propertyBinding.
	 * @param toolkit a {@link FormToolkit} to use in Eclipse Form Style. This parameter can be null.
	 * @param parent label container.
	 * @param editor propertyBinding to display.
	 * @param alternate alternative text.
	 * @return created label.
	 */
	Label createLabel(PropertiesEditingComponent editingComponent, FormToolkit toolkit, Composite parent, Object editor, String alternate);
	
	/**
	 * Instantiates a help button for the given propertyBinding.
	 * @param toolkit a {@link FormToolkit} to use in Eclipse Form Style. This parameter can be null.
	 * @param parent button container.
	 * @param editor propertyBinding to process.
	 * @param alternate alternative text.
	 * @param helpID help ID
	 * @return created control.
	 */
	Control createHelpButton(PropertiesEditingComponent editingComponent, FormToolkit toolkit, final Composite parent, Object editor);
	
}
