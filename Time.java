package oto;

public class Time {
	
	private int hour, minute;
	
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getDifference(Time other) {
		return  60 * (this.hour - other.hour) + this.minute - other.minute;
	}
}
