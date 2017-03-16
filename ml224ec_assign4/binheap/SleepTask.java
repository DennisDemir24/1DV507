package ml224ec_assign4.binheap;

/**
 * A simple class to demonstrate simplicity of Task. 
 * For a more serious attempt, see WorkTask.
 * @author Martin Lyrå
 *
 */
public class SleepTask extends Task {
	
	@Override
	public int priority() {
		return 2;
	}

	@Override
	public String getDescription() {
		return "Go to sleep - ZzzzzzzzZzzzzzz";
	}

	@Override
	public void doTask() {
		return;
	}
}
