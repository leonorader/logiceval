package evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValueListGeneratorTest {

	@Test
	public void generateSmallValueListTest() {
	    	
		List<Values> expectedValues = new ArrayList<Values>();
		Values v1 = new Values(2);
		v1.add(true);
		v1.add(true);
		expectedValues.add(v1);
		
		Values v2 = new Values(2);
		v2.add(true);
		v2.add(false);
		expectedValues.add(v2);
		
		Values v3 = new Values(2);
		v3.add(false);
		v3.add(true);
		expectedValues.add(v3);
		
		Values v4 = new Values(2);
		v4.add(false);
		v4.add(false);
		expectedValues.add(v4);
	
		List<Values> values = ValueListGenerator.generateValueList(2);
		
	    assertEquals(expectedValues.toString(), values.toString());
	    
    }

}
