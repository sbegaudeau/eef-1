/**
 * <copyright>
 * </copyright>
 *
 * $Id: EefnrItemProviderAdapterFactory.java,v 1.5.2.1 2010/08/30 08:49:45 sbouchet Exp $
 */
package org.eclipse.emf.eef.eefnr.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.eef.eefnr.util.EefnrAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EefnrItemProviderAdapterFactory extends EefnrAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EefnrItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.Root} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootItemProvider rootItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.Root}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRootAdapter() {
		if (rootItemProvider == null) {
			rootItemProvider = new RootItemProvider(this);
		}

		return rootItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TotalSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TotalSampleItemProvider totalSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TotalSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTotalSampleAdapter() {
		if (totalSampleItemProvider == null) {
			totalSampleItemProvider = new TotalSampleItemProvider(this);
		}

		return totalSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TextSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextSampleItemProvider textSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TextSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextSampleAdapter() {
		if (textSampleItemProvider == null) {
			textSampleItemProvider = new TextSampleItemProvider(this);
		}

		return textSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.CheckboxSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckboxSampleItemProvider checkboxSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.CheckboxSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCheckboxSampleAdapter() {
		if (checkboxSampleItemProvider == null) {
			checkboxSampleItemProvider = new CheckboxSampleItemProvider(this);
		}

		return checkboxSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TextareaSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextareaSampleItemProvider textareaSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TextareaSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextareaSampleAdapter() {
		if (textareaSampleItemProvider == null) {
			textareaSampleItemProvider = new TextareaSampleItemProvider(this);
		}

		return textareaSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.RadioSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RadioSampleItemProvider radioSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.RadioSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRadioSampleAdapter() {
		if (radioSampleItemProvider == null) {
			radioSampleItemProvider = new RadioSampleItemProvider(this);
		}

		return radioSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.EObjectFlatComboViewerSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectFlatComboViewerSampleItemProvider eObjectFlatComboViewerSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.EObjectFlatComboViewerSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEObjectFlatComboViewerSampleAdapter() {
		if (eObjectFlatComboViewerSampleItemProvider == null) {
			eObjectFlatComboViewerSampleItemProvider = new EObjectFlatComboViewerSampleItemProvider(this);
		}

		return eObjectFlatComboViewerSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.ReferencesTableSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferencesTableSampleItemProvider referencesTableSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.ReferencesTableSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReferencesTableSampleAdapter() {
		if (referencesTableSampleItemProvider == null) {
			referencesTableSampleItemProvider = new ReferencesTableSampleItemProvider(this);
		}

		return referencesTableSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.EMFComboViewerSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMFComboViewerSampleItemProvider emfComboViewerSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.EMFComboViewerSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEMFComboViewerSampleAdapter() {
		if (emfComboViewerSampleItemProvider == null) {
			emfComboViewerSampleItemProvider = new EMFComboViewerSampleItemProvider(this);
		}

		return emfComboViewerSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.MultiValuedEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultiValuedEditorSampleItemProvider multiValuedEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.MultiValuedEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMultiValuedEditorSampleAdapter() {
		if (multiValuedEditorSampleItemProvider == null) {
			multiValuedEditorSampleItemProvider = new MultiValuedEditorSampleItemProvider(this);
		}

		return multiValuedEditorSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TableCompositionEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableCompositionEditorSampleItemProvider tableCompositionEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TableCompositionEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTableCompositionEditorSampleAdapter() {
		if (tableCompositionEditorSampleItemProvider == null) {
			tableCompositionEditorSampleItemProvider = new TableCompositionEditorSampleItemProvider(this);
		}

		return tableCompositionEditorSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.AdvancedReferencesTableSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdvancedReferencesTableSampleItemProvider advancedReferencesTableSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.AdvancedReferencesTableSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAdvancedReferencesTableSampleAdapter() {
		if (advancedReferencesTableSampleItemProvider == null) {
			advancedReferencesTableSampleItemProvider = new AdvancedReferencesTableSampleItemProvider(this);
		}

		return advancedReferencesTableSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.AdvancedEObjectFlatComboViewerSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdvancedEObjectFlatComboViewerSampleItemProvider advancedEObjectFlatComboViewerSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.AdvancedEObjectFlatComboViewerSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAdvancedEObjectFlatComboViewerSampleAdapter() {
		if (advancedEObjectFlatComboViewerSampleItemProvider == null) {
			advancedEObjectFlatComboViewerSampleItemProvider = new AdvancedEObjectFlatComboViewerSampleItemProvider(this);
		}

		return advancedEObjectFlatComboViewerSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.AdvancedTableCompositionEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdvancedTableCompositionEditorSampleItemProvider advancedTableCompositionEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.AdvancedTableCompositionEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAdvancedTableCompositionEditorSampleAdapter() {
		if (advancedTableCompositionEditorSampleItemProvider == null) {
			advancedTableCompositionEditorSampleItemProvider = new AdvancedTableCompositionEditorSampleItemProvider(this);
		}

		return advancedTableCompositionEditorSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.FlatReferencesTableSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlatReferencesTableSampleItemProvider flatReferencesTableSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.FlatReferencesTableSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFlatReferencesTableSampleAdapter() {
		if (flatReferencesTableSampleItemProvider == null) {
			flatReferencesTableSampleItemProvider = new FlatReferencesTableSampleItemProvider(this);
		}

		return flatReferencesTableSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.Sample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SampleItemProvider sampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.Sample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSampleAdapter() {
		if (sampleItemProvider == null) {
			sampleItemProvider = new SampleItemProvider(this);
		}

		return sampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TextSampleWithTwoTabs} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextSampleWithTwoTabsItemProvider textSampleWithTwoTabsItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TextSampleWithTwoTabs}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextSampleWithTwoTabsAdapter() {
		if (textSampleWithTwoTabsItemProvider == null) {
			textSampleWithTwoTabsItemProvider = new TextSampleWithTwoTabsItemProvider(this);
		}

		return textSampleWithTwoTabsItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.TableCompositionExtensionEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableCompositionExtensionEditorSampleItemProvider tableCompositionExtensionEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.TableCompositionExtensionEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTableCompositionExtensionEditorSampleAdapter() {
		if (tableCompositionExtensionEditorSampleItemProvider == null) {
			tableCompositionExtensionEditorSampleItemProvider = new TableCompositionExtensionEditorSampleItemProvider(this);
		}

		return tableCompositionExtensionEditorSampleItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (rootItemProvider != null) rootItemProvider.dispose();
		if (totalSampleItemProvider != null) totalSampleItemProvider.dispose();
		if (textSampleItemProvider != null) textSampleItemProvider.dispose();
		if (checkboxSampleItemProvider != null) checkboxSampleItemProvider.dispose();
		if (textareaSampleItemProvider != null) textareaSampleItemProvider.dispose();
		if (radioSampleItemProvider != null) radioSampleItemProvider.dispose();
		if (eObjectFlatComboViewerSampleItemProvider != null) eObjectFlatComboViewerSampleItemProvider.dispose();
		if (referencesTableSampleItemProvider != null) referencesTableSampleItemProvider.dispose();
		if (emfComboViewerSampleItemProvider != null) emfComboViewerSampleItemProvider.dispose();
		if (multiValuedEditorSampleItemProvider != null) multiValuedEditorSampleItemProvider.dispose();
		if (tableCompositionEditorSampleItemProvider != null) tableCompositionEditorSampleItemProvider.dispose();
		if (advancedReferencesTableSampleItemProvider != null) advancedReferencesTableSampleItemProvider.dispose();
		if (advancedEObjectFlatComboViewerSampleItemProvider != null) advancedEObjectFlatComboViewerSampleItemProvider.dispose();
		if (advancedTableCompositionEditorSampleItemProvider != null) advancedTableCompositionEditorSampleItemProvider.dispose();
		if (flatReferencesTableSampleItemProvider != null) flatReferencesTableSampleItemProvider.dispose();
		if (sampleItemProvider != null) sampleItemProvider.dispose();
		if (textSampleWithTwoTabsItemProvider != null) textSampleWithTwoTabsItemProvider.dispose();
		if (tableCompositionExtensionEditorSampleItemProvider != null) tableCompositionExtensionEditorSampleItemProvider.dispose();
	}

}
