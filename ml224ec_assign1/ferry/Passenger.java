package ml224ec_assign1.ferry;

public class Passenger implements Embarkable {

	public static final int BASE_COST = 20;
	
	private Vehicle vehicle;
	
	public Passenger() {}
	
	public Passenger(Vehicle v)
	{
		vehicle = v;
	}
	
	@Override
	public int totalCost() {
		return BASE_COST;
	}

	@Override
	public int getSpace() {
		return 1;
	}

	@Override
	public boolean isVehicle() {
		return false;
	}

	public boolean hasVehicle() {
		return vehicle != null;
	}
}
