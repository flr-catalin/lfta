package com.uvt.lfta.twostacksmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Utility class for string tokenization.
 * 
 * @author Catalin Florea
 */
public class ExpressionTokenizer {

	/**
	 * Tokenizes the expression with the operators defined in the operator store.
	 * 
	 * @param expression the expression to tokenize
	 * @param operatorStore the operator store
	 * @return the tokenized expression
	 */
	public static List<String> tokenize(String expression, OperatorStore operatorStore) {
		StringTokenizer stringTokenizer = new StringTokenizer(expression, operatorStore.getAsString(), true);
		
		List<String> tokens = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			tokens.add(stringTokenizer.nextToken());
		}
		
		return tokens;
	}
	
}
