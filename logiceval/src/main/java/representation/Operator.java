package representation;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum class for the accepted operators
 *
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
