package representation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

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
		assertTrue(variable.consistsOfAVariable());
    }
	
	@Test
	public void getVariablesOfVariableTest() {
		Variable a = new Variable("A");
		Set<Variable> set = a.getVariables();
		Set<Variable> expectedSet = new HashSet<Variable>();
		assertEquals(expectedSet, set);
    }

}
