package ml224ec_assign4.time;

import java.util.ArrayList;
import java.util.List;

public class StringConcatMain {

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
	
	static char[] referenceString = "abcdefghijklmnopqrstuvwxzy".toCharArray();
	
	static List<String> shortStrings;
	static List<String> longStrings;
	
	static long timerStartTimeStamp;
	static long timerLastTimeStamp;
	

	public static void main(String[] args)
	{
		prepareStrings();
		
		int totalConcatsShortOp = 0;
		int totalConcatsLongOp = 0;
		int totalConcatsShortBuilder = 0;
		int totalConcatsLongBuilder = 0;
		
		System.out.printf("Perfomring %d runs of four different tests, estimated time until completion: %d seconds.\n", TEST_REPEAT_COUNT, 4 * TEST_REPEAT_COUNT);
		for (int i = 0; i < TEST_REPEAT_COUNT; i++)
		{
			totalConcatsShortOp += operatorConcats(StringLengthMode.SHORT);
			System.out.print('.');
			totalConcatsLongOp += operatorConcats(StringLengthMode.LONG);
			System.out.print('.');
			totalConcatsShortBuilder += builderConcats(StringLengthMode.SHORT);
			System.out.print('.');
			totalConcatsLongBuilder += builderConcats(StringLengthMode.LONG);
			System.out.print('.');
		}
		System.out.print('\n');
		
		int averageConcatsShortOp = totalConcatsShortOp/TEST_REPEAT_COUNT;
		int averageConcatsLongOp = totalConcatsLongOp/TEST_REPEAT_COUNT;
		int averageConcatsShortBuilder = totalConcatsShortBuilder/TEST_REPEAT_COUNT;
		int averageConcatsLongBuilder = totalConcatsLongBuilder/TEST_REPEAT_COUNT;
		
		System.out.println("Tests done!\nAverage concats per second, per approach:");
		System.out.printf("Operator, short: %d concats/s\n", averageConcatsShortOp);
		System.out.printf("Operator, long: %d concats/s\n", averageConcatsLongOp);
		System.out.printf("Append (StringBuilder), short: %d concats/s\n", averageConcatsShortBuilder);
		System.out.printf("Append (StringBuilder), long: %d concats/s\n", averageConcatsLongBuilder);
	}
	
	/**
	 * Executes string concatenation using the plus operator. Running one concatenation per cycle.
	 * The cycle is repeated until at least one second has elapsed.
	 * @param mode
	 * @return Amount of concatenations done within an estimate of one second
	 */
	private static int operatorConcats(StringLengthMode mode)
	{
		String receiver = "";
		String str = getList(mode).get(0);
		
		timerStartTimeStamp = getCurrentTime();
		while (AVERAGE_TIME_LIMIT > getCurrentTime() - timerStartTimeStamp)
			receiver += str;
		
		return receiver.length()/str.length();
	}
	
	/**
	 * Executes string concatenation using Stringbuilder with a bad case approach; appending a string and then building it in one cycle.
	 * Repeats the cycle until at least one second has elapsed.
	 * @param mode
	 * @return Amount of concatenations done within an estimate of one second
	 */
	private static int builderConcats(StringLengthMode mode)
	{
		StringBuilder builder = new StringBuilder();
		String str = getList(mode).get(0);
		
		int concats = 0;
		timerStartTimeStamp = getCurrentTime();
		while (AVERAGE_TIME_LIMIT > getCurrentTime() - timerStartTimeStamp)
		{
			builder.append(str);
			builder.toString();
			concats++;
		}
		
		return concats;
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
