/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.ui.swt.internal.view.lock;

import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.runtime.util.EEFEditingServiceProvider;
import org.eclipse.emf.eef.runtime.view.lock.EEFLockManager;
import org.eclipse.emf.eef.runtime.view.lock.policies.EEFLockEvent;
import org.eclipse.emf.eef.runtime.view.lock.policies.EEFLockPolicy;
import org.eclipse.emf.eef.runtime.view.lock.policies.EEFPropertyLockEvent;
import org.eclipse.emf.eef.runtime.view.notify.EEFNotifier;
import org.eclipse.emf.eef.runtime.view.notify.EEFNotifierProvider;
import org.eclipse.emf.eef.runtime.view.notify.impl.LockNotification;
import org.eclipse.emf.eef.runtime.view.notify.impl.PropertyLockNotification;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.emf.eef.views.ViewElement;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class EditingViewLockManager implements EEFLockManager {
	
	private EEFEditingServiceProvider eefEditingServiceProvider;
	private EEFNotifierProvider eefNotifierProvider;

	/**
	 * @param eefEditingServiceProvider the eefEditingServiceProvider to set
	 */
	public void setEEFEditingServiceProvider(EEFEditingServiceProvider eefEditingServiceProvider) {
		this.eefEditingServiceProvider = eefEditingServiceProvider;
	}

	/**
	 * @param eefNotifierProvider the eefNotifierProvider to set
	 */
	public void setEEFNotifierProvider(EEFNotifierProvider eefNotifierProvider) {
		this.eefNotifierProvider = eefNotifierProvider;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.services.EEFService#serviceFor(java.lang.Object)
	 */
	public boolean serviceFor(Object element) {
		return element instanceof PropertiesEditingView;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#initView(java.lang.Object)
	 */
	public void initView(Object view) {
		if (view instanceof PropertiesEditingView) {
			@SuppressWarnings("unchecked")
			PropertiesEditingView<Composite> editingView = (PropertiesEditingView<Composite>) view;
			PropertiesEditingComponent editingComponent = editingView.getEditingComponent();
			EObject editedEObject = editingComponent.getEObject();
			Collection<EEFLockPolicy> policies = editingComponent.getLockPolicies();
			boolean autowire = editingComponent.getEditingContext().getOptions().autowire();

			checkViewLockingTowardsPolicies(editingView, editedEObject, policies);
			checkEditorsLockingTowardPolicies(editingView, editingComponent, editedEObject, policies, autowire);
		}
	}

	private void checkViewLockingTowardsPolicies(PropertiesEditingView<Composite> editingView, EObject editedEObject, Collection<EEFLockPolicy> policies) {
		for (EEFLockPolicy lockPolicy : policies) {
			if (lockPolicy.isLocked(editingView.getEditingComponent().getEditingContext(), editedEObject)) {
				lockView(editingView);
			}
		}
	}

	private void checkEditorsLockingTowardPolicies(PropertiesEditingView<Composite> editingView, PropertiesEditingComponent editingComponent,
			EObject editedEObject, Collection<EEFLockPolicy> policies, boolean autowire) {
		TreeIterator<EObject> viewContents = editingView.getViewModel().eAllContents();
		while (viewContents.hasNext()) {
			EObject next = viewContents.next();
			if (next instanceof ElementEditor) {
				EStructuralFeature feature = eefEditingServiceProvider.getEditingService(editedEObject).featureFromEditor(editingComponent.getEditingContext(), next);
				for (EEFLockPolicy lockPolicy : policies) {
					if (lockPolicy.isLocked(editingView.getEditingComponent().getEditingContext(), editedEObject, feature)) {
						lockEditor(editingView, next);
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#lockView(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void lockView(Object view) {
		if (view instanceof PropertiesEditingView) {
			for (PropertyEditor editor : ((PropertiesEditingView<Composite>) view).getAllPropertyEditors()) {
				editor.getPropertyEditorViewer().lock();
			}
			EEFNotifier notifier = eefNotifierProvider.getNotifier(view);
			notifier.notify(view, new LockNotification("This view is locked."));
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#lockEditor(java.lang.Object, java.lang.Object)
	 */
	public void lockEditor(Object view, Object editor) {
		if (view instanceof PropertiesEditingView && editor instanceof ViewElement) {
			@SuppressWarnings("unchecked")
			PropertyEditor propertyEditor = ((PropertiesEditingView<Composite>) view).getPropertyEditor((ViewElement) editor);
			if (propertyEditor != null) {
				propertyEditor.getPropertyEditorViewer().lock();
				EEFNotifier notifier = eefNotifierProvider.getNotifier(view);
				notifier.notify(view, new PropertyLockNotification(editor, "This editor is locked."));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#clearViewLock(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void clearViewLock(Object view) {
		if (view instanceof PropertiesEditingView) {
			for (PropertyEditor editor : ((PropertiesEditingView<Composite>) view).getAllPropertyEditors()) {
				editor.getPropertyEditorViewer().unlock();
			}
			EEFNotifier notifier = eefNotifierProvider.getNotifier(view);
			notifier.clearViewNotification(view);
		}		
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#clearEditorLock(java.lang.Object, java.lang.Object)
	 */
	public void clearEditorLock(Object view, Object editor) {
		if (view instanceof PropertiesEditingView && editor instanceof ViewElement) {
			@SuppressWarnings("unchecked")
			PropertyEditor propertyEditor = ((PropertiesEditingView<Composite>) view).getPropertyEditor((ViewElement) editor);
			if (propertyEditor != null) {
				propertyEditor.getPropertyEditorViewer().unlock();
				EEFNotifier notifier = eefNotifierProvider.getNotifier(view);
				notifier.clearEditorNotification(view, editor);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.lock.EEFLockManager#fireLockChange(org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent, java.lang.Object, org.eclipse.emf.eef.runtime.view.lock.policies.EEFLockEvent)
	 */
	public void fireLockChange(PropertiesEditingComponent editingComponent, Object view, EEFLockEvent lockEvent) {
		if (lockEvent instanceof EEFPropertyLockEvent) {
			Object editor = editingComponent.getBinding().propertyEditor(editingComponent.getEObject(), ((EEFPropertyLockEvent) lockEvent).getLockedFeature(), editingComponent.getEditingContext().getOptions().autowire());
			if (lockEvent.getState() == EEFLockEvent.LockState.LOCKED) {
				lockEditor(view, editor);
			} else {
				clearEditorLock(view, editor);
			}
		} else {
			if (lockEvent.getState() == EEFLockEvent.LockState.LOCKED) {
				lockView(view);
			} else {
				clearViewLock(view);
			}
		}
	}
}
