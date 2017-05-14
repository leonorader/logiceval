package representation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test class for {@link Variable.class}.
 */
public class VariableTest {
	
	@Test
	public void variableConstructorTest() {
		Variable variable = new Variable("B");
		assertEquals(variable.toString(), "B");
    }
	
	@Test
	public void variableUpperConstructorTest() {
		Variable variable = new Variable("AppLe");
		assertEquals(variable.toString(), "APPLE");
    }
	
	@Test
	public void variableExpressionConstructorTest() {
		Variable b = new Variable("B");
		Expression variable = new Expression(b);
		assertEquals(variable.toString(), "( B )");
    }
	
	@Test
	public void variableIsVariableTest() {
		Variable b = new Variable("B");
		Expression variable = new Expression(b);
		assertTrue(variable.isVariable());
    }

}
