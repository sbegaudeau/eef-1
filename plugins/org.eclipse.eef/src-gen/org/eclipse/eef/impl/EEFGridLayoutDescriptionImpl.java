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
package org.eclipse.eef.impl;

import org.eclipse.eef.EEFGridLayoutDescription;
import org.eclipse.eef.EefPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EEF Grid Layout Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.eef.impl.EEFGridLayoutDescriptionImpl#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * <li>{@link org.eclipse.eef.impl.EEFGridLayoutDescriptionImpl#isMakeColumnsWithEqualWidth <em>Make Columns With Equal
 * Width</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EEFGridLayoutDescriptionImpl extends EEFLayoutDescriptionImpl implements EEFGridLayoutDescription {
	/**
	 * The default value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNumberOfColumns()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_COLUMNS_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getNumberOfColumns()
	 * @generated
	 * @ordered
	 */
	protected int numberOfColumns = EEFGridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;

	/**
	 * The default value of the '{@link #isMakeColumnsWithEqualWidth() <em>Make Columns With Equal Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMakeColumnsWithEqualWidth()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMakeColumnsWithEqualWidth() <em>Make Columns With Equal Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMakeColumnsWithEqualWidth()
	 * @generated
	 * @ordered
	 */
	protected boolean makeColumnsWithEqualWidth = EEFGridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EEFGridLayoutDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EefPackage.Literals.EEF_GRID_LAYOUT_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNumberOfColumns(int newNumberOfColumns) {
		int oldNumberOfColumns = numberOfColumns;
		numberOfColumns = newNumberOfColumns;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS, oldNumberOfColumns,
					numberOfColumns));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isMakeColumnsWithEqualWidth() {
		return makeColumnsWithEqualWidth;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMakeColumnsWithEqualWidth(boolean newMakeColumnsWithEqualWidth) {
		boolean oldMakeColumnsWithEqualWidth = makeColumnsWithEqualWidth;
		makeColumnsWithEqualWidth = newMakeColumnsWithEqualWidth;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH,
					oldMakeColumnsWithEqualWidth, makeColumnsWithEqualWidth));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
			return getNumberOfColumns();
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
			return isMakeColumnsWithEqualWidth();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
			setNumberOfColumns((Integer) newValue);
			return;
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
			setMakeColumnsWithEqualWidth((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
			setNumberOfColumns(EEFGridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT);
			return;
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
			setMakeColumnsWithEqualWidth(EEFGridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
			return numberOfColumns != EEFGridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;
		case EefPackage.EEF_GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
			return makeColumnsWithEqualWidth != EEFGridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (numberOfColumns: "); //$NON-NLS-1$
		result.append(numberOfColumns);
		result.append(", makeColumnsWithEqualWidth: "); //$NON-NLS-1$
		result.append(makeColumnsWithEqualWidth);
		result.append(')');
		return result.toString();
	}

} // EEFGridLayoutDescriptionImpl
