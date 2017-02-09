package ml224ec_assign1.ferry;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import ml224ec_assign1.ferry.*;

public class FerryMain {
	
	private enum EmbarkableType {
		PASSENGER,
		BICYCLE,
		CAR,
		BUS,
		LORRY;
		
		public static EmbarkableType pick()
		{
			return EmbarkableType.values()[new Random().nextInt(EmbarkableType.values().length)];
		}
	}
	
	private static final int MAX_LORRIES = 5;
	private static final int MAX_BUSES = 10;
	private static final int MAX_CARS = 50;
	private static final int MAX_BICYCLES = 100;
	private static final int MAX_STANDALONE_PASSENGERS = 150;
	
	private static final int MAX_EMBARKABLES = 250;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FerryBoat boat = new FerryBoat();
		
		Queue<Embarkable> destinationA = new ArrayBlockingQueue<Embarkable>(MAX_EMBARKABLES);
		Queue<Embarkable> destinationB = new ArrayBlockingQueue<Embarkable>(MAX_EMBARKABLES);
		
		// Fill the queues for the demostration
		System.out.println("Populating embarkment queues..");
		populateQueue(destinationA);
		populateQueue(destinationB);
		System.out.println("Finished!");
		
		System.out.println("Embarking from destination A..");
		while (!destinationA.isEmpty())
		{
			Embarkable e = destinationA.remove(); // dequeue the first one
			
			if (boat.hasSpaceFor(e))
				boat.embark(e);
		}
		int incomeA = boat.countMoney();
		System.out.printf("%s\n%d SEK earned from destination A\n", boat, incomeA);
		
		System.out.println("Moving from A to B");
		
		boat.disembark();
		System.out.println("Passengers & vehicles from A disembarked at B\n");
		
		System.out.println("Embarking from destination B..");
		while (!destinationB.isEmpty())
		{
			Embarkable e = destinationB.remove(); // dequeue the first one
			
			if (boat.hasSpaceFor(e))
				boat.embark(e);
		}
		int incomeB = boat.countMoney() - incomeA;
		System.out.printf("%s\n%d SEK earned from destination B\n", boat, incomeB);
		
		System.out.println("Moving from B to A");
		
		boat.disembark();
		System.out.println("Passengers & vehicles from A disembarked at B\n");
			
		System.out.printf("\n..and that concludes the day\n%d SEK worth in embarkment fees were paid that day\n", boat.countMoney());
	}

	// massive hack to fulfill a need for populated queues
	private static void populateQueue(Queue<Embarkable> queue)
	{
		Random rng = new Random();
		
		int embarkables = rng.nextInt(MAX_EMBARKABLES) + 1;
		int filled = 0;
		
		int passengerCount = 0;
		int carCount = 0;
		int bicycleCount = 0;
		int busCount = 0;
		int lorryCount = 0;
		
		while (filled < embarkables)
		{
			Embarkable e = null;
			switch (EmbarkableType.pick())
			{
				case PASSENGER:
				{
					if (passengerCount < MAX_STANDALONE_PASSENGERS)
					{
						e = new Passenger();
						passengerCount++;
					}
					break;
				}
				case BICYCLE:
				{
					if (bicycleCount < MAX_BICYCLES)
					{
						e = new Bicycle();
						bicycleCount++;
					}
					break;
				}
				case CAR:
				{
					if (carCount < MAX_CARS)
					{
						e = new Car();
						carCount++;
					}
					break;
				}
				case BUS:
				{
					if (busCount < MAX_BUSES)
					{
						e = new Bus();
						busCount++;
					}
					break;
				}
				case LORRY:
				{
					if (lorryCount < MAX_LORRIES)
					{
						e = new Lorry();
						lorryCount++;
					}
					break;
				}
			}
			if (e == null) // Try again, <insert insult here>..
				continue;
			
			if (e.isVehicle())
				populateVehicle((Vehicle)e);
			queue.add(e);
			filled++;
		}
	}
	
	// fills the vehicle with passengers
	private static void populateVehicle(Vehicle v)
	{
		int passengerCount = new Random().nextInt(v.getMaxPassengers()) + 1;
		
		Passenger[] passengers = new Passenger[passengerCount];
		
		for (int i = 0; i < passengerCount; i++)
			passengers[i] = new Passenger(v);
		
		v.setPassengers(passengers);
	}
}
