package ml224ec_assign4.binheap;

import java.util.Arrays;

/**
 * Array-based implementation of binary heap, using a generic edit of the required class specification.
 * <br>Percolation based on Max-Min order approach (biggest first).
 * <br>Inherits JDoc entries for members implemented by <code>BinaryHeap</code> from <code>BinaryHeap</code>.
 * <br><br>
 * Definition based on Victor S. Adamchik's 2009 description and implementation of Binary Heaps.<br>
 * Source: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html
 * @author Martin Lyrå
 *
 */
public class BinaryIntHeap implements BinaryHeap<Integer> {

	private static final int INITIAL_ARRAY_SIZE = 8;
	private Integer data[];
	private int size;
	
	/**
	 * Required by class specification provided by Exercise 5
	 */
	public BinaryIntHeap()
	{
		data = new Integer[INITIAL_ARRAY_SIZE + 1]; // position 0 is never used
		size = 0;
	}
	
	@Override
	public void insert(Integer n) {
		if (n == null)
			return;
		
		if (isFull())
			resize();
		
		int pos = ++size; // never use 0
		
		// percolate up using MAX -> MIN order approach
		while(pos > 1 && n.compareTo(data[pos/2]) > 0)
		{
			data[pos] = data[pos/2];
			pos = pos/2;
		}
		data[pos] = n;
	}

	@Override
	public Integer pullHighest() {
		Integer returnValue = data[1];
		
		Integer a = data[1] = data[size];
		data[size] = null;
		size--;
		
		int pos = 1;
		while(pos < size && isWithinArray(pos*2))
		{	
			int posB = pos*2;
			
			if (
					exists(posB) && exists(posB+1)
					&& 
					data[posB] < data[posB+1]) // left child is smaller than right
				posB++; // posB = posB + 1;
			
			if (data[posB] != null && a.compareTo(data[posB]) < 0) // max -> min order approach
			{
				data[pos] = data[posB];
				data[posB] = a;
				pos = posB;
			}
			else break;
		}
		
		return returnValue;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Prints out the entire contents as an String for debugging purposes
	 */
	public String toString()
	{
		return Arrays.toString(data);
	}
	
	/**
	 * Doubles the data array size
	 */
	private void resize()
	{
		int newSize = data.length*2 - 1; // == (data.length - 1)*2 + 1
		Integer tmp[] = new Integer[newSize];
		
		System.arraycopy(data, 1, tmp, 1, data.length-1);
		
		data = tmp;
	}
	
	/**
	 * Checks if the data array is full by comparsion of size to array length (excluding index 0)
	 * @return True if size >= length - 1
	 */
	private boolean isFull()
	{
		return size >= data.length - 1;
	}
	
	/**
	 * Checks if the index is within the array
	 * @param index
	 * @return
	 */
	private boolean isWithinArray(int index)
	{
		return index < data.length;
	}
	
	/**
	 * Checks if the index is both within array bounds, and if it points to a non-null value
	 * @param index
	 * @return
	 */
	private boolean exists(int index)
	{
		return isWithinArray(index) && data[index] != null;
	}
}
