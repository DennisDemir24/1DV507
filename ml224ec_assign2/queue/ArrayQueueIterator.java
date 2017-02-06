package ml224ec_assign2.queue;

import java.util.Iterator;

public class ArrayQueueIterator implements Iterator<Object> {

	private final Object[] data;
	
	private final int first;
	//private final int last;
	private final int size;
	
	private int position;
	private int processedSize;
	
	public ArrayQueueIterator(ArrayQueue arrayQueue)
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
	public Object next() {
		Object obj = data[position];
		
		if (position + 1 >= data.length)
			position = 0;
		else
			position++;
		
		processedSize++;
		
		return obj;
	}

}
