package ml224ec_assign3.count_words;

public interface WordSet extends Iterable<Word> {

	/**
	 * Adds <code>word</code> to the set, if not already added
	 * @param word
	 */
	public void add(Word word);
	
	/**
	 * Returns true if the set contains <code>word</code>
	 * @param word
	 * @return
	 */
	public boolean contains(Word word);
	
	/**
	 * Returns the current size of the set
	 * @return
	 */
	public int size();
	
	/**
	 * Returns String of the content in List style (<code>[ a, b, c ]</code>)
	 * @return
	 */
	public String toString();
	
}
