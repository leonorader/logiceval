package evaluation;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import representation.Expression;
import representation.Operator;
import representation.Variable;

public class EvaluatorTest {
	
	@Test 
	public void evaluateVariable() {
		Variable b = new Variable("B");
		Expression expression = new Expression(b);
		Map<Variable, Boolean> values = new HashMap<Variable, Boolean>();
		values.put(b, true);
		assertTrue(ExpressionEvaluator.evaluate(expression, values));		
	}
	
	
	@Test 
	public void evaluateVerySimpleNotExpression() {
		Variable a = new Variable("A");
		Expression expression = new Expression(Operator.NOT, a);
		Map<Variable, Boolean> values = new HashMap<Variable, Boolean>();
		values.put(a, true);
		assertFalse(ExpressionEvaluator.evaluate(expression, values));		
	}
	
	@Test 
	public void evaluateSimpleNotExpression() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression expression = new Expression(Operator.NOT, e1);
		Map<Variable, Boolean> values = new HashMap<Variable, Boolean>();
		values.put(a, true);
		values.put(b, false);
		assertFalse(ExpressionEvaluator.evaluate(expression, values));		
	}
	
	@Test 
	public void evaluateVerySimpleBinaryExpression() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Map<Variable, Boolean> values = new HashMap<Variable, Boolean>();
		values.put(a, true);
		values.put(b, true);
		for(Operator o : Operator.getBinaryOperators()) {
			Expression expression = new Expression(a, o, b);
			assertTrue(ExpressionEvaluator.evaluate(expression, values));	
		}
	}
	
	@Test 
	public void evaluateLongAndExpression() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Variable c = new Variable("C");
		Map<Variable, Boolean> values = new HashMap<Variable, Boolean>();
		values.put(a, true);
		values.put(b, true);
		values.put(c, true);
		Expression expression = new Expression()
				.addSubExpression(a).addOperator(Operator.AND)
				.addSubExpression(b).addOperator(Operator.AND)
				.addSubExpression(c);
	    assertTrue(ExpressionEvaluator.evaluate(expression, values));	
	}
	
	@Test 
	public void evaluateTautology() {
		Variable b = new Variable("B");
		Expression expression = new Expression(b, Operator.OR, new Expression(Operator.NOT, b));
		assertTrue(ExpressionEvaluator.isTautology(expression));		
	}
	
	@Test 
	public void evaluateMoreComplexTautology() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression expression = new Expression();
		Expression e1 = new Expression(a, Operator.AND, b);
		Expression e2 = new Expression(Operator.NOT, a);
		Expression e3 = new Expression(Operator.NOT, b);
		expression.addSubExpression(e1).addOperator(Operator.OR)
			.addSubExpression(e2).addOperator(Operator.OR).addSubExpression(e3);
		assertTrue(ExpressionEvaluator.isTautology(expression));		
	}
	
	
	@Test 
	public void evaluateContradiction() {
		Variable b = new Variable("B");
		Expression expression = new Expression(b, Operator.AND, new Expression(Operator.NOT, b));
		assertTrue(ExpressionEvaluator.isContradiction(expression));			
	}
			
	@Test 
	public void evaluateMoreComplexContradiction() {
		Variable a = new Variable("A");
		Variable b = new Variable("B");
		Expression expression = new Expression();
		Expression e1 = new Expression(a, Operator.OR, b);
		Expression e2 = new Expression(Operator.NOT, a);
		Expression e3 = new Expression(Operator.NOT, b);
		expression.addSubExpression(e1).addOperator(Operator.AND)
			.addSubExpression(e2).addOperator(Operator.AND).addSubExpression(e3);
		assertTrue(ExpressionEvaluator.isContradiction(expression));		
	}
	
	@Test 
	public void evaluateSatisfiableExpression() {
		Variable b = new Variable("B");
		Expression expression = new Expression(b, Operator.OR, b);
		assertTrue(ExpressionEvaluator.isSatisfiable(expression));			
	}
	
	@Test 
	public void evaluateNotSatisfiableExpression() {
		Variable b = new Variable("B");
		Expression expression = new Expression(b, Operator.AND, new Expression(Operator.NOT, b));
		assertFalse(ExpressionEvaluator.isSatisfiable(expression));		
	}
	
	

}
