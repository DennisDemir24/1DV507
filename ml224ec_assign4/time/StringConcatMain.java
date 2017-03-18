package ml224ec_assign4.time;

import java.util.ArrayList;
import java.util.List;

public class StringConcatMain {

	/**
	 * This class breaks the class design for two reasons:
	 * <li>Internal nested class</li>
	 * <li>It is supposed to be a struct</li>
	 * @author Martin Lyrå
	 *
	 */
	private static class TestResult {
		
		public int concats;
		public int strlen;
		
		public TestResult(int concats, int strlen)
		{
			this.concats = concats;
			this.strlen = strlen;
		}
	}
	
	private static final boolean USE_NANOSECS = true;
	
	private static final long SECOND_IN_MS = 1000;
	private static final long SECOND_IN_NS = 1000000000; // 1 sec
	private static final long AVERAGE_TIME_LIMIT = ( USE_NANOSECS ? SECOND_IN_NS : SECOND_IN_MS ); // 1 sec
	
	private static final int TEST_REPEAT_COUNT = 5;
	
	private enum StringLengthMode
	{
		SHORT(1),
		LONG(80);
		
		public final int value;
		
		StringLengthMode(int len)
		{
			value = len;
		}
	}
	
	private static char[] referenceString = "abcdefghijklmnopqrstuvwxzy".toCharArray();
	
	private static List<String> shortStrings;
	private static List<String> longStrings;

	public static void main(String[] args)
	{
		prepareStrings();
		
		int totalConcatsShortOp = 0;
		int totalConcatsLongOp = 0;
		int totalConcatsShortBuilder = 0;
		int totalConcatsLongBuilder = 0;
		
		int totalStringLength1 = 0;
		int totalStringLength2 = 0;
		int totalStringLength3 = 0;
		int totalStringLength4 = 0;
		
		System.out.printf("Performing %d runs of four different tests, estimated time until completion: %d seconds.\n", TEST_REPEAT_COUNT, 4 * TEST_REPEAT_COUNT);
		for (int i = 0; i < TEST_REPEAT_COUNT; i++)
		{
			TestResult t1 = operatorConcats(StringLengthMode.SHORT);
			totalConcatsShortOp += t1.concats;
			totalStringLength1 += t1.strlen;
			System.out.print('.');
			TestResult t2 =	operatorConcats(StringLengthMode.LONG);
			totalConcatsLongOp += t2.concats;
			totalStringLength2 += t2.strlen;
			System.out.print('.');
			TestResult t3 = builderConcats(StringLengthMode.SHORT);
			totalConcatsShortBuilder += t3.concats;
			totalStringLength3 += t3.strlen;
			System.out.print('.');
			TestResult t4 =	builderConcats(StringLengthMode.LONG);
			totalConcatsLongBuilder += t4.concats;
			totalStringLength4 += t4.strlen;
			System.out.print('.');
		}
		System.out.print('\n');
		
		int averageConcatsShortOp = totalConcatsShortOp/TEST_REPEAT_COUNT;
		int averageConcatsLongOp = totalConcatsLongOp/TEST_REPEAT_COUNT;
		int averageConcatsShortBuilder = totalConcatsShortBuilder/TEST_REPEAT_COUNT;
		int averageConcatsLongBuilder = totalConcatsLongBuilder/TEST_REPEAT_COUNT;
		
		int averageStringLength1 = totalStringLength1/TEST_REPEAT_COUNT;
		int averageStringLength2 = totalStringLength2/TEST_REPEAT_COUNT;
		int averageStringLength3 = totalStringLength3/TEST_REPEAT_COUNT;
		int averageStringLength4 = totalStringLength4/TEST_REPEAT_COUNT;
		
		System.out.println("Tests done!\nPer approach: Average concats per second, average final string length");
		System.out.printf("Operator, short: %d concats/s, %d chars\n", averageConcatsShortOp, averageStringLength1);
		System.out.printf("Operator, long: %d concats/s, %d chars\n", averageConcatsLongOp, averageStringLength2);
		System.out.printf("Append (StringBuilder), short: %d concats/s, %d chars\n", averageConcatsShortBuilder, averageStringLength3);
		System.out.printf("Append (StringBuilder), long: %d concats/s, %d chars\n", averageConcatsLongBuilder, averageStringLength4);
	}
	
	/**
	 * Executes string concatenation using the plus operator. Running one concatenation per cycle.
	 * The cycle is repeated until at least one second has elapsed.
	 * @param mode
	 * @return Results of the test containing concatenations per second and the final string length
	 */
	private static TestResult operatorConcats(StringLengthMode mode)
	{
		String receiver = "";
		String str = getList(mode).get(0);
		
		long startTimeStamp = getCurrentTime();
		while (AVERAGE_TIME_LIMIT > getCurrentTime() - startTimeStamp)
			receiver += str;
		
		return new TestResult(receiver.length()/str.length(), receiver.length());
	}
	
	/**
	 * Executes string concatenation using StringBuilder with a bad case approach; appending a string and then building it in one cycle.
	 * Repeats the cycle until at least one second has elapsed.
	 * @param mode
	 * @return Results of the test containing concatenations per second and the final string length
	 */
	private static TestResult builderConcats(StringLengthMode mode)
	{
		StringBuilder builder = new StringBuilder();
		String rec = "";
		String str = getList(mode).get(0);
		
		long startTimeStamp = getCurrentTime();
		while (AVERAGE_TIME_LIMIT > getCurrentTime() - startTimeStamp)
		{
			builder.append(str);
			rec = builder.toString();
		}
		
		return new TestResult(rec.length()/str.length(), rec.length());
	}
	
	private static long getCurrentTime()
	{
		return (USE_NANOSECS ? System.nanoTime() : System.currentTimeMillis());
	}
	
	/**
	 * Prepares short and long strings depending on given constant value for each StringLengthMode.
	 */
	private static void prepareStrings()
	{
		shortStrings = new ArrayList<String>();
		longStrings = new ArrayList<String>();
		for (char c : referenceString)
		{
			String shortStr = "";
			for (int i = 0; i < StringLengthMode.SHORT.value; i++)
				shortStr += c;
			shortStrings.add(shortStr);
			
			String longStr = "";
			for (int i = 0; i < StringLengthMode.LONG.value; i++)
			{
				if (i%10 == 9) // 10th number starting 0 as 1st
					c = Character.toUpperCase(c); // make the 10th character a big letter to aid inspection of output
				longStr += c;
			}
			longStrings.add(longStr);
		}
	}
	
	/**
	 * Returns a list of strings for appropriate StringLengthMode
	 * @param mode
	 * @return
	 */
	private static List<String> getList(StringLengthMode mode)
	{
		switch (mode)
		{
		case SHORT:
			return shortStrings;
		case LONG:
			return longStrings;
		default:
			return null;
		}
	}
}
