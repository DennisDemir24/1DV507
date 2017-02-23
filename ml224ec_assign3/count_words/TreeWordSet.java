package ml224ec_assign3.count_words;

import java.util.Iterator;

public class TreeWordSet implements WordSet {

	private class BinaryTreeNode<T extends Comparable<T>> {
		final T element;
		
		BinaryTreeNode<T> parent; // used for iteration
		
		BinaryTreeNode<T> left;
		BinaryTreeNode<T> right;
		
		public BinaryTreeNode(T element)
		{
			this.element = element;
		}
		
		private BinaryTreeNode(BinaryTreeNode<T> parentNode, T element)
		{
			this(element);
			parent = parentNode;
		}
		
		public boolean add(T element)
		{
			int diff = this.element.compareTo(element);
			
			// duplicates (x == 0) are never added
			if (diff < 0) // this.element < element
			{
				if (right == null)
				{
					right = new BinaryTreeNode<T>(this, element);
					return true;
				}
				else
					return right.add(element);
			}
			else if (diff > 0) // this.element > element
			{
				if (left == null)
				{
					left = new BinaryTreeNode<T>(this, element);
					return true;
				}
				else
					return left.add(element);
			}
			return false; // the element wasn't added
		}
		
		/**
		 * Recursively search the tree branches until a match has made. 
		 * Returns <code>true</code> if it has found the input value <code>element</code>.
		 * Else <code>false</code> if it does not exist.
		 * @return
		 */
		public boolean probe(T element)
		{
			int diff = this.element.compareTo(element);
			if (diff == 0)
				return true;
			else
			{
				if (diff < 0 && right != null)
					return right.probe(element);
				if (diff > 0 && left != null)
					return left.probe(element);
			}
			return false;
		}
		
		@Override
		public String toString()
		{
			return String.format("[ %s, l: %s, r: %s ]",
					element,
					(left != null ? left.element : null),
					(right != null ? right.element : null));
		}
	}
	
	private class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T>
	{
		private final BinaryTreeNode<T> root; 	// for rewinding
		private final T minValue;				// for reverse iteration (high -> low)
		private final T maxValue;
		
		private BinaryTreeNode<T> currentNode;
		private T previousValue = null;
		
		public BinaryTreeIterator(BinaryTreeNode<T> root)
		{
			this.root = root;
			
			// set current position to minimum
			currentNode = root;
			while (currentNode.left != null)
				currentNode = currentNode.left;
			
			minValue = currentNode.element;
			
			// find maximum
			BinaryTreeNode<T> node = root;
			while (node.right != null)
				node = node.right;
			
			maxValue = node.element;
		}

		@Override
		public boolean hasNext() {
			return previousValue == null || maxValue.compareTo(previousValue) > 0;
		}

		@Override
		public T next() {
			if (previousValue != null)
				findNext();
			return (previousValue = currentNode.element); // assign, THEN return
		}
		
		// beware, I once knew what this code did when I wrote it
		// but it took me at least 10 mins to forget everything
		// what it did - how and why
		//
		// now it is uncharted black magic no one can explain or dare to touch
		private void findNext() {
			if (currentNode.right != null)
			{
				currentNode = currentNode.right;
				while (currentNode.left != null) // go to the lowest value of the right branch
					currentNode = currentNode.left;
			}
			else
			{
				currentNode = currentNode.parent; // reverse
				while (currentNode.element.compareTo(previousValue) < 0) // as long the previous value is greater than the selected
					currentNode = currentNode.parent;
			}
		}
	}
	
	BinaryTreeNode<Word> root;
	
	int size = 0;
	
	@Override
	public Iterator<Word> iterator() {
		return new BinaryTreeIterator<Word>(root);
	}

	@Override
	public void add(Word word) {
		if (root == null)
		{
			root = new BinaryTreeNode<Word>(word);
			size++;
		}
		else
			if (root.add(word))
				size++;
	}

	@Override
	public boolean contains(Word word) {
		return root.probe(word);
	}

	@Override
	public int size() {
		return size;
	}

}
