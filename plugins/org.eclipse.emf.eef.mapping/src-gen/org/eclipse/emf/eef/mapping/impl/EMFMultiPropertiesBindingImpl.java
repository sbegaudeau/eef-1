/*******************************************************************************
 * Copyright (c) 2008, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.mapping.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.eef.mapping.EMFMultiPropertiesBinding;
import org.eclipse.emf.eef.mapping.MappingPackage;
import org.eclipse.emf.eef.mapping.navigation.ModelNavigation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMF Multi Properties Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.eef.mapping.impl.EMFMultiPropertiesBindingImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.emf.eef.mapping.impl.EMFMultiPropertiesBindingImpl#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EMFMultiPropertiesBindingImpl extends AbstractPropertyBindingImpl
		implements EMFMultiPropertiesBinding {
	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected EList<EStructuralFeature> model;

	/**
	 * The cached value of the '{@link #getNavigation() <em>Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigation()
	 * @generated
	 * @ordered
	 */
	protected ModelNavigation navigation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMFMultiPropertiesBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.EMF_MULTI_PROPERTIES_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EStructuralFeature> getModel() {
		if (model == null) {
			model = new EObjectResolvingEList<EStructuralFeature>(
					EStructuralFeature.class, this,
					MappingPackage.EMF_MULTI_PROPERTIES_BINDING__MODEL);
		}
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelNavigation getNavigation() {
		return navigation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigation(ModelNavigation newNavigation,
			NotificationChain msgs) {
		ModelNavigation oldNavigation = navigation;
		navigation = newNavigation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION,
					oldNavigation, newNavigation);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigation(ModelNavigation newNavigation) {
		if (newNavigation != navigation) {
			NotificationChain msgs = null;
			if (navigation != null)
				msgs = ((InternalEObject) navigation)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION,
								null, msgs);
			if (newNavigation != null)
				msgs = ((InternalEObject) newNavigation)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION,
								null, msgs);
			msgs = basicSetNavigation(newNavigation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION,
					newNavigation, newNavigation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION:
			return basicSetNavigation(null, msgs);
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
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__MODEL:
			return getModel();
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION:
			return getNavigation();
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
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__MODEL:
			getModel().clear();
			getModel().addAll(
					(Collection<? extends EStructuralFeature>) newValue);
			return;
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION:
			setNavigation((ModelNavigation) newValue);
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
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__MODEL:
			getModel().clear();
			return;
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION:
			setNavigation((ModelNavigation) null);
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
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__MODEL:
			return model != null && !model.isEmpty();
		case MappingPackage.EMF_MULTI_PROPERTIES_BINDING__NAVIGATION:
			return navigation != null;
		}
		return super.eIsSet(featureID);
	}

} //EMFMultiPropertiesBindingImpl
