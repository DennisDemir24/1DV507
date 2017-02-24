package ml224ec_assign3.histogram_revisited;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Histogram {

	private static final int LIMIT_LOWER = 1;
	private static final int INTERVAL_RANGE = 10;
	//private static final int INTERVAL_SORT_COUNT = 10;
	
	/**
	 * Modified utility copy of Histogram from ml224ec_assign1,
	 * made to only return a list of lists containing numbers in the intervals (n-10) + 1 -> n,
	 * where n is x*10. Keep in mind that n = 0 gives you all [unaccounted] numbers that are below 1.
	 * <br><br>
	 * Example:<br>
	 * At n = 0, you get numbers that are outside any intervals given at <code>n > 0</code><br>
	 * At n = 1, you have numbers found in the interval 1 -> 10<br>
	 * At n = 2, you have numbers found in the interval 11 -> 20<br>
	 * And etcera.
	 * <br><br>
	 * This function throws <code>IOException</code> whenever an error would occur.
	 * @param file
	 * @return <code>List</code> containing <code>List&ltInteger&gt</code>s, the first list contains all numbers under 1, meanwhile other lists contains numbers as described in documentation.
	 * @throws IOException
	 */
	public static List<List<Integer>> readFromFile(String file) throws IOException
	{	
		Path path = Paths.get(file);
		
		List<String> buffer = Files.readAllLines(path);
		
		List<List<Integer>> frequency_defines = new ArrayList<List<Integer>>();
		
		// stats
		for (int i = 0; i < buffer.size(); i++)
		{
			int N = 0;
			int I = Integer.parseInt(buffer.get(i)); // turn string into int
			
			if (I >= LIMIT_LOWER)
			{
				int m = (I - 1); // required for X1 - (X+1)0 ranges, eg. 11 - 20
				int n = m - m%INTERVAL_RANGE;
				N = n/INTERVAL_RANGE + 1; // reduce the selected integer into an index
			}
			
			// Expand..
			int length = frequency_defines.size();
			int diff = N - length + 1; // offset by one for intervals
			
			if (diff > 0) // ..if the location at selected index does not exist
				for (int a = 0; a < diff; a++) // for every non-existent slot
					frequency_defines.add(new ArrayList<Integer>()); // reserve a slot
			// end expand
			
			frequency_defines.get(N).add(I);
		}
		
		return frequency_defines;
	}	
}
