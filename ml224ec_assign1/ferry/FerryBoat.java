package ml224ec_assign1.ferry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FerryBoat implements Ferry {

	private static final int MAX_CARS = 40;
	private static final int MAX_VEHICLE_SPACE = MAX_CARS * Car.BASE_SPACE_COST;
	private static final int MAX_PASSENGER_SPACE = 200;
	
	private static final int VEHICLE_SPACE_THRESHOLD = (int)(MAX_VEHICLE_SPACE * 0.9);
	private static final int PASSENGER_SPACE_THRESHOLD = (int)(MAX_PASSENGER_SPACE * 0.9);
	
	private List<Vehicle> vehicles;
	private List<Passenger> passengers;
	
	private int usedVehicleSpace;
	private int usedPassengerRoom;
	
	private int earnedMoney;
	
	public FerryBoat()
	{
		vehicles = new ArrayList<Vehicle>();
		passengers = new ArrayList<Passenger>();
	}
	
	@Override
	public int countPassengers() {
		return usedPassengerRoom;
	}

	@Override
	public int countVehicleSpace() {
		return usedVehicleSpace;
	}

	@Override
	public int countMoney() {
		return earnedMoney;
	}

	@Override
	public Iterator<Vehicle> iterator() {
		return vehicles.iterator();
	}

	@Override
	public void embark(Vehicle v) {
		for (Passenger p : v.getPassengers())
		{
			passengers.add(p);
			usedPassengerRoom += p.getSpace();
		}
		
		vehicles.add(v);
		usedVehicleSpace += v.getSpace();
		
		earnedMoney += v.totalCost();
		checkRemainingSpace(true);
	}

	@Override
	public void embark(Passenger p) {
		passengers.add(p);
		usedPassengerRoom += p.getSpace();
		
		earnedMoney += p.totalCost();
		checkRemainingSpace(false);
	}
	
	public void embark(Embarkable e)
	{
		if (e.isVehicle())
			embark((Vehicle)e);
		else
			embark((Passenger)e);
	}
	
	private void checkRemainingSpace(boolean checkVehicles) {
		
		if (usedPassengerRoom >= PASSENGER_SPACE_THRESHOLD)
		{
			if (usedVehicleSpace == MAX_PASSENGER_SPACE)
				System.out.println("NOTE: No more passenger room remaining!");
			else
				System.out.printf("NOTE: Space equivalent to %d passengers remaining\n", MAX_PASSENGER_SPACE - usedPassengerRoom);
		}
		if (checkVehicles && usedVehicleSpace >= VEHICLE_SPACE_THRESHOLD)
		{
			if (usedVehicleSpace == MAX_VEHICLE_SPACE)
			{
				System.out.println("NOTE: No more vehicle space remaining!");
				return;
			}
			int diff = MAX_VEHICLE_SPACE - usedVehicleSpace;
			int cars = diff / Car.BASE_SPACE_COST;
			System.out.printf("NOTE: Space equivalent to %d cars (%d bicycles) remaining\n", cars, diff);
		}
	}

	@Override
	public void disembark() {
		usedVehicleSpace = usedPassengerRoom = 0;
		vehicles.clear();
		passengers.clear();
	}

	@Override
	public boolean hasSpaceFor(Vehicle v) {
		return (v.getSpace() + usedVehicleSpace) < MAX_VEHICLE_SPACE && (v.getPassengerCount() + usedPassengerRoom) < MAX_PASSENGER_SPACE;
	}

	@Override
	public boolean hasRoomFor(Passenger p) {
		return (p.getSpace() + usedPassengerRoom) < MAX_PASSENGER_SPACE;
	}
	
	public boolean hasSpaceFor(Embarkable e) {
		if (e.isVehicle())
			return hasSpaceFor((Vehicle)e);
		else
			return hasRoomFor((Passenger)e);
	}

	@Override
	public String toString()
	{
		String data = "Ship manifest\n"
				+ String.format("Total vehicles: %d\nBicycles: %d\nCars: %d\nBuses: %d\nLorries: %d\n",
						vehicles.size(),
						vehicles.stream().filter((v) -> {return v instanceof Bicycle;}).count(),
						vehicles.stream().filter((v) -> {return v instanceof Car;}).count(),
						vehicles.stream().filter((v) -> {return v instanceof Bus;}).count(),
						vehicles.stream().filter((v) -> {return v instanceof Lorry;}).count())
				+ String.format("Total passengers: %d\nFrom vehicles: %d\nStandalone: %d\n", 
						passengers.size(),
						passengers.stream().filter((p) -> {return p.hasVehicle();}).count(),
						passengers.stream().filter((p) -> {return !p.hasVehicle();}).count());
		
		return data;
	}
}
