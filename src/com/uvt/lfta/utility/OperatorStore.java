package com.uvt.lfta.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class used for storing the defined operators.
 * 
 * @author Catalin Florea
 */
public class OperatorStore {

	/** The list of operators. */
	private List<Operator> operators;

	/**
	 * Public constructor.
	 */
	public OperatorStore() {
		this.operators = new ArrayList<>();
	}
	
	/**
	 * @param operator the operator to add
	 */
	public void addOperator(Operator operator) {
		if (operator == null) {
			throw new IllegalArgumentException("The operator cannot be null.");
		}
		
		operators.add(operator);
	}
	
	/**
	 * @param operator the operator to remove
	 */
	public void removeOperator(Operator operator) {
		if (operator == null) {
			throw new IllegalArgumentException("The operator cannot be null.");
		}
		
		operators.remove(operator);
	}
	
	/**
	 * @return all operators concatenated into a single string
	 */
	public String getAsString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Operator operator : operators) {
			stringBuilder.append(operator.getSymbol());
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Gets the Operator object by the symbol priority.
	 * 
	 * @param symbol the symbol
	 * @return the operator object
	 */
	public Operator getOperatorBySymbol(String symbol) {
		Optional<Operator> optionalOperator = operators.stream()
				.filter(operator -> StringUtils.isEqual(operator.getSymbol(), symbol))
				.findFirst();
		
		if (optionalOperator.isPresent()) {
			Operator operator = optionalOperator.get();
			return new Operator(operator.getSymbol(), operator.getPriority());
		}
		
		return null;
	}
	
}
