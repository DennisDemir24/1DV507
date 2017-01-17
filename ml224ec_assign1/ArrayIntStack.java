package ml224ec_assign1;

import ml224ec_assign1.int_collection.AbstractIntCollection;
import ml224ec_assign1.int_collection.IntStack;

public class ArrayIntStack extends AbstractIntCollection implements IntStack {

	// int size
	// int[] values
	private int position = -1;
	
	@Override
	public void push(int n) {
		if (size++ > values.length)
			resize();
		
		position++;
		values[position] = n;
	}

	@Override
	public int pop() throws IndexOutOfBoundsException {
		if (size - 1 < 0)
			throw new IndexOutOfBoundsException();
		
		int i = peek();
		
		// we do not need to nullify primitives
		// values[position] = null;
		position--;
		size--;
		
		return i;
	}

	@Override
	public int peek() throws IndexOutOfBoundsException {
		if (position < 0)
			throw new IndexOutOfBoundsException();
		return values[position];
	}

}
