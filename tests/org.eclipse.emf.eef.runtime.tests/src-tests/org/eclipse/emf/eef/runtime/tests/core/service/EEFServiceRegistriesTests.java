/**
 * 
 */
package org.eclipse.emf.eef.runtime.tests.core.service;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.internal.services.viewhandler.ViewHandlerProviderRegistryImpl;
import org.eclipse.emf.eef.runtime.services.EEFServiceSimpleRegistry;
import org.eclipse.emf.eef.runtime.services.PriorityCircularityException;
import org.eclipse.emf.eef.runtime.services.emf.EMFService;
import org.eclipse.emf.eef.runtime.services.emf.EMFServiceProvider;
import org.eclipse.emf.eef.runtime.services.viewhandler.ViewHandler;
import org.eclipse.emf.eef.runtime.services.viewhandler.ViewHandlerProvider;
import org.eclipse.emf.eef.runtime.services.viewhandler.ViewHandlerProviderRegistry;
import org.eclipse.emf.eef.runtime.services.viewhandler.exceptions.ViewConstructionException;
import org.eclipse.emf.eef.runtime.services.viewhandler.exceptions.ViewHandlingException;
import org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironmentBuilder;
import org.junit.Test;

/**
 * This class test the standard registries for EEF Services.
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class EEFServiceRegistriesTests {
	
	/**
	 * This class tests the most common EEF Service Registry. The default registry is loaded and
	 * a service (an {@link EMFService}) is get for the EcorePackage. Then a specific service is
	 * added to the registry and a new service is get. The test checks that the two services are 
	 * different.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testCustomizedEMFService() {
		EEFTestEnvironmentBuilder builder = new EEFTestEnvironmentBuilder();
		EMFServiceProvider emfServiceProvider = builder.createEMFServiceProvider(builder.createEMFServices());
		assertTrue("Bad type of EMFServiceRegistry.", emfServiceProvider instanceof EEFServiceSimpleRegistry);
		EMFService defaultEMFService = emfServiceProvider.getEMFServiceForPackage(EcorePackage.eINSTANCE);
		((EEFServiceSimpleRegistry<EPackage, EMFService>)emfServiceProvider).addService(new CustomEMFService());
		EMFService customEMFService = emfServiceProvider.getEMFServiceForPackage(EcorePackage.eINSTANCE);
		assertNotSame("Bad type of EMFService returned.", defaultEMFService, customEMFService);
	}
	
	@Test
	public void testOrderedEEFServiceRegistrySelection() {
		EEFTestEnvironmentBuilder builder = new EEFTestEnvironmentBuilder();
		ViewHandlerProviderRegistry registry = null;
		registry = builder.createEmptyViewHandlerProviderRegistry();
		assertTrue("Bad type of ViewHandlerProviderRegistry.", registry instanceof ViewHandlerProviderRegistryImpl);
		ViewHandlerProviderRegistryImpl vhpRegistry = (ViewHandlerProviderRegistryImpl) registry;
		String component1 = "component1";
		String component2 = "component2";
		String component3 = "component3";
		String component4 = "component4";
		String component5 = "component5";
		String component6 = "component6";
		String component7 = "component7";
		String component8 = "component8";
		final Object obj1 = "Object 1";
		final Object obj2 = "Object 2";
		
		// this adds sequence test many case of the graph creation algorithm (using proxies or not, ...)
		ViewHandlerProvider result1 = addToRegistry(vhpRegistry, component1, obj1, component3);
		ViewHandlerProvider result2 = addToRegistry(vhpRegistry, component2, obj1, component3);
		addToRegistry(vhpRegistry, component4, obj1, component5);
		addToRegistry(vhpRegistry, component3, obj1, component4, component6);
		addToRegistry(vhpRegistry, component6, obj1);
		addToRegistry(vhpRegistry, component7, obj2, component8);
		addToRegistry(vhpRegistry, component8, obj2, component4);		
		
		addToRegistry(vhpRegistry, component5, obj1);
		ViewHandlerProvider viewHandlerProvider = vhpRegistry.getViewHandlerProvider(obj1);
		assertTrue("Bad comparison algorithm.", viewHandlerProvider == result1 || viewHandlerProvider == result2);
		
	}

	@Test
	public void testOrderedEEFServiceRegistryNonCircularity() {
		EEFTestEnvironmentBuilder builder = new EEFTestEnvironmentBuilder();
		ViewHandlerProviderRegistry registry = null;
		registry = builder.createEmptyViewHandlerProviderRegistry();
		assertTrue("Bad type of ViewHandlerProviderRegistry.", registry instanceof ViewHandlerProviderRegistryImpl);
		ViewHandlerProviderRegistryImpl vhpRegistry = (ViewHandlerProviderRegistryImpl) registry;
		String component1 = "component1";
		String component2 = "component2";
		String component3 = "component3";
		final Object obj1 = "Object 1";
		
		addToRegistry(vhpRegistry, component1, obj1, component2);
		addToRegistry(vhpRegistry, component2, obj1, component3);

		// this phase ensure that we can't create circularity in priority graph
		ViewHandlerProvider result3 = addToRegistry(vhpRegistry, component3, obj1, component2);
		assertNull("Bad circularity check", result3);		
	}

	protected ViewHandlerProvider addToRegistry(ViewHandlerProviderRegistryImpl registry, String componentName, final Object obj, final String... priorityOver) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(EEFTestEnvironmentBuilder.COMPONENT_NAME_KEY, componentName);
		if (priorityOver != null && priorityOver.length > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < priorityOver.length; i++) {
				builder.append(priorityOver[i]);
				if (i <= (priorityOver.length - 1)) {
					builder.append(",");
				}
			}
			properties.put(EEFTestEnvironmentBuilder.PRIORITY_OVER_KEY, builder.toString());
		}
		final ViewHandler<Object> viewHandler = new ViewHandler<Object>() {
			
			public Object createView(Object... args) throws ViewConstructionException { return null; }

			public Object getView() { return null;	}

			public ViewHandlerProvider getProvider() { return null;	}

			public void initView(PropertiesEditingComponent component) { }
 
			public void setValue(Object field, Object value) throws ViewHandlingException { }

			public void unsetValue(Object field) throws ViewHandlingException { }

			public void addValue(Object field, Object newValue) throws ViewHandlingException { }

			public void addAllValues(Object field, Collection<?> values) throws ViewHandlingException { }

			public void removeValue(Object field, Object value) throws ViewHandlingException { }

			public void removeAllValues(Object field, Collection<?> values) throws ViewHandlingException { }

			public void moveValue(Object field, Object value, int newIndex) throws ViewHandlingException { }

			public void dispose() { }
			
		};
		ViewHandlerProvider handler = new VHPTest(viewHandler, obj);
		
		try {
			registry.addService(handler, properties);
		} catch (PriorityCircularityException e) {
			return null;
		}
		return handler;
	}

	private final class VHPTest implements ViewHandlerProvider {
		private final ViewHandler<Object> viewHandler;
		private final Object obj;

		private VHPTest(ViewHandler<Object> viewHandler, Object obj) {
			this.viewHandler = viewHandler;
			this.obj = obj;
		}

		public boolean serviceFor(Object element) {
			return element == obj;
		}

		public ViewHandler<?> getHandler(PropertiesEditingComponent component, Object view) {
			return viewHandler;
		}
	}

	private final class CustomEMFService implements EMFService {
		public boolean serviceFor(EPackage element) {
			return element == EcorePackage.eINSTANCE;
		}

		public EStructuralFeature mapFeature(EObject editedObject, EStructuralFeature feature) {
			return null;
		}

		public Notifier highestNotifier(EObject src) {
			return null;
		}

		public boolean equals(EPackage ePack1, EPackage ePack2) {
			return false;
		}

		public boolean equals(EClass eClass1, EClass eClass2) {
			return false;
		}
	}

}