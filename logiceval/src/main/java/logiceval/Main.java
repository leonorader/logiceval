package logiceval;

import representation.Expression;
import representation.Operator;
import representation.Variable;

/**
 * Main class for running the application.
 */
public class Main {

	public static void main(String[] args) {
		if(args.length >= 1) {
			String expr = args[0];
			System.out.println(expr);
		}		
	}

}
