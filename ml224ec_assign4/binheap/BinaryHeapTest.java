package ml224ec_assign4.binheap;

import java.util.Arrays;

import org.junit.Test;

/**
 * Quick simple Unit-Test class to both debug and test <code>BinaryIntHeap</code>
 * @author Martin Lyrå
 *
 */
public class BinaryHeapTest {

	private static final int TEST_INTEGERS[][] = {
			new int[]{-5,-4,5,4,-3,-2,3,2,1,-1,0},
			new int[]{0,1,2,3,4,5,6,7,8,9,10},
			new int[]{9,1,22,4,9,2,-4,-22,7,2,4,64,11,8,33}
	};

	@Test
	public void test() {
		
		for (int i = 0; i < TEST_INTEGERS.length; i++)
		{
			int[] integers = TEST_INTEGERS[i];
			System.out.printf("\n:: Test run %d ::\nUsing sample array:\n%s\n", i + 1, Arrays.toString(integers));
			BinaryIntHeap heap = new BinaryIntHeap();
			
			for (int integer : integers)
				heap.insert(integer);
			
			int expectedSize = integers.length;
			int actualSize = heap.size();
			
			System.out.printf("Size of sample array vs heap: %d vs %d\n", expectedSize, actualSize);
			
			// Expecting this to be printed out in max-min order (biggest first)
			while (!heap.isEmpty())
				System.out.print(heap.pullHighest() + " ");
			System.out.println();
		}	
	}

}
