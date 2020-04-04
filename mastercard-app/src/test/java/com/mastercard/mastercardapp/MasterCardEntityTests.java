package com.mastercard.mastercardapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.mastercard.mastercardapp.common.Road;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MasterCardEntityTests {
	
	@Test
	void testRoadObject() {
		Road road = new Road("Source", "Destination");
		assertEquals( "Source",road.getSource(),"Test Road Object Source");
		assertEquals( "Destination",road.getDestination(),"Test Road Object Destination");
		
	}

}
