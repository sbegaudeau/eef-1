/**
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 */
package org.eclipse.eef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EEF Conditional Style</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.eef.EEFConditionalStyle#getPreconditionExpression <em>Precondition Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eef.EefPackage#getEEFConditionalStyle()
 * @model abstract="true"
 * @generated
 */
public interface EEFConditionalStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition Expression</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Precondition Expression</em>' attribute.
	 * @see #setPreconditionExpression(String)
	 * @see org.eclipse.eef.EefPackage#getEEFConditionalStyle_PreconditionExpression()
	 * @model
	 * @generated
	 */
	String getPreconditionExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.eef.EEFConditionalStyle#getPreconditionExpression <em>Precondition
	 * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Precondition Expression</em>' attribute.
	 * @see #getPreconditionExpression()
	 * @generated
	 */
	void setPreconditionExpression(String value);

} // EEFConditionalStyle
