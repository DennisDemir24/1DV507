package ml224ec_assign4.binheap;

public class WorkTask extends Task {

	private interface InternalTask
	{
		void doTask();
	}
	
	private int priority;
	private String description;
	
	private InternalTask lambda;
	
	public WorkTask(int priority, String description)
	{
		this.priority = priority;
		this.description = description;
	}
	
	public WorkTask(int priority, String description, InternalTask lambda)
	{
		this(priority, description);
		
		this.lambda = lambda;
	}
	
	@Override
	public int compareTo(Task other) {
		return this.priority() - other.priority();
	}

	@Override
	public int priority() {
		return priority;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void doTask() {
		if (lambda != null)
			lambda.doTask();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof WorkTask)
		{
			return equals((WorkTask) other);
		}
		return false;
	}
	
	private boolean equals(WorkTask other) {
		return 
				this.priority == other.priority &&
				this.description == other.description &&
				this.lambda == other.lambda;
	}
	
	public String toString()
	{
		return String.format("(%d, '%s')", priority(), description);
	}
}
