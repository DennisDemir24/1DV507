package ml224ec_assign4.queue_generic;

import java.util.Iterator;

/**
 * LinkNodeIteratior is an implementation of Iterator for LinkNode,
 * an implementation of the head-tail approach for linked collection 
 * @author Martin Lyrå
 *
 */
public class LinkNodeIterator<T> implements Iterator<T> {

	private final LinkNode<T> head; // head for rewinding
	private LinkNode<T> current;
	
	/**
	 * Default constructor for LinkNodeIterator,
	 * it takes a LinkNode that represents as the 'head'
	 * of a linked collection.
	 * @param node - the head of linked collection
	 */
	public LinkNodeIterator(LinkNode<T> node)
	{
		head = new LinkNode<T>(null); // due to how the linked concept is implemented, start with a dummy object for head
		head.link(node);
		
		current = head;
	}
	
	@Override
	public boolean hasNext() {
		return current.hasNext();
	}

	@Override
	public T next() {
		current = current.next();
		return current.value();
	}

}
