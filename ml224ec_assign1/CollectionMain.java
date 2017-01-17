package ml224ec_assign1;

public class CollectionMain {

	public static void main(String[] args) {
		testList();
		testStack();
	}

	private static void testList()
	{
		System.out.println("Testing ArrayIntList");
		ArrayIntList ail = new ArrayIntList();
		
		System.out.println("Adding 3x 100");
		
		ail.add(100);
		ail.add(100);
		ail.add(100);
		
		System.out.println(ail.toString());
		System.out.print('\n');
		System.out.println("Adding 200 at index 0, 555 and 444 at 3");
		
		ail.addAt(200, 0);
		ail.addAt(555, 3);
		ail.addAt(444, 3);
		
		System.out.println(ail.toString());
		System.out.print('\n');
		System.out.println("Removing index 0");
		
		ail.remove(0);
		
		System.out.println(ail.toString());
		System.out.print('\n');
		
		System.out.printf("get(1): %d\n", ail.get(1));
		System.out.printf("get(3): %d\n", ail.get(3));
		
		System.out.printf("indexOf(444): %d\n", ail.indexOf(444));
		
		System.out.print('\n');
	}
	
	private static void testStack()
	{
		System.out.println("Testing ArrayIntStack");
		ArrayIntStack ais = new ArrayIntStack();
		
		System.out.println("Pushing 300, 200, and 100");
		
		ais.push(300);
		ais.push(200);
		ais.push(100);
		
		System.out.println(ais.toString());
		
		System.out.printf("pop(): %d\n", ais.pop());
		System.out.printf("pop(): %d\n", ais.pop());
		System.out.printf("peek(): %d\n", ais.peek());
		
		System.out.println(ais.toString());
	}
}
