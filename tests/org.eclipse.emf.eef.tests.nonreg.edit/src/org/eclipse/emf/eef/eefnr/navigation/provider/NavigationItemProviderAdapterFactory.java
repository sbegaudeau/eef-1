/**
 * <copyright>
 * </copyright>
 *
 * $Id: NavigationItemProviderAdapterFactory.java,v 1.4 2010/11/30 08:24:13 glefur Exp $
 */
package org.eclipse.emf.eef.eefnr.navigation.provider;

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

import org.eclipse.emf.eef.eefnr.navigation.util.NavigationAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NavigationItemProviderAdapterFactory extends NavigationAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
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
	public NavigationItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.DeferedFlatReferenceTableEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeferedFlatReferenceTableEditorSampleItemProvider deferedFlatReferenceTableEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.DeferedFlatReferenceTableEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeferedFlatReferenceTableEditorSampleAdapter() {
		if (deferedFlatReferenceTableEditorSampleItemProvider == null) {
			deferedFlatReferenceTableEditorSampleItemProvider = new DeferedFlatReferenceTableEditorSampleItemProvider(this);
		}

		return deferedFlatReferenceTableEditorSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.DeferedReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeferedReferenceItemProvider deferedReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.DeferedReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeferedReferenceAdapter() {
		if (deferedReferenceItemProvider == null) {
			deferedReferenceItemProvider = new DeferedReferenceItemProvider(this);
		}

		return deferedReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.DeferedReferenceTableEditorSample} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeferedReferenceTableEditorSampleItemProvider deferedReferenceTableEditorSampleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.DeferedReferenceTableEditorSample}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeferedReferenceTableEditorSampleAdapter() {
		if (deferedReferenceTableEditorSampleItemProvider == null) {
			deferedReferenceTableEditorSampleItemProvider = new DeferedReferenceTableEditorSampleItemProvider(this);
		}

		return deferedReferenceTableEditorSampleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.Owner} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OwnerItemProvider ownerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.Owner}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOwnerAdapter() {
		if (ownerItemProvider == null) {
			ownerItemProvider = new OwnerItemProvider(this);
		}

		return ownerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.MultipleReferencer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultipleReferencerItemProvider multipleReferencerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.MultipleReferencer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMultipleReferencerAdapter() {
		if (multipleReferencerItemProvider == null) {
			multipleReferencerItemProvider = new MultipleReferencerItemProvider(this);
		}

		return multipleReferencerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.Subtype} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubtypeItemProvider subtypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.Subtype}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSubtypeAdapter() {
		if (subtypeItemProvider == null) {
			subtypeItemProvider = new SubtypeItemProvider(this);
		}

		return subtypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.SingleReferencer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleReferencerItemProvider singleReferencerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.SingleReferencer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSingleReferencerAdapter() {
		if (singleReferencerItemProvider == null) {
			singleReferencerItemProvider = new SingleReferencerItemProvider(this);
		}

		return singleReferencerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.AnotherSubType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnotherSubTypeItemProvider anotherSubTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.AnotherSubType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAnotherSubTypeAdapter() {
		if (anotherSubTypeItemProvider == null) {
			anotherSubTypeItemProvider = new AnotherSubTypeItemProvider(this);
		}

		return anotherSubTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.eef.eefnr.navigation.Element} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementItemProvider elementItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.eef.eefnr.navigation.Element}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createElementAdapter() {
		if (elementItemProvider == null) {
			elementItemProvider = new ElementItemProvider(this);
		}

		return elementItemProvider;
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
		if (deferedFlatReferenceTableEditorSampleItemProvider != null) deferedFlatReferenceTableEditorSampleItemProvider.dispose();
		if (deferedReferenceItemProvider != null) deferedReferenceItemProvider.dispose();
		if (deferedReferenceTableEditorSampleItemProvider != null) deferedReferenceTableEditorSampleItemProvider.dispose();
		if (ownerItemProvider != null) ownerItemProvider.dispose();
		if (multipleReferencerItemProvider != null) multipleReferencerItemProvider.dispose();
		if (subtypeItemProvider != null) subtypeItemProvider.dispose();
		if (singleReferencerItemProvider != null) singleReferencerItemProvider.dispose();
		if (anotherSubTypeItemProvider != null) anotherSubTypeItemProvider.dispose();
		if (elementItemProvider != null) elementItemProvider.dispose();
	}

}
