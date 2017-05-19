package evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import representation.Expression;
import representation.Logical;
import representation.Operator;
import representation.Variable;

/**
 * Collection of evaluation methods.
 *
 */
public final class ExpressionEvaluator {

    /**
     * Don't let anyone instantiate this class.
     */
    private ExpressionEvaluator() {}
    
    /**
     * Evaluates an expression with the given values.
     * 
     * @param expression
     * @param values
     * @return boolean
     */
    public static boolean evaluate(Expression expression, Map<Variable, Boolean> values) {
    	boolean result = false;
    	List<Expression> subExpressions = expression.getSubExpressions();
    	List<Operator> operators = expression.getOperators();
    	if(expression.isVariable()) {
    		result = values.get(expression);
    	} else if(expression.consistsOfAVariable() || expression.isVariable()) {
    		result = values.get(subExpressions.get(0));
    	} else if(expression.isSimpleExpression()) {
    		Operator o = operators.get(0); 
    		switch(o) {
				case AND: 		result = Logical.and(evaluate(subExpressions.get(0), values), evaluate(subExpressions.get(1), values)); break;
				case IMPLIES:   result = Logical.implies(evaluate(subExpressions.get(0), values), evaluate(subExpressions.get(1), values)); break;	
				case OR:		result = Logical.or(evaluate(subExpressions.get(0), values), evaluate(subExpressions.get(1), values)); break;
				case NOT: 		result = Logical.not(evaluate(subExpressions.get(0), values)); break;
				default: 		break; 
    		}
    	} else if(operators.stream().allMatch((Operator operator) -> operator == Operator.OR)) {
    		List<Boolean> evaluatedOnes = subExpressions.stream().map((Expression e) -> evaluate(e, values)).collect(Collectors.toList());
    		for(boolean subValue : evaluatedOnes) {
    			result = Logical.or(result, subValue);
    		}
    	} else if(operators.stream().allMatch((Operator operator) -> operator == Operator.AND)) {
    		List<Boolean> evaluatedOnes = subExpressions.stream().map((Expression e) -> evaluate(e, values)).collect(Collectors.toList());
    		result = true;
    		for(boolean subValue : evaluatedOnes) {
    			result = Logical.and(result, subValue);
    		}
    	}
		return result;
    }
    
    /**
     * Decides if the expression is a tautology.
     * 
     * @param expression
     * @return Boolean
     */
    public static Boolean isTautology(Expression expression) {
    	
    	List<Boolean> results = evaluate(expression);
    	Boolean isTautolgy = results.stream().allMatch(Boolean::valueOf);
    	
		return isTautolgy;
    }
    
    /**
     * Decides if the expression is satisfiable.
     * 
     * @param expression
     * @return Boolean
     */
    public static Boolean isSatisfiable(Expression expression) {
    	
    	List<Boolean> results = evaluate(expression);
    	Boolean isSatisfiable = results.stream().anyMatch(Boolean::valueOf);
    	
		return isSatisfiable;
    }
    
    /**
     * Decides if the expression is a contradiction.
     * 
     * @param expression
     * @return Boolean
     */
    public static Boolean isContradiction(Expression expression) {
    	
    	List<Boolean> results = evaluate(expression);
    	Boolean isContradiction = results.stream().noneMatch(Boolean::valueOf);
    	
		return isContradiction;
    }
    
    /**
     * Evaluates an Expression with all the possible ways.
     * Returns a list of the results.
     * 
     * @param expression
     * @return List<Boolean>
     */
    public static List<Boolean> evaluate(Expression expression) {
    	List<Variable> variables = new ArrayList<Variable>(expression.getVariables());
    	List<HashMap<Variable, Boolean>> generatedValues = ValueListGenerator.generateValues(variables);
    	
    	List<Boolean> results = new ArrayList<Boolean>(generatedValues.size());
    	
    	for(Map<Variable, Boolean> values : generatedValues) {
    		Boolean result = evaluate(expression, values);
    		results.add(result);
    	}
		return results;
    }
    
    /**
     * Returns the generated possible test cases for an expression.
     * 
     * @param expression
     * @return List<HashMap<Variable, Boolean>>
     */
    public static List<HashMap<Variable, Boolean>> getData(Expression expression) {
    	List<Variable> variables = new ArrayList<Variable>(expression.getVariables());
    	List<HashMap<Variable, Boolean>> generatedValues = ValueListGenerator.generateValues(variables);
    	return generatedValues;
    }
    
}
