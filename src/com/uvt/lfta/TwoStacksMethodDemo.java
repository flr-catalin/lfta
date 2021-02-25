package com.uvt.lfta;

public class TwoStacksMethodDemo {

	public static void main(String[] args) {
		OperatorStore operatorStore = new OperatorStore();
		operatorStore.addOperator(new Operator("#", 0));
		operatorStore.addOperator(new Operator("+", 1));
		operatorStore.addOperator(new Operator("-", 1));
		operatorStore.addOperator(new Operator("*", 2));
		operatorStore.addOperator(new Operator("/", 2));
		
		TwoStacksMethod twoStacksMethod = new TwoStacksMethod(operatorStore);
		twoStacksMethod.execute("#m-n*t*x/v+k*r#");
	}
	
}
