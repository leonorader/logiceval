package evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import representation.Variable;

public final class ValueListGenerator {

    /**
     * Don't let anyone instantiate this class.
     */
    private ValueListGenerator() {}

    /**
     * Generates all the possible test case for an expression.
     * 
     * @param variables
     * @return List<HashMap<Variable, Boolean>>
     */
	public static List<HashMap<Variable, Boolean>> generateValues(List<Variable> variables) {
		List<HashMap<Variable, Boolean>> generatedValues = new ArrayList<HashMap<Variable, Boolean>>();
		List<Values> valuesSet = generateValueList(variables.size());
		
		HashMap<Variable, Boolean> mapOfValues;
		
		for(Values values : valuesSet) {
			mapOfValues = new HashMap<Variable, Boolean>();
			for(int i = 0; i < variables.size(); i++) {
				mapOfValues.put(variables.get(i), values.getValues().get(i));
			}
			generatedValues.add(mapOfValues);
		}
		return generatedValues;
	}
	
	/**
	 * This is the low level generator for a given size.
	 * 
	 * @param size
	 * @return List<Values>
	 */
    public static List<Values> generateValueList(int size) {
    	
    	List<Values> valuesSet = new ArrayList<Values>();   
    	
        for (int i = 0; i < Math.pow(2, size); i++) {
            String binaryString = Integer.toBinaryString(i);
            while (binaryString.length() < size)
                binaryString = "0" + binaryString;
            char[] characters = binaryString.toCharArray();
            Values values = new Values(size);
            for (int j = 0; j < characters.length; j++) {
            	values.add(characters[j] == '0' ? true : false);
            }
            valuesSet.add(values);            
        }
		return valuesSet;
    }
    
}