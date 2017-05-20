package logiceval.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import logiceval.validation.ExpressionValidator;


public class ExpressionValidatorTest {

    @Test
    public void validSymbolTest() {
        assertTrue(ExpressionValidator.isValid("( A | B ) & ( C  > D )"));
    }

    @Test
    public void validSymbolTest2() {
        assertTrue(ExpressionValidator.isValid("( 0 1 !&  asd | < > >>>   ^  A B C a b c not asdawfa )"));
    }

    @Test
    public void invalidSymbolTest() {
        assertFalse(ExpressionValidator.isValid("( 0 1 !&  asd | < >  ^  A B C a b c -.-)"));
    }

    @Test
    public void invalidSymbolTest2() {
        assertFalse(ExpressionValidator.isValid("(A | B) & (@C & D)"));
    }

    @Test
    public void validParenthesesTest() {
        assertTrue(ExpressionValidator.isValid("(A | B) & (C & D)"));
    }

    @Test
    public void validParenthesesTest2() {
        assertTrue(ExpressionValidator.isValid("((A | B)) & ((C & D))"));
    }

    @Test
    public void badParenthesesTest() {
        assertFalse(ExpressionValidator.isValid("(A | B) & (C & D"));
    }

    @Test
    public void badParenthesesTest2() {
        assertFalse(ExpressionValidator.isValid("(A | B)) & (C & D"));
    }

    @Test
    public void badParenthesesTest3() {
        assertFalse(ExpressionValidator.isValid("(A | B) & (C & D))"));
    }

}
