package logiceval;

import representation.Expression;
import representation.Operator;
import representation.Variable;
import validation.ExpressionValidator;
import parsing.ExpressionParser;
import evaluating.ExpressionEvaluator;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Main class for running the application.
 */
public class Main {

	/**
	* Reads logical expressions and datasets from standard input. 
	*/
	private static void readingFromStandardInput() {
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Please give the logical expression!");
			String answer = sc.nextLine();
			Expression expression = new ExpressionParser().parse(answer);
			if(expression!=null){
				ExpressionEvaluator eval = new ExpressionEvaluator(expression);
				LinkedList<String> values = new LinkedList<>();
				for(int i=0;i<eval.getNumberOfVariables();++i) {
					System.out.println("Please give the value of the "+ (i+1) +". variable! (true or 1/false or 0)");
					answer = sc.nextLine();
					while(!answer.toUpperCase().equals("TRUE") && !answer.toUpperCase().equals("FALSE") && !answer.equals("0") && !answer.equals("1")) {
						System.out.println("The answer should be true,false,0 or 1!");
						answer = sc.nextLine();
					}
					values.add(answer);
				}
				System.out.println("\nThe value of the given logical expression: " + eval.evaluate(values) + "\n");
			}else{
				System.out.println("Incorrect given expression!");
				continue;
			}
			System.out.println("Do you want to give another logical expression? (Y/N)");
			answer = sc.nextLine();
			while(!answer.toUpperCase().equals("N") && !answer.toUpperCase().equals("NO") && !answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("YES")){
				   System.out.println("The answer should be Y or N!");
				   answer = sc.nextLine();
			}
			if(answer.toUpperCase().equals("N") || answer.toUpperCase().equals("NO")){
				break;
			}
			
		}
	}

	/**
	* Reads logical expressions and datasets from an input file. 
	*/
	private static void readingFromFile(String file) {
		LinkedList<String> exprs = new LinkedList<>();
		LinkedList<LinkedList<String>> values = new LinkedList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			while(br.ready()){
				exprs.add(br.readLine());
				values.add(new LinkedList<>(Arrays.asList(br.readLine().split(","))));
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found!");
			return;
		}catch(IOException e) {}
		
		for(String e : exprs) {
			System.out.println("Expression: " + e);
			Expression expression = new ExpressionParser().parse(e);
			LinkedList<String> value = values.remove();
			if(expression!=null){
				ExpressionEvaluator eval = new ExpressionEvaluator(expression);
				System.out.println("Dataset: " + value);
				Boolean bool = eval.evaluate(value);
				if(bool != null){
					System.out.println("The evaluation of the logical expression: " + bool + "\n");
				}else{
					System.err.println("The size of given dataset is not equal to the numbers of variables!\n");
				}
			}else{
				System.out.println("Incorrect logical expression!\n");
				continue;
			}
		}
	}
	
	/**
	* Gets logical expression and dataset from parameters. 
	*/
	private static void readingFromParameters(String[] args) {
		LinkedList<String> params = new LinkedList<>(Arrays.asList(args));
		String expr = params.remove();
		System.out.println("Expression: " + expr);
		Expression expression = new ExpressionParser().parse(expr);
		if(expression!=null) {
			ExpressionEvaluator eval = new ExpressionEvaluator(expression);
			System.out.println("Dataset: " + params);
			Boolean bool = eval.evaluate(params);
			if(bool != null){
				System.out.println("The evaluation of the logical expression: " + bool + "\n");
			}else{
				System.err.println("The size of given dataset is not equal to the numbers of variables!\n");
			}
		}else{
			System.out.println("Incorrect logical expression!\n");
		}
	}
	
	/**
	* The main method for reading logical expressions and datasets. The way of reading depends on the number of arguments. 
	*/
	public static void main(String[] args) {
		boolean console = args.length == 0;
		boolean file = args.length == 1;
		boolean parameter = args.length > 1;
		
		if(console){
			readingFromStandardInput();
		}
		if(file){
			readingFromFile(args[0]);
		}
		if(parameter){
			readingFromParameters(args);
		}
	}

}