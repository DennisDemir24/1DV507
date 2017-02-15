package ml224ec_assign3;

import java.util.Scanner;

public class EuclideanMain {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int[] integers = new int[2];
		
		System.out.println("Please input two integers: ");
		
		for (int i = 0; i < integers.length; i++)
		{
			boolean vaild = false;
			while (!vaild)
			{
				try {
					System.out.printf("%d > ", i + 1);
					integers[i] = input.nextInt();
					vaild = true; // the code will never reach here if an exception is thrown in the previous line
				}
				catch (Exception e) {
					input.next(); // flush the input to not hang on this loop
					System.out.println("It has to be an integer, please try again.");
				}
			}
		}
		
		input.close();
		
		System.out.printf("\nThe greatest common divisor of %d and %d is %d."
				, integers[0], integers[1], GCD(integers[0],integers[1]));
	}
	
	// taken from my own solution for Fractals in 1DV506
	private static int GCD(int a, int b)
	{
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

}
