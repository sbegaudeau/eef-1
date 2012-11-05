/**
 * 
 */
package org.eclipse.emf.eef.runtime.tests.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContextFactory;
import org.eclipse.emf.eef.runtime.editingModel.EditingModelBuilder;
import org.eclipse.emf.eef.runtime.editingModel.PropertiesEditingModel;
import org.eclipse.emf.eef.runtime.internal.context.PropertiesEditingContextFactoryImpl;
import org.eclipse.emf.eef.runtime.internal.services.emf.EMFServiceImpl;
import org.eclipse.emf.eef.runtime.notify.ModelChangesNotificationManager;
import org.eclipse.emf.eef.runtime.services.EEFService;
import org.eclipse.emf.eef.runtime.services.EEFServiceRegistry;
import org.eclipse.emf.eef.runtime.services.editingProviding.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.services.editingProviding.PropertiesEditingProviderImpl;
import org.eclipse.emf.eef.runtime.services.emf.EMFService;
import org.eclipse.emf.eef.runtime.services.impl.EEFServiceRegistryImpl;
import org.eclipse.emf.eef.runtime.services.impl.PriorityCircularityException;
import org.eclipse.emf.eef.runtime.services.viewhandler.ViewHandlerProvider;
import org.eclipse.emf.eef.runtime.tests.views.EClassMockView;
import org.eclipse.emf.eef.runtime.tests.views.RootView;
import org.eclipse.emf.eef.runtime.tests.views.SampleView;
import org.eclipse.emf.eef.runtime.ui.internal.services.view.ViewServiceImpl;
import org.eclipse.emf.eef.runtime.ui.services.view.ViewService;
import org.eclipse.emf.eef.runtime.ui.view.handlers.editingview.PropertiesEditingViewHandlerProvider;
import org.eclipse.emf.eef.runtime.ui.view.handlers.reflect.ReflectViewHandlerProvider;
import org.eclipse.emf.eef.runtime.ui.view.handlers.swt.SWTViewHandlerProvider;
import org.eclipse.emf.eef.runtime.ui.view.lock.EditingViewLockManager;
import org.eclipse.emf.eef.runtime.ui.view.notify.EditingViewNotifier;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.ToolkitPropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.EMFPropertiesToolkit;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.swttoolkit.SWTToolkit;
import org.eclipse.emf.eef.runtime.view.lock.EEFLockManager;
import org.eclipse.emf.eef.runtime.view.lock.impl.NullLockManager;
import org.eclipse.emf.eef.runtime.view.notify.EEFNotifier;
import org.eclipse.emf.eef.views.Container;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.emf.eef.views.View;
import org.eclipse.emf.eef.views.ViewsFactory;
import org.eclipse.emf.eef.views.toolkits.Toolkit;
import org.eclipse.emf.eef.views.toolkits.Widget;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EEFTestEnvironment {

	public static final String TESTS_EDITING_MODEL_ID = "org.eclipse.emf.eef.runtime.tests.model";
	public static final boolean FIRST_ECLASS_SAMPLE_ABSTRACTNESS = false;
	public static final String FIRST_ECLASS_SAMPLE_NAME = "EClass1";

	public static final String COMPONENT_NAME_KEY = "component.name";
	public static final String OBJECTCLASS_KEY = "objectClass";
	public static final String PRIORITY_OVER_KEY = "priority.over";

	public static final String REFLECT_VIEW_HANDLER_PROVIDER_NAME = "ReflectViewHandlerProvider";
	public static final String SWT_VIEW_HANDLER_PROVIDER_NAME = "SWTViewHandlerProvider";
	public static final String PROPERTIES_EDITING_VIEW_HANDLER_PROVIDER_NAME = "PropertiesEditingViewHandlerProvider";

	private Builder builder;

	private EEFTestEnvironment(Builder builder) {
		this.builder = builder;
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getSampleModel()
	 */
	public EObject getSampleModel() {
		return builder.getSampleModel();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getEditedObject()
	 */
	public EObject getEditedObject() {
		return builder.getEditedObject();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getAdapterFactory()
	 */
	public AdapterFactory getAdapterFactory() {
		return builder.getAdapterFactory();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getEditingModel()
	 */
	public PropertiesEditingModel getEditingModel() {
		return builder.getEditingModel();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getEMFServiceProvider()
	 */
	public EEFServiceRegistry getServiceRegistry() {
		return builder.getServiceRegistry();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getModelChangesNotificationManager()
	 */
	public ModelChangesNotificationManager getModelChangesNotificationManager() {
		return builder.getModelChangesNotificationManager();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getEditingContextFactory()
	 */
	public PropertiesEditingContextFactory getEditingContextFactory() {
		return builder.getEditingContextFactory();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.eef.runtime.tests.util.EEFTestEnvironment.Builder#getEditingContext()
	 */
	public PropertiesEditingContext getEditingContext() {
		return builder.getEditingContext();
	}

	public final static class Builder {

		private ResourceSet resourceSet;

		private EObject sampleModel;
		private EObject editedObject;
		private PropertiesEditingModel editingModel;
		private AdapterFactory adapterFactory;

		private EEFServiceRegistry serviceRegistry;
		private Collection<Class<? extends EEFService<?>>> preloadedServices;
		private Collection<EEFServiceDescriptor<? extends EEFService<Object>>> eefServices;
		private ModelChangesNotificationManager notificationManager;

		private PropertiesEditingContextFactory editingContextFactory;
		private PropertiesEditingContext editingContext;

		public Builder() {
			sampleModel = null;
			editedObject = null;
			editingModel = null;
			adapterFactory = null;
			preloadedServices = new ArrayList<Class<? extends EEFService<?>>>();
			eefServices = new ArrayList<EEFTestEnvironment.EEFServiceDescriptor<? extends EEFService<Object>>>();
			serviceRegistry = null;
			notificationManager = null;
			editingContextFactory = null;
			editingContext = null;
		}

		public ResourceSet getResourceSet() {
			if (resourceSet == null) {
				resourceSet = new ResourceSetImpl();
			}
			return resourceSet;
		}

		public EObject getSampleModel() {
			if (sampleModel == null) {
				sampleModel = createEcoreSampleModel();
			}
			return sampleModel;
		}

		public EObject getEditedObject() {
			if (editedObject == null) {
				editedObject = createEditedObject();
			}
			return editedObject;
		}

		public AdapterFactory getAdapterFactory() {
			if (adapterFactory == null) {
				adapterFactory = createAdapterFactory();
			}
			return adapterFactory;
		}

		public PropertiesEditingModel getEditingModel() {
			if (editingModel == null) {
				editingModel = createEditingModel();
			}
			return editingModel;
		}

		public EEFServiceRegistry getServiceRegistry() {
			if (serviceRegistry == null) {
				serviceRegistry = createServiceRegistry();
			}
			return serviceRegistry;
		}

		public ModelChangesNotificationManager getModelChangesNotificationManager() {
			if (notificationManager == null) {
				notificationManager = createNotificationManager();
			}
			return notificationManager;
		}

		public PropertiesEditingContextFactory getEditingContextFactory() {
			if (editingContextFactory == null) {
				editingContextFactory = createPropertiesEditingContextFactory();
			}
			return editingContextFactory;
		}

		public PropertiesEditingContext getEditingContext() {
			if (editingContext == null) {
				editingContext = createEditingContext();
			}
			return editingContext;
		}	

		/**
		 * @param sampleModel the sampleModel to set
		 * @return 
		 */
		public Builder setSampleModel(EObject sampleModel) {
			this.sampleModel = sampleModel;
			return this;
		}
		/**
		 * @param editedObject the editedObject to set
		 * @return 
		 */
		public Builder setEditedObject(EObject editedObject) {
			this.editedObject = editedObject;
			return this;
		}
		/**
		 * @param editingModel the editingModel to set
		 */
		public Builder setEditingModel(PropertiesEditingModel editingModel) {
			this.editingModel = editingModel;
			return this;
		}
		/**
		 * @param adapterFactory the adapterFactory to set
		 */
		public Builder setAdapterFactory(AdapterFactory adapterFactory) {
			this.adapterFactory = adapterFactory;
			return this;
		}

		/**
		 * @param serviceRegistry
		 * @return
		 */
		public Builder setServiceRegistry(EEFServiceRegistry serviceRegistry) {
			this.serviceRegistry= serviceRegistry;
			return this;
		}
		
		public Builder setPreloadedService(Class<? extends EEFService<?>> serviceKind, Collection<EEFServiceDescriptor<? extends EEFService<Object>>> descriptors) {
			preloadedServices.add(serviceKind);
			eefServices.addAll(descriptors);
			return this;
		}

		/**
		 * @param notificationManager the notificationManager to set
		 */
		public Builder setNotificationManager(ModelChangesNotificationManager notificationManager) {
			this.notificationManager = notificationManager;
			return this;
		}

		/**
		 * @param editingContextFactory the editingContextFactory to set
		 */
		public Builder setEditingContextFactory(PropertiesEditingContextFactory editingContextFactory) {
			this.editingContextFactory = editingContextFactory;
			return this;
		}
		/**
		 * @param editingContext the editingContext to set
		 */
		public Builder setEditingContext(PropertiesEditingContext editingContext) {
			this.editingContext = editingContext;
			return this;
		}

		public EEFTestEnvironment build() {
			return new EEFTestEnvironment(this);
		}

		/*==========================================================================
		 * 
		 * Default creation methods
		 * 
		 *========================================================================== */		
		public EPackage createEcoreSampleModel() {
			Resource ecoreResource = getResourceSet().createResource(URI.createURI("eef://sample.ecore"));
			EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
			ePackage.setName("sample");
			ePackage.setNsPrefix("eef-sample");
			ePackage.setNsURI("http://eef/sample");
			EClass eClass = EcoreFactory.eINSTANCE.createEClass();
			eClass.setName(FIRST_ECLASS_SAMPLE_NAME);
			eClass.setAbstract(FIRST_ECLASS_SAMPLE_ABSTRACTNESS);
			ePackage.getEClassifiers().add(eClass);
			EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
			eClass2.setName("EClass2");
			ePackage.getEClassifiers().add(eClass2);
			eClass.getESuperTypes().add(eClass2);
			EClass eClass3 = EcoreFactory.eINSTANCE.createEClass();
			eClass3.setName("EClass3");
			ePackage.getEClassifiers().add(eClass3);
			eClass.getESuperTypes().add(eClass3);
			EClass eClass4 = EcoreFactory.eINSTANCE.createEClass();
			eClass4.setName("EClass4");
			ePackage.getEClassifiers().add(eClass4);
			ecoreResource.getContents().add(ePackage);

			Resource ecoreResource2 = getResourceSet().createResource(URI.createURI("eef://sample2.ecore"));
			EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
			ePackage2.setName("sample2");
			ePackage2.setNsPrefix("eef-sample2");
			ePackage2.setNsURI("http://eef/sample2");
			EClass eClass5 = EcoreFactory.eINSTANCE.createEClass();
			eClass5.setName("EClass5");
			eClass5.setAbstract(true);
			ePackage2.getEClassifiers().add(eClass5);
			ecoreResource2.getContents().add(ePackage2);


			return ePackage;
		}

		public EObject createEditedObject() {
			EObject sampleModel = getSampleModel();
			if (sampleModel instanceof EPackage) {
				return ((EPackage) sampleModel).getEClassifiers().get(0);
			} else {
				return sampleModel;
			}
		}

		public AdapterFactory createAdapterFactory() {
			return new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}

		public PropertiesEditingModel createEditingModel() {
			return new EditingModelBuilder(TESTS_EDITING_MODEL_ID)
			.bindClass(EcorePackage.Literals.ECLASS)
			.withView(EClassMockView.class)
			.build();
		}


		/**
		 * Build a {@link PropertiesEditingModel} with simple SWT views for the EEF tests.
		 * @return a sample {@link PropertiesEditingModel}.
		 */
		public PropertiesEditingModel createEditingModelWithSWTViews() {
			return new EditingModelBuilder(TESTS_EDITING_MODEL_ID)
			.bindClass(EcorePackage.Literals.ECLASS).withView(SampleView.class)
			.bindClass(EcorePackage.Literals.EPACKAGE).withView(RootView.class)
			.build();
		}

		public PropertiesEditingModel createEditingModelWithPropertiesEditingViews() {
			List<Toolkit> toolkits = new ArrayList<Toolkit>();
			ResourceSet rset = new ResourceSetImpl();
			Resource resource = rset.getResource(URI.createURI("eeftoolkit:/org.eclipse.emf.eef.runtime.ui/org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.swttoolkit.SWTToolkit"), true);
			toolkits.add((Toolkit) resource.getContents().get(0));
			resource = rset.getResource(URI.createURI("eeftoolkit:/org.eclipse.emf.eef.runtime.ui/org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.emfpropertiestoolkit.EMFPropertiesToolkit"), true);
			toolkits.add((Toolkit) resource.getContents().get(0));
			List<View> views = createEcoreViews(toolkits);
			return new EditingModelBuilder(TESTS_EDITING_MODEL_ID)
			.bindClass(EcorePackage.Literals.ECLASS)
			.withView(views.get(0))
			.withView(views.get(1))
			.bindProperty(EcorePackage.Literals.ECLASSIFIER__DEFAULT_VALUE)
			.withEditor(views.get(1).getElements().get(0))
			.build();
		}

		public PropertiesEditingModel createEditingModelWithContainersPropertiesEditingViews() {
			List<Toolkit> toolkits = new ArrayList<Toolkit>();
			ResourceSet rset = new ResourceSetImpl();
			Resource resource = rset.getResource(URI.createURI("eeftoolkit:/org.eclipse.emf.eef.runtime.ui/org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.swttoolkit.SWTToolkit"), true);
			toolkits.add((Toolkit) resource.getContents().get(0));
			List<View> views = createEcoreViewsWithContainers(toolkits);
			return new EditingModelBuilder(TESTS_EDITING_MODEL_ID)
			.bindClass(EcorePackage.Literals.ECLASS)
			.withView(views.get(0))
			.build();
		}

		public List<View> createEcoreViewsWithContainers(List<Toolkit> toolkits) {
			List<View> result = new ArrayList<View>();
			View eClassView = ViewsFactory.eINSTANCE.createView();
			eClassView.setName("EClass");
			Container container1 = ViewsFactory.eINSTANCE.createContainer();
			container1.setName("Container1");
			Widget groupWidget = searchWidget(toolkits.get(0), "Group");
			container1.setRepresentation(groupWidget);
			Container subContainer = ViewsFactory.eINSTANCE.createContainer();
			subContainer.setName("SubContainer");
			subContainer.setRepresentation(groupWidget);
			container1.getElements().add(subContainer);
			ElementEditor nameEditor = ViewsFactory.eINSTANCE.createElementEditor();
			nameEditor.setName("name");
			nameEditor.setRepresentation(searchWidget(toolkits.get(0), "Text"));
			subContainer.getElements().add(nameEditor);
			eClassView.getElements().add(container1);
			Container container2 = ViewsFactory.eINSTANCE.createContainer();
			container2.setName("Container2");
			container2.setRepresentation(groupWidget);			
			ElementEditor abstractEditor = ViewsFactory.eINSTANCE.createElementEditor();
			abstractEditor.setName("abstract");
			abstractEditor.setRepresentation(searchWidget(toolkits.get(0), "Checkbox"));
			container2.getElements().add(abstractEditor);
			eClassView.getElements().add(container2);
			result.add(eClassView);
			return result;
		}

		public List<View> createEcoreViews(List<Toolkit> toolkits) {
			List<View> result = new ArrayList<View>();
			View eClassView = ViewsFactory.eINSTANCE.createView();
			eClassView.setName("EClass");
			ElementEditor nameEditor = ViewsFactory.eINSTANCE.createElementEditor();
			nameEditor.setName("name");
			nameEditor.setRepresentation(searchWidget(toolkits.get(0), "Text"));
			eClassView.getElements().add(nameEditor);
			ElementEditor abstractEditor = ViewsFactory.eINSTANCE.createElementEditor();
			abstractEditor.setName("abstract");
			abstractEditor.setRepresentation(searchWidget(toolkits.get(0), "Checkbox"));
			eClassView.getElements().add(abstractEditor);
			ElementEditor superTypes = ViewsFactory.eINSTANCE.createElementEditor();
			superTypes.setName("eSuperTypes");
			superTypes.setRepresentation(searchWidget(toolkits.get(1), "EReferenceMultiEditor"));
			eClassView.getElements().add(superTypes);
			result.add(eClassView);
			View eClassInstanceView = ViewsFactory.eINSTANCE.createView();
			eClassInstanceView.setName("Instance");
			ElementEditor instanceTypeName = ViewsFactory.eINSTANCE.createElementEditor();
			instanceTypeName.setName("instance Type Name");
			instanceTypeName.setRepresentation(searchWidget(toolkits.get(0), "Text"));
			eClassInstanceView.getElements().add(instanceTypeName);
			result.add(eClassInstanceView);
			return result;
		}

		public EEFServiceRegistry createServiceRegistry() {
			EEFServiceRegistry serviceRegistry = new EEFServiceRegistryImpl();
			eefServices = populateEEFServices();
			try {
				for (EEFServiceDescriptor<? extends EEFService<Object>> service : eefServices) {
					Map<String, String> properties = new HashMap<String, String>();
					properties.put(EEFTestEnvironment.COMPONENT_NAME_KEY, service.name);
					if (service.hasPriorityOver != null && !service.hasPriorityOver.isEmpty()) {
						StringBuilder priority = new StringBuilder();
						for (Iterator<String> iterator = service.hasPriorityOver.iterator(); iterator.hasNext();) {
							String next = iterator.next();
							priority.append(next);
							if (iterator.hasNext()) {
								priority.append(',');
							}
						}
						properties.put(PRIORITY_OVER_KEY, priority.toString());
					}
					serviceRegistry.addService(service.service, properties);					
				}
			} catch (PriorityCircularityException e) {
				//TODO: can't happen!
			}
			return serviceRegistry;
		}

		@SuppressWarnings({ "unchecked" })
		protected Collection<EEFServiceDescriptor<? extends EEFService<Object>>> populateEEFServices() {
			if (!preloadedServices.contains(EMFService.class)) {
				for (EEFServiceDescriptor<EMFService> desc : createEMFServices()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(ViewService.class)) {
				for (EEFServiceDescriptor<ViewService> desc : createViewServices()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(ToolkitPropertyEditorProvider.class)) {
				for (EEFServiceDescriptor<ToolkitPropertyEditorProvider> desc : createEditorProviders()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(PropertiesEditingProvider.class)) {
				for (EEFServiceDescriptor<PropertiesEditingProvider> desc : createEditingProviders()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(ViewHandlerProvider.class)) {
				for (EEFServiceDescriptor<ViewHandlerProvider> desc : createVHandlerProviders()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(EEFNotifier.class)) {
				for (EEFServiceDescriptor<EEFNotifier> desc : createNotifiers()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			if (!preloadedServices.contains(EEFLockManager.class)) {
				for (EEFServiceDescriptor<EEFLockManager> desc : createLockManagers()) {
					eefServices.add((EEFServiceDescriptor<? extends EEFService<Object>>) desc);
				}
			}
			return eefServices;
		}

		public Collection<EEFServiceDescriptor<EMFService>> createEMFServices() {
			Collection<EEFServiceDescriptor<EMFService>> services = new ArrayList<EEFServiceDescriptor<EMFService>>();
			EEFServiceDescriptor<EMFService> descriptor = new EEFServiceDescriptor<EMFService>("emfservice.default", new EMFServiceImpl() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(EMFService.class.getName());
					return result;
				}

			});
			services.add(descriptor);
			return services;
		}

		public Collection<EEFServiceDescriptor<ViewService>> createViewServices() {
			Collection<EEFServiceDescriptor<ViewService>> result = new ArrayList<EEFServiceDescriptor<ViewService>>();
			result.add(new EEFServiceDescriptor<ViewService>("viewservice.default", new ViewServiceImpl() {
		
				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ViewService.class.getName());
					return result;
				}
		
			}));
			return result;
		}

		public Collection<EEFServiceDescriptor<ToolkitPropertyEditorProvider>> createEditorProviders() {
			Collection<EEFServiceDescriptor<ToolkitPropertyEditorProvider>> result = new ArrayList<EEFTestEnvironment.EEFServiceDescriptor<ToolkitPropertyEditorProvider>>();
			result.add(new EEFServiceDescriptor<ToolkitPropertyEditorProvider>("toolkitservice.swt", new SWTToolkit() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ToolkitPropertyEditorProvider.class.getName());
					return result;
				}

			}));
			result.add(new EEFServiceDescriptor<ToolkitPropertyEditorProvider>("toolkitservice.emfproperties", new EMFPropertiesToolkit() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ToolkitPropertyEditorProvider.class.getName());
					return result;
				}

			}));
			return result;
		}

		public Collection<EEFServiceDescriptor<PropertiesEditingProvider>> createEditingProviders() {
			Collection<EEFServiceDescriptor<PropertiesEditingProvider>> result = new ArrayList<EEFServiceDescriptor<PropertiesEditingProvider>>();
			result.add(new EEFServiceDescriptor<PropertiesEditingProvider>("propertieseditingprovider.default", new PropertiesEditingProviderImpl() {
		
				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.editingProviding.PropertiesEditingProviderImpl#getEditingModel()
				 */
				@Override
				protected PropertiesEditingModel getEditingModel() {
					return Builder.this.getEditingModel();
				}
		
				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(PropertiesEditingProvider.class.getName());
					return result;
				}
		
		
			}));
			return result;
		}
		
		public Collection<EEFServiceDescriptor<ViewHandlerProvider>> createVHandlerProviders() {
			Collection<EEFServiceDescriptor<ViewHandlerProvider>> result = new ArrayList<EEFTestEnvironment.EEFServiceDescriptor<ViewHandlerProvider>>();
			EEFServiceDescriptor<ViewHandlerProvider> desc = new EEFServiceDescriptor<ViewHandlerProvider>(REFLECT_VIEW_HANDLER_PROVIDER_NAME, new ReflectViewHandlerProvider() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ViewHandlerProvider.class.getName());
					return result;
				}

			});
			result.add(desc);
			desc = new EEFServiceDescriptor<ViewHandlerProvider>(SWT_VIEW_HANDLER_PROVIDER_NAME,new SWTViewHandlerProvider() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ViewHandlerProvider.class.getName());
					return result;
				}

			}, REFLECT_VIEW_HANDLER_PROVIDER_NAME);
			result.add(desc);
			desc = new EEFServiceDescriptor<ViewHandlerProvider>(PROPERTIES_EDITING_VIEW_HANDLER_PROVIDER_NAME, new PropertiesEditingViewHandlerProvider() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					List<String> result = new ArrayList<String>();
					result.add(ViewHandlerProvider.class.getName());
					return result;
				}

			}, SWT_VIEW_HANDLER_PROVIDER_NAME);
			result.add(desc);
			return result;
		}
		
		public Collection<EEFServiceDescriptor<EEFNotifier>> createNotifiers() {
			Collection<EEFServiceDescriptor<EEFNotifier>> result = new ArrayList<EEFTestEnvironment.EEFServiceDescriptor<EEFNotifier>>();
			EEFServiceDescriptor<EEFNotifier> desc = new EEFServiceDescriptor<EEFNotifier>("notifier.default", new EditingViewNotifier() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					Collection<String> result = new ArrayList<String>();
					result.add(EEFNotifier.class.getName());
					return result;
				}
				
			});
			result.add(desc);
			return result;
		}

		public Collection<EEFServiceDescriptor<EEFLockManager>> createLockManagers() {
			Collection<EEFServiceDescriptor<EEFLockManager>> result = new ArrayList<EEFTestEnvironment.EEFServiceDescriptor<EEFLockManager>>();
			EEFServiceDescriptor<EEFLockManager> desc = new EEFServiceDescriptor<EEFLockManager>("lockmanager.editingview", new EditingViewLockManager() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					Collection<String> result = new ArrayList<String>();
					result.add(EEFLockManager.class.getName());
					return result;
				}
				
			});
			result.add(desc);
			desc = new EEFServiceDescriptor<EEFLockManager>("lockmanager.default", new NullLockManager() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.eef.runtime.services.impl.AbstractEEFService#providedServices()
				 */
				@Override
				public Collection<String> providedServices() {
					Collection<String> result = new ArrayList<String>();
					result.add(EEFLockManager.class.getName());
					return result;
				}
				
			});
			result.add(desc);
			return result;
		}

		public ModelChangesNotificationManager createNotificationManager() {
			return new ModelChangesNotificationManagerTest();
		}

		public PropertiesEditingContextFactory createPropertiesEditingContextFactory() {
			PropertiesEditingContextFactory factory = new PropertiesEditingContextFactoryImpl();
			factory.setServiceRegistry(getServiceRegistry());
			factory.setNotificationManager(getModelChangesNotificationManager());
			return factory;
		}

		public PropertiesEditingContext createEditingContext() {
			return getEditingContextFactory().createPropertiesEditingContext(getAdapterFactory(), getEditedObject());
		}

		private Widget searchWidget(Toolkit toolkit, String name) {
			TreeIterator<EObject> eAllContents = toolkit.eAllContents();
			while (eAllContents.hasNext()) {
				EObject next = eAllContents.next();
				if (next instanceof Widget && name.equals(((Widget) next).getName())) {
					return (Widget) next;
				}
			}
			return null;
		}

	}

	private static final class ModelChangesNotificationManagerTest implements ModelChangesNotificationManager {

		private List<PropertiesEditingComponent> components;

		public ModelChangesNotificationManagerTest() {
			components = new ArrayList<PropertiesEditingComponent>();
		}

		public void unregisterEditingComponent(PropertiesEditingComponent editingComponent) {
			components.remove(editingComponent);
		}

		public void registerEditingComponentAsEventHandler(PropertiesEditingComponent editingComponent) {
			components.add(editingComponent);
		}

		public void initModelChangesNotifierIfNeeded(Notifier source) {
			source.eAdapters().add(new EContentAdapter() {

				/**
				 * {@inheritDoc}
				 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
				 */
				@Override
				public void notifyChanged(Notification notification) {
					for (PropertiesEditingComponent component : components) {
						component.notifyChanged(notification);
					}
				}

			});
		}
	}

	public static class EEFServiceDescriptor<T extends EEFService<?>> {

		private T service;
		private String name;
		private List<String> hasPriorityOver;



		public EEFServiceDescriptor(String name, T service) {
			this.name = name;
			this.service = service;
			this.hasPriorityOver = new ArrayList<String>();
		}



		public EEFServiceDescriptor(String name, T service, String... hasPriorityOver) {
			this.service = service;
			this.name = name;
			this.hasPriorityOver = Arrays.asList(hasPriorityOver);
		}

	}
}
