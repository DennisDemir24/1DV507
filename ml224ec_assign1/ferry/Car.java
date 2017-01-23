package ml224ec_assign1.ferry;

public class Car extends Vehicle {

	// it takes five bicycles to fill up the space equal a car
	public static final int BASE_SPACE_COST = 5 * Bicycle.BASE_SPACE_COST; 
	
	public Car()
	{
		maxPassengers = 4;
		baseCost = 100;
		passengerCost = 15;
		space = BASE_SPACE_COST;
		
		populate();
	}
	
}
