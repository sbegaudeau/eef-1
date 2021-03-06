/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.core.api.controllers;

import org.eclipse.eef.EEFButtonDescription;
import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFHyperlinkDescription;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFListDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFRadioDescription;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.internal.controllers.EEFButtonController;
import org.eclipse.eef.core.internal.controllers.EEFCheckboxController;
import org.eclipse.eef.core.internal.controllers.EEFGroupController;
import org.eclipse.eef.core.internal.controllers.EEFHyperlinkController;
import org.eclipse.eef.core.internal.controllers.EEFLabelController;
import org.eclipse.eef.core.internal.controllers.EEFListController;
import org.eclipse.eef.core.internal.controllers.EEFRadioController;
import org.eclipse.eef.core.internal.controllers.EEFSectionController;
import org.eclipse.eef.core.internal.controllers.EEFSelectController;
import org.eclipse.eef.core.internal.controllers.EEFTextController;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

/**
 * This factory will be used to create the controllers.
 *
 * @author sbegaudeau
 */
public class EEFControllersFactory {

	/**
	 * Creates a new group controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return The group controller
	 */
	public IEEFGroupController createGroupController(EEFGroupDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFGroupController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a new text controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A text controller
	 */
	public IEEFTextController createTextController(EEFTextDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFTextController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a new label controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The context adapter
	 * @return A label controller
	 */
	public IEEFLabelController createLabelController(EEFLabelDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFLabelController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a new button controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A button controller
	 */
	public IEEFButtonController createButtonController(EEFButtonDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFButtonController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a new select controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A label controller
	 */
	public IEEFSelectController createSelectController(EEFSelectDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFSelectController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a new checkbox controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A checkbox controller
	 */
	public IEEFCheckboxController createCheckboxController(EEFCheckboxDescription description, IVariableManager variableManager,
			IInterpreter interpreter, EditingContextAdapter editingContextAdapter) {
		return new EEFCheckboxController(description, variableManager, interpreter, editingContextAdapter);

	}

	/**
	 * Creates a new radio controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A radio controller
	 */
	public IEEFRadioController createRadioController(EEFRadioDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFRadioController(description, variableManager, interpreter, editingContextAdapter);
	}

	/**
	 * Creates a section controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return The section controller.
	 */
	public IEEFSectionController createSectionController(EEFPageDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFSectionController(variableManager, interpreter, description, editingContextAdapter);
	}

	/**
	 * Creates a list controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return The list controller.
	 */
	public IEEFListController createListController(EEFListDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		return new EEFListController(variableManager, interpreter, description, editingContextAdapter);
	}

	/**
	 * Creates a new hyperlink controller.
	 *
	 * @param description
	 *            The description
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 * @return A hyperlink controller
	 */
	public IEEFHyperlinkController createHyperlinkController(EEFHyperlinkDescription description, IVariableManager variableManager,
			IInterpreter interpreter, EditingContextAdapter editingContextAdapter) {
		return new EEFHyperlinkController(description, variableManager, interpreter, editingContextAdapter);
	}
}
