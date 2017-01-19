package ml224ec_assign1;

import java.util.Scanner;

public class PascalMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		
		System.out.println("Pascal Triangle");
		System.out.print("Select row to calculate (positive integer): ");
		int in = input.nextInt();
		
		input.close();
		
		if (in < 0)
			return;
		
		// end input
		
		int[] pascal = pascalRow(in);
		
		int len = pascal.length;
		
		for (int i = 0; i < len; i++)
		{
			if (i == 0)
				System.out.print("[ ");
			System.out.print(pascal[i]);
			if (i < len - 1)
				System.out.print(", ");
			else
				System.out.print(" ]");
		}
		
	}
	
	public static int[] pascalRow(int n)
	{
		if (n < 0)
			return new int[0]; // You fool!
		else if (n == 0)
			return new int[]{1};
		else
		{
			int[] r = pascalRow(n-1);
			int[] row = new int[n+1];
			for (int i = 0; i < row.length; i++)
			{
				int a = (i < 1 ? 0 : r[i - 1]);
				int b = (i < r.length ? r[i] : 0);
				row[i] = a + b;
			}	
			return row;
		}
	}

}
