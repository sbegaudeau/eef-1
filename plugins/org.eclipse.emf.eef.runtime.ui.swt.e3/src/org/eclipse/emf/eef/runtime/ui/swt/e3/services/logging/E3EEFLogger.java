/**
 * 
 */
package org.eclipse.emf.eef.runtime.ui.swt.e3.services.logging;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.eef.runtime.services.logging.EEFLogger;
import org.eclipse.emf.eef.runtime.ui.swt.e3.E3EEFRuntimeUIPlatformPlugin;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class E3EEFLogger implements EEFLogger {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.services.logging.EEFLogger#logWarning(java.lang.String, java.lang.String, java.lang.Throwable)
	 */
	public void logWarning(String pluginID, String message, Throwable t) {
		E3EEFRuntimeUIPlatformPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, pluginID, message, t));
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.services.logging.EEFLogger#logError(java.lang.String, java.lang.String, java.lang.Throwable)
	 */
	public void logError(String pluginID, String message, Throwable t) {
		E3EEFRuntimeUIPlatformPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, pluginID, message, t));
	}

}