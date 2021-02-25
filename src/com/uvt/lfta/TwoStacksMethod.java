package com.uvt.lfta;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class TwoStacksMethod {

	private Stack<Operator> operatorStack;
	
	private Stack<String> operandStack;
	
	private OperatorStore operatorStore;
	
	public TwoStacksMethod(OperatorStore operatorStore) {
		this.operatorStack = new Stack<Operator>();
		this.operandStack = new Stack<String>();
		this.operatorStore = operatorStore;
	}

	public void execute(String expression) {
		List<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression, operatorStore);
		
		for (String token : tokenizedExpression) {
			Optional<Operator> optionalOperator = operatorStore.getOperatorBySymbol(token);
			
			if (optionalOperator.isPresent()) {
				Operator operator = optionalOperator.get();
				
				while (!isHigherPriority(operator) && operandStack.size() > 1) {
					operandStack.add(getNewOperand());
				}
				
				System.out.println("Operatorul " + operator.getSymbol() + " cu prioritatea " + operator.getPriority() + " a ajuns in stiva.");
				operatorStack.add(operator);
			} else {
				System.out.println("Operandul " + token + " a ajuns in stiva.");
				operandStack.add(token);
			}
		}
	}

	private boolean isHigherPriority(Operator operator) {
		if (operator.getPriority() > getHighestPriority()) {
			return true;
		}
		
		return false;
	}
	
	private int getHighestPriority() {
		int highestPriority = -1;
		
		for (Operator operator : operatorStack) {
			if (operator.getPriority() > highestPriority) {
				highestPriority = operator.getPriority();
			}
		}
		
		return highestPriority;
	}
	
	private String getNewOperand() {
		Operator pop = operatorStack.pop();
		String lastOperand = operandStack.pop();
		String secondLastOperand = operandStack.pop();
		
		System.out.println("Aplicam operatorul " + pop.getSymbol() + " operanzilor " + secondLastOperand + " si " + lastOperand);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(secondLastOperand).append(pop.getSymbol()).append(lastOperand);
		
		return stringBuilder.toString();
	}
	
}
