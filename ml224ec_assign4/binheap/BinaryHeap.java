package ml224ec_assign4.binheap;

/**
 * An interface to declare all members as specified by the required class specification in Exercise 5
 * 
 * <li>Modified for generic support</lt>
 * <li>Default constructor not included, but it is required!</li>
 * 
 * @author Martin Lyrå
 *
 */
public interface BinaryHeap<T> {
	
	/**
	 * Inserts element <code>n</code> to the binary heap data structure.
	 * @param n
	 */
	public void insert(T n); 	// Add n to heap
	
	/**
	 * Returns and removes the element with the highest value found from the heap.
	 * @return A <code>T</code> element with the highest value of the heap.
	 */
	public T pullHighest();    	// Return and remove element with highest priority
	
	/**
	 * Returns the current size of this heap
	 * @return
	 */
	public int size();         	// Current heap size
	
	/**
	 * Returns true if heap is per conditions empty.
	 * @return
	 */
	public boolean isEmpty(); 	// True if heap is empty
}
