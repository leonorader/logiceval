package validation;

/**
 * Class for validating expressions.
 */
public class ExpressionValidator {

    /**
     * Calls the symbolCheck and parenthesesCheck methods to check if the expression is valid.
     *
     * @param expr The expression that needs to be checked.
     * @return Returns true if the expression is valid, false if the expression is invalid.
     */
    public boolean isValid(String expr) {
        return symbolCheck(expr) && parenthesesCheck(expr);
    }

    /**
     * Checks for valid symbols.
     *
     * @param expr The expression that needs to be checked.
     * @return True if all the symbols in the expression are valid, false if there are invalid symbols.
     */
    private boolean symbolCheck(String expr) {
        return expr.matches("[a-zA-Z0-1()^&!<>|\\s+-]+");
    }

    /**
     * Checks for balanced parentheses.
     *
     * @param expr The expression that needs to be checked.
     * @return True if the number of opening and closing brackets is the same, and false otherwise.
     */
    private boolean parenthesesCheck(String expr) {
        int open = 0;
        for (int i = 0; i < expr.length(); ++i) {
            char c = expr.charAt(i);
            switch (c) {
                case '(':
                    open++;
                    break;
                case ')':
                    open--;
                    if (open < 0) {
                        return false;
                    }
                    break;
            }
        }

        return open == 0;
    }

}
