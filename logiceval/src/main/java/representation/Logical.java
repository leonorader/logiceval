package representation;

/**
 * A class containing the methods for logical operators.
 */
public class Logical {

    //private constructor to prevent instantiation of class
    private Logical() {}

    /**
     * Method for the <i>negation</i> logical operator.
     *
     * @return false if x is true and true if x is false.
     */
    public static boolean not(boolean x) {
        return !x;
    }

    /**
     * Method for the <i>and</i> logical operator.
     *
     * @return true if all of the arguments are true and false otherwise.
     */
    public static boolean and(boolean... args) {
        for (boolean x : args) {
            if (!x) return false;
        }
        return true;
    }

    /**
     * Method for the <i>or</i> logical operator.
     *
     * @return false if all of the arguments are false and true otherwise.
     */
    public static boolean or(boolean... args) {
        for (boolean x : args) {
            if(x) return true;
        }
        return false;
    }
    
    /**
     * Method for the <i>and</i> logical operator.
     *
     * @return true if both of the arguments are true and false otherwise.
     */
    public static boolean and(boolean x, boolean y) {
    	return x && y;
    }

    /**
     * Method for the <i>or</i> logical operator.
     *
     * @return false if both of the arguments are false and true otherwise.
     */
    public static boolean or(boolean x, boolean y) {
    	return x || y;
    }

    /**
     * Method for the <i>implies</i> logical operator.
     *
     * @return false if x is true and y is false, and true otherwise.
     */
    public static boolean implies(boolean x, boolean y) {
        return !(x && !y);
    }

}
