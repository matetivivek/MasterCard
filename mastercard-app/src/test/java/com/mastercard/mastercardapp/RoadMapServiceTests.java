package com.mastercard.mastercardapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mastercard.mastercardapp.common.Constants;
import com.mastercard.mastercardapp.common.Road;
import com.mastercard.mastercardapp.service.RoadMapService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RoadMapServiceTests implements Constants {
	
	private List<Road> roads = new ArrayList<>();
	@Autowired
	private RoadMapService roadMapService;
	
	@BeforeAll
	public  void init(){
		//Graph1
		roads.add(new Road("A", "B"));
		roads.add(new Road("B", "C"));
		roads.add(new Road("C", "D"));
		//Graph2
		roads.add(new Road("E", "F"));
		roads.add(new Road("F", "G"));
		//Graph3
		roads.add(new Road("H", "I"));
		roadMapService.setRoads(roads);
	}
	
	
	
	
	
	@Test
	void testGroup1ForwardTraversalTwoEndPoints(){
		//Graph1 Tests
		assertEquals( YES,roadMapService.isConnected("A", "B"),"Travel from A to D");
		assertEquals( YES,roadMapService.isConnected("A", "D"),"Travel from A to D");
		assertEquals( YES,roadMapService.isConnected("B", "C"),"Travel from B to C");
		assertEquals( YES,roadMapService.isConnected("B", "D"),"Travel from B to D");
		//Graph1 Reverse
		assertEquals( YES,roadMapService.isConnected("D", "A"),"Travel from D to A");
		assertEquals( YES,roadMapService.isConnected("C", "B"),"Travel from C to B");
		assertEquals( YES,roadMapService.isConnected("B", "A"),"Travel from B to A");
		
		//Group 1 with Unknown
		assertEquals( NO,roadMapService.isConnected("A", "X"),"Travel from A to D");
		assertEquals( NO,roadMapService.isConnected("X", "D"),"Travel from A to D");
		
		//Graph2 Tests 
		assertEquals( YES,roadMapService.isConnected("E", "F"),"Travel from A to D");
		assertEquals( YES,roadMapService.isConnected("F", "G"),"Travel from D to A");
		assertEquals( YES,roadMapService.isConnected("F", "G"),"Travel from B to C");
		
		//Group 2 with Unknown
		assertEquals( NO,roadMapService.isConnected("E", "Unknown"),"Travel from A to D");
		assertEquals( NO,roadMapService.isConnected("Unknown", "G"),"Travel from A to D");
		
	}
	
	
	
	@Test
	void testPathtBetweenGroupsTraversal(){
		//Tests Between Group1 and Group1
		assertEquals( NO,roadMapService.isReachable("C", "F"),"Test Path between C and F");
		//Tests Between Group2 and Group3
		assertEquals( NO,roadMapService.isReachable("G", "H"),"Test Path between C and F");
		//Tests Between Group3 and Group1
		assertEquals( NO,roadMapService.isReachable("I", "A"),"Test Path between C and F");
	}
	
	
}
