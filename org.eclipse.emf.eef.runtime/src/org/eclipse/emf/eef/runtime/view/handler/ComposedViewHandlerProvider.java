/**
 * 
 */
package org.eclipse.emf.eef.runtime.view.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class ComposedViewHandlerProvider implements ViewHandlerProvider {
	
	private List<ViewHandlerProvider> providers;
	
	/**
	 * @param providers {@link ViewHandler}s to manage.
	 */
	private ComposedViewHandlerProvider(List<ViewHandlerProvider> providers) {
		this.providers = providers;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.handler.ViewHandlerProvider#canHandle(java.lang.Object)
	 */
	public boolean canHandle(Object view) {
		for (ViewHandlerProvider provider : providers) {
			if (provider.canHandle(view)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.view.handler.ViewHandlerProvider#getHandler(java.lang.Object)
	 */
	public ViewHandler<?> getHandler(Object view) {
		for (ViewHandlerProvider provider : providers) {
			if (provider.canHandle(view)) {
				return provider.getHandler(view);
			}
		}
		return null;
	}
	
	/**
	 * Builder for {@link ComposedViewHandlerProvider}.
	 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
	 */
	public static class Builder {
		
		private List<ViewHandlerProvider> handlers;


		/**
		 * Builder for {@link ComposedViewHandlerProvider}.
		 */
		public Builder() {
			handlers = new ArrayList<ViewHandlerProvider>();
		}
		
		/**
		 * @param handler {@link ViewHandlerProvider} to add.
		 * @return this builder.
		 */
		public Builder addHandler(ViewHandlerProvider handler) {
			handlers.add(handler);
			return this;
		}
		
		/**
		 * @param handler {@link ViewHandlerProvider} to remove.
		 * @return this builder.
		 */
		public Builder removeHandler(ViewHandlerProvider handler) {
			handlers.add(handler);
			return this;
		}
		
		/**
		 * @return the builded {@link ComposedViewHandlerProvider}.
		 */
		public ComposedViewHandlerProvider build() {
			return new ComposedViewHandlerProvider(handlers);
		}
		
	}

}
