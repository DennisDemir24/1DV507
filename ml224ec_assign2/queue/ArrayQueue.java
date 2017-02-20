package ml224ec_assign2.queue;

import java.util.Iterator;

/**
 * Implementation of Queue based on circular arrays.
 * @author Martin Lyrå
 *
 */
public class ArrayQueue implements Queue {

	Object[] data = new Object[8];
	
	int first	= 0;
	int next	= 0;
	int last 	= 0;
	
	int size	= 0;
	
	private void resize()
	{
		int oldSize = data.length;
		int newSize = oldSize * 2;
		int diff = newSize - oldSize;
		
		int headSize = data.length - first;
		
		Object[] tmp = new Object[newSize];
		
		if (first > last)
		{
			System.arraycopy(data, 0, tmp, 0, next); // next = last + 1
			System.arraycopy(data, first, tmp, first += diff, headSize);
		}
		else
		{
			System.arraycopy(data, first, tmp, first, size);
			next = size;
		}
		
		data = tmp;
	}
	
	private void increment()
	{
		last = next;
		if (size < data.length && next + 1 >= data.length)
		{
			next = 0;
		}
		else 
		{
			next++;
		}
		size++;
	}
	
	private void decrement()
	{
		if (size < data.length && first + 1 >= data.length)
			first = 0;
		else 
			first++;
		size--;
		
		if (size == 0) // saves us from headaches
			first = next = last = 0; 
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(Object element) {
		if (size + 1 > data.length)
			resize();
		data[next] = element;
		increment();
	}

	@Override
	public Object dequeue() {
		Object element = data[first];
		data[first] = null;
		decrement();
		return element;
	}

	@Override
	public Object first() {
		return data[first];
	}

	@Override
	public Object last() {
		return data[last];
	}

	@Override
	public Iterator<Object> iterator() {
		return new ArrayQueueIterator(this);
	}

	@Override
	public String toString() {
		
		String text = "[ ";
		
		Iterator<Object> it = iterator();
		while (it.hasNext())
			text += it.next() + (it.hasNext() ? ", " : "");
		
		text += " ]";
		
		return text;
	}
	
	// use this to return a full string representing the entire data memory
	public String toDebugString() {
		String text = "[ ";
		for (int i = 0; i < data.length; i++)
			text += data[i] + (i < data.length - 1 ? ", " : " ]");
		return text;
	}
}
