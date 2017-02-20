package ml224ec_assign3.count_words;

import static org.junit.Assert.*;

import java.util.function.BiPredicate;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for the class 'Word', not included in exercise, but necessary.
 * Also the first time I try applying anonymous functions to unit tests.
 * The design is wonky, but saves me time
 * @author Martin Lyrå
 *
 */
public class WordUnitTest {
	
	// private lambda interface for the comparison test
	private interface ComparisonLambda<T>
	{
		public abstract Integer compare(T first, T second);
	}

	private static final String[] WORD_TEST_VALUES = {
			"Wife", "wife", "lynx", "apple", "orange", "APPLE", "hello", "foo", "bar", "fOO"	
	};
	
	private static final Word[] TEST_WORDS = new Word[WORD_TEST_VALUES.length];
	
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
		int len = WORD_TEST_VALUES.length;
		for (int i = 0; i < len; i++)
		{
			Word w1 = TEST_WORDS[i];
			
			for (int j = 0; j < len; j++)
			{
				Word w2 = TEST_WORDS[j];
				
				boolean result = predicate.test(w1, w2);
				
				// as long they are actually not the same instance, consider each false a success
				if (i != j) 
					result = !result;
				
				if (!result)
					fail (
							String.format("Sample %d - Equality test failed for '%s' against '%s' (Sample %d)\nHashes:\n%d -> %d\n%d -> %d",
									i + 1, w1, w2, j + 1, i+1, w1.hashCode(), j+1, w2.hashCode())
							);
			}
		}
	}
	
	private void forEachCompare (ComparisonLambda<Word> comparison)
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
				
				int expected = s1.compareTo(s2);
				int result = comparison.compare(w1, w2);
				
				boolean areEqual = expected == result;
				
				if (!areEqual)
					fail (
							String.format("Sample %d - Comparsion test failed for '%s' against '%s' (Sample %d)\nComparisons:\nExpected: %d\nGot: -> %d",
									i + 1, w1, w2, j + 1, i+1, expected, j+1, result)
							);
			}
		}
	}
}
