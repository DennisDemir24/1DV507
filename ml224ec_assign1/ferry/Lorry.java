package ml224ec_assign1.ferry;

public class Lorry extends Vehicle {

	public Lorry()
	{
		maxPassengers = 2;
		baseCost = 300;
		passengerCost = super.passengerCost - 5; // 20 - 5 = 15
		space = 8 * Car.BASE_SPACE_COST; // 40, 40 bicycles
	}
	
}
