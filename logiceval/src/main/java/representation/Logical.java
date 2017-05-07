package representation;

/**
 * A class containing the methods for logical operators.
 * <p>
 * Created by Grekton on 2017.05.07..
 */
public class Logical {

    /**
     * Method for the <i>negation</i> logical operator.
     *
     * @return false if x is true and true if x is false.
     */
    public boolean not(boolean x) {
        return !x;
    }

    /**
     * Method for the <i>and</i> logical operator.
     *
     * @return true if x and y are true and false otherwise.
     */
    public boolean and(boolean x, boolean y) {
        if (x && y) return true;
        return false;
    }

    /**
     * Method for the <i>or</i> logical operator.
     *
     * @return true if x and y are false and false otherwise.
     */
    public boolean or(boolean x, boolean y) {
        if (!x && !y) return false;
        return true;
    }

    /**
     * Method for the <i>implies</i> logical operator.
     *
     * @return false if x is true and y is false, and true otherwise.
     */
    public boolean implies(boolean x, boolean y) {
        if (x && !y) return false;
        return true;
    }

}
