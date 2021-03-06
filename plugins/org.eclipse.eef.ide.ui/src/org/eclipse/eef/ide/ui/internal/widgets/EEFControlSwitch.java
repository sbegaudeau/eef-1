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
package org.eclipse.eef.ide.ui.internal.widgets;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eef.EEFButtonDescription;
import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingIf;
import org.eclipse.eef.EEFHyperlinkDescription;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFListDescription;
import org.eclipse.eef.EEFRadioDescription;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EefPackage;
import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.utils.EvalFactory;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManager;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManagerProvider;
import org.eclipse.eef.ide.ui.internal.EEFIdeUiPlugin;
import org.eclipse.eef.ide.ui.internal.Messages;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.widgets.Composite;

/**
 * Utility class used to dispatch the creation of the control to create.
 *
 * @author sbegaudeau
 */
public class EEFControlSwitch {
	/**
	 * The interpreter.
	 */
	private IInterpreter interpreter;

	/**
	 * The editing context adapter.
	 */
	private EditingContextAdapter editingContextAdapter;

	/**
	 * The constructor.
	 *
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The editing context adapter
	 */
	public EEFControlSwitch(IInterpreter interpreter, EditingContextAdapter editingContextAdapter) {
		this.interpreter = interpreter;
		this.editingContextAdapter = editingContextAdapter;
	}

	/**
	 * Creates the control from the given description in the given parent.
	 *
	 * @param parent
	 *            The parent in which the control should be created
	 * @param formContainer
	 *            The container of the form
	 * @param controlDescription
	 *            The description of the control to be created
	 * @param variableManager
	 *            The variable manager
	 * @return The list of all the lifecycle manager that have been created. One description can create multiple
	 *         controls and thus multiple lifecycle managers (for example dynamic mappings).
	 */
	public List<IEEFLifecycleManager> doCreate(Composite parent, IEEFFormContainer formContainer, EEFControlDescription controlDescription,
			IVariableManager variableManager) {
		List<IEEFLifecycleManager> lifecycleManagers = new ArrayList<IEEFLifecycleManager>();
		if (controlDescription instanceof EEFContainerDescription) {
			IVariableManager childVariableManager = variableManager.createChild();
			IEEFLifecycleManager eefContainerLifecycleManager = null;

			IEEFLifecycleManagerProvider eefLifecycleManagerProvider = EEFIdeUiPlugin.getPlugin().getEEFLifecycleManagerProvider(controlDescription);
			if (eefLifecycleManagerProvider != null) {
				eefContainerLifecycleManager = eefLifecycleManagerProvider.getLifecycleManager(controlDescription, childVariableManager, interpreter,
						this.editingContextAdapter);
			} else {
				eefContainerLifecycleManager = new EEFContainerLifecycleManager((EEFContainerDescription) controlDescription, childVariableManager,
						this.interpreter, this.editingContextAdapter);
			}

			eefContainerLifecycleManager.createControl(parent, formContainer);
			lifecycleManagers.add(eefContainerLifecycleManager);
		} else if (controlDescription instanceof EEFWidgetDescription) {
			lifecycleManagers.addAll(
					this.createWidgetControl(parent, formContainer, (EEFWidgetDescription) controlDescription, variableManager.createChild()));
		} else if (controlDescription instanceof EEFDynamicMappingFor) {
			lifecycleManagers
					.addAll(this.createDynamicMappingControl(parent, formContainer, (EEFDynamicMappingFor) controlDescription, variableManager));
		}
		return lifecycleManagers;
	}

	/**
	 * Creates the control for the widget with the given {@link EEFWidgetDescription}.
	 *
	 * @param parent
	 *            The composite parent
	 * @param formContainer
	 *            The form container
	 * @param widgetDescription
	 *            The description of the widget to create
	 * @param childVariableManager
	 *            The child variable manager
	 * @return The lifecycle managers created for the widget(s)
	 */
	private List<IEEFLifecycleManager> createWidgetControl(Composite parent, IEEFFormContainer formContainer, EEFWidgetDescription widgetDescription,
			IVariableManager childVariableManager) {
		List<IEEFLifecycleManager> lifecycleManagers = new ArrayList<IEEFLifecycleManager>();

		// First, let's see if an external contributor does not want to handle this description
		IEEFLifecycleManagerProvider eefLifecycleManagerProvider = EEFIdeUiPlugin.getPlugin().getEEFLifecycleManagerProvider(widgetDescription);
		if (eefLifecycleManagerProvider != null) {
			// We have a lifecycle manager provider, let's use it
			IEEFLifecycleManager eefLifecycleManager = eefLifecycleManagerProvider.getLifecycleManager(widgetDescription, childVariableManager,
					interpreter, this.editingContextAdapter);
			if (eefLifecycleManager != null) {
				eefLifecycleManager.createControl(parent, formContainer);
				lifecycleManagers.add(eefLifecycleManager);
			}
		} else if (widgetDescription instanceof EEFCustomWidgetDescription) {
			// A custom widget cannot be supported if the lifecycle manager retrieved is null
			String message = MessageFormat.format(Messages.EEFIdeUiPlugin_lifecycleManagerNotFound, widgetDescription.getIdentifier());
			EEFIdeUiPlugin.getPlugin().error(message);
		} else {
			// We have not found a custom lifecycle manager, fallback to the default behavior
			if (widgetDescription instanceof EEFTextDescription) {
				EEFTextDescription eefTextDescription = (EEFTextDescription) widgetDescription;

				EEFTextLifecycleManager eefTextLifecycleManager = new EEFTextLifecycleManager(eefTextDescription, childVariableManager, interpreter,
						this.editingContextAdapter);
				eefTextLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefTextLifecycleManager);
			} else if (widgetDescription instanceof EEFLabelDescription) {
				EEFLabelDescription eefLabelDescription = (EEFLabelDescription) widgetDescription;

				EEFLabelLifecycleManager eefLabelLifecycleManager = new EEFLabelLifecycleManager(eefLabelDescription, childVariableManager,
						interpreter, this.editingContextAdapter);
				eefLabelLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefLabelLifecycleManager);
			} else if (widgetDescription instanceof EEFSelectDescription) {
				EEFSelectDescription eefSelectDescription = (EEFSelectDescription) widgetDescription;

				EEFSelectLifecycleManager eefSelectLifecycleManager = new EEFSelectLifecycleManager(eefSelectDescription, childVariableManager,
						interpreter, this.editingContextAdapter);
				eefSelectLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefSelectLifecycleManager);
			} else if (widgetDescription instanceof EEFRadioDescription) {
				EEFRadioDescription eefRadioDescription = (EEFRadioDescription) widgetDescription;

				EEFRadioLifecycleManager eefRadioLifecycleManager = new EEFRadioLifecycleManager(eefRadioDescription, childVariableManager,
						interpreter, this.editingContextAdapter);
				eefRadioLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefRadioLifecycleManager);
			} else if (widgetDescription instanceof EEFCheckboxDescription) {
				EEFCheckboxDescription eefCheckboxDescription = (EEFCheckboxDescription) widgetDescription;

				EEFCheckboxLifecycleManager eefCheckboxLifecycleManager = new EEFCheckboxLifecycleManager(eefCheckboxDescription,
						childVariableManager, interpreter, this.editingContextAdapter);
				eefCheckboxLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefCheckboxLifecycleManager);
			} else if (widgetDescription instanceof EEFButtonDescription) {
				EEFButtonDescription eefButtonDescription = (EEFButtonDescription) widgetDescription;

				EEFButtonLifecycleManager eefButtonLifecycleManager = new EEFButtonLifecycleManager(eefButtonDescription, childVariableManager,
						interpreter, this.editingContextAdapter);
				eefButtonLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefButtonLifecycleManager);
			} else if (widgetDescription instanceof EEFListDescription) {
				EEFListDescription eefListDescription = (EEFListDescription) widgetDescription;

				IEEFLifecycleManager eefListLifecycleManager = new EEFListLifecycleManager(eefListDescription, childVariableManager, interpreter,
						this.editingContextAdapter);
				eefListLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefListLifecycleManager);
			} else if (widgetDescription instanceof EEFHyperlinkDescription) {
				EEFHyperlinkDescription eefHyperlinkDescription = (EEFHyperlinkDescription) widgetDescription;

				EEFHyperlinkLifecycleManager eefHyperlinkLifecycleManager = new EEFHyperlinkLifecycleManager(eefHyperlinkDescription,
						childVariableManager, interpreter, this.editingContextAdapter);
				eefHyperlinkLifecycleManager.createControl(parent, formContainer);

				lifecycleManagers.add(eefHyperlinkLifecycleManager);
			}
		}

		return lifecycleManagers;
	}

	/**
	 * Creates the widgets for the dynamic mapping defined in the description.
	 *
	 * @param parent
	 *            The composite parent
	 * @param formContainer
	 *            The form container
	 * @param dynamicMappingFor
	 *            The root of a dynamic mapping definition
	 * @param variableManager
	 *            The variable manager
	 * @return The list of the lifecycle manager created
	 */
	private List<IEEFLifecycleManager> createDynamicMappingControl(Composite parent, IEEFFormContainer formContainer,
			EEFDynamicMappingFor dynamicMappingFor, IVariableManager variableManager) {
		List<IEEFLifecycleManager> lifecycleManagers = new ArrayList<IEEFLifecycleManager>();

		String iterableExpression = dynamicMappingFor.getIterableExpression();
		EAttribute iterableEAttribute = EefPackage.Literals.EEF_DYNAMIC_MAPPING_FOR__ITERABLE_EXPRESSION;
		String iterator = dynamicMappingFor.getIterator();

		EAttribute ifExpressionEAttribute = EefPackage.Literals.EEF_DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION;

		Object iterableExpressionResult = EvalFactory.of(this.interpreter, variableManager).logIfBlank(iterableEAttribute)
				.evaluate(iterableExpression);
		for (Object object : Util.asCollection(iterableExpressionResult, Object.class)) {
			Map<String, Object> switchExpressionVariables = new HashMap<String, Object>();
			switchExpressionVariables.put(EEFExpressionUtils.SELF, variableManager.getVariables().get(EEFExpressionUtils.SELF));
			switchExpressionVariables.put(EEFExpressionUtils.INPUT, variableManager.getVariables().get(EEFExpressionUtils.INPUT));
			switchExpressionVariables.put(iterator, object);

			List<EEFDynamicMappingIf> dynamicMappingIfs = dynamicMappingFor.getIfs();
			for (EEFDynamicMappingIf dynamicMappingIf : dynamicMappingIfs) {
				Boolean isValid = EvalFactory.of(this.interpreter, switchExpressionVariables).logIfInvalidType(Boolean.class)
						.logIfBlank(ifExpressionEAttribute).evaluate(dynamicMappingIf.getPredicateExpression());
				if (isValid != null && isValid.booleanValue()) {
					IVariableManager childVariableManager = variableManager.createChild();
					childVariableManager.put(iterator, object);
					lifecycleManagers.addAll(this.createWidgetControl(parent, formContainer, dynamicMappingIf.getWidget(), childVariableManager));
				}
			}
		}

		return lifecycleManagers;
	}
}
