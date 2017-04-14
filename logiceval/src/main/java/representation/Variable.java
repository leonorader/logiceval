package representation;

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
		super(null, null, null);
		this.name = name.toUpperCase();
	}

	@Override
	public boolean isVariable() {
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
