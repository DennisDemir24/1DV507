package ml224ec_assign3.count_words;

import java.util.Iterator;

public class HashWordSet implements WordSet {

	private class HashNode<T extends Object> {
		T data;
		HashNode<T> next;
		
		HashNode(T data) {
			this.data = data;
		}
	}
	
	private class HashNodeIterator<T extends Object> implements Iterator<T>
	{
		private final HashNode<T>[] buckets;
		private final int size;
		
		private int processedCount;
		private int bucketIndex;
		private HashNode<T> currentNode;
		
		public HashNodeIterator(HashNode<T>[] data, int size)
		{
			this.size = size;
			buckets = data;
			bucketIndex = processedCount = 0;
			
			findNext();
		}

		@Override
		public boolean hasNext() {
			return processedCount < size;
		}

		@Override
		public T next() {
			T data = currentNode.data;
			processedCount++;
			findNext();
			return data;
		}
		
		private void findNext()
		{
			if (currentNode == null || currentNode.next == null) // end of the chain?
			{
				if (currentNode != null)
					bucketIndex++;
				if (bucketIndex < buckets.length)
				{
					while (bucketIndex < buckets.length - 1 && buckets[bucketIndex] == null)
						bucketIndex++;
					currentNode = buckets[bucketIndex];
				}
			}
			else 
				currentNode = currentNode.next; // go-to the next link in the chain
		}
	}
	
	private static final int INITIAL_BUCKET_SIZE = 16;
	
	HashNode<Word>[] data = new HashNode[INITIAL_BUCKET_SIZE];
	
	int size;
	
	@Override
	public Iterator<Word> iterator() {
		return new HashNodeIterator<Word>(data, size);
	}

	@Override
	public void add(Word word) {
		int bucketArrayIndex = hashFunc(word.hashCode());
		HashNode<Word> bucket = data[bucketArrayIndex];
		
		if (bucket == null)
		{
			data[bucketArrayIndex] = new HashNode<Word>(word);
			size++;
		}
		else if (!contains(bucket, word))
		{
			HashNode<Word> node = bucket;
			while (node.next != null)
				node = node.next;
			node.next = new HashNode<Word>(word);
			size++;
		}
		
		if (size == data.length)
			rehash();
	}

	@Override
	public boolean contains(Word word) {
		return contains(getBucketByHash(word.hashCode()), word);
	}
	
	private boolean contains(HashNode<Word> node, Word word)
	{
		while (node != null)
		{
			if (node.data != null && node.data.equals(word))
				return true;
			else
				node = node.next;
		}
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		String string = "[ ";
		
		for (HashNode<Word> node : data)
		{
			HashNode<Word> n = node;
			while (node != null)
			{
				string += node.data; 
				if (node.next != null)
					string += ", ";
				node = node.next;
			}
			if (n != null)
				string += ", ";
		}
		
		return string += " ]";
	}
	
	// Technically, you don't need this for accuracy, but if you want a faster optimized Set,
	// then you need this as it helps eliminating long linked node chains.
	// Otherwise the big O, during search in 'contains', will increment for every element added,
	// making it longer during 'add' and 'contains' operations.
	// Also eliminates null data
	private void rehash()
	{
		HashNode<Word>[] temp = data;
		data = new HashNode[size * 2];
		size = 0;
		
		for (HashNode<Word> node : temp)
			while (node != null)
			{
				if (node.data != null)
					add(node.data);
				node = node.next;
			}
	}
	
	private HashNode<Word> getBucketByHash(int hash)
	{
		return data[hashFunc(hash)];
	}

	private int hashFunc(int hash)
	{
		return Math.abs(hash % data.length);
	}
	
}
