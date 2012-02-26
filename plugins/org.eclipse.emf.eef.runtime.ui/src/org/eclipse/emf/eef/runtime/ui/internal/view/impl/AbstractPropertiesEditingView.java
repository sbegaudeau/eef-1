/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.internal.view.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.notify.PropertiesEditingEvent;
import org.eclipse.emf.eef.runtime.ui.internal.view.util.ViewHelperImpl;
import org.eclipse.emf.eef.runtime.ui.internal.view.util.ViewSettingsImpl;
import org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView;
import org.eclipse.emf.eef.runtime.ui.view.ViewHelper;
import org.eclipse.emf.eef.runtime.ui.view.ViewSettings;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MonovaluedPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.MultivaluedPropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditor;
import org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorProvider;
import org.eclipse.emf.eef.runtime.ui.view.section.SectionPropertiesEditingView;
import org.eclipse.emf.eef.views.ElementEditor;
import org.eclipse.emf.eef.views.View;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public abstract class AbstractPropertiesEditingView implements PropertiesEditingView {

	protected PropertiesEditingComponent editingComponent;
	protected View viewDescriptor;
	protected PropertyEditorProvider propertyEditorProvider;
	
	protected Map<ElementEditor, PropertyEditor> propertyEditors;
	protected Composite contentsComposite;
	
	/**
	 * Non-parameterized constructor for {@link SectionPropertiesEditingView} purpose.
	 * Mustn't be use otherwise.
	 */
	public AbstractPropertiesEditingView() { }
	
	/**
	 * @param editingComponent {@link PropertiesEditingComponent} managing the view.
	 */
	public AbstractPropertiesEditingView(PropertiesEditingComponent editingComponent, View viewDescriptor) {
		this.viewDescriptor = viewDescriptor;
		this.editingComponent = editingComponent;
		this.propertyEditors = new HashMap<ElementEditor, PropertyEditor>();
		editingComponent.addEditingListener(this);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#getEditingComponent()
	 */
	public PropertiesEditingComponent getEditingComponent() {
		return editingComponent;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#getViewHelper()
	 */
	public ViewHelper getViewHelper() {
		if (editingComponent != null) {
			return new ViewHelperImpl(editingComponent);
		} else {
			return new ViewHelperImpl();
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#getViewSettings()
	 */
	public ViewSettings getViewSettings() {
		return new ViewSettingsImpl();
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#setPropertyEditorProvider(org.eclipse.emf.eef.runtime.ui.view.propertyeditors.PropertyEditorProvider)
	 */
	public void setPropertyEditorProvider(PropertyEditorProvider propertyEditorProvider) {
		this.propertyEditorProvider = propertyEditorProvider;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#getContents()
	 */
	public Composite getContents() {
		return contentsComposite;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#init()
	 */
	public void init() {
		UnmodifiableIterator<ElementEditor> elementEditors = Iterators.filter(viewDescriptor.eAllContents(), ElementEditor.class);
		while (elementEditors.hasNext()) {
			ElementEditor elementEditor = elementEditors.next();
			EStructuralFeature feature = editingComponent.getBinding().feature(elementEditor, editingComponent.getEditingContext().getOptions().autowire());
			if (feature != null) {
				PropertyEditor propertyEditor = propertyEditors.get(elementEditor);
				propertyEditor.init(feature);
			}
			
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.notify.EditingListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.notify.PropertiesEditingEvent)
	 */
	public void firePropertiesChanged(PropertiesEditingEvent event) {
		// Default : Do nothing
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#setValue(java.lang.Object, java.lang.Object)
	 */
	public void setValue(Object field, Object value) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MonovaluedPropertyEditor) {
				((MonovaluedPropertyEditor) propertyEditor).setValue(value);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#unsetValue(java.lang.Object)
	 */
	public void unsetValue(Object field) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MonovaluedPropertyEditor) {
				((MonovaluedPropertyEditor) propertyEditor).unsetValue();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#addValue(java.lang.Object, java.lang.Object)
	 */
	public void addValue(Object field, Object value) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MultivaluedPropertyEditor) {
				((MultivaluedPropertyEditor) propertyEditor).addValue(value);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#addAllValues(java.lang.Object, java.util.Collection)
	 */
	public void addAllValues(Object field, Collection<?> values) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MultivaluedPropertyEditor) {
				((MultivaluedPropertyEditor) propertyEditor).addAllValues(values);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#removeValue(java.lang.Object, java.lang.Object)
	 */
	public void removeValue(Object field, Object value) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MultivaluedPropertyEditor) {
				((MultivaluedPropertyEditor) propertyEditor).removeValue(value);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#removeAllValues(java.lang.Object, java.util.Collection)
	 */
	public void removeAllValues(Object field, Collection<?> values) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MultivaluedPropertyEditor) {
				((MultivaluedPropertyEditor) propertyEditor).removeAllValues(values);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.ui.view.PropertiesEditingView#moveValue(java.lang.Object, java.lang.Object, int)
	 */
	public void moveValue(Object field, Object value, int newIndex) {
		if (field instanceof ElementEditor) {
			PropertyEditor propertyEditor = propertyEditors.get(field);
			if (propertyEditor instanceof MultivaluedPropertyEditor) {
				((MultivaluedPropertyEditor) propertyEditor).moveValue(value, newIndex);
			}
		}
	}

}