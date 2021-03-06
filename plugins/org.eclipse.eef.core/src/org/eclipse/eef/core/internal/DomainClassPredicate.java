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
package org.eclipse.eef.core.internal;

import java.util.function.Predicate;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.eef.core.api.IEEFDomainClassTester;
import org.eclipse.emf.ecore.EObject;

/**
 * This class is used to filter EObjects using a domain class.
 *
 * @author pcdavid
 * @author sbegaudeau
 */
public class DomainClassPredicate implements Predicate<Object> {
	/**
	 * The domain class name.
	 */
	private final String domainClassName;

	/**
	 * The domain class tester.
	 */
	private IEEFDomainClassTester domainClassTester;

	/**
	 * The constructor.
	 *
	 * @param domainClassName
	 *            A domain class "packageName::className" or "packageName.className".
	 * @param domainClassTester
	 *            The class used to test the domain class
	 */
	public DomainClassPredicate(String domainClassName, IEEFDomainClassTester domainClassTester) {
		this.domainClassName = domainClassName;
		this.domainClassTester = domainClassTester;
	}

	@Override
	public boolean test(Object input) {
		if (input instanceof EObject) {
			return this.domainClassTester.eInstanceOf((EObject) input, domainClassName);
		}
		return Util.isBlank(domainClassName);
	}

}
