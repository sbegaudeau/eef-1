/*******************************************************************************
 * Copyright (c) 2016, 2018 Obeo.
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

import java.util.function.Consumer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.eef.EEFWidgetAction;

/**
 * The EEFHyperlinkController is responsible of supporting all the interactions with the widgets created for an
 * EEFHyperlinkDescription.
 *
 * @author mbats
 */
public interface IEEFHyperlinkController extends IEEFOnClickController {
	/**
	 * Register a consumer which will be called with the new value of the hyperlink when it will change.
	 *
	 * @param consumer
	 *            The consumer of the new value of the hyperlink
	 */
	void onNewValue(Consumer<Object> consumer);

	/**
	 * Remove the consumer of the new value of the hyperlink.
	 */
	void removeNewValueConsumer();

	/**
	 * Invoked when the user clicks on an action button.
	 *
	 * @param action
	 *            Widget action
	 * @return the status of the action execution
	 */
	IStatus action(EEFWidgetAction action);

	/**
	 * Compute the display value.
	 *
	 * @param value
	 *            Element associated to the hyperlink
	 * @return The display value
	 */
	String computeDisplayValue(Object value);

}
