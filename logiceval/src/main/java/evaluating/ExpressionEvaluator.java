package evaluating;

import representation.*;
import parsing.*;
import java.util.LinkedList;

/**
 * A class for evaluating logical expressions.
 */
public class ExpressionEvaluator {
	
	private Expression expr;
	private int numbersOfVariables = 0; 
	
	/**
	*Constructor to initialize class fields.
	*
	*/
	public ExpressionEvaluator(Expression expr) {
		this.expr = expr;
		countingVariables(expr);
	}
	
	/**
	*Counts the variables in the given expression.
	*
	*@param expr The Expression that contains the variables that need to be counted.
	*/
	private void countingVariables(Expression expr){
		if(expr.isVariable()){
			++numbersOfVariables;
			return;
		}
		if(expr.getLeftExpression()!=null){
			countingVariables(expr.getLeftExpression());
		}
		countingVariables(expr.getRightExpression());
	}
	
	/**
	*Gets the number of variables in the given expression.
	*
	*@return Returns the number of variables.
	*/
	public int getNumberOfVariables() {
		return numbersOfVariables;
	}
	
	/**
	*Evaluates the logical expression by the given dataset.
	*
	*@param dataset LinkedList that contains the dataset.
	*@return Returns null if the size of dataset is not equal to the number of variables or returns true/false depending on the evaluation of the logical expression.
	*/
	public Boolean evaluate(LinkedList<String> dataset) {
		if(dataset.size() != numbersOfVariables) {
			return null;
		}
		return evaluating(expr, dataset);
	}
	
	/**
	*Private method for evaluating.
	*
	*@param expr The expression that needs to be evaluated.
	*@param dataset The List that contains the dataset.
	*@return Returns true or false depending on the evaluation of the logical expression.
	*/
	private boolean evaluating(Expression expr, LinkedList<String> dataset) {
		if(expr.isVariable()){
			return getValueOfVariable(dataset.remove());
		}
		if(expr.getLeftExpression()!=null){
			Operator operator = expr.getOperator();
			if(operator==Operator.AND)
				return Logical.and(evaluating(expr.getLeftExpression(), dataset), evaluating(expr.getRightExpression(), dataset));
			if(operator==Operator.OR)
				return Logical.or(evaluating(expr.getLeftExpression(), dataset), evaluating(expr.getRightExpression(), dataset));
			return Logical.implies(evaluating(expr.getLeftExpression(), dataset), evaluating(expr.getRightExpression(), dataset));
		}
		return Logical.not(evaluating(expr.getRightExpression(), dataset));
	}
	
	/**
	*Gets value of an element from the dataset.
	*
	*@param bool The string element from the dataset.
	*@return Returns true if it is a true boolean, or returns false if it is not.
	*/
	private boolean getValueOfVariable(String bool) {
		return bool.equals("1") || bool.toUpperCase().equals("TRUE");
	}
	
}