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
	
	// Algothrim based on https://gist.github.com/codeblocks/898596
	// This comment below is copied directly from the source file provided above
	/*
	 * Divide  : Divide the n-element array into two n/2-element
	 *           sub-arrays
	 * Conquer : Sort the two sub-arrays recursively using
	 *           merge sort
	 * Combine : Merge the two sorted subsequences to form the
	 *           sorted array
	 */
	// These words will be 
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
			
			// (Divide & Conquer)
			// recursive part 
			mergeSort(in, start, middle); // left hand part
			mergeSort(in, middle + 1, end); // right hand part
			
			// (Combine)
			// do the actual sorting 
			merge(in, start, middle, end);
		}
	}
	
	private static void merge(int[] in, int start, int middle, int end)
	{	
		int[] data = in.clone();// work copy
		int lhi = start;		// left hand index
		int rhi = (middle + 1);	// right hand index
		int k = start;			// actual data index
		
		while ((lhi <= middle || rhi <= end) && k < end)
		{
			int lhv = 0; // left hand value
			int rhv = 0; // right hand value
			
			if (lhi <= middle)
				lhv = data[lhi];
			else
				lhv = data[rhi+1]; // use the right hand instead
			
			if (rhi <= end)
				rhv = data[rhi];
			else
				rhv = data[lhi+1]; // use the left hand instead
			
			if (lhv > rhv) // is left hand [value] greater than right hand [value]?
			{
				in[k] = rhv; 		// lhv is winner
				in[k + 1] = lhv; 	// put the winner at the space to right, it will be investigated at the next iteration anyway
				rhi++;
			}
			else // left hand value is weaker to right hand value - rhv is winner
			{
				in[k] = lhv;		// rhv is winner
				in[k + 1] = rhv; 	// same here
				lhi++;
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
			
			// (Divide & Conquer)
			// recursive part 
			mergeSort(in, c, start, middle); // left hand part
			mergeSort(in, c, middle + 1, end); // right hand part
			
			// (Combine)
			// do the actual sorting 
			merge(in, c, start, middle, end);
		}
	}
	
	private static void merge(String[] in, Comparator<String> c, int start, int middle, int end)
	{	
		String[] data = in.clone();// work copy
		int lhi = start;		// left hand index
		int rhi = (middle + 1);	// right hand index
		int k = start;			// actual data index
		
		while ((lhi <= middle || rhi <= end) && k < end)
		{
			String lhv = ""; // left hand value
			String rhv = ""; // right hand value
			
			if (lhi <= middle) 
				lhv = data[lhi];
			else
				lhv = data[rhi+1]; // use the right hand instead
			
			if (rhi <= end)
				rhv = data[rhi];
			else
				rhv = data[lhi+1]; // use the left hand instead
			
			// is x more or less different to y according to Comparator c?
			// 0 = equal
			if (c.compare(lhv, rhv) > 0) 
			{							
				in[k] = rhv; 		// lhv is winner
				in[k + 1] = lhv; 	// put the winner at the space to right, it will be investigated at the next iteration anyway
				rhi++;
			}
			else // left hand value is weaker to right hand value - rhv is winner
			{
				in[k] = lhv;		// rhv is winner
				in[k + 1] = rhv; 	// same here
				lhi++;
			}
			k++;
		}
	}
}
