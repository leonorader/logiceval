package util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import representation.Expression;
import representation.Operator;
import representation.Variable;

public class InputReaderTest {

	@Test
	public void readExpressionsTest() {
		List<Expression> expectedExpressions = new ArrayList<Expression>();
		expectedExpressions.add(new Expression(new Variable("A"), Operator.OR, new Variable("B")));
		expectedExpressions.add(new Expression(Operator.NOT, new Variable("C")));
		expectedExpressions.add(new Expression(Operator.NOT, new Expression(new Variable("dog"), Operator.AND, new Variable("hedgehog"))));
		try {
			List<Expression> expressions = InputReader.readExpressions("src/test/resources/e.in");
			assertTrue(expectedExpressions.equals(expressions));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readEmptyExpressionsTest() {
		List<Expression> expectedExpressions = new ArrayList<Expression>();
		try {
			List<Expression> expressions = InputReader.readExpressions("src/test/resources/ee.in");
			assertTrue(expectedExpressions.equals(expressions));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readVariablesTest() {
		List<HashMap<Variable, Boolean>> expectedData = new ArrayList<HashMap<Variable, Boolean>>();
		HashMap<Variable, Boolean> d1 = new HashMap<Variable, Boolean>();
		d1.put(new Variable("A"), true);
		d1.put(new Variable("B"), false);
		expectedData.add(d1);
		HashMap<Variable, Boolean> d2 = new HashMap<Variable, Boolean>();
		d2.put(new Variable("c"), true);
		expectedData.add(d2);
		HashMap<Variable, Boolean> d3 = new HashMap<Variable, Boolean>();
		d3.put(new Variable("dog"), false);
		d3.put(new Variable("hedgehog"), false);
		expectedData.add(d3);
		try {
			List<HashMap<Variable, Boolean>> data  = InputReader.readVariables("src/test/resources/f.in");
			assertTrue(expectedData.equals(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readEmptyVariablesTest() {
		List<HashMap<Variable, Boolean>> expectedData = new ArrayList<HashMap<Variable, Boolean>>();
		try {
			List<HashMap<Variable, Boolean>> data  = InputReader.readVariables("src/test/resources/fe.in");
			assertTrue(expectedData.equals(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
