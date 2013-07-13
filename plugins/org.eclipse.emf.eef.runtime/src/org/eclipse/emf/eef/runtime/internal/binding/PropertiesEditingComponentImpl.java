/**
 * 
 */
package org.eclipse.emf.eef.runtime.internal.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.binding.settings.EEFBindingSettings;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.editingModel.EClassBinding;
import org.eclipse.emf.eef.runtime.editingModel.EditingModelEnvironment;
import org.eclipse.emf.eef.runtime.editingModel.PropertiesEditingModel;
import org.eclipse.emf.eef.runtime.internal.binding.settings.AbstractEEFBindingSettings;
import org.eclipse.emf.eef.runtime.notify.PropertiesEditingEvent;
import org.eclipse.emf.eef.runtime.notify.PropertiesEditingListener;
import org.eclipse.emf.eef.runtime.notify.ViewChangeNotifier;
import org.eclipse.emf.eef.runtime.view.handle.ViewHandler;
import org.eclipse.emf.eef.runtime.view.handle.ViewHandlerFactory;
import org.eclipse.emf.eef.runtime.view.lock.policies.EEFLockPolicy;

import com.google.common.collect.Lists;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class PropertiesEditingComponentImpl implements PropertiesEditingComponent {

	private EEFBindingSettings bindingSettings;
	private EObject source;
	private PropertiesEditingContext editingContext;
	private PropertiesEditingModel editingModel;
	private List<ViewHandler<?>> viewHandlers;
	private Collection<EEFLockPolicy> lockPolicies;
	private ViewChangeNotifier viewChangeNotifier;
	private List<PropertiesEditingListener> listeners;
	
	/**
	 * @param bindingSettings {@link AbstractEEFBindingSettings} providing this component.
	 * @param source
	 */
	public PropertiesEditingComponentImpl(EEFBindingSettings bindingSettings, EObject source) {
		this.bindingSettings = bindingSettings;
		this.source = source;
		this.listeners = Lists.newArrayList();
		this.viewHandlers = Lists.newArrayList();
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getEObject()
	 * @state
	 */
	public EObject getEObject() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getEditingContext()
	 * @state
	 */
	public PropertiesEditingContext getEditingContext() {
		return editingContext;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#setEditingContext(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * @state
	 */
	public void setEditingContext(PropertiesEditingContext editingContext) {
		this.editingContext = editingContext;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getEditingModelEnvironment()
	 * @processing
	 */
	public EditingModelEnvironment getEditingModelEnvironment() {
		return bindingSettings.getEditingModelEnvironment();
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getEditingModel()
	 */
	public PropertiesEditingModel getEditingModel() {
		if (editingModel == null) {
			editingModel = bindingSettings.getEditingModel(source);
		}
		return editingModel;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getBinding()
	 * @processing
	 */
	public EClassBinding getBinding() {
		PropertiesEditingModel editingModel = getEditingModel();
		if (editingModel != null) {
			return editingModel.binding(source);
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#addEditingListener(org.eclipse.emf.eef.runtime.notify.PropertiesEditingListener)
	 * @state
	 */
	public void addEditingListener(PropertiesEditingListener listener) {
		listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#removeEditingListener(org.eclipse.emf.eef.runtime.notify.PropertiesEditingListener)
	 * @state
	 */
	public void removeEditingListener(PropertiesEditingListener listener) {
		listeners.remove(listener);		
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getLockPolicies()
	 */
	public Collection<EEFLockPolicy> getLockPolicies() {
		return lockPolicies;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#setLockPolicies(java.util.Collection)
	 */
	public void setLockPolicies(Collection<EEFLockPolicy> lockPolicies) {
		this.lockPolicies = lockPolicies;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#isAffectingEvent(org.eclipse.emf.common.notify.Notification)
	 */
	public boolean isAffectingEvent(Notification notification) {
		return notification.getNotifier() == source;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#enableLockPolicy(org.eclipse.emf.eef.runtime.view.lock.policies.EEFLockPolicy)
	 */
	public boolean enableLockPolicy(EEFLockPolicy lockPolicy) {
		return bindingSettings.enableLockPolicy(lockPolicy);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#getViewChangeNotifier()
	 * @state
	 */
	public ViewChangeNotifier getViewChangeNotifier() {
		if (viewChangeNotifier == null) {
			viewChangeNotifier = new ViewChangeNotifier(editingContext.getBindingManagerProvider(), this);
		}
		return viewChangeNotifier;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#createViewHandler(java.lang.Object)
	 */
	public ViewHandler<?> createViewHandler(Object view) {
		ViewHandler<?> specifiedHandler = getEditingModel().viewHandler(source, view);
		if (specifiedHandler != null) {
			registerViewHandler(specifiedHandler);
			return specifiedHandler;
		} else {
			ViewHandlerFactory viewHandlerFactory = bindingSettings.getViewHandlerFactory(view);
			if (viewHandlerFactory != null) {
				ViewHandler<?> handler = viewHandlerFactory.getHandler(this, view);
				if (handler != null) {
					registerViewHandler(handler);
					return handler;
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#createViewHandlers()
	 * @processing
	 */
	public Collection<ViewHandler<?>> createViewHandlers() {
		PropertiesEditingModel editingModel = getEditingModel();
		if (editingModel != null) {
			List<ViewHandler<?>> result = Lists.newArrayList();
			for (Object view : editingModel.views(source)) {
				result.add(createViewHandler(view));
			}
			return result;
		}
		return Collections.emptyList();
	}

	/**
	 * @return the viewHandlers
	 */
	public List<ViewHandler<?>> getViewHandlers() {
		return viewHandlers;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#registerViewHandler(org.eclipse.emf.eef.runtime.view.handle.ViewHandler)
	 * @state
	 */
	public void registerViewHandler(ViewHandler<?> handler) {
		viewHandlers.add(handler);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#unregisterViewHandler(org.eclipse.emf.eef.runtime.view.handle.ViewHandler)
	 * @state
	 */
	public void unregisterViewHandler(ViewHandler<?> handler) {
		viewHandlers.remove(handler);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @state
	 */
	public void propagateEvent(PropertiesEditingEvent event) {
		event.addHolder(this);
		for (PropertiesEditingListener listener : listeners) {
			if (!event.hold(listener))
				listener.firePropertiesChanged(event);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.PropertiesEditingComponent#dispose()
	 * @state
	 */
	public void dispose() {
		List<ViewHandler<?>> handlers = Lists.newArrayList(viewHandlers);
		for (ViewHandler<?> handler : handlers) {
			handler.dispose();
		}
		editingContext.disposeComponent(this);
		
		// Making a blank component to be sure to not reuse it!
		bindingSettings = null;
		source = null;
		editingContext = null;
		editingModel = null;
		viewHandlers.clear();
		viewChangeNotifier = null;
		listeners.clear();
	}

}
