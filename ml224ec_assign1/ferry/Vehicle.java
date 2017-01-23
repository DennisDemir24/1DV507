package ml224ec_assign1.ferry;

import java.util.Random;

public abstract class Vehicle implements Embarkable {
	protected int maxPassengers = 1;
	protected int space = 1;
	protected int baseCost = 20;
	protected int passengerCost = Passenger.BASE_COST;
	protected Passenger[] passengers;
	
	public Vehicle()
	{
		populate();
	}
	
	// fills the vehicle with passengers
	protected void populate()
	{
		int passengerCount = new Random().nextInt(maxPassengers) + 1;
		
		passengers = new Passenger[passengerCount];
		
		for (int i = 0; i < passengerCount; i++)
			passengers[i] = new Passenger(this);
	}
	
	public int getPassengerCount()
	{
		return passengers.length;
	}
	
	public Passenger[] getPassengers()
	{
		return passengers;
	}
	
	@Override
	public int totalCost()
	{
		return baseCost + (passengerCost * passengers.length);
	}
	
	@Override
	public int getSpace()
	{
		return space;
	}
	
	@Override
	public boolean isVehicle() {
		return true;
	}
}
