package ml224ec_assign2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ml224ec_assign2.queue.ArrayQueue;
import ml224ec_assign2.queue.LinkedQueue;
import ml224ec_assign2.queue.Queue;

public class QueueUnitTest {
	
	private Class targetClass;
	
	private final Object[][] VALUE_SAMPLES = new Object[4][];
	private final Queue[] QUEUE_SAMPLES = new Queue[4];
	
	public QueueUnitTest()
	{
		targetClass = LinkedQueue.class;
		//targetClass = ArrayQueue.class; // change this to <ClassName>.class to test an other class
	}
	
	/*
	public QueueUnitTest(Class<Queue> targetClass) {
		this.targetClass = targetClass;
	}
	*/
	
	public void setTestClass (Class c)
	{
		targetClass = c;
	}
	
	@Before
	public void setUpSamples()
	{
		VALUE_SAMPLES[0] = new Object[0];
		VALUE_SAMPLES[1] = new Object[] {1};
		VALUE_SAMPLES[2] = new Object[] {1,2,3,4};
		VALUE_SAMPLES[3] = new Object[] {1,2,3,4,5,6,7,8};
	
		for (int i = 0; i < QUEUE_SAMPLES.length || i < VALUE_SAMPLES.length; i++)
			QUEUE_SAMPLES[i] = build(VALUE_SAMPLES[i]);
	}
	
	@Test
	public void testSize() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			int expectedSize = VALUE_SAMPLES[i].length;
			int resultSize = q.size();
			
			if (resultSize != expectedSize)
				fail(
						String.format(
								"Sample %d: Expected size %d, got %d from",
								i, expectedSize, resultSize)
						);
		}
		
	}

	@Test
	public void testIsEmpty() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
			depopulateAll(q); // removes all values
			
			int testSize = q.size();
			
			if (!q.isEmpty())
				fail(
						String.format(
								"Sample %d: Despite removal of all data, sample says it is not empty! (Size: %d)",
								i, testSize)
						);
		}
	}

	@Test
	public void testEnqueue() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
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
	}

	@Test
	public void testDequeue() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
			Object previousFirst = q.first();
			
			for (Object o : VALUE_SAMPLES[i])
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
	}

	@Test
	public void testFirst() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
			Object previousFirst = q.first();
			
			for (Object o : VALUE_SAMPLES[i])
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
	}
		
	@Test
	public void testLast() {
	
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
			Object beforeLast = q.last();
			
			depopulate(q, 4);
			
			if (q.size() != 0 && beforeLast != null && !beforeLast.equals(q.last()))
				fail(
						String.format("Sample %d: Last element stays in the queue, but as unexpected, it has changed",
								i 
								)
						);
		}
	
	}

	@Test
	public void testIterator() {
		
		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			
			Object[] expectedValues = VALUE_SAMPLES[i];
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
	}

	@Test
	public void testToString() {

		for (int i = 0; i < QUEUE_SAMPLES.length; i++)
		{
			Queue q = QUEUE_SAMPLES[i];
			Object[] values = VALUE_SAMPLES[i];
			
			String expected = arrayToString(values);
			String result = q.toString(); // actual method to be tested
			
			if (expected.compareTo(result) > 0)
				fail(
						String.format("Sample %d: String mismatch.\nExpected: %s\nGot: %s",
								i, expected, result)
						);
		}
	}
	
	private Queue instantize()
	{
		try {
			return (Queue)targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Queue build(Object[] items)
	{
		Queue q = instantize();
		fill(q, items);
		return q;
	}
	
	private void fill(Queue q, Object[] objects)
	{
		for (Object o : objects)
			q.enqueue(o);
	}

	private void populate(Queue q, int amount)
	{
		for (int i = 0; i < amount; i++)
			q.enqueue(100 + i);
	}
	
	private void depopulate(Queue q, int amount)
	{
		for (int i = 0; i < amount && !q.isEmpty(); i++)
			q.dequeue();
	}
	
	private void depopulateAll(Queue q)
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
