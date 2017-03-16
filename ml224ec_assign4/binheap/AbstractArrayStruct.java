package ml224ec_assign4.binheap;

/**
 * Abstract class for straight array-based data structures. Contains protected members
 * and an internal array field and a field for the size. The functions can be overriden
 * if required.
 * @author Martin Lyrå
 *
 * @param <T>
 */
public abstract class AbstractArrayStruct<T extends Comparable<T>> {
	
	protected static final int INITIAL_ARRAY_SIZE = 8;
	protected T[] data;
	protected int size;
	
	protected AbstractArrayStruct()
	{
		this.data = (T[]) makeArray(INITIAL_ARRAY_SIZE);
		this.size = 0;
	}
	
	/**
	 * Doubles the data array size
	 */
	protected void resize()
	{
		int newSize = data.length*2; // == (data.length - 1)*2 + 1
		T[] tmp = (T[]) makeArray(newSize);
		
		System.arraycopy(data, 0, tmp, 0, data.length);
		
		data = tmp;
	}
	
	/**
	 * Checks if the data array is full by comparsion of size to array length (excluding index 0)
	 * @return True if size >= length - 1
	 */
	protected boolean isFull()
	{
		return size >= data.length - 1;
	}
	
	/**
	 * Checks if the index is within the array
	 * @param index
	 * @return
	 */
	protected boolean isWithinArray(int index)
	{
		return index < data.length;
	}
	
	/**
	 * Checks if the index is both within array bounds, and if it points to a non-null value
	 * @param index
	 * @return
	 */
	protected boolean exists(int index)
	{
		return isWithinArray(index) && data[index] != null;
	}
	
	/**
	 * Searches the internal array for any equal match, if a match was found. 
	 * An index will be returned.
	 * @param object - The object to be searched for
	 * @return Any integer equal or greater than 0 on success, -1 on failure.
	 */
	protected int indexOf(T object)
	{
		if (object != null)
			for (int i = 0; i < data.length; i++)
				if (data[i] != null && data[i].equals(object))
					return i;
		return -1;
	}
	
	/**
	 * Searches the internal array for any match, uses <code>indexOf</code>. Returns true if 
	 * the returned index is not <code>-1</code>.
	 * @param object - The object to be searched for
	 * @return True if found, otherwise false
	 */
	protected boolean contains(T object)
	{
		return indexOf(object) != -1;
	}
	
	/**
	 * Helper function for making quick arrays. Also mainly to assist generation of generic
	 * arrays.
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private T[] makeArray(int size)
	{
		return (T[]) new Comparable[size];
	}
}
