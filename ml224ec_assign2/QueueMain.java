package ml224ec_assign2;

import java.util.Iterator;

import ml224ec_assign2.queue.LinkedQueue;
import ml224ec_assign2.queue.Queue;

public class QueueMain {
	
	public static void main(String[] args) {

		Queue queue = new LinkedQueue();
		
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
	}

}
