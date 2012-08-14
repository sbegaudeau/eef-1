/**
 * 
 */
package org.eclipse.emf.eef.runtime.tests.suite;

import org.eclipse.emf.eef.runtime.tests.core.CustomViewHandlerTests;
import org.eclipse.emf.eef.runtime.tests.core.SampleEditingTests;
import org.eclipse.emf.eef.runtime.tests.core.binding.EClassBindingResolverTests;
import org.eclipse.emf.eef.runtime.tests.core.binding.EditingModelBuilderTests;
import org.eclipse.emf.eef.runtime.tests.core.binding.PlatformResourceRegistryResourceBinding;
import org.eclipse.emf.eef.runtime.tests.core.binding.PropertyBindingTests;
import org.eclipse.emf.eef.runtime.tests.core.binding.SampleMultiViewsEditingTests;
import org.eclipse.emf.eef.runtime.tests.core.binding.ViewsComponentsLinkingTests;
import org.eclipse.emf.eef.runtime.tests.core.notify.EditingListenerTests;
import org.eclipse.emf.eef.runtime.tests.core.notify.ViewNotificationTests;
import org.eclipse.emf.eef.runtime.tests.core.service.EMFServiceProvidingTests;
import org.eclipse.emf.eef.runtime.tests.ui.EEFViewerTests;
import org.eclipse.emf.eef.runtime.tests.ui.PropertiesEditingViewTests;
import org.eclipse.emf.eef.runtime.tests.ui.notify.ModelNotificationInBeanViewTests;
import org.eclipse.emf.eef.runtime.tests.ui.notify.ModelNotificationInPropertiesEditingViewTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CustomViewHandlerTests.class,
	SampleEditingTests.class,
	EClassBindingResolverTests.class,
	EditingModelBuilderTests.class,
	PlatformResourceRegistryResourceBinding.class,
	PropertyBindingTests.class,
	SampleMultiViewsEditingTests.class,
	ViewsComponentsLinkingTests.class,
	EditingListenerTests.class,
	ViewNotificationTests.class,
	EMFServiceProvidingTests.class,
	EEFViewerTests.class,
	PropertiesEditingViewTests.class,
	ModelNotificationInBeanViewTests.class,
	ModelNotificationInPropertiesEditingViewTests.class
})
public class AllTests {

}