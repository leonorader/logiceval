package representation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
<<<<<<< HEAD
 * Class representing a logical expression
 *
=======
 * Class representing a logical expression.
>>>>>>> origin/master
 */
public class Expression {
	
	protected List<Expression> subExpressions;
	protected List<Operator> operators;
	
	/**
	 * Constructor of an expression, that will have more than 2 subexpressions.
	 */
	public Expression() {
		subExpressions = new ArrayList<Expression>();
		operators = new ArrayList<Operator>();
	}
	
	/**
	 * Constructor of an expression that has a binary operator between its left and right subexpressions
	 * 
	 * @param Expression leftExpression
	 * @param Operator operator
	 * @param Expression rightExpression
	 */	
	public Expression(Expression leftExpression, Operator operator, Expression rightExpression) {
		subExpressions = new ArrayList<Expression>(2);
		subExpressions.add(leftExpression);
		subExpressions.add(rightExpression);
		operators = new ArrayList<Operator>(1);
		operators.add(operator);
	}
	
	/**
	 * Constructor of an expression that has a unary operator 
	 * 
	 * @param Operator operator
	 * @param Expression rightExpression
	 */
	public Expression(Operator operator, Expression rightExpression) {
		subExpressions = new ArrayList<Expression>(1);
		subExpressions.add(rightExpression);
		operators = new ArrayList<Operator>(1);
		operators.add(operator);
	}
	
	/**
	 * Constructor of an expression that is only a variable 
	 * 
	 * @param Variable variable
	 */
	public Expression(Variable variable) {
		subExpressions = new ArrayList<Expression>(1);
		subExpressions.add(variable);
		operators = null;
	}
	
	/**
	 * Constructor helper method. This way you can add more subexpressions.
	 * Use only with the no-parameter constructor.
	 * 
	 * @param Expression subExpression
	 * @return Expression this
	 */
	public Expression addSubExpression(Expression subExpression) {
		subExpressions.add(subExpression);
		return this;
	}
	
	/**
	 * Constructor helper method. This way you can add more operators.
	 * Use only with the no-parameter constructor.
	 * 
	 * @param Expression operator
	 * @return Expression this
	 */
	public Expression addOperator(Operator operator) {
		operators.add(operator);
		return this;
	}
	
	/**
	 * To decide if the expression consists only of a variable.
	 * 
	 * @return boolean 
	 */
	public boolean consistsOfAVariable() {
		return (operators == null && subExpressions.size() == 1 && subExpressions.get(0).isVariable());
	}
	
	/**
	 * Tells if an expression has only one operator and at a maximum 2 subexpressions.
	 * @return
	 */
	public boolean isSimpleExpression() {
		return operators.size() == 1 && subExpressions.size() > 0 && subExpressions.size() < 3;
	}
	
	/**
	 * An expression is never a variable.
	 * 
	 * @return boolean 
	 */
	public boolean isVariable() {
		return false;
	}

	/**
	 * Collects all the variables of the expression.
	 * 
	 * @return Set<Variables>
	 */
	public Set<Variable> getVariables() {
		Set<Variable> variables = new HashSet<Variable>();
		for (Expression expression : subExpressions)  {
			if(expression.isVariable()) {
				variables.add((Variable) expression);
			} else {
				variables.addAll(expression.getVariables());
			}
		}
		return variables;
	}	
	
	
	/**
	 * Returns the subexpressions of the expression.
	 * 
	 * @return List<Expression>
	 */
	public List<Expression> getSubExpressions() {
		return subExpressions;
	}
	
	/**
	 * Returns the operators of the expression.
	 * 
	 * @return List<Operator>
	 */
	public List<Operator> getOperators() {
		return operators;
	}

	/**
	 * Returns a string representation of the Expression.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("( ");
		if(consistsOfAVariable()) {
			s.append(subExpressions.get(0));
			s.append(" ");
		} else if(subExpressions.size() == 1 && operators.size() == 1) {
			s.append(operators.get(0));
			s.append(" ");
			s.append(subExpressions.get(0));
			s.append(" ");
		} else {
			s.append(subExpressions.get(0));
			s.append(" ");
			for(int i = 1; i < subExpressions.size(); i++) {
				s.append(operators.get(i - 1));
				s.append(" ");
				s.append(subExpressions.get(i));
				s.append(" ");
			}
		}		
		s.append(")");
		return s.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operators == null) ? 0 : operators.hashCode());
		result = prime * result + ((subExpressions == null) ? 0 : subExpressions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expression other = (Expression) obj;
		if (operators == null) {
			if (other.operators != null)
				return false;
		} else if (!operators.equals(other.operators))
			return false;
		if (subExpressions == null) {
			if (other.subExpressions != null)
				return false;
		} else if (!subExpressions.equals(other.subExpressions))
			return false;
		return true;
	}
	
	
	
}
