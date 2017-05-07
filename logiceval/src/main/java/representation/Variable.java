package representation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a simple variable
 * extends Expression
 * 
 * @author leah
 *
 */

public class Variable extends Expression {
	
	private String name;
	
	/**
	 * Constructor of a variable. 
	 * (Name becomes upper case)
	 * 
	 * @param String name
	 */
	public Variable(String name) {
		this.name = name.toUpperCase();
	}

	@Override
	public boolean isVariable() {
		return true;
	}
	
	@Override
	public Set<Variable> getVariables() {
		return new HashSet<Variable>();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
