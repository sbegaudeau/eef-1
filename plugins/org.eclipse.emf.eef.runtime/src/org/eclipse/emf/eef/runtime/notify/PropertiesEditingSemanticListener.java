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
package org.eclipse.emf.eef.runtime.notify;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.eef.runtime.components.PropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.parts.PropertiesEditingPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public abstract class PropertiesEditingSemanticListener extends EContentAdapter {

	private PropertiesEditingComponent component;

	private PropertiesEditingPart part;

	/**
	 * @param component
	 */
	public PropertiesEditingSemanticListener(PropertiesEditingComponent component) {
		this.component = component;
	}

	/**
	 * @return the part
	 */
	public PropertiesEditingPart getPart() {
		return part;
	}

	/**
	 * @param part
	 *            the part to set
	 */
	public void setPart(PropertiesEditingPart part) {
		this.part = part;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		if (part == null)
			component.dispose();
		else {
			Runnable updateRunnable = new Runnable() {
				public void run() {
					runUpdateRunnable(notification);
				}
			};
			if (null == Display.getCurrent()) {
				PlatformUI.getWorkbench().getDisplay().syncExec(updateRunnable);
			} else {
				updateRunnable.run();
			}
		}
	}

	/**
	 * Performs update in the views
	 * 
	 * @param notification
	 *            the model notification
	 */
	public abstract void runUpdateRunnable(Notification notification);

}