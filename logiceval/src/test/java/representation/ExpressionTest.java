package representation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest {
	
	@Test
	public void expressionConstructorTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression expression = new Expression(a, Operator.OR, b);
		assertEquals(expression.toString(), "( A OR B )");
    }
	
	@Test
	public void complexConstructorTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression expression = new Expression(Operator.NOT, e1);
		assertEquals(expression.toString(), "( NOT ( A OR B ) )");
    }
	
	@Test
	public void veryComplexExpressionConstructorTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression e2 = new Expression(a, Operator.AND, b);
		Expression expression = new Expression(e1, Operator.IMPLIES, e2);
		assertEquals(expression.toString(), "( ( A OR B ) IMPLIES ( A AND B ) )");
    }

	@Test
	public void expressionIsNotVariableTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression expression = new Expression(a, Operator.OR, b);
		assertFalse(expression.isVariable());
    }
}
