/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.ide.ui.internal.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFDynamicMappingCase;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingSwitch;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.utils.Util;
import org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.widgets.Composite;

/**
 * This class will handle the lifecycle of the {@link EEFContainerDescription}.
 *
 * @author sbegaudeau
 */
public class EEFContainerLifecycleManager implements ILifecycleManager {

	/**
	 * The variable manager.
	 */
	private IVariableManager variableManager;

	/**
	 * The interpreter.
	 */
	private IInterpreter interpreter;

	/**
	 * The editing domain.
	 */
	private TransactionalEditingDomain editingDomain;

	/**
	 * The description of the container.
	 */
	private EEFContainerDescription description;

	/**
	 * The lifecycle managers of the child of the container.
	 */
	private List<ILifecycleManager> lifecycleManagers = new ArrayList<ILifecycleManager>();

	/**
	 * The constructor.
	 *
	 * @param description
	 *            The description of the container
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingDomain
	 *            The editing domain
	 */
	public EEFContainerLifecycleManager(EEFContainerDescription description, IVariableManager variableManager, IInterpreter interpreter,
			TransactionalEditingDomain editingDomain) {
		this.description = description;
		this.variableManager = variableManager;
		this.interpreter = interpreter;
		this.editingDomain = editingDomain;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.internal.widgets.ILifecycleManager#createControl(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage)
	 */
	@Override
	public void createControl(Composite parent, EEFTabbedPropertySheetPage tabbedPropertySheetPage) {
		List<EEFWidgetDescription> widgets = this.description.getWidgets();
		for (EEFWidgetDescription eefWidgetDescription : widgets) {
			this.createWidgetControl(parent, tabbedPropertySheetPage, eefWidgetDescription, this.variableManager.createChild());
		}

		List<EEFDynamicMappingFor> dynamicMappings = this.description.getDynamicMappings();
		for (EEFDynamicMappingFor dynamicMappingFor : dynamicMappings) {
			this.createDynamicMappingControl(parent, tabbedPropertySheetPage, dynamicMappingFor);
		}
	}

	/**
	 * Creates the control for the widget with the given {@link EEFWidgetDescription}.
	 *
	 * @param parent
	 *            The composite parent
	 * @param tabbedPropertySheetPage
	 *            The tabbed property sheet page
	 * @param eefWidgetDescription
	 *            The description of the widget to create
	 * @param childVariableManager
	 *            The variable manager to use for the widget to create
	 */
	private void createWidgetControl(Composite parent, EEFTabbedPropertySheetPage tabbedPropertySheetPage, EEFWidgetDescription eefWidgetDescription,
			IVariableManager childVariableManager) {
		if (eefWidgetDescription instanceof EEFTextDescription) {
			EEFTextDescription eefTextDescription = (EEFTextDescription) eefWidgetDescription;

			EEFTextLifecycleManager eefTextLifecycleManager = new EEFTextLifecycleManager(eefTextDescription, childVariableManager, interpreter,
					editingDomain);
			eefTextLifecycleManager.createControl(parent, tabbedPropertySheetPage);

			this.lifecycleManagers.add(eefTextLifecycleManager);
		} else if (eefWidgetDescription instanceof EEFLabelDescription) {
			EEFLabelDescription eefLabelDescription = (EEFLabelDescription) eefWidgetDescription;

			EEFLabelLifecycleManager eefLabelLifecycleManager = new EEFLabelLifecycleManager(eefLabelDescription, childVariableManager, interpreter);
			eefLabelLifecycleManager.createControl(parent, tabbedPropertySheetPage);

			this.lifecycleManagers.add(eefLabelLifecycleManager);
		}
	}

	/**
	 * Creates the widgets for the dynamic mapping defined in the description.
	 *
	 * @param parent
	 *            The composite parent
	 * @param tabbedPropertySheetPage
	 *            The tabbed property sheet page
	 * @param dynamicMappingFor
	 *            The root of a dynamic mapping definition
	 */
	private void createDynamicMappingControl(Composite parent, EEFTabbedPropertySheetPage tabbedPropertySheetPage,
			EEFDynamicMappingFor dynamicMappingFor) {
		String domainClassExpression = dynamicMappingFor.getDomainClassExpression();

		EEFDynamicMappingSwitch dynamicMappingSwitch = dynamicMappingFor.getSwitch();
		String switchExpression = dynamicMappingSwitch.getSwitchExpression();

		if (!Util.isBlank(domainClassExpression) && !Util.isBlank(switchExpression)) {
			IEvaluationResult domainClassExpressionResult = this.interpreter.evaluateExpression(this.variableManager.getVariables(),
					domainClassExpression);
			if (domainClassExpressionResult.success()) {
				for (EObject eObject : domainClassExpressionResult.asEObjects()) {
					String iterator = dynamicMappingFor.getIterator();

					Map<String, Object> switchExpressionVariables = new HashMap<String, Object>();
					switchExpressionVariables.put(EEFExpressionUtils.SELF, this.variableManager.getVariables().get(EEFExpressionUtils.SELF));
					switchExpressionVariables.put(iterator, eObject);
					IEvaluationResult switchExpressionResult = this.interpreter.evaluateExpression(switchExpressionVariables, switchExpression);
					EEFWidgetDescription eefWidgetDescription = this.getWidgetDescription(switchExpressionResult, dynamicMappingSwitch.getCases());

					if (eefWidgetDescription != null) {
						IVariableManager childVariableManager = this.variableManager.createChild();
						childVariableManager.put(iterator, eObject);
						this.createWidgetControl(parent, tabbedPropertySheetPage, eefWidgetDescription, childVariableManager);
					}
				}
			}
		}
	}

	/**
	 * Returns the widget description of the {@link EEFDynamicMappingCase} with a caseExpression equal to the result of
	 * the switchExpression.
	 *
	 * @param switchExpressionResult
	 *            The result of the evaluation of the switchExpression
	 * @param cases
	 *            All the possible cases for the switch considered
	 * @return The {@link EEFWidgetDescription} for the matching case or <code>null</code> if the switch expression
	 *         result is not a success or if none of the {@link EEFDynamicMappingCase} matches
	 */
	private EEFWidgetDescription getWidgetDescription(IEvaluationResult switchExpressionResult, List<EEFDynamicMappingCase> cases) {
		if (switchExpressionResult.success()) {
			EEFDynamicMappingCase caseMatching = null;
			for (EEFDynamicMappingCase dynamicMappingCase : cases) {
				IEvaluationResult caseExpressionResult = this.interpreter.evaluateExpression(this.variableManager.getVariables(),
						dynamicMappingCase.getCaseExpression());
				if (caseExpressionResult.success() && caseExpressionResult.getValue() != null
						&& caseExpressionResult.getValue().equals(switchExpressionResult.getValue())) {
					caseMatching = dynamicMappingCase;
					break;
				}
			}

			if (caseMatching != null) {
				return caseMatching.getWidget();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.internal.widgets.ILifecycleManager#aboutToBeShown()
	 */
	@Override
	public void aboutToBeShown() {
		for (ILifecycleManager lifecycleManager : lifecycleManagers) {
			lifecycleManager.aboutToBeShown();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.internal.widgets.ILifecycleManager#refresh()
	 */
	@Override
	public void refresh() {
		for (ILifecycleManager lifecycleManager : lifecycleManagers) {
			lifecycleManager.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.internal.widgets.ILifecycleManager#aboutToBeHidden()
	 */
	@Override
	public void aboutToBeHidden() {
		for (ILifecycleManager lifecycleManager : lifecycleManagers) {
			lifecycleManager.aboutToBeHidden();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.internal.widgets.ILifecycleManager#dispose()
	 */
	@Override
	public void dispose() {
		for (ILifecycleManager lifecycleManager : lifecycleManagers) {
			lifecycleManager.dispose();
		}
	}

}