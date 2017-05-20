package logiceval;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import logiceval.evaluation.ExpressionEvaluator;
import logiceval.parsing.ExpressionParser;
import logiceval.representation.Expression;
import logiceval.representation.Variable;
import logiceval.util.InputReader;
import logiceval.validation.ExpressionValidator;

/**
 * Console mode.
 * Defines the possible options and calls the proper methods.
 */
public class Console {

	private String[] args = null;
	private Options options = new Options();

	public Console(String[] args) {

		this.args = args;

		options.addOption("h", "help", false, "shows help");
		options.addOption("x", "expression", true, "sets the expression");
		options.addOption("v", "validation", false, "validates the expression");
		options.addOption("p", "parsing", false, "validates, parses and formats the expression");
		options.addOption("f", "format", false, "validates, parses and formats the expression");
		options.addOption("e", "evaluation", false, "validates, parses and evaluates the expression");
		options.addOption("t", "type", true, "sets the type of the evaluation");
		options.addOption("d", "data", true, "sets the data for the evaluation; please use this format:  A:0;B:1  where A and B are the names of the variables, and 0 and 1 are false and true");
		options.addOption("ef", "expressionfile", true, "sets the path to the file with the expressions");
		options.addOption("df", "datafile", true, "sets the path to the file with the data for the expressions");
		
	}

	/**
	 * Parses the command line arguments.
	 */
	public void parse() {
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		
		StringBuilder s = new StringBuilder();

		try {

			cmd = parser.parse(options, args);
			
			if (cmd.hasOption("h")) {
				showHelp();
			} else if (cmd.hasOption("x")) {
				String expression = cmd.getOptionValue("x");
				if(cmd.hasOption("v")) {
					boolean result = ExpressionValidator.isValid(expression);
					s.append("The given expression ").append(expression).append("  is ");
					s.append((result ? "valid" : "invalid")).append(".");
					System.out.println(s.toString());
				} else if(cmd.hasOption("p")|| cmd.hasOption("f")) {
					ExpressionParser expressionParser = new ExpressionParser();
					Expression parsedExpression = expressionParser.parse(expression);
					if(parsedExpression != null) {
						s.append("Validated, parsed and formatted expression: ").append(parsedExpression);
						System.out.println(s.toString());
					} else {
						s.append("The given expression is invalid: ").append(expression);
						System.out.println(s.toString());
					}
				} else if(cmd.hasOption("e")) {  
					String type = cmd.getOptionValue("t");
					ExpressionParser expressionParser = new ExpressionParser();
					Expression parsedExpression = expressionParser.parse(expression);
					if(cmd.hasOption("t")) {
						if(parsedExpression != null) {
							if("data".equals(type)) {
								if(cmd.hasOption("d")) {
									Map<Variable, Boolean> parsedData = parseData(cmd.getOptionValue("d"));
									if(parsedData != null) {
										boolean result = ExpressionEvaluator.evaluate(parsedExpression, parsedData);
										s.append("The result of the expression ").append(parsedExpression).append(" is ");
										s.append((result ? "TRUE" : "FALSE")).append(".");
										System.out.println(s.toString());
									} else {
										System.out.println("The given dataset is not formatted well.");
									}
								} else {
									System.out.println("Evaluation type 'with data' set, but no data given.");
								}
							} else if("tautology".equals(type)) {
								boolean result = ExpressionEvaluator.isTautology(parsedExpression);
								s.append("The expression ").append(parsedExpression).append(" is ");
								s.append((result ? "tautology" : "not tautology")).append(".");
								System.out.println(s.toString());
							} else if("contradiction".equals(type)) {
								boolean result = ExpressionEvaluator.isContradiction(parsedExpression);
								s.append("The expression ").append(parsedExpression).append(" is ");
								s.append((result ? "contradiction" : "not contradiction")).append(".");
								System.out.println(s.toString());
							} else if("satisfiable".equals(type)) {
								boolean result = ExpressionEvaluator.isSatisfiable(parsedExpression);
								s.append("The expression ").append(parsedExpression).append("  is ");
								s.append((result ? "satisfiable" : "not satisfiable")).append(".");
								System.out.println(s.toString());
							} else {
								System.out.println("Unknown type of evaluation. Please use help.");
							}
						} else {
							s.append("The given expression is invalid: ").append(expression);
							System.out.println(s.toString());
						}
					} else {
						if(parsedExpression != null) {
							List<Boolean> results = ExpressionEvaluator.evaluate(parsedExpression);
							s.append("The results of the expression ").append(parsedExpression).append(" are ");
							List<HashMap<Variable, Boolean>> data = ExpressionEvaluator.getData(parsedExpression);
							for(int i = 0; i < data.size(); i++ ) {
								System.out.print(data.get(i));
								System.out.print(results.get(i) ? "TRUE" : "FALSE");
							}							
						} else {
							s.append("The given expression is invalid: ").append(expression);
							System.out.println(s.toString());
						}
					}
				} else {
					ExpressionParser expressionParser = new ExpressionParser();
					Expression parsedExpression = expressionParser.parse(expression);
					if(parsedExpression != null) {
						List<Boolean> results = ExpressionEvaluator.evaluate(parsedExpression);
						s.append("The results of the expression ").append(parsedExpression).append(" are ");
						List<HashMap<Variable, Boolean>> data = ExpressionEvaluator.getData(parsedExpression);
						for(int i = 0; i < data.size(); i++ ) {
							System.out.print(data.get(i));
							System.out.print(results.get(i) ? "TRUE" : "FALSE");
						}							
					} else {
						s.append("The given expression is invalid: ").append(expression);
						System.out.println(s.toString());
					}
				}
			} else if (cmd.hasOption("ef") && cmd.hasOption("df")) {
				String ePath = cmd.getOptionValue("ef");
				String dPath = cmd.getOptionValue("df");
				
				try {
					List<Expression> expressions = InputReader.readExpressions(ePath);
					List<HashMap<Variable, Boolean>> variables = InputReader.readVariables(dPath);
					if(expressions.size() == variables.size()) {
						for(int i = 0; i < expressions.size(); i++) {
							boolean result = ExpressionEvaluator.evaluate(expressions.get(i), variables.get(i));
							System.out.println(expressions.get(i) + " with " + variables.get(i) + " : " +result);
						}
					} else {
						System.out.println("The given files are not the same length!");
					}
				} catch (IOException e) {
					System.out.println("No file found!");
				}
			}
			
		} catch (ParseException e) {
			System.out.println("Failed to parse command line properties");
			showHelp();
		}
	}

	/**
	 * Parses the data given in a compressed form.
	 * @param data
	 * @return Map<Variable, Boolean>
	 */
	private Map<Variable, Boolean> parseData(String data) {
		Map<Variable, Boolean> map = new HashMap<Variable, Boolean>();
		String[] variables = data.split(";");
		for(String varval : variables) {
			String[] sides = varval.split(":");
			map.put(new Variable(sides[0]), (sides[1].equals("1") ? true : false));
		}
		return map;
	}

	/**
	 * Shows help in the command line with the available options.
	 */
	private void showHelp() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("Help", options);
	}


}
