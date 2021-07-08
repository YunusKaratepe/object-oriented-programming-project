package oto;

public class SubscribedVehicle implements Vehicle{
	
	private Subscription subscription;
	private String plate;
	
	public SubscribedVehicle(String plate, Subscription subscription) {
		this.plate = plate;
		this.subscription = subscription;
	}
	

	@Override
	public String getPlate() {
		return this.plate;
	}

	@Override
	public Subscription getSubscription() {
		return this.subscription;
	}

	@Override
	public boolean isOfficial() {
		return false;
	}
	
	public void setSubscription(Subscription s) {
		this.subscription = s;
	}


	 

}
