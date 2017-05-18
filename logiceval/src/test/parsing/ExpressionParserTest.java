package parsing;

import static org.junit.Assert.*;
import representation.Expression;

import org.junit.Test;

/**
 * JUnit test class for {@link ExpressionParser.class}.
 */
public class ExpressionParserTest {

    @Test
    public void validExpressionTest1() {
		String expr = "(dog or cat) and hedgehog";
		Expression expression = new ExpressionParser().parse(expr);
		assertEquals(expression.toString(), "( ( DOG OR CAT ) AND HEDGEHOG )");
    }
	
	@Test
    public void validExpressionTest2() {
		String expr = "( a | b ) & (c  > d )";
		Expression expression = new ExpressionParser().parse(expr);
		assertEquals(expression.toString(), "( ( A OR B ) AND ( C IMPLIES D ) )");
    }
	
	@Test
    public void validExpressionTest3() {
		String expr = "dog and cat or (a or b > (whale and shark)) or not platypus";
		Expression expression = new ExpressionParser().parse(expr);
		assertEquals(expression.toString(), "( ( ( DOG AND CAT ) OR ( ( A OR B ) IMPLIES ( WHALE AND SHARK ) ) ) OR ( NOT PLATYPUS ) )");
    }
	
	@Test
    public void invalidExpressionTest1() {
		String expr = "apple pineapple";
		Expression expression = new ExpressionParser().parse(expr);
		assertNull(expression);
    }
	
	@Test
    public void invalidExpressionTest2() {
		String expr = "one and two or ";
		Expression expression = new ExpressionParser().parse(expr);
		assertNull(expression);
    }

	@Test
    public void invalidExpressionTest3() {
		String expr = "a and b (c or d > (e and f))";
		Expression expression = new ExpressionParser().parse(expr);
		assertNull(expression);
    }
}