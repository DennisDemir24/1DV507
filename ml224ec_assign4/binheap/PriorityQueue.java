package ml224ec_assign4.binheap;

public interface PriorityQueue<T> {

	/**
	 * Inserts T to the queue.
	 * @param t
	 */
	void insert(T t);
	
	/**
	 * Removes the T with the highest priority value from the queue and returns it.
	 * @return
	 */
	T pullHighest();
	/**
	 * Returns the T with the highest priority value from the queue, without removing it.
	 * @return
	 */
	T peekHighest();
	
	/**
	 * Returns the size of the queue
	 * @return
	 */
	int size();
	
	/**
	 * Returns true if queue contains the input <code>T t</code>
	 * @return
	 */
	boolean contains(T t);
	/**
	 * Returns true if queue is per conditions empty.
	 * @return
	 */
	boolean isEmpty();
}
