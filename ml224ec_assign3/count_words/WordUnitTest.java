package ml224ec_assign3.count_words;

import static org.junit.Assert.*;

import java.util.function.BiPredicate;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for the class 'Word', not included in exercise, but necessary.
 * Also the first time I try applying anonymous functions to unit tests.
 * The design is wonky, but saves time
 * @author Martin Lyrå
 *
 */
public class WordUnitTest {
	
	// private lambda interface for the comparison test
	private interface ComparisonLambda<T>
	{
		public abstract Integer compare(T first, T second);
	}
	
	private interface IteratorLambda<T>
	{
		public abstract Object apply(T first, T second);
	}

	private static final String[] WORD_TEST_VALUES = {
			"Wife", "wife", "lynx", "apple", "orange", "APPLE", "hello", "foo", "bar", "fOO"	
	};
	
	private static final Word[] TEST_WORDS = new Word[WORD_TEST_VALUES.length];
	
	/**
	 * Sets up all the input values for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < WORD_TEST_VALUES.length; i++)
			TEST_WORDS[i] = new Word(WORD_TEST_VALUES[i]);
	}

	@Test
	public void testToString() 
	{
		forEachExpect(
				(Word word,String string) -> {
					return string.compareTo(word.toString()) == 0;
				});
	}
	
	@Test
	public void testHashCode()
	{
		forEachTestEquality(
				(Word first, Word second) -> {
					return first.hashCode() == second.hashCode();
				}
				);
	}
	
	@Test
	public void testEquals() 
	{
		forEachTestEquality(
				(Word first, Word second) -> {
					return first.equals(second);
				});
	}
	
	@Test
	public void testCompareTo()
	{
		forEachCompare(
				(Word first, Word second) -> {
					return first.compareTo(second);
				}
				);
	}
	
	// internal functions
	
	/**
	 * Expects the result to be true, else it throws <code>AssertionError</code> on failure.
	 * @param predicate
	 */
	private void forEachExpect(BiPredicate<Word, String> predicate)
	{
		for (int i = 0; i < WORD_TEST_VALUES.length; i++)
		{
			Word w = TEST_WORDS[i];
			String s = WORD_TEST_VALUES[i].toLowerCase();
			
			boolean result = predicate.test(w, s);
			
			if (!result)
				fail (
						String.format("Sample %d - Result mismatch: Expected %s, got %s",
								i + 1, s, w)
						);
		}
	}

	private void forEachTestEquality(BiPredicate<Word, Word> predicate)
	{
		forEachEquals(
				(Word first, Word second) -> {
						return predicate.test(first, second);
					},
				(String first, String second) -> {
						return first.equals(second);
					}
				);
	}
	
	private void forEachCompare (ComparisonLambda<Word> comparison)
	{
		forEachEquals(
				(Word first, Word second) -> {
						return comparison.compare(first, second);
					},
				(String first, String second) -> {
						return first.compareTo(second);
					}
				);
	}
	
	/**
	 * Boilerplate equality function for handling non-special tests with fair complexity. 
	 * It tests the input values and then compares them to the values returned by the class test.
	 * If both have given the same result, then it is considered a success.
	 * Throws <code>AssertionError</code> when the results are not equal.
	 * 
	 * @param wordPredicate
	 * @param expectPredicate
	 */
	
	private void forEachEquals (
			IteratorLambda<Word> wordPredicate,
			IteratorLambda<String> expectPredicate)
	{
		int len = WORD_TEST_VALUES.length;
		for (int i = 0; i < len; i++)
		{
			Word w1 = TEST_WORDS[i];
			String s1 = WORD_TEST_VALUES[i].toLowerCase();
			
			for (int j = 0; j < len; j++)
			{
				Word w2 = TEST_WORDS[j];
				String s2 = WORD_TEST_VALUES[j].toLowerCase();
				
				Object expected = expectPredicate.apply(s1, s2);
				Object result = wordPredicate.apply(w1, w2);
				
				boolean areEqual = expected == result;
				
				if (!areEqual)
					fail (
							String.format("Sample %d - Comparsion test failed for '%s' against '%s' (Sample %d)\nResults:\nExpected: %d\nGot: -> %d",
									i + 1, w1, w2, j + 1, i+1, expected, j+1, result)
							);
			}
		}
	}
}
