package ml224ec_assign4.queue_generic;

import java.util.Iterator;

public class ArrayQueueIterator<T> implements Iterator<T> {

	private final T[] data;
	
	private final int first;
	//private final int last;
	private final int size;
	
	private int position;
	private int processedSize;
	
	public ArrayQueueIterator(ArrayQueue<T> arrayQueue)
	{
		this.data = arrayQueue.data;
		this.first = arrayQueue.first;
		//this.last = arrayQueue.last;
		this.size = arrayQueue.size;
		
		position = first;
	}
	
	@Override
	public boolean hasNext() {
		return processedSize < size;
	}

	@Override
	public T next() {
		T obj = data[position];
		
		if (position + 1 >= data.length)
			position = 0;
		else
			position++;
		
		processedSize++;
		
		return obj;
	}

}
