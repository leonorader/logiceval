package parsing;

import representation.*;
import validation.*;
import java.util.Stack;
import java.util.LinkedList;

/**
 * A class for parsing given String expression to fit our Expression model.
 */
public class ExpressionParser {
	
	private LinkedList<String> tokens;
	private Stack<String> stackForTokens;
	private LinkedList<LinkedList<Expression>> listsForExpressions;
	
	/**
	*Constructor to initialize class fields.
	*
	*/
	public ExpressionParser() {
		tokens = new LinkedList<>();
		stackForTokens = new Stack<>();
		listsForExpressions = new LinkedList<>();
		listsForExpressions.add(new LinkedList<>());
	}
	
	/**
	*Checks if the given expression is valid, then tokenizes the expression and parses them to our Expression model.
	*
	*@param expr The String expression that need to be parsed to our Expression model.
	*@return Returns Expression if the given expression is correct, null if it is not correct.
	*/
	public Expression parse(String expr){
		if(!new ExpressionValidator().isValid(expr)){
			return null;
		}
		tokenize(expr);
		Expression expression = parsingTokens();
		clean();
		return expression;
	}
	
	/**
	*Checks the token is a binary operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is a binary operator, false otherwise.
	*/
	private boolean isBinaryOperator(String token) {
		return isAnd(token) || isOr(token) || isImplication(token);
	}
	
	/**
	*Checks the token is an AND operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is an AND operator, false if it is not.
	*/
	private boolean isAnd(String token) {
		return token.equals("&") || token.equals("^") || token.toUpperCase().equals("AND");
	}
	
	/**
	*Checks the token is an OR operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is an OR operator, false if it is not.
	*/
	private boolean isOr(String token) {
		return token.equals("|") || token.equals("v") || token.toUpperCase().equals("OR");
	}
	
	/**
	*Checks the token is an IMPLICATION operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is an IMPLICATION operator, false if it is not.
	*/
	private boolean isImplication(String token) {
		return token.equals(">") || token.toUpperCase().equals("IMPLIES");
	}
	
	/**
	*Checks the token is a unary operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is a unary operator, false otherwise.
	*/
	private boolean isUnaryOperator(String token) {
		return isNot(token);
	}
	
	/**
	*Checks the token is a NOT operator or not.
	*
	*@param token The String token that needs to be checked.
	*@return Return true if the token is a NOT operator, false if it is not.
	*/
	private boolean isNot(String token) {
		return token.equals("!") || token.toUpperCase().equals("NOT");
	}
	
	/**
	*Gets unary operator by the given token.
	*
	*@param token The String that defines a unary operator.
	*@return Returns a unary operator if the given token defines a unary operator, null if it doesn't.
	*/
	private Operator getUnaryOperator(String token) {
		if(isNot(token)){
			return Operator.NOT;
		}
		return null;
	}
	
	/**
	*Gets binary operator by the given token.
	*
	*@param token The String token that defines a binary operator.
	*@return Returns a binary operator if the given token defines a binary operator, null if it doesn't.
	*/
	private Operator getBinaryOperator(String token){
		if(isAnd(token)) {
			return Operator.AND;
		}
		if(isOr(token)) {
			return Operator.OR;
		}
		if(isImplication(token)) {
			return Operator.IMPLIES;
		}
		return null;
	}
	
	/**
	*Creates an Expression by the given parameters.
	*
	*@param token The String token that defines what kind of expression needs to be created.
	*@param list The LinkedList list containing necessary expressions or variables that need to create the expression.
	*/
	private void creatingExpression(String token, LinkedList<Expression> list) {
		if(isBinaryOperator(token)){
			creatingBinaryExpression(token, list);
		}else if(isUnaryOperator(token)){
			creatingUnaryExpression(token, list);
		}else{
			creatingVariable(token, list);
		}
	}
	
	/**
	*Creates a binary Expression by the given parameters.
	*
	*@param operatorToken The String token that defines a binary operator.
	*@param list The LinkedList list containing two expressions or variables that need to create the binary expression.
	*/
	private void creatingBinaryExpression(String operatorToken, LinkedList<Expression> list) {
		Expression rightExpression = list.removeLast();
		Expression leftExpression = list.removeLast();
		Operator operator = getBinaryOperator(operatorToken);
		list.add(new Expression(leftExpression, operator, rightExpression));
		stackForTokens.pop();
	}
	
	/**
	*Creates a unary Expression by the given parameters.
	*
	*@param operatorToken The String token that defines a unary operator.
	*@param list The LinkedList list containing one expressions or variables that needs to create the unary expression.
	*/
	private void creatingUnaryExpression(String operatorToken, LinkedList<Expression> list) {
		Expression rightExpression = list.removeLast();
		Operator operator = getUnaryOperator(operatorToken);
		list.add(new Expression(operator, rightExpression));
		stackForTokens.pop();
	}
	
	/**
	*Creates a variable by the given parameters.
	*
	*@param token The String token that defines a true or false variable.
	*@param list The LinkedList list to adding the variable.
	*/
	private void creatingVariable(String token, LinkedList<Expression> list) {
		list.add(new Variable(token));
	}
	
	/**
	*Checks the given expression and parses tokens to our Expression model.
	*
	*@return Return true if the expression is valid, false if it is invalid.
	*/
	private Expression parsingTokens() {
		String token = "";
		String topToken = "";
		LinkedList<Expression> lastList = listsForExpressions.getLast();
		while(tokens.size()!=0){
			token = tokens.remove();
			if(token.equals("(")){
				if(lastList.size() != 0 && stackForTokens.empty()){
					return null;
				}
				listsForExpressions.add(new LinkedList<>());
				lastList = listsForExpressions.getLast();
				stackForTokens.push(token);
				topToken = stackForTokens.peek();
				continue;
			}else if(token.equals(")")){
				if(lastList.size()!=1 || !stackForTokens.peek().equals("(")) {
					return null;
				}
				Expression expr = lastList.remove();
				listsForExpressions.removeLast();
				lastList = listsForExpressions.getLast();
				lastList.add(expr);
				stackForTokens.pop();
				if(stackForTokens.empty()) {
					continue;
				}
				topToken = stackForTokens.peek();
			}else if(isBinaryOperator(token)) {
				if(lastList.size()<1 || tokens.size()==0){
					return null;
				}
				stackForTokens.push(token);
				continue;
			}else if(isUnaryOperator(token)) {
				if(tokens.size()==0){
					return null;
				}
				stackForTokens.push(token);
			}else{
				creatingExpression(token, lastList);
				if(stackForTokens.empty()) {
					continue;
				}
				topToken = stackForTokens.peek();
			}
			if(!topToken.equals("") && isBinaryOperator(topToken) && !isUnaryOperator(token)) {
				creatingExpression(topToken, lastList);
				continue;
			}
			if(!topToken.equals("") && isUnaryOperator(topToken)) {
				if(lastList.size() < 1) {
					return null;
				}
				creatingExpression(topToken, lastList);
			}
		}
		while(lastList.size()!=1){
			if(stackForTokens.empty()) {
				return null;
			}
			token = stackForTokens.peek();
			creatingExpression(token, lastList);
			
		}
		return lastList.remove();
	}
	
	/**
	*Add token to LinkedList tokens if the token is not empty.
	*
	*@param token The Token that need to be checked.
	*@return Return true if the string is not empty, false if it is.
	*/
	private boolean addTokenIfNotEmpty(String token) {
		if(!token.equals("")){
			tokens.add(token);
			return true;
		}
		return false;
	}
	
	/**
	*Tokenizes the given String expression.
	*
	*@param expr The String expression that need to be tokenized.
	*/
	private void tokenize(String expr) {
		String token = "";
		for (char c : expr.toCharArray()){
			if(c == ' '){
				if(addTokenIfNotEmpty(token)){
					token = "";
				}
			}else if(c == '(' || c == ')'){
				if(addTokenIfNotEmpty(token)){
					token = "";
				}
				tokens.add(Character.toString(c));
			}else{
				token = token + c;
			}
		}
		addTokenIfNotEmpty(token);
	}
	
	/**
	*Reinitalizes each fields except the expression class fied.
	*/
	private void clean() {
		tokens = new LinkedList<>();
		stackForTokens = new Stack<>();
		listsForExpressions = new LinkedList<>();
		listsForExpressions.add(new LinkedList<>());
	}
	
}
