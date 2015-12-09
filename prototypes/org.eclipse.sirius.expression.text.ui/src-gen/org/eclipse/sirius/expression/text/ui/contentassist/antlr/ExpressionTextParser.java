/*
 * generated by Xtext
 */
package org.eclipse.sirius.expression.text.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.sirius.expression.text.services.ExpressionTextGrammarAccess;

public class ExpressionTextParser extends AbstractContentAssistParser {
	
	@Inject
	private ExpressionTextGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.sirius.expression.text.ui.contentassist.antlr.internal.InternalExpressionTextParser createParser() {
		org.eclipse.sirius.expression.text.ui.contentassist.antlr.internal.InternalExpressionTextParser result = new org.eclipse.sirius.expression.text.ui.contentassist.antlr.internal.InternalExpressionTextParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getSiriusExpressionPackageAccess().getGroup(), "rule__SiriusExpressionPackage__Group__0");
					put(grammarAccess.getSiriusExpressionClassAccess().getGroup(), "rule__SiriusExpressionClass__Group__0");
					put(grammarAccess.getFQNAccess().getGroup(), "rule__FQN__Group__0");
					put(grammarAccess.getFQNAccess().getGroup_1(), "rule__FQN__Group_1__0");
					put(grammarAccess.getSiriusVariableAccess().getGroup(), "rule__SiriusVariable__Group__0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getGroup(), "rule__SiriusExpressionDescription__Group__0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getGroup_3(), "rule__SiriusExpressionDescription__Group_3__0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getGroup_3_1(), "rule__SiriusExpressionDescription__Group_3_1__0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getGroup_16(), "rule__SiriusExpressionDescription__Group_16__0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getGroup_16_1(), "rule__SiriusExpressionDescription__Group_16_1__0");
					put(grammarAccess.getSiriusParameterAccess().getGroup(), "rule__SiriusParameter__Group__0");
					put(grammarAccess.getBoundAccess().getGroup(), "rule__Bound__Group__0");
					put(grammarAccess.getSiriusExpressionPackageAccess().getEPackageAssignment_1(), "rule__SiriusExpressionPackage__EPackageAssignment_1");
					put(grammarAccess.getSiriusExpressionPackageAccess().getExpressionClassesAssignment_3(), "rule__SiriusExpressionPackage__ExpressionClassesAssignment_3");
					put(grammarAccess.getSiriusExpressionClassAccess().getEClassAssignment_1(), "rule__SiriusExpressionClass__EClassAssignment_1");
					put(grammarAccess.getSiriusExpressionClassAccess().getVariablesAssignment_3(), "rule__SiriusExpressionClass__VariablesAssignment_3");
					put(grammarAccess.getSiriusExpressionClassAccess().getExpressionDescriptionsAssignment_4(), "rule__SiriusExpressionClass__ExpressionDescriptionsAssignment_4");
					put(grammarAccess.getSiriusVariableAccess().getDocumentationAssignment_0(), "rule__SiriusVariable__DocumentationAssignment_0");
					put(grammarAccess.getSiriusVariableAccess().getNameAssignment_2(), "rule__SiriusVariable__NameAssignment_2");
					put(grammarAccess.getSiriusVariableAccess().getETypeAssignment_4(), "rule__SiriusVariable__ETypeAssignment_4");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getExpressionAssignment_1(), "rule__SiriusExpressionDescription__ExpressionAssignment_1");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getParametersAssignment_3_0(), "rule__SiriusExpressionDescription__ParametersAssignment_3_0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getParametersAssignment_3_1_1(), "rule__SiriusExpressionDescription__ParametersAssignment_3_1_1");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getReturnTypeAssignment_6(), "rule__SiriusExpressionDescription__ReturnTypeAssignment_6");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getLowerBoundAssignment_8(), "rule__SiriusExpressionDescription__LowerBoundAssignment_8");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getUpperBoundAssignment_10(), "rule__SiriusExpressionDescription__UpperBoundAssignment_10");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getVariableContainersAssignment_16_0(), "rule__SiriusExpressionDescription__VariableContainersAssignment_16_0");
					put(grammarAccess.getSiriusExpressionDescriptionAccess().getVariableContainersAssignment_16_1_1(), "rule__SiriusExpressionDescription__VariableContainersAssignment_16_1_1");
					put(grammarAccess.getSiriusParameterAccess().getOptionalAssignment_0(), "rule__SiriusParameter__OptionalAssignment_0");
					put(grammarAccess.getSiriusParameterAccess().getVariableAssignment_1(), "rule__SiriusParameter__VariableAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.sirius.expression.text.ui.contentassist.antlr.internal.InternalExpressionTextParser typedParser = (org.eclipse.sirius.expression.text.ui.contentassist.antlr.internal.InternalExpressionTextParser) parser;
			typedParser.entryRuleSiriusExpressionPackage();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS" };
	}
	
	public ExpressionTextGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ExpressionTextGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
