/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.ui.swt.internal.view.propertyeditors.impl.emfpropertiestoolkit.mutivaluededitor;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.editingModel.EStructuralFeatureBinding;
import org.eclipse.emf.eef.runtime.editingModel.PropertyBinding;
import org.eclipse.emf.eef.runtime.notify.PropertiesEditingEvent;
import org.eclipse.emf.eef.runtime.notify.PropertiesEditingEventImpl;
import org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer;
import org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewerListener;
import org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiValuedDialog;
import org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.util.ArrayFeatureContentProvider;
import org.eclipse.emf.eef.runtime.ui.swt.resources.ImageManager;
import org.eclipse.emf.eef.runtime.ui.swt.util.SWTViewService;
import org.eclipse.emf.eef.runtime.ui.swt.viewer.EditUIProvidersFactory;
import org.eclipse.emf.eef.runtime.ui.swt.viewer.filters.ViewerFilterBuilderProvider;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorViewer;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.impl.PropertyEditorImpl;
import org.eclipse.emf.eef.runtime.util.EEFEditingServiceProvider;
import org.eclipse.emf.eef.runtime.util.EMFServiceProvider;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class MultiValuedPropertyEditor extends PropertyEditorImpl implements MultivaluedPropertyEditor {

	protected EMFServiceProvider emfServiceProvider;
	protected EEFEditingServiceProvider eefEditingServiceProvider;
	protected EditUIProvidersFactory editUIProvidersFactory;
	protected ImageManager imageManager;
	protected ViewerFilterBuilderProvider filterBuilderProvider;

	protected PropertiesEditingView<Composite> view;
	protected ElementEditor elementEditor;
	protected PropertyEditorViewer<MultiLinePropertyViewer> propertyEditorViewer;

	private MultiLinePropertyViewerListener listener;

	public MultiValuedPropertyEditor(EMFServiceProvider emfServiceProvider, EEFEditingServiceProvider eefEditingServiceProvider, EditUIProvidersFactory editUIProvidersFactory, ImageManager imageManager, ViewerFilterBuilderProvider filterBuilderProvider, PropertiesEditingView<Composite> view,
			ElementEditor elementEditor, PropertyEditorViewer<MultiLinePropertyViewer> propertyEditorViewer) {
		this.emfServiceProvider = emfServiceProvider;
		this.eefEditingServiceProvider = eefEditingServiceProvider;
		this.editUIProvidersFactory = editUIProvidersFactory;
		this.imageManager = imageManager;
		this.filterBuilderProvider = filterBuilderProvider;
		this.propertyEditorViewer = propertyEditorViewer;
		this.view = view;
		this.elementEditor = elementEditor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor#init()
	 */
	public void init() {
		propertyEditorViewer.getViewer().setContentProvider(new ArrayFeatureContentProvider(eefEditingServiceProvider, (SWTViewService) view.getViewService(), view.getEditingComponent().getEditingContext(), elementEditor));
		PropertyBinding propertyBinding = view.getEditingComponent().getBinding().propertyBinding(elementEditor, view.getEditingComponent().getEditingContext().getOptions().autowire());
		ILabelProvider labelProvider;
		if (propertyBinding != null) {
			labelProvider = editUIProvidersFactory.createPropertyBindingLabelProvider(view.getEditingComponent().getEditingContext(), propertyBinding);
		} else {
			labelProvider = editUIProvidersFactory.createLabelProvider(view.getEditingComponent().getEditingContext().getAdapterFactory());
		}
		propertyEditorViewer.getViewer().setLabelProvider(labelProvider);
		if (propertyBinding instanceof EStructuralFeatureBinding) {
			EStructuralFeature feature = ((EStructuralFeatureBinding) propertyBinding).getFeature();
			propertyEditorViewer.getViewer().setLowerBound(feature.getLowerBound());
			propertyEditorViewer.getViewer().setUpperBound(feature.getUpperBound());
		}
		propertyEditorViewer.getViewer().setInput(view.getEditingComponent().getEObject());
		initListener();
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.heightHint = view.getViewSettings().getMultiEditorHeight();
		layoutData.horizontalSpan = 2;
		propertyEditorViewer.getViewer().setLayoutData(layoutData);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor#getPropertyEditorViewer()
	 */
	public PropertyEditorViewer<?> getPropertyEditorViewer() {
		return propertyEditorViewer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor#addValue(java.lang.Object)
	 */
	public void addValue(Object value) {
		listener.disable();
		propertyEditorViewer.getViewer().refresh();
		listener.enable();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor#addAllValues(java.util.Collection)
	 */
	public void addAllValues(Collection<?> values) {
		listener.disable();
		propertyEditorViewer.getViewer().refresh();
		listener.enable();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor#removeValue(java.lang.Object)
	 */
	public void removeValue(Object value) {
		listener.disable();
		propertyEditorViewer.getViewer().refresh();
		listener.enable();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor#removeAllValues(java.util.Collection)
	 */
	public void removeAllValues(Collection<?> values) {
		listener.disable();
		propertyEditorViewer.getViewer().refresh();
		listener.enable();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor#moveValue(java.lang.Object,
	 *      int)
	 */
	public void moveValue(Object value, int newIndex) {
		listener.disable();
		propertyEditorViewer.getViewer().refresh();
		listener.enable();
	}

	/**
	 * Initialize the listener on the MultiLinePropertyViewer.
	 */
	private void initListener() {
		if (listener == null) {
			listener = createPropertyViewerListener();
			propertyEditorViewer.getViewer().addReferenceEditorListener(listener);
		}
	}

	/**
	 * Creates the listener to add to the viewer in order to process viewer
	 * events.
	 * 
	 * @return the {@link MultiLinePropertyViewerListener} to add to the viewer.
	 */
	protected MultiLinePropertyViewerListener createPropertyViewerListener() {
		return new MultiLinePropertyViewerListener(this, view, elementEditor, propertyEditorViewer.getViewer()) {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#removeAll(java.util.Collection)
			 */
			public void removeAll(Collection<?> removedElements) {
				if (isEnabled()) {
					propertyEditor.firePropertiesChanged(view.getEditingComponent(), new PropertiesEditingEventImpl(view, elementEditor, PropertiesEditingEvent.REMOVE_MANY, removedElements, null));
					viewer.refresh();
				}
			}

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#remove(java.lang.Object)
			 */
			public void remove(Object removedElement) {
				if (isEnabled()) {
					propertyEditor.firePropertiesChanged(view.getEditingComponent(), new PropertiesEditingEventImpl(view, elementEditor, PropertiesEditingEvent.REMOVE, removedElement, null));
					viewer.refresh();
				}
			}

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#moveUp(java.lang.Object)
			 */
			public void moveUp(Object movedElement) {
				if (isEnabled()) {
					PropertiesEditingComponent editingComponent = view.getEditingComponent();
					EObject editedObject = editingComponent.getEObject();
					EObject editedElement = editedObject;
					Object currentValue = eefEditingServiceProvider.getEditingService(editedObject).getValue(editingComponent.getEditingContext(), editedElement, elementEditor);
					if (currentValue instanceof List<?>) {
						int oldIndex = ((List<?>) currentValue).indexOf(movedElement);
						if (oldIndex > 0) {
							propertyEditor.firePropertiesChanged(view.getEditingComponent(), new PropertiesEditingEventImpl(view, elementEditor, PropertiesEditingEvent.MOVE, oldIndex, oldIndex - 1));
							viewer.refresh();
						}
					}
				}
			}

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#moveDown(java.lang.Object)
			 */
			public void moveDown(Object movedElement) {
				if (isEnabled()) {
					PropertiesEditingComponent editingComponent = view.getEditingComponent();
					EObject editedObject = editingComponent.getEObject();
					EObject editedElement = editedObject;
					Object currentValue = eefEditingServiceProvider.getEditingService(editedObject).getValue(editingComponent.getEditingContext(), editedElement, elementEditor);
					if (currentValue instanceof List<?>) {
						int oldIndex = ((List<?>) currentValue).indexOf(movedElement);
						if (oldIndex < ((List<?>) currentValue).size()) {
							propertyEditor.firePropertiesChanged(editingComponent, new PropertiesEditingEventImpl(view, elementEditor, PropertiesEditingEvent.MOVE, oldIndex, oldIndex + 1));
							viewer.refresh();
						}
					}
				}
			}

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#edit(java.lang.Object)
			 */
			public void edit(Object editedElement) {
				// TODO: We have to invoke the EditingPropertyPolicy
			}

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.eef.runtime.ui.swt.internal.widgets.MultiLinePropertyViewer.MultiLinePropertyViewerListener#add()
			 */
			public void add() {
				if (isEnabled()) {
					MultiValuedDialog dialog = new MultiValuedDialog(viewer.getControl().getShell());
					dialog.setTitle("Attribute editing");
					if (dialog.open() == Window.OK) {
						if (dialog.getValue() != null) {
							propertyEditor.firePropertiesChanged(view.getEditingComponent(), new PropertiesEditingEventImpl(view, elementEditor, PropertiesEditingEvent.ADD, null, dialog.getValue()));
							viewer.refresh();
						}
					}
				}
			}
		};
	}

}
