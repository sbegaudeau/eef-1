/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.eef.eefnr.parts;

/**
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class EefnrViewsRepository {

	public static final int SWT_KIND = 0;

	public static final int FORM_KIND = 1;


	/**
	 * Root view descriptor
	 * 
	 */
	public static class Root {
		public static class Properties {
	
			
			public static String samples = "eefnr::Root::properties::samples";
			
	
		}
	
	}

	/**
	 * TotalSample view descriptor
	 * 
	 */
	public static class TotalSample {
		public static class Properties {
	
			
			public static String textRequiredProperty = "eefnr::TotalSample::properties::textRequiredProperty";
			
			
			public static String textOptionalProperty = "eefnr::TotalSample::properties::textOptionalProperty";
			
			
			public static String checkboxRequiredProperty = "eefnr::TotalSample::properties::checkboxRequiredProperty";
			
			
			public static String checkboxOptionalProperty = "eefnr::TotalSample::properties::checkboxOptionalProperty";
			
			
			public static String textareaRequiredProperty = "eefnr::TotalSample::properties::textareaRequiredProperty";
			
			
			public static String textareaOptionalProperty = "eefnr::TotalSample::properties::textareaOptionalProperty";
			
			
			public static String radioRequiredProperty = "eefnr::TotalSample::properties::radioRequiredProperty";
			
			
			public static String radioOptionalProperty = "eefnr::TotalSample::properties::radioOptionalProperty";
			
			
			public static String eobjectflatcomboviewerRequiredProperty = "eefnr::TotalSample::properties::eobjectflatcomboviewerRequiredProperty";
			
			
			public static String eobjectflatcomboviewerOptionalProperty = "eefnr::TotalSample::properties::eobjectflatcomboviewerOptionalProperty";
			
			
			public static String referencestableRequiredProperty = "eefnr::TotalSample::properties::referencestableRequiredProperty";
			
			
			public static String referencestableOptionalProperty = "eefnr::TotalSample::properties::referencestableOptionalProperty";
			
			
			public static String emfcomboviewerRequiredProperty = "eefnr::TotalSample::properties::emfcomboviewerRequiredProperty";
			
			
			public static String emfcomboviewerOptionalProperty = "eefnr::TotalSample::properties::emfcomboviewerOptionalProperty";
			
			
			public static String multivaluededitorRequiredProperty = "eefnr::TotalSample::properties::multivaluededitorRequiredProperty";
			
			
			public static String multivaluededitorOptionalProperty = "eefnr::TotalSample::properties::multivaluededitorOptionalProperty";
			
			
			public static String tablecompositionRequiredProperty = "eefnr::TotalSample::properties::tablecompositionRequiredProperty";
			
			
			public static String tablecompositionOptionalProperty = "eefnr::TotalSample::properties::tablecompositionOptionalProperty";
			
			
			public static String advancedreferencestableRequiredProperty = "eefnr::TotalSample::properties::advancedreferencestableRequiredProperty";
			
			
			public static String advancedreferencestableOptionalProperty = "eefnr::TotalSample::properties::advancedreferencestableOptionalProperty";
			
			
			public static String advancedeobjectflatcomboviewerRequiredPropery = "eefnr::TotalSample::properties::advancedeobjectflatcomboviewerRequiredPropery";
			
			
			public static String advancedeobjectflatcomboviewerOptionalPropery = "eefnr::TotalSample::properties::advancedeobjectflatcomboviewerOptionalPropery";
			
			
			public static String advancedtablecompositionRequiredProperty = "eefnr::TotalSample::properties::advancedtablecompositionRequiredProperty";
			
			
			public static String advancedtablecompositionOptionalProperty = "eefnr::TotalSample::properties::advancedtablecompositionOptionalProperty";
			
			
			public static String name = "eefnr::TotalSample::properties::name";
			
			// Start of user code for CustomElementEditor ElementEditor key
			public static String customElementEditor = "eefnr::TotalSample::properties::CustomElementEditor";
			// End of user code
			
	
		}
	
	}

	/**
	 * TextSample view descriptor
	 * 
	 */
	public static class TextSample {
		public static class Properties {
	
			
			public static String textRequiredProperty = "eefnr::TextSample::properties::textRequiredProperty";
			
			
			public static String textOptionalProperty = "eefnr::TextSample::properties::textOptionalProperty";
			
			
			public static String textROProperty = "eefnr::TextSample::properties::textROProperty";
			
	
		}
	
	}

	/**
	 * CheckboxSample view descriptor
	 * 
	 */
	public static class CheckboxSample {
		public static class Properties {
	
			
			public static String checkboxRequiredProperty = "eefnr::CheckboxSample::properties::checkboxRequiredProperty";
			
			
			public static String checkboxOptionalProperty = "eefnr::CheckboxSample::properties::checkboxOptionalProperty";
			
			
			public static String checkboxROProperty = "eefnr::CheckboxSample::properties::checkboxROProperty";
			
	
		}
	
	}

	/**
	 * TextareaSample view descriptor
	 * 
	 */
	public static class TextareaSample {
		public static class Properties {
	
			
			public static String textareaRequiredProperty = "eefnr::TextareaSample::properties::textareaRequiredProperty";
			
			
			public static String textareaOptionalProperty = "eefnr::TextareaSample::properties::textareaOptionalProperty";
			
			
			public static String textareaROProperty = "eefnr::TextareaSample::properties::textareaROProperty";
			
	
		}
	
	}

	/**
	 * RadioSample view descriptor
	 * 
	 */
	public static class RadioSample {
		public static class Properties {
	
			
			public static String radioRequiredProperty = "eefnr::RadioSample::properties::radioRequiredProperty";
			
			
			public static String radioOptionalProperty = "eefnr::RadioSample::properties::radioOptionalProperty";
			
			
			public static String radioROProperty = "eefnr::RadioSample::properties::radioROProperty";
			
	
		}
	
	}

	/**
	 * EObjectFlatComboViewerSample view descriptor
	 * 
	 */
	public static class EObjectFlatComboViewerSample {
		public static class Properties {
	
			
			public static String eobjectflatcomboviewerRequiredPropery = "eefnr::EObjectFlatComboViewerSample::properties::eobjectflatcomboviewerRequiredPropery";
			
			
			public static String eobjectflatcomboviewerOptionalPropery = "eefnr::EObjectFlatComboViewerSample::properties::eobjectflatcomboviewerOptionalPropery";
			
			
			public static String eobjectflatcomboviewerROPropery = "eefnr::EObjectFlatComboViewerSample::properties::eobjectflatcomboviewerROPropery";
			
	
		}
	
	}

	/**
	 * ReferencesTableSample view descriptor
	 * 
	 */
	public static class ReferencesTableSample {
		public static class Properties {
	
			
			public static String referencestableRequiredProperty = "eefnr::ReferencesTableSample::properties::referencestableRequiredProperty";
			
			
			public static String referencestableOptionalProperty = "eefnr::ReferencesTableSample::properties::referencestableOptionalProperty";
			
			
			public static String referencestableROProperty = "eefnr::ReferencesTableSample::properties::referencestableROProperty";
			
	
		}
	
	}

	/**
	 * EMFComboViewerSample view descriptor
	 * 
	 */
	public static class EMFComboViewerSample {
		public static class Properties {
	
			
			public static String emfcomboviewerRequiredProperty = "eefnr::EMFComboViewerSample::properties::emfcomboviewerRequiredProperty";
			
			
			public static String emfcomboviewerOptionalProperty = "eefnr::EMFComboViewerSample::properties::emfcomboviewerOptionalProperty";
			
			
			public static String emfcomboviewerROProperty = "eefnr::EMFComboViewerSample::properties::emfcomboviewerROProperty";
			
	
		}
	
	}

	/**
	 * MultiValuedEditorSample view descriptor
	 * 
	 */
	public static class MultiValuedEditorSample {
		public static class Properties {
	
			
			public static String multivaluededitorRequiredProperty = "eefnr::MultiValuedEditorSample::properties::multivaluededitorRequiredProperty";
			
			
			public static String multivaluededitorOptionalProperty = "eefnr::MultiValuedEditorSample::properties::multivaluededitorOptionalProperty";
			
			
			public static String multivaluededitorROProperty = "eefnr::MultiValuedEditorSample::properties::multivaluededitorROProperty";
			
	
		}
	
	}

	/**
	 * TableCompositionEditorSample view descriptor
	 * 
	 */
	public static class TableCompositionEditorSample {
		public static class Properties {
	
			
			public static String tablecompositionRequiredProperty = "eefnr::TableCompositionEditorSample::properties::tablecompositionRequiredProperty";
			
			
			public static String tablecompositionOptionalProperty = "eefnr::TableCompositionEditorSample::properties::tablecompositionOptionalProperty";
			
			
			public static String tablecompositionROProperty = "eefnr::TableCompositionEditorSample::properties::tablecompositionROProperty";
			
	
		}
	
	}

	/**
	 * AdvancedReferencesTableSample view descriptor
	 * 
	 */
	public static class AdvancedReferencesTableSample {
		public static class Properties {
	
			
			public static String advancedreferencestableRequiredProperty = "eefnr::AdvancedReferencesTableSample::properties::advancedreferencestableRequiredProperty";
			
			
			public static String advancedreferencestableOptionalProperty = "eefnr::AdvancedReferencesTableSample::properties::advancedreferencestableOptionalProperty";
			
			
			public static String advancedreferencestableROProperty = "eefnr::AdvancedReferencesTableSample::properties::advancedreferencestableROProperty";
			
	
		}
	
	}

	/**
	 * AdvancedEObjectFlatComboViewerSample view descriptor
	 * 
	 */
	public static class AdvancedEObjectFlatComboViewerSample {
		public static class Properties {
	
			
			public static String advancedeobjectflatcomboviewerRequiredProperty = "eefnr::AdvancedEObjectFlatComboViewerSample::properties::advancedeobjectflatcomboviewerRequiredProperty";
			
			
			public static String advancedeobjectflatcomboviewerOptionalProperty = "eefnr::AdvancedEObjectFlatComboViewerSample::properties::advancedeobjectflatcomboviewerOptionalProperty";
			
			
			public static String advancedeobjectflatcomboviewerROProperty = "eefnr::AdvancedEObjectFlatComboViewerSample::properties::advancedeobjectflatcomboviewerROProperty";
			
			
			public static String advancedeobjectflatcomboviewerCompoRequiredProperty = "eefnr::AdvancedEObjectFlatComboViewerSample::properties::advancedeobjectflatcomboviewerCompoRequiredProperty";
			
			
			public static String advancedeobjectflatcomboviewerCompoOptionalProperty = "eefnr::AdvancedEObjectFlatComboViewerSample::properties::advancedeobjectflatcomboviewerCompoOptionalProperty";
			
	
		}
	
	}

	/**
	 * AdvancedTableCompositionEditorSample view descriptor
	 * 
	 */
	public static class AdvancedTableCompositionEditorSample {
		public static class Properties {
	
			
			public static String advancedtablecompositionRequiredProperty = "eefnr::AdvancedTableCompositionEditorSample::properties::advancedtablecompositionRequiredProperty";
			
			
			public static String advancedtablecompositionOptionalProperty = "eefnr::AdvancedTableCompositionEditorSample::properties::advancedtablecompositionOptionalProperty";
			
			
			public static String advancedtablecompositionROProperty = "eefnr::AdvancedTableCompositionEditorSample::properties::advancedtablecompositionROProperty";
			
	
		}
	
	}

	/**
	 * FlatReferenceTableSample view descriptor
	 * 
	 */
	public static class FlatReferenceTableSample {
		public static class Properties {
	
			
			public static String flatreferencetableRequiredProperty = "eefnr::FlatReferenceTableSample::properties::flatreferencetableRequiredProperty";
			
			
			public static String flatreferencetableOptionalProperty = "eefnr::FlatReferenceTableSample::properties::flatreferencetableOptionalProperty";
			
			
			public static String flatreferencetableROProperty = "eefnr::FlatReferenceTableSample::properties::flatreferencetableROProperty";
			
	
		}
	
	}

	/**
	 * Sample view descriptor
	 * 
	 */
	public static class Sample {
		public static class Properties {
	
			
			public static String textRequiredProperty = "eefnr::Sample::properties::textRequiredProperty";
			
			
			public static String textOptionalProperty = "eefnr::Sample::properties::textOptionalProperty";
			
	
		}
	
	}

	/**
	 * TextSampleFirstTab view descriptor
	 * 
	 */
	public static class TextSampleFirstTab {
		public static class Properties {
	
			
			public static String textRequiredPropertyInFirstTab = "eefnr::TextSampleFirstTab::properties::textRequiredPropertyInFirstTab";
			
			
			public static String textOptionalPropertyInFirstTab = "eefnr::TextSampleFirstTab::properties::textOptionalPropertyInFirstTab";
			
	
		}
	
	}

	/**
	 * TextSampleSecondTab view descriptor
	 * 
	 */
	public static class TextSampleSecondTab {
		public static class Properties {
	
			
			public static String textRequiredPropertyInSecondTab = "eefnr::TextSampleSecondTab::properties::textRequiredPropertyInSecondTab";
			
			
			public static String textOptionalPropertyInSecondTab = "eefnr::TextSampleSecondTab::properties::textOptionalPropertyInSecondTab";
			
	
		}
	
	}

	/**
	 * TableCompositionExtensionEditorSample view descriptor
	 * 
	 */
	public static class TableCompositionExtensionEditorSample {
		public static class Properties {
	
			
			public static String name = "eefnr::TableCompositionExtensionEditorSample::properties::name";
			
			
			public static String tablecompositionRequiredProperty = "eefnr::TableCompositionExtensionEditorSample::properties::tablecompositionRequiredProperty";
			
			
			public static String tablecompositionOptionalProperty = "eefnr::TableCompositionExtensionEditorSample::properties::tablecompositionOptionalProperty";
			
	
		}
	
	}

	/**
	 * ImageViewerSample view descriptor
	 * 
	 */
	public static class ImageViewerSample {
		public static class Properties {
	
			
			public static String imageviewerRequiredProperty = "eefnr::ImageViewerSample::properties::imageviewerRequiredProperty";
			
			
			public static String imageviewerOptionalProperty = "eefnr::ImageViewerSample::properties::imageviewerOptionalProperty";
			
			
			public static String imageviewerROProperty = "eefnr::ImageViewerSample::properties::imageviewerROProperty";
			
	
		}
	
	}

	/**
	 * SelectionDialogSample view descriptor
	 * 
	 */
	public static class SelectionDialogSample {
		public static class Properties {
	
			
			public static String selectionDialogRequiredProperty = "eefnr::SelectionDialogSample::properties::selectionDialogRequiredProperty";
			
			
			public static String selectionDialogOptionalProperty = "eefnr::SelectionDialogSample::properties::selectionDialogOptionalProperty";
			
			
			public static String selectionDialogROProperty = "eefnr::SelectionDialogSample::properties::selectionDialogROProperty";
			
	
		}
	
	}

	/**
	 * SingleCompositionViewerSample view descriptor
	 * 
	 */
	public static class SingleCompositionViewerSample {
		public static class Properties {
	
			
			public static String singlecompositionviewerSingleRequiredProperty = "eefnr::SingleCompositionViewerSample::properties::singlecompositionviewerSingleRequiredProperty";
			
			
			public static String singlecompositionviewerSingleOptionalProperty = "eefnr::SingleCompositionViewerSample::properties::singlecompositionviewerSingleOptionalProperty";
			
			
			public static String singlecompositionviewerMultiRequiredProperty = "eefnr::SingleCompositionViewerSample::properties::singlecompositionviewerMultiRequiredProperty";
			
			
			public static String singlecompositionviewerMultiOptionalProperty = "eefnr::SingleCompositionViewerSample::properties::singlecompositionviewerMultiOptionalProperty";
			
			
			public static String singlecompositionviewerSingleROProperty = "eefnr::SingleCompositionViewerSample::properties::singlecompositionviewerSingleROProperty";
			
	
		}
	
	}

	/**
	 * SingleCompositionEditorSample view descriptor
	 * 
	 */
	public static class SingleCompositionEditorSample {
		public static class Properties {
	
			
			public static String singlecompositionviewerRequiredProperty = "eefnr::SingleCompositionEditorSample::properties::singlecompositionviewerRequiredProperty";
			
			
			public static String singlecompositionviewerOptionalProperty = "eefnr::SingleCompositionEditorSample::properties::singlecompositionviewerOptionalProperty";
			
			
			public static String singlecompositionviewerROProperty = "eefnr::SingleCompositionEditorSample::properties::singlecompositionviewerROProperty";
			
			
			public static String singlecompositionviewerOnAbstractOptionalProperty = "eefnr::SingleCompositionEditorSample::properties::singlecompositionviewerOnAbstractOptionalProperty";
			
	
		}
	
	}

	/**
	 * EReferencesViewerSample view descriptor
	 * 
	 */
	public static class EReferencesViewerSample {
		public static class Properties {
	
			
			public static String ereferencesviewerRequiredProperty = "eefnr::EReferencesViewerSample::properties::ereferencesviewerRequiredProperty";
			
			
			public static String ereferencesviewerOptionalProperty = "eefnr::EReferencesViewerSample::properties::ereferencesviewerOptionalProperty";
			
			
			public static String ereferencesviewerROProperty = "eefnr::EReferencesViewerSample::properties::ereferencesviewerROProperty";
			
	
		}
	
	}

	/**
	 * LinkEObjectFlatComboViewerSample view descriptor
	 * 
	 */
	public static class LinkEObjectFlatComboViewerSample {
		public static class Properties {
	
			
			public static String linkeobjectflatcomboviewerRequiredPropery = "eefnr::LinkEObjectFlatComboViewerSample::properties::linkeobjectflatcomboviewerRequiredPropery";
			
			
			public static String linkeobjectflatcomboviewerOptionalPropery = "eefnr::LinkEObjectFlatComboViewerSample::properties::linkeobjectflatcomboviewerOptionalPropery";
			
			
			public static String linkeobjectflatcomboviewerROPropery = "eefnr::LinkEObjectFlatComboViewerSample::properties::linkeobjectflatcomboviewerROPropery";
			
	
		}
	
	}

	/**
	 * LinkEReferenceViewerSample view descriptor
	 * 
	 */
	public static class LinkEReferenceViewerSample {
		public static class Properties {
	
			
			public static String linkereferenceviewerRequiredPropery = "eefnr::LinkEReferenceViewerSample::properties::linkereferenceviewerRequiredPropery";
			
			
			public static String linkereferenceviewerOptionalPropery = "eefnr::LinkEReferenceViewerSample::properties::linkereferenceviewerOptionalPropery";
			
			
			public static String linkereferenceviewerROPropery = "eefnr::LinkEReferenceViewerSample::properties::linkereferenceviewerROPropery";
			
	
		}
	
	}

}
