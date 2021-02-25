package com.uvt.lfta;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ExpressionTokenizer {

	public static List<String> tokenize(String expression, OperatorStore operatorStore) {
		return tokenize(expression, operatorStore.getAsString());
	}
	
	public static List<String> tokenize(String expression, String delimiters) {
		StringTokenizer stringTokenizer = new StringTokenizer(expression, delimiters, true);
		
		List<String> tokens = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			tokens.add(stringTokenizer.nextToken());
		}
		
		return tokens;
	}
	
}
