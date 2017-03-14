package ml224ec_assign4.queue_generic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class QueueUnitTest {
	
	private final Class[] QUEUE_TEST_CLASSES = {
			ArrayQueue.class,
			LinkedQueue.class
	};
	private final Class[] OBJECT_TEST_CLASSES = {
			Integer.class,
			String.class
	};
	
	// unconventional styling due to otherwise limited readibility
	
	// Holds all value samples for Object class
	private final Map<Class<Object>, Object[][]>  
	VALUE_MAP 
		= new HashMap<Class<Object>, Object[][]>();
	
	// Holds all the queues for Queue class
	private final Map<Class<Queue<Object>>, Map<Class<Object>, List<Queue<Object>>> > 
	QUEUE_MAP 
		= 	new HashMap<Class<Queue<Object>>, Map<Class<Object>, List<Queue<Object>>> >();
	
	@Before
	public void setUpSamples()
	{
		for (Class<Queue<Object>> queueClass : QUEUE_TEST_CLASSES)
		{
			Map<Class<Object>, List<Queue<Object>>> objectQueueList = new HashMap<Class<Object>, List<Queue<Object>>>();
			for (Class<Object> objectClass : OBJECT_TEST_CLASSES)
			{
				VALUE_MAP.put(objectClass, new Object[][] {
					new Object[]{},
					new Object[]{1,2,3,4},
					new Object[]{1,2,3,4,5,6,7,8},
					new Object[]{1,2,3,4,5,6,7,8,9,10,11,12}
				});
				
				List<Queue<Object>> queueList = new ArrayList<Queue<Object>>();
				for (Object[] items : VALUE_MAP.get(objectClass))
				{
					queueList.add(build(queueClass, items));
				}
				objectQueueList.put(objectClass, queueList);
			}
			QUEUE_MAP.put(queueClass, objectQueueList);
		}
	}
	
	@Test
	public void testSize() {
		
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
				int expectedSize = valueSamples[i].length;
				int resultSize = q.size();
				
				if (resultSize != expectedSize)
					fail(
							String.format(
									"Sample %d: Expected size %d, got %d from",
									i, expectedSize, resultSize)
							);
			}
		});
	}

	@Test
	public void testIsEmpty() {
		
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
			
				depopulateAll(q); // removes all values
				
				int testSize = q.size();
				
				if (!q.isEmpty())
					fail(
							String.format(
									"Sample %d: Despite removal of all data, sample says it is not empty! (Size: %d)",
									i, testSize)
							);
			}
		});
	}

	@Test
	public void testEnqueue() {
		
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
			
				Object beforeFirst = q.first();
				Object beforeLast = q.last();
				int beforeSize = q.size();
				
				populate(q,4); // add four new integers 
				
				Object afterFirst = q.first();
				Object afterLast = q.last();
				int afterSize = q.size();
				
				boolean fail = false;
				String message = String.format("Sample %d: Failed the enqueue test, following error(s) have been observed:\n", i);
				
				if (beforeSize == 0)
				{
					if (	beforeFirst == null &&
							afterFirst == null )
					{
						message += String.format(
								"First element failed to change after populating empty queue. (%s vs %s).\n",
								beforeFirst, afterFirst
								);
						fail = true;
					}
					if (	beforeLast == null &&
							afterLast == null )
					{
						message += String.format(
								"Last element failed to change after populating empty queue. (%s vs %s).\n",
								beforeLast, afterLast
								);
						fail = true;
					}
				} // end size == 0
				else // size > 0
				{
					if (	beforeFirst == null ||
							afterFirst == null || 
							!beforeFirst.equals(afterFirst))
					{
						message += String.format(
								"First element failed to remain the same or is null (%s vs %s).\n",
								beforeFirst, afterFirst
								);
						fail = true;
					}
					if (	beforeLast == null ||
							afterLast == null || 
							beforeLast.equals(afterLast))
					{
						message += String.format(
								"Last element failed to change or is null (%s vs %s).\n",
								beforeLast, afterLast
								);
						fail = true;
					}
				} // end size > 0
				if ( 	beforeSize == afterSize ||
						beforeSize > afterSize  ) 
				{
					message += String.format(
							"Size failed to increment, either it remained unchanged, or has shrunk (%d vs %d). ",
							beforeSize, afterSize);
					fail = true;
				}
				
				if (fail)
					fail(message);
			}
		});
	}

	@Test
	public void testDequeue() {
		
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
			
				Object previousFirst = q.first();
				
				for (Object o : valueSamples[i])
				{
					Object obj = q.dequeue();
					if (!o.equals(obj))
						fail(
								String.format(
										"Sample %d: Queue data does not match up with expected value sample. Expected %s, got %s.",
										i, o, obj
										)
								);
					else if (previousFirst.equals(q.first()))
						fail(
								String.format(
										"Sample %d: First element remained unchanged.\nPrevious: %s\nCurrent: %s",
										i, previousFirst, q.first()
										)
								);
					else
						previousFirst = q.first();
				}		
			}
		});
	}

	@Test
	public void testFirst() {
		
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
			
				Object previousFirst = q.first();
				
				for (Object o : valueSamples[i])
				{
					Object obj = q.dequeue();
					Object currentFirst = q.first();
					if (!o.equals(obj))
						fail(
								String.format(
										"Sample %d: Queue data does not match up with expected value sample. Expected %s, got %s.",
										i, o, obj
										)
								);
					else if (previousFirst.equals(currentFirst))
						fail(
								String.format(
										"Sample %d: First element remained unchanged.\nPrevious: %s\nCurrent: %s",
										i, previousFirst, currentFirst
										)
								);
					else
						previousFirst = currentFirst;
				}		
			}
		});
	}
		
	@Test
	public void testLast() {
	
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
			
				Object beforeLast = q.last();
				
				depopulate(q, 4);
				
				if (q.size() != 0 && beforeLast != null && !beforeLast.equals(q.last()))
					fail(
							String.format("Sample %d: Last element stays in the queue, but as unexpected, it has changed",
									i 
									)
							);
			}
		});
	}

	@Test
	public void testIterator() {
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
				Object[] expectedValues = valueSamples[i];
				
				int expectedSize = expectedValues.length;
				
				Iterator<Object> it = q.iterator(); // actual method to be tested
				
				if (expectedSize > 0 && !it.hasNext())
				{
					if (it.next() != null) // does the iterator have data?
						fail (
								String.format("Sample %d: (%s) Iterator has data, but its 'hasNext' says it has nothing.",
										i, it.getClass().getName())
								);
					else //
						fail (
								String.format("Sample %d: (%s) Expected data in iterator, but iterator has nothing.",
										i, it.getClass().getName())
								);
				}
				
				for (int j = 0; j < expectedSize; j++)
				{
					Object obj = it.next();
					if (!expectedValues[j].equals(obj)) // fails if the returned data is different from the value sample
						fail (
								String.format("Sample %d: Iterator data does not match up with value sample! Expected %s, got %s.",
										i, expectedValues[j], obj )
								);
				}	
			}
		});
	}

	@Test
	public void testToString() {
		foreach((List<Queue<Object>> queueSamples, Object[][] valueSamples) -> {
			
			for (int i = 0; i < queueSamples.size(); i++)
			{
				Queue<Object> q = queueSamples.get(i);
				Object[] values = valueSamples[i];
				
				String expected = arrayToString(values);
				String result = q.toString(); // actual method to be tested
				
				if (expected.compareTo(result) > 0)
					fail(
							String.format("Sample %d: String mismatch.\nExpected: %s\nGot: %s",
									i, expected, result)
							);
			}
			
		});
	}
	
	// Helper functions below
	
	// Interface for boilerplate lambdas
	private interface TestFunction<T1, T2>
	{
		public void call(T1 t1, T2 t2);
	}
	
	/**
	 * Boilerplate function to help iterating through test values and samples as per interface class
	 * and type class. Also helps pinpointing which class and for what type the test has failed.
	 * <br>
	 * Where the actual test function is a lambda put in as a parameter.
	 * @param testFunction
	 */
	private void foreach(TestFunction<List<Queue<Object>>, Object[][]> testFunction)
	{
		for (Class<Queue<Object>> queueClassKey : QUEUE_MAP.keySet())
		{
			Map<Class<Object>, List<Queue<Object>>> value = QUEUE_MAP.get(queueClassKey);
			for (Class<Object> objectClassKey : value.keySet())
			{
				List<Queue<Object>> queues = value.get(objectClassKey);
				Object[][] valueSamples = VALUE_MAP.get(objectClassKey);
				
				try {					
					testFunction.call(queues, valueSamples);
				}
				catch (Throwable t)
				{
					String message = 
							String.format("Test function failed for;\nQueue class: %s\nObject class: %s\nWith the cause:\n%s",
									queueClassKey.getSimpleName(),
									objectClassKey.getSimpleName(),
									t);
					fail(message);
				}
			}
		}
	}
	
	private Queue<Object> instantize(Class<Queue<Object>> targetClass)
	{
		try {
			return targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Queue<Object> build(Class<Queue<Object>> targetClass, Object[] items)
	{
		Queue<Object> q = instantize(targetClass);
		fill(q, items);
		return q;
	}
	
	private void fill(Queue<Object> q, Object[] objects)
	{
		for (Object o : objects)
			q.enqueue(o);
	}

	private void populate(Queue<Object> q, int amount)
	{
		for (int i = 0; i < amount; i++)
			q.enqueue(100 + i);
	}
	
	private void depopulate(Queue<Object> q, int amount)
	{
		for (int i = 0; i < amount && !q.isEmpty(); i++)
			q.dequeue();
	}
	
	private void depopulateAll(Queue<Object> q)
	{
		while (!q.isEmpty())
			q.dequeue();
	}
	
	private String arrayToString(Object[] arr)
	{
		String str = "[ ";
		
		int len = arr.length;
		for (int i = 0; i < len; i++)
			str += arr[i] + (i < len - 1 ? ", " : "");
		str += " ]";
		
		return str;
	}
}
