/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.tests.internal;

import java.io.IOException;
import java.util.Arrays;

import org.eclipse.eef.EefPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import static org.junit.Assert.fail;

/**
 * This test is used to make sure that all the models used are available.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle:javadocmethod" })
public class EEFDataTests {
	/**
	 * Test model used to display the label of a text widget.
	 */
	public static final String EEFTEXTCONTROLLERTESTS_LABEL = "/data/controllers/text/EEFTextControllerTests-Label.xmi"; //$NON-NLS-1$

	/**
	 * Test model used to compute the value of the text widget using the structural feature name.
	 */
	public static final String EEFTEXTCONTROLLERTESTS_VALUE = "/data/controllers/text/EEFTextControllerTests-Value.xmi"; //$NON-NLS-1$

	/**
	 * Test model used to compute the help of a text widget.
	 */
	public static final String EEFTEXTCONTROLLERTESTS_HELP = "/data/controllers/text/EEFTextControllerTests-Help.xmi"; //$NON-NLS-1$

	/**
	 * The current directory.
	 */
	public static final String CURRENTDIR = System.getProperty("user.dir"); //$NON-NLS-1$

	/**
	 * All the test models.
	 */
	private static final String[] MODELS = new String[] { EEFTEXTCONTROLLERTESTS_LABEL, EEFTEXTCONTROLLERTESTS_VALUE, EEFTEXTCONTROLLERTESTS_HELP, };

	/**
	 * Loads the resource with the given URI in the given resource set.
	 *
	 * @param resourceSet
	 *            The resource set
	 * @param uri
	 *            The uri
	 * @return The resource loaded or <code>null</code> if none could be found
	 */
	public static Resource loadResource(ResourceSet resourceSet, URI uri) {
		resourceSet.getPackageRegistry().put(EefPackage.eNS_URI, EefPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl()); //$NON-NLS-1$
		return resourceSet.getResource(uri, true);
	}

	@Test
	public void testAvailableModels() {
		Arrays.stream(MODELS).map(model -> URI.createFileURI(CURRENTDIR + model)).forEach(uri -> {
			Resource resource = EEFDataTests.loadResource(new ResourceSetImpl(), uri);
			assertThat(resource, notNullValue());
			try {
				resource.load(null);
			} catch (IOException e) {
				fail(e.getMessage());
			}
		});
	}
}