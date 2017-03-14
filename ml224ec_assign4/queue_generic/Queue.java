package ml224ec_assign4.queue_generic;

import java.util.Iterator;

/**
 * A collection data structure based on FIFO, First In First Out.
 * Re-implementation as Generic class
 * @author Martin Lyrå
 *
 */
public interface Queue<T extends Object> {  
	
		/**
		 * An integer function that returns the size, or the queue's length.
		 * @return Current length of the queue
		 */
	   public int size();                     // current queue size 
	   
	   /**
	    * A boolean function that returns true if empty.
	    * @return
	    */
	   public boolean isEmpty();              // true if queue is empty 
	   
	   /**
		 * Adds object <code>element</code> to the end of the queue.
		 */
	   public void enqueue(T element);   // add element at end of queue 
	   
	   /**
	    * Retrieves the first element from the front end of the queue, and removes it from the queue.
	    * @return The first queued Object as T
	    */
	   public T dequeue();               // return and remove first element. 
	   
	   /**
	    * Retrieves the first element from the front end of the queue. 
	    * Except, like <code>peek</code>, it won't be removed. 
	    * @return The first queued Object as T
	    */
	   public T first();                 // return (without removing) first element 
	   
	   /**
	    * Retrieves the last element from the back end of the queue. 
	    * Except, like <code>peek</code>, it won't be removed. 
	    * @return The first queued Object as T
	    */
	   public T last();                  // return (without removing) last element 
	   
	   /**
	    * Returns a string containing the queue's active contents in a similar format to List's.
	    * @return A String containing the contents seperated by commas, <code>[ ]</code> if empty. 
	    */
	   public String toString();              // return a string representation of the queue content
	   
	   /**
	    * Creates an iterator for this queue.
	    * @return The queue's iterator.
	    */
	   public Iterator<T> iterator();    // element iterator
}
