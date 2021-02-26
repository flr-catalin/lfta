package com.uvt.lfta.twostacksmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.uvt.lfta.utility.ExpressionTokenizer;
import com.uvt.lfta.utility.Operator;
import com.uvt.lfta.utility.OperatorStore;
import com.uvt.lfta.utility.StringUtils;

/**
 * Implementation of the two stacks method.
 * 
 * @author Catalin Florea
 */
public class TwoStacksMethod {

	/** The operator stack. */
	private Stack<Operator> operatorStack;
	
	/** The operand stack. */
	private Stack<String> operandStack;
	
	/** The operator store. */
	private OperatorStore operatorStore;
	
	/** The current paranthesis priority modifier. */
	private int paranthesisPriority;
	
	/** The order of evaluation. */
	private List<String> orderOfEvaluation;
	
	/**
	 * Public constructor. 
	 */
	public TwoStacksMethod(OperatorStore operatorStore) {
		this.operatorStack = new Stack<>();
		this.operandStack = new Stack<>();
		this.operatorStore = operatorStore;
		this.paranthesisPriority = 0;
		this.orderOfEvaluation = new ArrayList<>();
	}

	/**
	 * Executes the two stacks method against the given expression.
	 * 
	 * @param expression the expression to run the method against.
	 */
	public List<String> execute(String expression) {
		// tokenize the expression
		List<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression, operatorStore);
		
		for (String token : tokenizedExpression) {
			Operator operator = operatorStore.getOperatorBySymbol(token);
			
			// check if the current token is an operator
			if (operator != null) {
				// if it's an open paranthesis increase the priority modifier by 10 and continue
				if (StringUtils.isEqual(operator.getSymbol(), "(")) {
					paranthesisPriority += 10;
					continue;
				}
				
				// if it's a closed paranthesis decrease the priority modifier by 10 and continue
				if (StringUtils.isEqual(operator.getSymbol(), ")")) {
					paranthesisPriority -= 10;
					continue;
				}
				
				// add the paranthesis priority
				operator.addPriority(paranthesisPriority);
				
				// while there is a higher priority operator in the stack, remove it 
				// and apply the operator to the last two operands
				while (!isHigherPriority(operator) && operandStack.size() > 1) {
					Operator firstOperator = operatorStack.pop();
					String firstOperand = operandStack.pop();
					String secondOperand = operandStack.pop();
					
					String newOperand = getNewOperand(firstOperator, firstOperand, secondOperand);
					orderOfEvaluation.add(newOperand);
					operandStack.add(newOperand);
				}
				
				// finally add the new operator
				operatorStack.add(operator);
			} else {
				// operands always go in the stack
				operandStack.add(token);
			}
		}
		
		return orderOfEvaluation;
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
	
	/**
	 * Creates the new operand that results from applying the operator.
	 * 
	 * @return the new operand
	 */
	private String getNewOperand(Operator firstOperator, String firstOperand, String secondOperand) {
		StringBuilder stringBuilder = new StringBuilder();
		
		appendOperand(stringBuilder, secondOperand);
		stringBuilder.append(firstOperator.getSymbol());
		appendOperand(stringBuilder, firstOperand);
		
		return stringBuilder.toString();
	}
	
	/**
	 * Appends an operand with surrounding paranthesis if it is not atomic.
	 * 
	 * @param stringBuilder the string builder
	 * @param operand the operand
	 */
	private void appendOperand(StringBuilder stringBuilder, String operand) {
		if (isAtomicOperand(operand)) {
			stringBuilder.append(operand);
		} else {
			stringBuilder.append("(").append(operand).append(")");
		}
	}
	
	/**
	 * Checks if the given operand is atomic.
	 * 
	 * @param operand the operand to check
	 * @return true, if the operand is atomic
	 */
	private boolean isAtomicOperand(String operand) {
		return ExpressionTokenizer.tokenize(operand, operatorStore).size() == 1;
	}
	
}
