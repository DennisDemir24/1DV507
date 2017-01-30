package ml224ec_assign2.queue;

import java.util.Iterator;

/**
 * LinkedQueue is an implementation of Queue using
 * the head-tail approach to linked collections.
 * @author Martin Lyrå
 *
 */
public class LinkedQueue implements Queue {

	private int size = 0;
	private ObjectNode head;
	private ObjectNode tail;
	
	/**
	 * Returns the size of the queue.
	 */
	@Override
	public int size() {
		return size;
	}

	 /** 
	 * Returns true if the size equals <code>0</code>; the queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Adds object <code>element</code> to the end of the queue.
	 */
	@Override
	public void enqueue(Object element) {
		ObjectNode node = new ObjectNode(element);
		
		if (size < 1)
			head = node;
		if (tail != null)
			tail.link(node);
		
		tail = node;
		size++;
	}

	/**
	 * Removes and returns the first object from the list.
	 */
	@Override
	public Object dequeue() {
		Object object = head.value();
		if (head.hasNext() && size > 0)
			head = head.next();
		else 
			head = tail;
		size--;
		return object;
	}

	/**
	 * Returns the first object in the queue, without removing it.
	 * Akin to FIFOs' (e.g. Stacks) <code>peek()</code> which also returns
	 * an object without removing it.
	 */
	@Override
	public Object first() {
		return head.value();
	}

	/**
	 * Returns the last object in the queue, without removing it.
	 * Akin to FIFOs' (e.g. Stacks) <code>peek()</code> which also returns
	 * an object without removing it.
	 */
	@Override
	public Object last() {
		return tail.value();
	}

	/**
	 * Returns an specialized iterator for this queue.
	 */
	@Override
	public Iterator<Object> iterator() {
		return new ObjectNodeIterator(head);
	}

	/**
	 * Returns an string in similar fashion to the one provided by List classes.
	 */
	@Override
	public String toString()
	{
		String text = "[ ";
		Iterator<Object> it = iterator();
		for (int i = 0; it.hasNext(); i++)
			text += it.next() + (i < size() - 1 ? ", " : " ]");
		return text;
	}
	
}
