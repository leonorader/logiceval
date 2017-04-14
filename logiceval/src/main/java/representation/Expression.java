package representation;

/**
 * Class representing a logical expression
 * 
 * @author leah
 *
 */

public class Expression {
	
	private Expression leftExpression;
	private Expression rightExpression;
	private Operator operator;
	
	/**
	 * Constructor of an expression that has a binary operator between its left and right subexpressions
	 * 
	 * @param Expression leftExpression
	 * @param Operator operator
	 * @param Expression rightExpression
	 */	
	public Expression(Expression leftExpression, Operator operator, Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.operator = operator;
		this.rightExpression = rightExpression;
	}
	
	/**
	 * Constructor of an expression that has a unary operator 
	 * (leftExpression -> null)
	 * 
	 * @param Operator operator
	 * @param Expression rightExpression
	 */
	public Expression(Operator operator, Expression rightExpression) {
		this.leftExpression = null;
		this.operator = operator;
		this.rightExpression = rightExpression;
	}
	
	/**
	 * Constructor of an expression that is only a variable 
	 * (leftExpression -> null)
	 * (rightExpression -> null)
	 * 
	 * @param Variable variable
	 */
	public Expression(Variable variable) {
		this.leftExpression = null;
		this.operator = null;
		this.rightExpression = variable;
	}
	
	/**
	 * To decide if the expression is only a variable.
	 * 
	 * @return boolean 
	 */
	public boolean isVariable() {
		return (leftExpression == null && operator == null);
	}

	@Override
	public String toString() {
		String s = null;
		if(leftExpression == null && operator == null) {
			s =  "( " + rightExpression + " )";
		} else if(leftExpression == null) {
			s =  "( " + operator + " " + rightExpression + " )";			
		} else {
			s =  "( " + leftExpression + " " + operator + " " + rightExpression + " )";
		}
		return s;
	}	
	
	/*public String toStringWithValues() {
		String s = null;
		if(leftExpression != null) {
			s =  "( " + leftExpression.getValue() + " " + mainOperator + " " + rightExpression.getValue() + " )";
		} else {
			s =  "( " + mainOperator + " " + rightExpression.getValue() + " )";
		}
		return s;
	}*/
	
}
