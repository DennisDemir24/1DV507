package ml224ec_assign3;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class SortTest {

	private static final int[][] INTEGER_VALUE_SAMPLES = {
			new int[]{10,0},
			new int[]{10,20,0,-10},
			new int[]{0,0,2,1,0},
			new int[]{-1,-5,-2},
			new int[]{4,3,2,1},
			new int[]{32,54,19,204,11,-54},
			new int[]{-54,-21,0,12,22,-261,77,-51,0,1,-5}
	};
	
	private static final String[][] STRING_VALUE_SAMPLES = {
			new String[] {"Steve", "Alexander", "Bo", "Julian", "Anna", "Erica"},
			new String[] {"A", "B", "C", "c", "b", "a" }
	};
	
	private static int[][] sortedIntegerSamples;
	private static String[][] sortedStringSamples;
	
	// The comparator to use for String tests
	private static final Comparator<String> TEST_STRING_COMPARATOR = String.CASE_INSENSITIVE_ORDER;
	
	@Before
	public void setUp() throws Exception {
		// Integers
		sortedIntegerSamples = new int[INTEGER_VALUE_SAMPLES.length][];
		
		for (int i = 0; i < INTEGER_VALUE_SAMPLES.length; i++)
			sortedIntegerSamples[i] = INTEGER_VALUE_SAMPLES[i].clone();
		for (int[] arr : sortedIntegerSamples)
			Arrays.sort(arr);
		
		// Strings
		sortedStringSamples = new String[STRING_VALUE_SAMPLES.length][];
		
		for (int i = 0; i < STRING_VALUE_SAMPLES.length; i++)
			sortedStringSamples[i] = STRING_VALUE_SAMPLES[i].clone();
		for (String[] arr : sortedStringSamples)
			Arrays.sort(arr, TEST_STRING_COMPARATOR);
	}

	@Test
	public void testInsertionSortInteger() {
		
		System.out.println("=== INSERTION SORT (int) ===");
		for (int i = 0; i < INTEGER_VALUE_SAMPLES.length; i++)
		{
			int[] arr = INTEGER_VALUE_SAMPLES[i];
			
			System.out.printf("%d > Testing sample %d: %s\n", i, i, Arrays.toString(arr));
			int[] sorted = SortingAlgorithms.insertSort(arr);
			
			// evaluate the result
			String sortedStr = Arrays.toString(sorted);
			String expectedStr = Arrays.toString(sortedIntegerSamples[i]);
			
			if (sortedStr.compareTo(expectedStr) != 0)
			{
				String message = String.format("Sample %d: Sorting failed, comparsion returned non-zero.\nExpected %s\nGot %s",
						i, expectedStr, sortedStr);
				System.out.println(message);
				fail (message);
			}
			
			System.out.printf("%d > Expected: %s\n%d > Result: %s\n\n",
					i, expectedStr,
					i, sortedStr);
		}
	}
	
	@Test
	public void testMergeSortInteger()
	{
		System.out.println("=== MERGE SORT (int) ===");
		for (int i = 0; i < INTEGER_VALUE_SAMPLES.length; i++)
		{
			int[] arr = INTEGER_VALUE_SAMPLES[i];
			String arrayPreStr = Arrays.toString(arr);
			
			System.out.printf("%d > Testing sample %d: %s\n", i, i, Arrays.toString(arr));
			int[] sorted = SortingAlgorithms.mergeSort(arr);
			
			String arrayPostStr = Arrays.toString(arr);
			
			// evaluate the result
			String sortedStr = Arrays.toString(sorted);
			String expectedStr = Arrays.toString(sortedIntegerSamples[i]);
			
			if (arrayPreStr.compareTo(arrayPostStr) != 0)
			{
				String message = String.format("%d > Sample %d: Sorting failed, original array was changed.", i,i);
				System.out.println(message);
				fail (message);
			}
			
			if (sortedStr.compareTo(expectedStr) != 0)
			{
				String message = String.format("%d > Sample %d: Sorting failed, comparsion returned non-zero.\nExpected %s\nGot %s",
						i, i, expectedStr, sortedStr);
				System.out.println(message);
				fail (message);
			}
			
			System.out.printf("%d > Expected: %s\n%d > Result: %s\n\n", 
					i, expectedStr,
					i, sortedStr);
		}
	}

	@Test
	public void testInsertionSortString() {
		
		System.out.println("=== INSERTION SORT (String) ===");
		for (int i = 0; i < STRING_VALUE_SAMPLES.length; i++)
		{
			String[] arr = STRING_VALUE_SAMPLES[i];
			
			System.out.printf("%d > Testing sample %d: %s\n", i, i, Arrays.toString(arr));
			String[] sorted = SortingAlgorithms.insertSort(arr, TEST_STRING_COMPARATOR);
			
			// evaluate the result
			String sortedStr = Arrays.toString(sorted);
			String expectedStr = Arrays.toString(sortedStringSamples[i]);
			
			if (sortedStr.compareTo(expectedStr) != 0)
			{
				String message = String.format("Sample %d: Sorting failed, comparsion returned non-zero.\nExpected %s\nGot %s",
						i, expectedStr, sortedStr);
				System.out.println(message);
				fail (message);
			}
			
			System.out.printf("%d > Expected: %s\n%d > Result: %s\n\n",
					i, expectedStr,
					i, sortedStr);
		}
	}
	
	@Test
	public void testMergeSortString() {
		
		System.out.println("=== MERGE SORT (String) ===");
		for (int i = 0; i < STRING_VALUE_SAMPLES.length; i++)
		{
			String[] arr = STRING_VALUE_SAMPLES[i];
			
			System.out.printf("%d > Testing sample %d: %s\n", i, i, Arrays.toString(arr));
			String[] sorted = SortingAlgorithms.mergeSort(arr, TEST_STRING_COMPARATOR);
			
			// evaluate the result
			String sortedStr = Arrays.toString(sorted);
			String expectedStr = Arrays.toString(sortedStringSamples[i]);
			
			if (sortedStr.compareTo(expectedStr) != 0)
			{
				String message = String.format("Sample %d: Sorting failed, comparsion returned non-zero.\nExpected %s\nGot %s",
						i, expectedStr, sortedStr);
				System.out.println(message);
				fail (message);
			}
			
			System.out.printf("%d > Expected: %s\n%d > Result: %s\n\n",
					i, expectedStr,
					i, sortedStr);
		}
	}
}
