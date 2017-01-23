package ml224ec_assign1;

public class SumMain {

	private static final int[] NUMBERS = new int[]{3,5,10};
	
	public static void main(String[] args) {
		
		// Why recursive?
		
		// Recursive solution to e.g. summation of a number to the other
		// is fast and easy to implement compared to iterative 
		// implementations, as you've to copy and paste them. Meanwhile
		// you can reuse recursive implementations with ease.
		
		// It is also cleaner and therefore easier to maintain. Reducing
		// the chance of the next guy, who has to maintain your code,
		// seeks you down and brutally murder you for sending him to a 
		// personal hell.

		for (int i : NUMBERS)
		{
			System.out.printf("sum(N) for N = %d: %d\n", i, sum(i));
			System.out.printf("sum(1, N/2) + sum(N/2 + 1, N) for N = %d: %d\n\n", i, sum(1, i/2) + sum(i/2 + 1, i));
		}
		
	}
	
	private static int sum(int n)
	{
		return sum(1,n);
	}
	
	private static int sum(int lower, int upper)
	{
		if (upper == lower)
			return upper;
		else
			return sum(lower, upper - 1) + upper;
	}
}
