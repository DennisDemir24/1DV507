package ml224ec_assign4.queue_generic;

/**
 * LinkNode is the implementation of the node part of linked collections.
 * Most ideal for the one-way heal-tail approach.
 * @author Martin Lyrå
 *
 */
public class LinkNode<T> {

	private T object	= null;
	private LinkNode<T> link = null;
	
	/**
	 * Default constructor for LinkNode,
	 * a fundamental piece in implementation of linked collections
	 * @param object - The Object (as T) the node corresponds
	 */
	public LinkNode(T object)
	{
		this.object = object;
	}
	
	/**
	 * Adds an LinkNode as a reference in the head-tail link approach
	 * @param link - Reference to next LinkNode
	 */
	public void link(LinkNode<T> link)
	{
		this.link = link;
	}
	
	/**
	 * Returns the Object (as T) this class represents.
	 * @return The Object (as T) it corresponds
	 */
	public T value()
	{
		return object;
	}
	
	/**
	 * Returns true is the next node is not null.
	 * @return True if next() won't return null
	 */
	
	public boolean hasNext()
	{
		return link != null;
	}
	
	/**
	 * Returns the next node referenced by this node.
	 * @return The next reference in the link.
	 */
	public LinkNode<T> next()
	{
		return link;
	}
	
}
