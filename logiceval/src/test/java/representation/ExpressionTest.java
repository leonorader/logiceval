package representation;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * JUnit test class for {@link Expression.class}.
 */
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
	
	@Test
	public void getVariablesOfComplexExpressionTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression expression = new Expression(Operator.NOT, e1);
		Set<Variable> set = expression.getVariables();
		Set<Variable> expectedSet = new HashSet<Variable>();
		expectedSet.add(a);
		expectedSet.add(b);
		assertEquals(set, expectedSet);
    }
	
	@Test
	public void getVariablesOfVeryComplexExpressionTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression e2 = new Expression(a, Operator.AND, b);
		Expression expression = new Expression(e1, Operator.IMPLIES, e2);
		Set<Variable> set = expression.getVariables();
		Set<Variable> expectedSet = new HashSet<Variable>();
		expectedSet.add(a);
		expectedSet.add(b);
		assertEquals(set, expectedSet);
    }
	
	@Test
	public void longExpressionTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Variable c = new Variable("C");
		Variable d = new Variable("D");
		Variable e = new Variable("E");
		Expression expression = new Expression();
		expression
			.addSubExpression(a)
			.addOperator(Operator.AND)
			.addSubExpression(b)
			.addOperator(Operator.AND)
			.addSubExpression(c)
			.addOperator(Operator.AND)
			.addSubExpression(d)
			.addOperator(Operator.AND)
			.addSubExpression(e);
		assertEquals(expression.toString(), "( A AND B AND C AND D AND E )");
	}
	
	@Test
	public void longAndComplexExpressionTest() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Variable c = new Variable("C");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression e2 = new Expression(a, Operator.OR, c);
		Expression expression = new Expression();
		expression
			.addSubExpression(a)
			.addOperator(Operator.AND)
			.addSubExpression(e1)
			.addOperator(Operator.AND)
			.addSubExpression(e2);
		assertEquals(expression.toString(), "( A AND ( A OR B ) AND ( A OR C ) )");
	}
}
