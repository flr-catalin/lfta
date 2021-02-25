package com.uvt.lfta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperatorStore {

	private List<Operator> operators;

	public OperatorStore() {
		this.operators = new ArrayList<>();
	}
	
	public void addOperator(Operator operator) {
		if (operator == null) {
			throw new IllegalArgumentException("The operator cannot be null.");
		}
		
		operators.add(operator);
	}
	
	public void removeOperator(Operator operator) {
		if (operator == null) {
			throw new IllegalArgumentException("The operator cannot be null.");
		}
		
		operators.remove(operator);
	}
	
	public String getAsString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Operator operator : operators) {
			stringBuilder.append(operator.getSymbol());
		}
		
		return stringBuilder.toString();
	}
	
	public Optional<Operator> getOperatorBySymbol(String symbol) {
		return operators.stream()
				.filter(operator -> StringUtils.isEqual(operator.getSymbol(), symbol))
				.findFirst();
	}
	
}
