package ml224ec_assign4.time;

import java.util.Comparator;
import java.util.Random;

public class SortingTimeMain {

	private static final boolean USE_NANOSECS = true;
	
	private static final long SECOND_IN_MS = 1000;
	private static final long SECOND_IN_NS = 1000000000; // 1 sec
	private static final long AVERAGE_TIME_LIMIT = ( USE_NANOSECS ? SECOND_IN_NS : SECOND_IN_MS ); // 1 sec
	
	private static final int TEST_REPEAT_COUNT = 5;
	
	private enum TargetType {
		INTEGER,
		STRING
	}
	
	private static final int TEST_ARRAY_AMOUNT = 10;
	private static final int TEST_ARRAY_LENGTH = 64;
	
	private static final int TEST_INTEGER_FACTOR = 8;
	private static final int TEST_STRING_LENGTH = 10;
	private static final String REFERENCE_STRING = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXZY";
	
	// The comparator to use for String tests
	private static final Comparator<String> TEST_STRING_COMPARATOR = String.CASE_INSENSITIVE_ORDER;
		
	private static int integerArrays[][];
	private static String stringArrays[][];
	
	public static void main(String[] args) 
	{	
		prepareArrays();
		
		int totalSortedInsertInteger = 0;
		int totalSortedInsertString = 0;
		int totalSortedMergeInteger = 0;
		int totalSortedMergeString = 0;
		
		System.out.printf("Parameters:\nArrays per cycle: %d, Array length: %d,\nString length: %d, Repeat count: %d\nUses nanosecond time precision: %s\n",
				TEST_ARRAY_AMOUNT, TEST_ARRAY_LENGTH, TEST_STRING_LENGTH, TEST_REPEAT_COUNT, (USE_NANOSECS ? "Yes" : "No"));
		System.out.printf("\nPerforming %d runs of four different tests, estimated time until completion: %d seconds.\n", TEST_REPEAT_COUNT, 4 * TEST_REPEAT_COUNT);
		for (int i = 0; i < TEST_REPEAT_COUNT; i++)
		{
			totalSortedInsertInteger += insertSorted(TargetType.INTEGER);
			System.out.print('.');
			totalSortedInsertString += insertSorted(TargetType.STRING);
			System.out.print('.');
			totalSortedMergeInteger += mergeSorted(TargetType.INTEGER);
			System.out.print('.');
			totalSortedMergeString += mergeSorted(TargetType.STRING);
			System.out.print('.');
		}
		System.out.print('\n');
		
		int averageSortedInsertInteger = totalSortedInsertInteger/TEST_REPEAT_COUNT;
		int averageSortedInsertString = totalSortedInsertString/TEST_REPEAT_COUNT;
		int averageSortedMergeInteger = totalSortedMergeInteger/TEST_REPEAT_COUNT;
		int averageSortedMergeString = totalSortedMergeString/TEST_REPEAT_COUNT;
		
		int sorts1 = averageSortedInsertInteger/TEST_ARRAY_LENGTH;
		int sorts2 = averageSortedInsertString/TEST_ARRAY_LENGTH;
		int sorts3 = averageSortedMergeInteger/TEST_ARRAY_LENGTH;
		int sorts4 = averageSortedMergeString/TEST_ARRAY_LENGTH;
		System.out.print("Tests done!\n\n");
		
		System.out.println("Estimated values representing amount of sorted elements per second, per approach:");
		System.out.printf("Insert, int: %d (~%d sorts/s)\n", averageSortedInsertInteger, sorts1);
		System.out.printf("Insert, string: %d (~%d sorts/s)\n", averageSortedInsertString, sorts2);
		System.out.printf("Merge, int: %d (~%d sorts/s)\n", averageSortedMergeInteger, sorts3);
		System.out.printf("Merge, string: %d (~%d sorts/s)\n", averageSortedMergeString, sorts4);
	
	}
	
	private static int insertSorted(final TargetType type)
	{
		return testSortMethod(type,
				(int[] arr) 
				-> SortingAlgorithms.insertSort(arr),
				(String[] arr, Comparator<String> c) 
				-> SortingAlgorithms.insertSort(arr, c)
				);
	}
	
	private static int mergeSorted(final TargetType type)
	{
		return testSortMethod(type,
				(int[] arr) 
				-> SortingAlgorithms.mergeSort(arr),
				(String[] arr, Comparator<String> c) 
				-> SortingAlgorithms.mergeSort(arr, c)
				);
	}
	
	private interface IntegerSortFunction {
		public void sort(int[] arr);
	}
	private interface StringSortFunction {
		public void sort(String[] arr, Comparator<String> comparator);
	}
	
	/**
	 * Boilerplate function for taking and executing sorting by taking sorting
	 * functions as parameters, then feeding them with appropriate values. 
	 * Also features a small feature that adjusts the result if there was an 
	 * excess difference between the time limit (1 second) and time elapsed during execution.
	 * @param type
	 * @param integerSort
	 * @param stringSort
	 * @return
	 */
	private static int testSortMethod(
			final TargetType type,
			final IntegerSortFunction integerSort,
			final StringSortFunction stringSort)
	{
		int sorted = 0;
		long elapsed = 0;
		long startTimestamp = getCurrentTime();
		while (AVERAGE_TIME_LIMIT > elapsed)
		{
			if (type == TargetType.INTEGER)
			{
				for (int[] arr : integerArrays)
				{
					if (AVERAGE_TIME_LIMIT <= getCurrentTime() - startTimestamp)
						break;
					integerSort.sort(arr);
					sorted += TEST_ARRAY_LENGTH;
				}
			}
			else if (type == TargetType.STRING)
			{
				for (String[] arr : stringArrays)
				{
					if (AVERAGE_TIME_LIMIT <= getCurrentTime() - startTimestamp)
						break;
					stringSort.sort(arr, TEST_STRING_COMPARATOR);
					sorted += TEST_ARRAY_LENGTH;
				}
			}
			else
				break;
			elapsed = getCurrentTime() - startTimestamp;
		}
		
		double total = elapsed;
		double excessFactor = (total)/((double)AVERAGE_TIME_LIMIT);
		
		return (int) (sorted/excessFactor);
	}
	
	private static long getCurrentTime()
	{
		return (USE_NANOSECS ? System.nanoTime() : System.currentTimeMillis());
	}
	
	private static void prepareArrays()
	{
		Random rng = new Random();
		integerArrays = new int[TEST_ARRAY_AMOUNT][TEST_ARRAY_LENGTH];
		stringArrays = new String[TEST_ARRAY_AMOUNT][TEST_ARRAY_LENGTH];
		
		for (int[] arr : integerArrays)
			for(int i = 0; i < TEST_ARRAY_LENGTH; i++)
				arr[i] = rng.nextInt(TEST_ARRAY_LENGTH * TEST_INTEGER_FACTOR);
		
		for (String[] arr : stringArrays)
			for(int i = 0; i < TEST_ARRAY_LENGTH; i++)
				arr[i] = makeRandomString(rng, TEST_STRING_LENGTH);
	}
	
	private static String makeRandomString(Random rng, int length)
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < length; i++)
			builder.append(REFERENCE_STRING.charAt(rng.nextInt(REFERENCE_STRING.length())));
		
		return builder.toString();
	}
}
