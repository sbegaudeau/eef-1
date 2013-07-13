/**
 * 
 */
package org.eclipse.emf.samples.conference.providers;

import org.eclipse.emf.eef.runtime.binding.settings.EEFBindingSettingsImpl;

/**
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 *
 */
public class ConferenceEEFBindingSettings extends EEFBindingSettingsImpl {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.binding.settings.EEFBindingSettingsImpl#getEditingModelPath()
	 */
	
	protected String getEditingModelPath() {
		return "platform:/plugin/org.eclipse.emf.examples.eef.edit/models/conference.editingmodel";
	}

	


}