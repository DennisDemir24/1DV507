package ml224ec_assign4.binheap;

import java.util.Arrays;

/**
 * An priority queue implementation using array-based binary heaps (Max-heap order).
 * Reuses code from <code>BinaryIntHeap</code>, with extension of the reusable <code>AbstractArrayStruct</code> class.
 * <br>Inherits JDoc entries for members implemented by <code>PriorityQueue</code> from <code>PriorityQueue</code>.
 * <br><br>
 * Definition based on Victor S. Adamchik's 2009 description and implementation of Binary Heaps.<br>
 * Source: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html
 * @author Martin Lyrå
 *
 * @param T - Any type that extends/implements <code>Comparable<T></code>
 */
public class BinaryHeapQueue<T extends Comparable<T>> 
	extends AbstractArrayStruct<T>
	implements PriorityQueue<T> 
{
	
	BinaryHeapQueue()
	{
		super();
	}
	
	@Override
	public void insert(T t) {
		if (t == null)
			return;
		
		if (isFull())
			resize();
		
		int pos = ++size; // never use 0
		
		// percolate up using MAX -> MIN order approach
		while(pos > 1 && t.compareTo(data[pos/2]) > 0)
		{
			data[pos] = data[pos/2];
			pos = pos/2;
		}
		data[pos] = t;
	}

	@Override
	public T pullHighest() {
		T returnValue = data[1];
		
		T a = data[1] = data[size];
		data[size] = null;
		size--;
		
		int pos = 1;
		while(pos < size && isWithinArray(pos*2))
		{	
			int posB = pos*2;
			
			if (
					exists(posB) && exists(posB+1)
					&& 
					data[posB].compareTo(data[posB+1]) < 0) // left child is smaller than right
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
	public T peekHighest() {
		return data[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T t) {
		if (t == null || isEmpty() || t.compareTo(data[1]) > 0)
			return false;
		 
		return super.contains(t);
		
		// earlier attempt on an algorithm faster than O(n) without using comparators
		/*
		int pos = 1;
		if (t.equals(data[pos]))
			return true;
		while(pos < size && isWithinArray(pos*2))
		{		
			int posB = pos*2;
			int posC = posB + 1;
			
			System.out.printf("%s | %s | Target: %s\n", data[posB], data[posB + 1], t);
			
			// in a Max -> Min order system, check the right child first before checking on the left child
			if  ( exists(posC))
			{
				if (t.compareTo(data[posC]) <= 0)
				{
					if (t.equals(data[posC]))
						return true;
					else
						pos = posC;
				}
			}
			if ( exists(posB) )
			{
				if (t.compareTo(data[posB]) <= 0)
				{
					if (t.equals(data[posB]))
						return true;
					else
						pos = posB;
				}
			}	
			else break;
			/*
			if (data[posB] != null && t.compareTo(data[posB]) < 0) // max -> min order approach
			{
				pos = posB;
			}
			else break;
		}
		
		return false;
		*/
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
}
