package representation;

/**
 * Class representing a logical expression
 * 
 * @author leah
 * @contributors 
 *
 */

public class Expression {
	
	private Expression leftExpression;
	private Expression rightExpression;
	private Operator operator;
	
	public Expression(Expression leftExpression, Operator operator, Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.operator = operator;
		this.rightExpression = rightExpression;
	}
	
	public Expression(Operator operator, Expression rightExpression) {
		this.leftExpression = null;
		this.operator = operator;
		this.rightExpression = rightExpression;
	}
	
	public Expression(Variable variable) {
		this.leftExpression = null;
		this.operator = null;
		this.rightExpression = variable;
	}
	
	public boolean isVariable() {
		return (leftExpression == null && operator == null);
	}

	public Expression getLeftExpression(){
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}
	
	public Operator getOperator() {
		return operator;
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
