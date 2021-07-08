package oto;
import java.time.LocalDate;

public class Date {
	
	private int day, month, year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	public boolean isAfterThan(Date other) {
		
		if(other.year < this.year) 
			return true;	
		else if(other.year == this.year) {
			if(other.month < this.month) 
				return true;
			else if(other.month == this.month) 
				return ( other.day < this.day );		
		}
		return false;
	}
	
	public boolean isBeforeThan(Date other) {
		if( this.isEqualsWith(other) )
			return false;
		return ( ! this.isAfterThan(other) );
	}
	
	
	public boolean isEqualsWith(Date other) {
		return ( this.year == other.year && this.month == other.month && this.day == other.day );
	}
	
	
	public static Date getToday() {	
		return new Date(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());	
	}
	
	@Override
	public String toString() {
		return day + "." + month + "." + year;
	}
	
		
}
