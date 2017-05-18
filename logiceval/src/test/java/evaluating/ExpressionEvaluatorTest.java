package evaluating;

import static org.junit.Assert.*;
import java.util.*;
import representation.Expression;
import parsing.ExpressionParser;

import org.junit.Test;

/**
 * JUnit test class for {@link ExpressionEvaluator.class}.
 */
public class ExpressionEvaluatorTest {

    @Test
    public void validEvaluatingTest1() {
		String expr = "A & B";
		LinkedList<String> dataset = new LinkedList<>(Arrays.asList(new String[]{"true","false"}));
		Expression expression = new ExpressionParser().parse(expr);
		assertFalse(new ExpressionEvaluator(expression).evaluate(dataset));
    }
	
	@Test
    public void validEvaluatingTest2() {
		String expr = "A & B";
		LinkedList<String> dataset = new LinkedList<>(Arrays.asList(new String[]{"1","1"}));
		Expression expression = new ExpressionParser().parse(expr);
		assertTrue(new ExpressionEvaluator(expression).evaluate(dataset));
    }
	
	@Test
    public void validEvaluatingTest3() {
		String expr = "(appla & pineapple) | (strawberry > blueberry)";
		LinkedList<String> dataset = new LinkedList<>(Arrays.asList(new String[]{"true","1","0","true"}));
		Expression expression = new ExpressionParser().parse(expr);
		assertTrue(new ExpressionEvaluator(expression).evaluate(dataset));
    }
	
	@Test
    public void invalidEvaluatingTest1() {
		String expr = "A & B";
		LinkedList<String> dataset = new LinkedList<>(Arrays.asList(new String[]{"true"}));
		Expression expression = new ExpressionParser().parse(expr);
		assertNull(new ExpressionEvaluator(expression).evaluate(dataset));
    }
	
	@Test
    public void invalidEvaluatingTest2() {
		String expr = "A & B or C";
		LinkedList<String> dataset = new LinkedList<>(Arrays.asList(new String[]{"true","0","false","1"}));
		Expression expression = new ExpressionParser().parse(expr);
		assertNull(new ExpressionEvaluator(expression).evaluate(dataset));
    }
	
	@Test
    public void numberOfVariablesTest1() {
		String expr = "A & B";
		Expression expression = new ExpressionParser().parse(expr);
		assertEquals(2, new ExpressionEvaluator(expression).getNumberOfVariables(), 0);
    }
	
	@Test
    public void numberOfVariablesTest2() {
		String expr = "dog and cat or (a or b > (whale and shark)) or not platypus";
		Expression expression = new ExpressionParser().parse(expr);
		assertEquals(7, new ExpressionEvaluator(expression).getNumberOfVariables(), 0);
    }
}