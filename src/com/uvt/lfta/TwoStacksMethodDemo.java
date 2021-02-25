package com.uvt.lfta;

/**
 * Demo class for the two stacks method.
 * 
 * @author Catalin Florea
 */
public class TwoStacksMethodDemo {

	public static void main(String[] args) {
		OperatorStore operatorStore = new OperatorStore();
		operatorStore.addOperator(new Operator("(", 0));
		operatorStore.addOperator(new Operator(")", 0));
		operatorStore.addOperator(new Operator("#", 0));
		operatorStore.addOperator(new Operator("+", 1));
		operatorStore.addOperator(new Operator("-", 1));
		operatorStore.addOperator(new Operator("*", 2));
		operatorStore.addOperator(new Operator("/", 2));
		
		TwoStacksMethod twoStacksMethod = new TwoStacksMethod(operatorStore);
		twoStacksMethod.execute("#x-y*(z+q*o)/k#");
	}
	
}
