package ml224ec_assign3;

import java.util.Comparator;

public class SortingAlgorithms {

	public static int[] insertSort(int[] in)
	{
		int[] out = in.clone();
		
		for (int i = 1; i < in.length; i++)
		{
			int x = out[i];
			int j = i - 1;
			
			while (j >= 0 && out[j] > x)
			{
				out[j+1] = out[j];
				j--;
			}
			out[j+1] = x;
		}
		
		return out;
	}
	
	public static int[] mergeSort(int[] in)
	{
		int[] out = in.clone();

		mergeSort(out, 0, out.length - 1); // recursive
		
		return out;
	}
	
	// recursive
	private static void mergeSort(int[] in, int start, int end)
	{
		int middle;
		if (start < end)
		{
			middle = (start + end)/2;
			
			// recursive part
			mergeSort(in, start, middle);
			mergeSort(in, middle + 1, end);
			
			// do the actual sorting
			merge(in, start, middle, end);
		}
	}
	
	private static void merge(int[] in, int start, int middle, int end)
	{	
		int[] data = in.clone();// work copy
		int i = start;			// left hand index
		int j = (middle + 1);	// right hand index
		int k = start;			// actual data index
		
		while ((i <= middle || j <= end) && k < end)
		{
			int x = 0; // left hand value
			int y = 0; // right hand value
			
			if (i <= middle)
				x = data[i];
			else
				x = data[j+1]; // use the right hand instead
			
			if (j <= end)
				y = data[j];
			else
				y = data[i+1]; // use the left hand instead
			
			if (x > y) // is left hand [value] greater than right hand [value]?
			{
				in[k] = y;
				in[k + 1] = x; // put the loser at the space to right, it will be replaced at the next iteration anyway
				j++;
			}
			else
			{
				in[k] = x;
				in[k + 1] = y;
				i++;
			}
			k++;
		}
	}
	
	public static String[] insertSort(String[] in, Comparator<String> c)
	{
		String[] out = in.clone();
		
		for (int i = 1; i < in.length; i++)
		{
			String x = out[i];
			int j = i - 1;
			
			while (j >= 0 && c.compare(out[j],x) > 0)
			{
				out[j+1] = out[j];
				j--;
			}
			out[j+1] = x;
		}
		
		return out;
	}
	
	public static String[] mergeSort(String[] in, Comparator<String> c)
	{
		String[] out = in.clone();

		mergeSort(out, c, 0, out.length - 1); // recursive
		
		return out;
	}
	
	// recursive
	private static void mergeSort(String[] in, Comparator<String> c, int start, int end)
	{
		int middle;
		if (start < end)
		{
			middle = (start + end)/2;
			
			// recursive part
			mergeSort(in, c, start, middle);
			mergeSort(in, c, middle + 1, end);
			
			// do the actual sorting
			merge(in, c, start, middle, end);
		}
	}
	
	private static void merge(String[] in, Comparator<String> c, int start, int middle, int end)
	{	
		String[] data = in.clone();// work copy
		int i = start;			// left hand index
		int j = (middle + 1);	// right hand index
		int k = start;			// actual data index
		
		while ((i <= middle || j <= end) && k < end)
		{
			String x = ""; // left hand value
			String y = ""; // right hand value
			
			if (i <= middle)
				x = data[i];
			else
				x = data[j+1]; // use the right hand instead
			
			if (j <= end)
				y = data[j];
			else
				y = data[i+1]; // use the left hand instead
			
			if (c.compare(x, y) > 0) 	// is x more or less different to y according to Comparator c?
			{							// 0 = equal
				in[k] = y;
				in[k + 1] = x; // put the loser at the space to right, it will be replaced at the next iteration anyway
				j++;
			}
			else
			{
				in[k] = x;
				in[k + 1] = y;
				i++;
			}
			k++;
		}
	}
}
