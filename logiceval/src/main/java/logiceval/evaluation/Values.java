package logiceval.evaluation;

import java.util.ArrayList;
import java.util.List;


/**
 * Container for the generated values.
 */
public class Values {
	
	private List<Boolean> values;
	private int capacity;
	private int size = 0;
	
	public Values(int capacity) {
		this.capacity = capacity;
		values = new ArrayList<Boolean>(capacity);
	}
	
	/**
	 * Adds a boolean value to the container.
	 * @param value boolean value to be added to the container
	 */
	public void add(Boolean value) {
		if(size < capacity) {
			values.add(value);
			size++;
		}		
	}
	
	/**
	 * Return the collected boolean values.
	 * @return a list of Boolean values that are in the container
	 */
	public List<Boolean> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return values.toString();
	}

}
