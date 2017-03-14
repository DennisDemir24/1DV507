package ml224ec_assign4.queue_generic;

import java.util.Iterator;

/**
 * LinkedQueue is an implementation of Queue using
 * the head-tail approach to linked collections.
 * @author Martin Lyrå
 *
 */
public class LinkedQueue<T> implements Queue<T> {

	private int size = 0;
	private LinkNode<T> head;
	private LinkNode<T> tail;
	
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
	public void enqueue(T element) {
		LinkNode<T> node = new LinkNode<T>(element);
		
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
	public T dequeue() {
		T object = head.value();
		head = head.next();
		size--;
		return object;
	}

	/**
	 * Returns the first object in the queue, without removing it.
	 * Akin to FIFOs' (e.g. Stacks) <code>peek()</code> which also returns
	 * an object without removing it.
	 */
	@Override
	public T first() {
		if (head == null)
			return null;
		return head.value();
	}

	/**
	 * Returns the last object in the queue, without removing it.
	 * Akin to FIFOs' (e.g. Stacks) <code>peek()</code> which also returns
	 * an object without removing it.
	 */
	@Override
	public T last() {
		if (tail == null)
			return null;
		return tail.value();
	}

	/**
	 * Returns an specialized iterator for this queue.
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkNodeIterator<T>(head);
	}

	/**
	 * Returns an string in similar fashion to the one provided by List classes.
	 */
	@Override
	public String toString()
	{
		String text = "[ ";
		Iterator<T> it = iterator();
		while (it.hasNext())
			text += it.next() + (it.hasNext() ? ", " : "");
		text += " ]";
		return text;
	}
	
}
