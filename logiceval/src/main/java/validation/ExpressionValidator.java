package validation;


/**
 * Created by Grekton on 2017.04.26..
 */
public class ExpressionValidator {

    boolean isValid(String expr) {

        //check for valid symbols
        if(!expr.matches("[a-zA-Z0-1()^&!<>|\\s+-]+")) {
            return false;
        }

        //check for balanced parentheses
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
