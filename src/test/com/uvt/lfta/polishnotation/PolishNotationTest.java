package test.com.uvt.lfta.polishnotation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.uvt.lfta.polishnotation.PolishNotation;
import com.uvt.lfta.utility.Operator;
import com.uvt.lfta.utility.OperatorStore;
import com.uvt.lfta.utility.StringUtils;

public class PolishNotationTest {

	private OperatorStore operatorStore;
	
	@Before
	public void before() {
		operatorStore = new OperatorStore();
		operatorStore.addOperator(new Operator("(", 0));
		operatorStore.addOperator(new Operator(")", 0));
		operatorStore.addOperator(new Operator("+", 1));
		operatorStore.addOperator(new Operator("-", 1));
		operatorStore.addOperator(new Operator("*", 2));
		operatorStore.addOperator(new Operator("/", 2));
	}
	
	@Test
	public void test_1() {
		String polishNotation = getPolishNotation("a+b");
		
		assertTrue("Was: " + polishNotation + " Expected: " + "ab+",
				StringUtils.isEqual(polishNotation, "ab+"));
	}
	
	@Test
	public void test_2() {
		String polishNotation = getPolishNotation("x*(y-z)");
		
		assertTrue("Was: " + polishNotation + " Expected: " + "xyz-*",
				StringUtils.isEqual(polishNotation, "xyz-*"));
	}
	
	@Test
	public void test_3() {
		String polishNotation = getPolishNotation("x*y/z-k/(b-c)*s");
		
		assertTrue("Was: " + polishNotation + " Expected: " + "xy*z/kbc-/s*-",
				StringUtils.isEqual(polishNotation, "xy*z/kbc-/s*-"));
	}
	
	private String getPolishNotation(String expression) {
		PolishNotation polishNotation = new PolishNotation(operatorStore);
		return polishNotation.execute(expression);
	}
	
}
