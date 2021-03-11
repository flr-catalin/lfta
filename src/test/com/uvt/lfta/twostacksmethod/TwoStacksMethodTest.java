package test.com.uvt.lfta.twostacksmethod;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uvt.lfta.twostacksmethod.TwoStacksMethod;
import com.uvt.lfta.utility.CollectionUtils;
import com.uvt.lfta.utility.Operator;
import com.uvt.lfta.utility.OperatorStore;
import com.uvt.lfta.utility.StringUtils;

public class TwoStacksMethodTest {

	private OperatorStore operatorStore;
	
	@Before
	public void before() {
		operatorStore = new OperatorStore();
		operatorStore.addOperator(new Operator("(", 0));
		operatorStore.addOperator(new Operator(")", 0));
		operatorStore.addOperator(new Operator("#", 0));
		operatorStore.addOperator(new Operator("+", 1));
		operatorStore.addOperator(new Operator("-", 1));
		operatorStore.addOperator(new Operator("*", 2));
		operatorStore.addOperator(new Operator("/", 2));
	}
	
	@Test
	public void test_1() {
		List<String> orderOfEvaluation = getOrderOfEvaluation("#m-n*t*x/v+k*r#");
		
		System.out.println("Expression: m-n*t*x/v+k*r");
		
		assertTrue("Was: " + CollectionUtils.size(orderOfEvaluation) + " Expected: " + "6",
				CollectionUtils.isSize(orderOfEvaluation, 6));
		
		assertTrue("Was: " + orderOfEvaluation.get(0) + " Expected: " + "n*t",
				StringUtils.isEqual(orderOfEvaluation.get(0), "n*t"));
		assertTrue("Was: " + orderOfEvaluation.get(1) + " Expected: " + "(n*t)*x",
				StringUtils.isEqual(orderOfEvaluation.get(1), "(n*t)*x"));
		assertTrue("Was: " + orderOfEvaluation.get(2) + " Expected: " + "((n*t)*x)/v",
				StringUtils.isEqual(orderOfEvaluation.get(2), "((n*t)*x)/v"));
		assertTrue("Was: " + orderOfEvaluation.get(3) + " Expected: " + "m-(((n*t)*x)/v)",
				StringUtils.isEqual(orderOfEvaluation.get(3), "m-(((n*t)*x)/v)"));
		assertTrue("Was: " + orderOfEvaluation.get(4) + " Expected: " + "k*r",
				StringUtils.isEqual(orderOfEvaluation.get(4), "k*r"));
		assertTrue("Was: " + orderOfEvaluation.get(5) + " Expected: " + "(m-(((n*t)*x)/v))+(k*r)",
				StringUtils.isEqual(orderOfEvaluation.get(5), "(m-(((n*t)*x)/v))+(k*r)"));
		
		System.out.println("Order of evaluation:");
		orderOfEvaluation.stream().forEach(System.out::println);
		System.out.println();
	}
	
	@Test
	public void test_2() {
		List<String> orderOfEvaluation = getOrderOfEvaluation("#x-y*(z+q*o)/k#");
		
		System.out.println("Expression: x-y*(z+q*o)/k");
		
		assertTrue("Was: " + CollectionUtils.size(orderOfEvaluation) + " Expected: " + "5",
				CollectionUtils.isSize(orderOfEvaluation, 5));
		
		assertTrue("Was: " + orderOfEvaluation.get(0) + " Expected: " + "q*o",
				StringUtils.isEqual(orderOfEvaluation.get(0), "q*o"));
		assertTrue("Was: " + orderOfEvaluation.get(1) + " Expected: " + "z+(q*o)",
				StringUtils.isEqual(orderOfEvaluation.get(1), "z+(q*o)"));
		assertTrue("Was: " + orderOfEvaluation.get(2) + " Expected: " + "y*(z+(q*o))",
				StringUtils.isEqual(orderOfEvaluation.get(2), "y*(z+(q*o))"));
		assertTrue("Was: " + orderOfEvaluation.get(3) + " Expected: " + "(y*(z+(q*o)))/k",
				StringUtils.isEqual(orderOfEvaluation.get(3), "(y*(z+(q*o)))/k"));
		assertTrue("Was: " + orderOfEvaluation.get(4) + " Expected: " + "x-((y*(z+(q*o)))/k)",
				StringUtils.isEqual(orderOfEvaluation.get(4), "x-((y*(z+(q*o)))/k)"));
		
		System.out.println("Order of evaluation:");
		orderOfEvaluation.stream().forEach(System.out::println);
		System.out.println();
	}
	
	private List<String> getOrderOfEvaluation(String expression) {
		TwoStacksMethod twoStacksMethod = new TwoStacksMethod(operatorStore);
		return twoStacksMethod.execute(expression);
	}
	
}
