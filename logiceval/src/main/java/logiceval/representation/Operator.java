package logiceval.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the available logical operators.
 */

public enum Operator {
	
	IMPLIES,
	NOT,
	AND,
	OR;

	
	public static List<Operator> getBinaryOperators() {
		List<Operator> binaries = new ArrayList<Operator>();
		binaries.add(IMPLIES);
		binaries.add(AND);
		binaries.add(OR);
		return binaries;
	}
}
