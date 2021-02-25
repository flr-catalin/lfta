package com.uvt.lfta.twostacksmethod;

/**
 * Class for storing operator information.
 * 
 * @author Catalin Florea
 */
public class Operator {

	/** The operator's symbol. */
	private String symbol;
	
	/** The operator's priority. */
	private int priority;
	
	/**
	 * Public constructor.
	 */
	public Operator(String symbol, int priority) {
		this.symbol = symbol;
		this.priority = priority;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the amount to add
	 */
	public void addPriority(int priority) {
		this.priority += priority;
	}
	
}
