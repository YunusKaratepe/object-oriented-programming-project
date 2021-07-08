package oto;


public class AutoPark {
	
	private SubscribedVehicle[] subscribedVehicles;
	private ParkRecord[] parkRecords;
	private Double hourlyFee, incomeDaily;
	private int subscribedVehiclesIndex, parkRecordsIndex, capacity; 
	private int carCount;
	
	public AutoPark(double hourlyFee, int capacity) {

		this.capacity=capacity;
		subscribedVehicles = new SubscribedVehicle[8];
		parkRecords = new ParkRecord[8];
		this.hourlyFee = hourlyFee;
		carCount = 0;
		subscribedVehiclesIndex = 0;
		this.incomeDaily = 0.0;
		parkRecordsIndex = 0;
	}
	
	public SubscribedVehicle searchVehicle(String plate) {
		for(int i = 0; i < subscribedVehiclesIndex; i++) 
			if(subscribedVehicles[i].getPlate().equals(plate))
				return subscribedVehicles[i];
		return null;
	}
	
	public boolean isParked(String plate) {
	
		for(int i = 0; i < parkRecordsIndex; i++) 
			if(parkRecords[i].getVehicle().getPlate().equals(plate)) {
				if(null == parkRecords[i].getExitTime())
					return true; 
			}		
		return false; 
	}
	
	private void enlargeVehicleArray() {
		SubscribedVehicle[] expanded = new SubscribedVehicle[(subscribedVehicles.length)*2];
		System.arraycopy(subscribedVehicles, 0, expanded, 0, subscribedVehicles.length);
		subscribedVehicles = expanded;
	}
	public ParkRecord[] getParkRecords() {
		return parkRecords;
	}

	public void setParkRecords(ParkRecord[] parkRecords) {
		this.parkRecords = parkRecords;
	}

	private void enlargeParkRecords() {
		ParkRecord[] expanded = new ParkRecord[(parkRecords.length)*2];
		System.arraycopy(parkRecords, 0, expanded, 0, parkRecords.length);
		parkRecords = expanded;
	}
	
	
	public boolean addVehicle(SubscribedVehicle sV) {
		try {
			for(int i = 0; i < subscribedVehiclesIndex; i++) {
				if(sV.getPlate().equals( subscribedVehicles[i].getPlate() )) {
					throw new AyniAracMevcutException("Ayni arac mevcuttur.");
				}
			}
			subscribedVehicles[subscribedVehiclesIndex] = sV;
			subscribedVehiclesIndex++;
			return true;
		}catch(AyniAracMevcutException e) {
			e.printStackTrace();
			return false;
		}
		catch(ArrayIndexOutOfBoundsException e2) {
			//e2.printStackTrace();
			this.enlargeVehicleArray();
			subscribedVehicles[subscribedVehiclesIndex++] = sV;
			return true;
		}
	}
	
	public boolean vehicleEnters(String plate, Time enter, boolean isOfficial) {
		
		if(carCount < capacity) {
			if( ! isParked(plate) ) {
				try {
					if(searchVehicle(plate) != null) {	
						parkRecords[parkRecordsIndex] = new ParkRecord(enter, searchVehicle(plate));	
						parkRecordsIndex++;
					}else {
						if(isOfficial) {
							Vehicle v = new OfficialVehicle(plate);
							parkRecords[parkRecordsIndex] = new ParkRecord(enter, v);
							parkRecordsIndex++;
						}else {
							Vehicle v = new RegularVehicle(plate);
							parkRecords[parkRecordsIndex] = new ParkRecord(enter, v);
							parkRecordsIndex++;
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					enlargeParkRecords();
					if(isOfficial) {
						Vehicle v = new OfficialVehicle(plate);
						parkRecords[parkRecordsIndex++] = new ParkRecord(enter, v);
					}else {
						Vehicle v = new RegularVehicle(plate);
						parkRecords[parkRecordsIndex++] = new ParkRecord(enter, v);
					}
				}
				carCount++;
			return true;
			}	
		}
		return false;
	}
	
	public boolean vehicleExits(String plate, Time exit) {
		
		/*
		for(ParkRecord park : parkRecords) {
			if(park.getVehicle().getPlate().equals(plate)) {
				if(park.getExitTime()==null) {

					park.setExitTime(exit);
					payHourlyFee(park.getVehicle(), park.getParkingDuration());
					carCount--;
					return true;
				}
			}
		}*/
		for(int i = parkRecordsIndex - 1; i >= 0; i--) {
			if( parkRecords[i].getVehicle().getPlate().equals(plate) ){
				if( parkRecords[i].getExitTime() == null ) {
					parkRecords[i].setExitTime(exit);
					payHourlyFee(parkRecords[i].getVehicle(), parkRecords[i].getParkingDuration());
					carCount--;
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		String x = "Abone Araçlar :";
		for(int i = 0; i < subscribedVehiclesIndex; i++) {
			x += "\n" + subscribedVehicles[i].getPlate();
		}
		x += "\nPark Records :";
		for(int i = 0; i < parkRecordsIndex; i++) {
			if( null == parkRecords[i].getExitTime() ){
				x += "\n" + parkRecords[i].getVehicle().getPlate() + " Giris : " + parkRecords[i].getEnterTime() + " Arac cikis yapmadi.";
			}else {
				x += "\n" + parkRecords[i].getVehicle().getPlate() + " Park Süresi : " + parkRecords[i].getParkingDuration() + " Dakika";
			}
		}
		x += "\nToplamda Kazanilan Para : " + this.incomeDaily;
		
		return x;
	}
	
	public void payHourlyFee(Vehicle v, int parkingTime) {
		if( v.getSubscription() == null && ! v.isOfficial() )	// official degilse ve subscriptionu yoksa -->
			this.incomeDaily += ( parkingTime / 60 ) * this.hourlyFee; // dakika olarak gelecek saate çevirip her saat basýna bu kadar fee ödenecek. ÖRN : 7 saat 54 dk = 7 * hourlyFee;
		else if(  ! v.isOfficial() && ! v.getSubscription().isValid() )
			this.incomeDaily += ( parkingTime / 60 ) * this.hourlyFee;
	}

	public SubscribedVehicle[] getSubscribedVehicles() {
		return subscribedVehicles;
	}

	public int getSubscribedVehiclesIndex() {
		return subscribedVehiclesIndex;
	}

	

	public int getParkRecordsIndex() {
		return parkRecordsIndex;
	}

	public Double getIncomeDaily() {
		return incomeDaily;
	}

	 

	public void setCapacity(int kapasiti) {
		this.capacity = kapasiti;
		
	}

	public void setHourlyFee(double fee) {
		this.hourlyFee=fee;
	}
	
	public int getCarCount() {
		return this.carCount;
	}

	

	
}