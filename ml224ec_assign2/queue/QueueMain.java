package ml224ec_assign2.queue;

import java.util.Iterator;

public class QueueMain {
	
	private static final boolean DEBUG = true;
	private static final boolean USE_ARRAY = false;
	
	public static void main(String[] args) {

		Queue queue = (USE_ARRAY ? new ArrayQueue() : new LinkedQueue());
		
		queue.enqueue(400);
		queue.enqueue("Hello world!");
		queue.enqueue(666);
		queue.enqueue(Integer.MAX_VALUE);
		System.out.printf("%s\n\n", queue);
		
		System.out.printf("Current queue size: %d\nContents: \n", queue.size());
		
		Iterator<Object> it = queue.iterator();
		while (it.hasNext())
			System.out.println(it.next());
		
		System.out.println("\nDequeue-ing..");
		
		for (int i = 1; !queue.isEmpty(); i++)
			System.out.printf("%d: %s\n", i, queue.dequeue());
		System.out.printf("Current queue size: %d\n", queue.size());
		
		queue.enqueue(200);
		queue.enqueue(3000);
		queue.enqueue(40000);
		
		System.out.printf("\nThree elements (200, 3000, 40000) added..\nHead: %d\nTail: %d\n", queue.first(), queue.last());
		
		
		queue = (USE_ARRAY ? new ArrayQueue() : new LinkedQueue());
		
		queue.enqueue(1);
		
		Object o1 = queue.first();
		Object o2 = queue.dequeue();
		Object o3 = queue.first();
		
		System.out.printf("\nTesting 'first()':\nBefore: %s\nDequeue: %s\nAfter: %s", o1,o2,o3);
		
		
		if (DEBUG && USE_ARRAY)
		{
			queue.enqueue(330);
			queue.dequeue();
			queue.dequeue();
			
			System.out.printf("\nHead: %d\nTail: %d\n", queue.first(), queue.last());
			System.out.println(((ArrayQueue)queue).toDebugString()); // ArrayQueue-unique method
			
			for (int i = 0; i < 4; i++)
				queue.enqueue(11);
			
			queue.enqueue(1000);
			queue.enqueue(2000);
			
			System.out.println(queue);
			System.out.println(((ArrayQueue)queue).toDebugString());
			
			queue.enqueue(3000);
			
			System.out.println(queue);
			System.out.println(((ArrayQueue)queue).toDebugString());
			System.out.printf("\nHead: %d\nTail: %d\n", queue.first(), queue.last());
		}
	}

}
