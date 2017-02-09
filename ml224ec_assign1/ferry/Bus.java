package ml224ec_assign1.ferry;

public class Bus extends Vehicle {

	public Bus()
	{
		maxPassengers = 20;
		baseCost = 20;
		passengerCost = 10;
		space = 4 * Car.BASE_SPACE_COST; // 20, 20 bicycles or 4 cars
	}
	
}
