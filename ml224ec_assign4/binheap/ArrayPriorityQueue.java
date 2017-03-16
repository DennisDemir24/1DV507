package ml224ec_assign4.binheap;

import java.util.Arrays;

/**
 * A dangerously slow and unclean implementation of PriorityQueue. 
 * Lazily implemented with AbstractArrayStruct.
 * Made primarily to test the flexibility of the mentioned interface.
 * For a more serious attempt, consider checking out <code>BinaryHeapQueue</code>
 * @author Martin Lyrå
 *
 * @param <T>
 */
public class ArrayPriorityQueue<T extends Comparable<T>>
	extends AbstractArrayStruct<T>
	implements PriorityQueue<T>
{

	@Override
	public void insert(T t) {
		
		if (t == null)
			return;
		
		if (isFull())
			resize();
		
		data[size] = t;
		++size;
	}

	@Override
	public T pullHighest() {
		insertSort();
		T t = data[0];
		data[0] = null;
		insertSort();
		size--;
		return t;
	}

	@Override
	public T peekHighest() {
		insertSort();
		return data[0];
	}

	@Override
	public boolean contains(T t)
	{
		if (t == null)
			return false;
		return super.contains(t);
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
	 * Copy of insertSort, with modifications accommodating the internal array
	 * and occurrence of null pointers. Who are considered as inferior to everything,
	 * even negative numbers.
	 */
	private void insertSort()
	{
		T[] tmp = data.clone();
		
		for (int i = 1; i < data.length; i++)
		{
			T x = data[i];
			
			int j = i - 1;
			
			while (j >= 0 && x != null && (tmp[j] == null || x.compareTo(tmp[j]) > 0)) // null values are put last
			{
				tmp[j+1] = tmp[j];
				j--;
			}
			tmp[j+1] = x;
		}
		
		data = tmp;
	}
}
