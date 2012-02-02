/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.eef.runtime.editingModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.eef.runtime.editingModel.EClassBinding;
import org.eclipse.emf.eef.runtime.editingModel.EObjectView;
import org.eclipse.emf.eef.runtime.editingModel.EditingModelPackage;
import org.eclipse.emf.eef.runtime.editingModel.PropertyBinding;
import org.eclipse.emf.eef.runtime.editingModel.View;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.eef.runtime.editingModel.impl.EClassBindingImpl#getEClass <em>EClass</em>}</li>
 *   <li>{@link org.eclipse.emf.eef.runtime.editingModel.impl.EClassBindingImpl#getViews <em>Views</em>}</li>
 *   <li>{@link org.eclipse.emf.eef.runtime.editingModel.impl.EClassBindingImpl#getPropertyBindings <em>Property Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClassBindingImpl extends EObjectImpl implements EClassBinding {
	/**
	 * The cached value of the '{@link #getEClass() <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass eClass;

	/**
	 * The cached value of the '{@link #getViews() <em>Views</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViews()
	 * @generated
	 * @ordered
	 */
	protected EList<View> views;

	/**
	 * The cached value of the '{@link #getPropertyBindings() <em>Property Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyBinding> propertyBindings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClassBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditingModelPackage.Literals.ECLASS_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEClass() {
		if (eClass != null && eClass.eIsProxy()) {
			InternalEObject oldEClass = (InternalEObject)eClass;
			eClass = (EClass)eResolveProxy(oldEClass);
			if (eClass != oldEClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EditingModelPackage.ECLASS_BINDING__ECLASS, oldEClass, eClass));
			}
		}
		return eClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetEClass() {
		return eClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEClass(EClass newEClass) {
		EClass oldEClass = eClass;
		eClass = newEClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditingModelPackage.ECLASS_BINDING__ECLASS, oldEClass, eClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<View> getViews() {
		if (views == null) {
			views = new EObjectContainmentEList<View>(View.class, this, EditingModelPackage.ECLASS_BINDING__VIEWS);
		}
		return views;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyBinding> getPropertyBindings() {
		if (propertyBindings == null) {
			propertyBindings = new EObjectContainmentEList<PropertyBinding>(PropertyBinding.class, this, EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS);
		}
		return propertyBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EStructuralFeature feature(Object view) {
		for (PropertyBinding binding : getPropertyBindings()) {
			if (binding.getEditor().equals(view)) {
				return binding.getFeature();
			}
		}
		if (view instanceof String) {
			return eClass.getEStructuralFeature((String)view);
		}
		if (view instanceof EObject) {
			// Here we dont have an PropertyBinding to help us. We check if the view is an EObject (fon instance an ElementEditor)
			// We looking for an "name" structural feature and if this feature is type of String, we try to associate this name
			// with a structural feature of the handled EClass. For instance if an ElementEditor (which has a "name" feature) is named
			// "active" and the current EClass has a feature named "active", we return this feature.
			EStructuralFeature nameFeature = ((EObject) view).eClass().getEStructuralFeature("name");
			if (nameFeature != null && "java.lang.String".equals(nameFeature.getEType().getInstanceClassName())) {
				EStructuralFeature feature = eClass.getEStructuralFeature((String)((EObject) view).eGet(nameFeature));
				if (feature != null) {
					return feature;
				}
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * TODO: This could return a list a bindings ...
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object propertyEditor(EStructuralFeature feature) {
		for (PropertyBinding binding : getPropertyBindings()) {
			if (binding.getFeature().equals(feature)) {
				return binding.getEditor();
			}
		}
		for (Object view : views) {
			if (view instanceof EObjectView) {
				TreeIterator<EObject> eAllContents = ((EObjectView) view).getDefinition().eAllContents();
				while (eAllContents.hasNext()) {
					EObject next = eAllContents.next();
					EStructuralFeature nameFeature = next.eClass().getEStructuralFeature("name");
					if (nameFeature != null) {
						Object name = next.eGet(nameFeature);
						if (name instanceof String && name.equals(feature.getName())) {
							return next;
						}
					}
				}
			}
		}
		return feature.getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditingModelPackage.ECLASS_BINDING__VIEWS:
				return ((InternalEList<?>)getViews()).basicRemove(otherEnd, msgs);
			case EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS:
				return ((InternalEList<?>)getPropertyBindings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EditingModelPackage.ECLASS_BINDING__ECLASS:
				if (resolve) return getEClass();
				return basicGetEClass();
			case EditingModelPackage.ECLASS_BINDING__VIEWS:
				return getViews();
			case EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS:
				return getPropertyBindings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditingModelPackage.ECLASS_BINDING__ECLASS:
				setEClass((EClass)newValue);
				return;
			case EditingModelPackage.ECLASS_BINDING__VIEWS:
				getViews().clear();
				getViews().addAll((Collection<? extends View>)newValue);
				return;
			case EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS:
				getPropertyBindings().clear();
				getPropertyBindings().addAll((Collection<? extends PropertyBinding>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EditingModelPackage.ECLASS_BINDING__ECLASS:
				setEClass((EClass)null);
				return;
			case EditingModelPackage.ECLASS_BINDING__VIEWS:
				getViews().clear();
				return;
			case EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS:
				getPropertyBindings().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EditingModelPackage.ECLASS_BINDING__ECLASS:
				return eClass != null;
			case EditingModelPackage.ECLASS_BINDING__VIEWS:
				return views != null && !views.isEmpty();
			case EditingModelPackage.ECLASS_BINDING__PROPERTY_BINDINGS:
				return propertyBindings != null && !propertyBindings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EClassBindingImpl
