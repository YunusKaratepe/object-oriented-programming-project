package oto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JTest {

	@Test
	void test() throws HataliSubscriptionTarihiException {
		
		boolean b;
		int carCount;
		double incomeDaily = 0;
		
		Vehicle v1 = new SubscribedVehicle("subPlate1", new Subscription(new Date(11, 3, 2010), new Date(11, 4, 2020), "subPlate1"));
		Vehicle v2 = new SubscribedVehicle("subPlate2", new Subscription(new Date(11, 3, 2010), new Date(11, 4, 2018), "subPlate2"));
		Vehicle v3 = new OfficialVehicle("ofcPlate1");
		Vehicle v4 = new RegularVehicle("regPlate1");
		Vehicle v5 = new RegularVehicle("regPlate2");
		AutoPark apTest = new AutoPark(10, 4); // autopark with 10  = fee , 4 = capacity
		
		
		b = apTest.addVehicle((SubscribedVehicle) v1); // adding sub vehicle
		assertEquals(true, b);
		b = apTest.addVehicle((SubscribedVehicle) v2); // adding sub vehicle
		assertEquals(true, b);
		b = apTest.addVehicle((SubscribedVehicle) v1); // adding a sub vehicle with the same plate
		assertEquals(false, b);
		
		carCount = apTest.getCarCount();	assertEquals(0, carCount); // autopark is empty
		
		b = apTest.vehicleEnters("subPlate1", new Time(15, 0), v1.isOfficial()); // subscribed vehicle enters
		assertEquals(true, b);
		b = apTest.vehicleEnters("regPlate1", new Time(15, 0), v4.isOfficial()); // regular vehicle enters
		assertEquals(true, b);
		carCount = apTest.getCarCount();	assertEquals(2, carCount); // after 2 vehicles entered
		b = apTest.vehicleEnters("ofcPlate1", new Time(15, 0), v3.isOfficial()); // official vehicle enters
		assertEquals(true, b); 
		b = apTest.vehicleEnters("subPlate2", new Time(15, 0), v2.isOfficial()); // subscribed vehicle which has invalid 
		assertEquals(true, b); 
		b = apTest.vehicleEnters("regPlate2", new Time(15, 0), v5.isOfficial()); // regular vehicle tries to enter but capacity is 4 : So it can t.
		assertEquals(false, b);
		
		b = apTest.isParked("subPlate2"); // entered and non exited vehicle
		assertEquals(true, b);
		b = apTest.isParked("regPlate2"); // non entered vehicle
		assertEquals(false, b);	
		b = apTest.vehicleEnters("ofcPlate1", new Time(17, 0), v3.isOfficial()); // same vehicle tries to enter without exiting : false
		assertEquals(false, b); 
		
		
		b = apTest.vehicleExits("regPlate1", new Time(16, 33)); // entered regular vehicle exits
		assertEquals(true, b);	
		incomeDaily = apTest.getIncomeDaily(); assertEquals(10, incomeDaily); // 16.33 - 15.00 =>  1 hour fee
		
		carCount = apTest.getCarCount();	assertEquals(3, carCount); // after 1 vehicle was exited
		b = apTest.vehicleExits("subPlate1", new Time(17, 44)); // 2 entered vehicle exits
		assertEquals(true, b);
		incomeDaily = apTest.getIncomeDaily(); assertEquals(10, incomeDaily); // must be no change. Sub1 has valid subscription.
		
		b = apTest.vehicleExits("subPlate2", new Time(17, 55)); // entered vehicle exits
		assertEquals(true, b);	
		incomeDaily = apTest.getIncomeDaily(); assertEquals(30, incomeDaily); // invalid subscription : 17.55 - 15.00 => 2 hours fee
		
		b = apTest.vehicleExits("ofcPlate1", new Time(16, 33)); // entered before and exited vehicle tries to exit
		assertEquals(true, b);
		incomeDaily = apTest.getIncomeDaily(); assertEquals(30, incomeDaily); // no change : it is official vehicle
		
		b = apTest.isParked("subPlate1"); // entered and exited vehicle
		assertEquals(false, b);
		
		Vehicle searchedVehicle = apTest.searchVehicle("subPlate2"); // subscribed vehicle
		assertEquals(searchedVehicle, v2);
		
		searchedVehicle = apTest.searchVehicle("ofcPlate1"); // non subscribed vehicle
		assertEquals(searchedVehicle, null);
		
		
	}

}
