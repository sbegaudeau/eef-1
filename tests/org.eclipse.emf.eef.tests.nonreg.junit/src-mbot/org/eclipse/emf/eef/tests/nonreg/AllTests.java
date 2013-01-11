package org.eclipse.emf.eef.tests.nonreg;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotAddAdvTableCompoOnAbstractRef;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotAddTableCompo;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotCancelAddTableCompo;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotCancelSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotCancelSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotCancelSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRedoAddTableCompo;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRedoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRedoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRedoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRedoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotRemoveTableCompo;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUndoAddTableCompo;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUndoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUndoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUndoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUndoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetReferenceEOFCV;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.composed.detailsview.ComposedMBotUnsetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotCancelAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotCancelSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotCancelSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotCancelSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotEditSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotEditSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotEditSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotMoveTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRedoAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRedoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRedoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRedoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRedoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotRemoveTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUndoAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUndoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUndoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUndoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUndoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetFlatReferencesTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetReferenceEOFCV;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.propertiesView.PViewMBotUnsetReferencesTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotCancelAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotCancelSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotCancelSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotCancelSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotEditSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotEditSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRedoAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRedoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRedoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRedoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRedoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotRemoveTableComposition;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUndoAddTableComposition;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUndoSetAttributeRadio;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUndoSetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUndoSetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUndoSetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetFlatReferencesTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetReferenceAEOFCV;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetReferenceFlatRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetReferenceRefTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetReferencesTable;
import org.eclipse.emf.eef.tests.nonreg.wizard.WizardMBotUnsetTextArea;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$		
		
		// Properties Views
		suite.addTestSuite(PViewMBotAddTableComposition.class);
		suite.addTestSuite(PViewMBotCancelAddTableComposition.class);
		suite.addTestSuite(PViewMBotCancelSetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotCancelSetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotCancelSetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotEditSetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotEditSetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotEditSetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotMoveTableComposition.class);
		suite.addTestSuite(PViewMBotRedoAddTableComposition.class);
		suite.addTestSuite(PViewMBotRedoSetAttributeRadio.class);
		suite.addTestSuite(PViewMBotRedoSetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotRedoSetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotRedoSetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotRemoveTableComposition.class);
		suite.addTestSuite(PViewMBotSetAttributeRadio.class);
		suite.addTestSuite(PViewMBotSetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotSetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotSetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotUndoAddTableComposition.class);
		suite.addTestSuite(PViewMBotUndoSetAttributeRadio.class);
		suite.addTestSuite(PViewMBotUndoSetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotUndoSetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotUndoSetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotUnsetAEOFCV.class);
		suite.addTestSuite(PViewMBotUnsetEOFCV.class);
		suite.addTestSuite(PViewMBotUnsetFlatReferencesTable.class);
		suite.addTestSuite(PViewMBotUnsetReferenceAEOFCV.class);
		suite.addTestSuite(PViewMBotUnsetReferenceEOFCV.class);
		suite.addTestSuite(PViewMBotUnsetReferenceFlatRefTable.class);
		suite.addTestSuite(PViewMBotUnsetReferenceRefTable.class);
		suite.addTestSuite(PViewMBotUnsetReferencesTable.class);
		
		// Wizards
		
// It should'nt have never passed !
		suite.addTestSuite(WizardMBotAddTableComposition.class);
		suite.addTestSuite(WizardMBotCancelAddTableComposition.class);
		suite.addTestSuite(WizardMBotCancelSetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotCancelSetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotCancelSetReferenceRefTable.class);
		suite.addTestSuite(WizardMBotEditSetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotEditSetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotRedoAddTableComposition.class);
		suite.addTestSuite(WizardMBotRedoSetAttributeRadio.class);
		suite.addTestSuite(WizardMBotRedoSetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotRedoSetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotRedoSetReferenceRefTable.class);
		suite.addTestSuite(WizardMBotRemoveTableComposition.class);
		suite.addTestSuite(WizardMBotSetAttributeRadio.class);
		suite.addTestSuite(WizardMBotSetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotSetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotSetReferenceRefTable.class);
		suite.addTestSuite(WizardMBotUndoAddTableComposition.class);
		suite.addTestSuite(WizardMBotUndoSetAttributeRadio.class);
		suite.addTestSuite(WizardMBotUndoSetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotUndoSetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotUndoSetReferenceRefTable.class);
		suite.addTestSuite(WizardMBotUnsetAEOFCV.class);
		suite.addTestSuite(WizardMBotUnsetEOFCV.class);
		suite.addTestSuite(WizardMBotUnsetFlatReferencesTable.class);
		suite.addTestSuite(WizardMBotUnsetReferenceAEOFCV.class);
		suite.addTestSuite(WizardMBotUnsetReferenceFlatRefTable.class);
		suite.addTestSuite(WizardMBotUnsetReferenceRefTable.class);
		suite.addTestSuite(WizardMBotUnsetReferencesTable.class);
		suite.addTestSuite(WizardMBotUnsetTextArea.class);
		
		// Composed
		suite.addTestSuite(ComposedMBotAddAdvTableCompoOnAbstractRef.class);
		suite.addTestSuite(ComposedMBotAddTableCompo.class);
		suite.addTestSuite(ComposedMBotCancelAddTableCompo.class);
		suite.addTestSuite(ComposedMBotCancelSetReferenceAEOFCV.class);
		suite.addTestSuite(ComposedMBotCancelSetReferenceFlatRefTable.class);
		suite.addTestSuite(ComposedMBotCancelSetReferenceRefTable.class);
		suite.addTestSuite(ComposedMBotRedoAddTableCompo.class);
		suite.addTestSuite(ComposedMBotRedoSetAttributeRadio.class);
		suite.addTestSuite(ComposedMBotRedoSetReferenceAEOFCV.class);
		suite.addTestSuite(ComposedMBotRedoSetReferenceFlatRefTable.class);
		suite.addTestSuite(ComposedMBotRedoSetReferenceRefTable.class);
		suite.addTestSuite(ComposedMBotRemoveTableCompo.class);
		suite.addTestSuite(ComposedMBotSetAttributeRadio.class);
		suite.addTestSuite(ComposedMBotSetReferenceAEOFCV.class);
		suite.addTestSuite(ComposedMBotSetReferenceFlatRefTable.class);
		suite.addTestSuite(ComposedMBotSetReferenceRefTable.class);
		suite.addTestSuite(ComposedMBotUndoAddTableCompo.class);
		suite.addTestSuite(ComposedMBotUndoSetAttributeRadio.class);
		suite.addTestSuite(ComposedMBotUndoSetReferenceAEOFCV.class);
		suite.addTestSuite(ComposedMBotUndoSetReferenceFlatRefTable.class);
		suite.addTestSuite(ComposedMBotUndoSetReferenceRefTable.class);
		suite.addTestSuite(ComposedMBotUnsetAEOFCV.class);
		suite.addTestSuite(ComposedMBotUnsetEOFCV.class);
		suite.addTestSuite(ComposedMBotUnsetFlatRefTable.class);
		suite.addTestSuite(ComposedMBotUnsetReferenceAEOFCV.class);
		suite.addTestSuite(ComposedMBotUnsetReferenceEOFCV.class);
		suite.addTestSuite(ComposedMBotUnsetReferenceFlatRefTable.class);
		suite.addTestSuite(ComposedMBotUnsetReferenceRefTable.class);
		suite.addTestSuite(ComposedMBotUnsetRefTable.class);
		//$JUnit-END$
		return suite;
	}

}
