package oto;
public class ParkRecord {
	
	private Time enterTime, exitTime;
	private Vehicle vehicle;
	
	public ParkRecord(Time enterTime, Vehicle vehicle) {
		this.enterTime = enterTime;
		exitTime = null;
		this.vehicle = vehicle;
	}
	
	public int getParkingDuration() {
		
		return exitTime.getDifference(enterTime);
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public void setExitTime(Time t) {
		this.exitTime = t;
	}
	public Time getEnterTime() {
		return this.enterTime;
	}
	public Time getExitTime() {
		return this.exitTime;
	}
	
}
