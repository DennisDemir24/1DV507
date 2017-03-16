package ml224ec_assign4.binheap;

public class WorkMain {
	
	private static final Task[] tasks = new Task[]{
		new WorkTask(10, "Procastinate: You are having too much fun!"),
		new WorkTask(5, "Do the laundry"),
		new WorkTask(5, "Go shopping"),
		new WorkTask(5, "Go clean the room"),
		new WorkTask(9, "Do assignment"),
		new WorkTask(20, "Make dinner: You are hungry, time to cook some grub!"),
		new SleepTask()
	};
	
	public static void main(String[] args) {
		
		PriorityQueue<Task> taskQueue = new ArrayPriorityQueue<Task>();
		//PriorityQueue<Task> taskQueue = new BinaryHeapQueue<Task>();
		
		for (Task task : tasks)
			taskQueue.insert(task);
		
		System.out.println("How busy will I be today?");
		System.out.printf(" - 'You got %d tasks to do today.'\n", taskQueue.size());
		
		Task task = new WorkTask(9, "Do assignment");
		System.out.printf("\nDoes it include '%s', with the priority %d?\n", task.getDescription(), task.priority());
		System.out.printf("- '%s.'\n", (taskQueue.contains(task) ? "Yes" : "No"));
		
		Task mit = taskQueue.peekHighest(); // most important task
		System.out.println("\nWhat is the most important task today?");
		System.out.printf("- \"%s\" - with the priority %d.\n", mit.getDescription(), mit.priority());
		
		System.out.println("\nWhat is the today's task list - starting with priority and the description?");
		while (!taskQueue.isEmpty())
		{
			Task t = taskQueue.pullHighest();
			System.out.printf("%d - \"%s\"\n", t.priority(), t.getDescription());
		}
	}

}
