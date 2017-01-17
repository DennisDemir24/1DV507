package ml224ec_assign1;

import ml224ec_assign1.int_collection.AbstractIntCollection;
import ml224ec_assign1.int_collection.IntList;

public class ArrayIntList extends AbstractIntCollection implements IntList {
	
	@Override
	public void add(int n) {
		size++;
		if ( size > values.length )
			resize();
		values[size-1] = n;
	}

	@Override
	public void addAt(int n, int index) throws IndexOutOfBoundsException {
		if (index >= size)
			throw new IndexOutOfBoundsException();
		
		if (size + 1 > values.length)
			resize();
		
		// moves the "upper" part of the array a step farther
		System.arraycopy(values, index, values, index + 1, size - index);
		
		values[index] = n;
		size++;
	}

	@Override
	public void remove(int index) throws IndexOutOfBoundsException {
		if (index >= size)
			throw new IndexOutOfBoundsException();
		
		// moves the "upper" part of the array a step closer
		System.arraycopy(values, index + 1, values, index, size - index);
		
		size--;
	}

	@Override
	public int get(int index) throws IndexOutOfBoundsException {
		if (index >= size)
			throw new IndexOutOfBoundsException();
		return values[index];
	}

	@Override
	public int indexOf(int n) {
		for (int i = 0; i < size; i++)
			if (values[i] == n)
				return i;
		return -1; // not found, does not exist -- or the array was empty
	}

}
