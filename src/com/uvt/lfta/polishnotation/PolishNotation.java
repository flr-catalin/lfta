package com.uvt.lfta.polishnotation;

import java.util.List;
import java.util.Stack;

import com.uvt.lfta.utility.ExpressionTokenizer;
import com.uvt.lfta.utility.Operator;
import com.uvt.lfta.utility.OperatorStore;
import com.uvt.lfta.utility.StringUtils;

/**
 * Implementation for rewriting an expression in the polish notation.
 * 
 * @author Catalin Florea
 */
public class PolishNotation {

	/** The operator stack. */
	private Stack<Operator> operatorStack;
	
	/** The operator store. */
	private OperatorStore operatorStore;
	
	/**
	 * Public constructor.
	 */
	public PolishNotation(OperatorStore operatorStore) {
		this.operatorStack = new Stack<>();
		this.operatorStore = operatorStore;
	}
	
	/**
	 * Rewrites the given expression in the polish notation.
	 * 
	 * @param expression the expression to rewrite
	 * @return the polish notation for the expression
	 */
	public String execute(String expression) {
		// for building the expression in the polish notation
		StringBuilder stringBuilder = new StringBuilder();
		
		// tokenize the expression
		List<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression, operatorStore);
		
		for (String token : tokenizedExpression) {
			Operator operator = operatorStore.getOperatorBySymbol(token);
			
			// check if the curent token is an operator
			if (operator != null) {
				// if it's an open paranthesis it always goes in the stack
				if (StringUtils.isEqual(operator.getSymbol(), "(")) {
					operatorStack.add(operator);
					continue;
				}
				
				// if it's a closed paranthesis extract all the operators
				// until encountering the open paranthesis
				if (StringUtils.isEqual(operator.getSymbol(), ")")) {
					Operator firstOperator = operatorStack.pop();
					
					while (!StringUtils.isEqual(firstOperator.getSymbol(), "(")) {
						stringBuilder.append(firstOperator.getSymbol());
						firstOperator = operatorStack.pop();
					}
					
					continue;
				}
				
				// while there is a higher priority operator in the stack, remove it
				// and put the operator in the polish notation string
				while (!isHigherPriority(operator)) {
					Operator firstOperator = operatorStack.pop();
					stringBuilder.append(firstOperator.getSymbol());
				}
				
				operatorStack.add(operator);
			} else {
				stringBuilder.append(token);
			}
		}
		
		// empty the operator stack
		while (!operatorStack.isEmpty()) {
			stringBuilder.append(operatorStack.pop().getSymbol());
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Checks the given operator's priority in relation
	 * to the maximum priority from the operator stack.
	 * 
	 * @param operator the operator to check
	 * @return true, if the given operator's priority is higher
	 */
	private boolean isHigherPriority(Operator operator) {
		if (operator.getPriority() > getHighestPriority()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the highest priority from the operator stack.
	 * 
	 * @return the highest priority
	 */
	private int getHighestPriority() {
		if (operatorStack.isEmpty()) {
			return -1;
		}
		
		return operatorStack.peek().getPriority();
	}
	
}
