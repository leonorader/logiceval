package representation;

public class Variable extends Expression{
	
	private String name;
	
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
