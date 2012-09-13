/**
 * 
 */
package org.eclipse.emf.eef.runtime.services.viewhandler.notify;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public interface EEFPropertyNotification extends EEFNotification {
	
	/**
	 * @return the target editor for this notifcation.
	 */
	Object getEditor();

}
