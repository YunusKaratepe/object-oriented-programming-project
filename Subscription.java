package oto;

import java.util.Calendar;

public class Subscription {
	
	private Date begin, end;
	private SubscribedVehicle subscribedVehicle;
	
	public Subscription(Date begin, Date end, String plate) throws HataliSubscriptionTarihiException {
		
		if( begin.isBeforeThan(end) ) {
			subscribedVehicle = new SubscribedVehicle(plate, this);
			this.begin=begin;
			this.end = end;
		}else {
			throw new HataliSubscriptionTarihiException("Hatali Kayýt Tarihi Girildi");
		}
	}
	
	public boolean isValid() {
		return ( end.isAfterThan( Date.getToday() ) );
	}

	public SubscribedVehicle getSubscribedVehicle() {
		return subscribedVehicle;
	}

	public void setBegin(Date begin) {
			this.begin = begin;
	}


	
	public Date getEnd() {
		return this.end;
	}
	
	
	
}
