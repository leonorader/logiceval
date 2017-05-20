package logiceval.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logiceval.parsing.ExpressionParser;
import logiceval.representation.Expression;
import logiceval.representation.Variable;

/**
 * Util class to help with the file reading.
 */
public class InputReader {

	private InputReader() {};

	/**
	 * Reads a file containing an expression every line.
	 * @param path string path to the file
	 * @return a list of expressions
	 * @throws IOException when the path is not valid
	 */
	public static List<Expression> readExpressions(String path) throws IOException {
		List<Expression> expressions = new ArrayList<Expression>();

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String expression;
		int counter = 0;
		while((expression = reader.readLine()) != null) {
			counter++;
			ExpressionParser expressionParser = new ExpressionParser();
			Expression parsedExpression = expressionParser.parse(expression);
			if(parsedExpression != null) {
				expressions.add(parsedExpression);
			} else {
				throw (new IOException("Invalid expression at line " + counter));
			}

		}
		reader.close();
		return expressions;		
	}

	/**
	 * Reads a file containing variable names and its values in compressed form every line.
	 * @param path string path to the file
	 * @return a list of expressions
	 * @throws IOException when the path is not valid
	 */
	public static List<HashMap<Variable, Boolean>> readVariables(String path) throws IOException {
		List<HashMap<Variable, Boolean>> data = new ArrayList<HashMap<Variable, Boolean>>();

		BufferedReader reader = new BufferedReader(new FileReader(path));
		HashMap<Variable, Boolean> parsedData;
		String dataStr;
		while((dataStr = reader.readLine()) != null) {
			parsedData = parseData(dataStr);
			data.add(parsedData);
		}
		reader.close();
		return data;		
	}

	/**
	 * Parses the data given in a compressed form.
	 * @param data compressed string to parse containing variable values
	 * @return HashMap<Variable, Boolean> parsed map of variables and values
	 */
	private static HashMap<Variable, Boolean> parseData(String data) {
		HashMap<Variable, Boolean> map = new HashMap<Variable, Boolean>();
		String[] variables = data.split(";");
		for(String varval : variables) {
			String[] sides = varval.split(":");
			map.put(new Variable(sides[0]), (sides[1].equals("1") ? true : false));
		}
		return map;
	}

}
