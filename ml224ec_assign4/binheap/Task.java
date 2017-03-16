package ml224ec_assign4.binheap;

public abstract class Task implements Comparable<Task> {
	
	@Override
	public int compareTo(Task other)
	{
		return this.priority() - other.priority();
	}
	
	/**
	 * Gets the priority of this task.
	 * @return
	 */
	abstract int priority();
	
	/**
	 * Gets this Task's description
	 * @return
	 */
	abstract String getDescription();
	
	/**
	 * Do the thing
	 */
	abstract void doTask();
}
