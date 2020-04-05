package com.mastercard.mastercardapp.service.impl.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mastercard.mastercardapp.common.Constants;
import com.mastercard.mastercardapp.common.Road;

public abstract class PathFinderAlgorithm implements Constants{

	private Set<String> CITIES; // Total Unique Cities

	private Map<String, LinkedList<String>> adj;
	
	public final void setUpRoads(List<Road> roads) {
		this.adj = new HashMap<>();
		CITIES = new HashSet<String>();
		roads.forEach(road -> {
			CITIES.add(road.getSource());
			CITIES.add(road.getDestination());
			});
		CITIES.forEach(city -> adj.put(city, new LinkedList<>()));
		roads.forEach(road -> addRoad(road.getSource(), road.getDestination()));
	}

//Function to add road between two cities 
	private void addRoad(String v, String w) {
		//adding city w as adjacent city for v in linkedlist
		adj.get(v).add(w);
		//adding city v as adjacent city for w in linkedlist
		adj.get(w).add(v);
	}
	
	public final String isReachable(String s, String d) {
		// Mark all the cities as not visited(By default set as false)
		Map<String, Boolean> visited = new HashMap<String, Boolean>();

		CITIES.forEach(city -> visited.put(city, false));
		// Create a queue 
		LinkedList<String> queue = new LinkedList<String>();

		// Mark the current city  s as visited and enqueue it
		visited.put(s, true);
		queue.add(s);
		
		// 'i' will be used to get all adjacent cities of a city
		Iterator<String> i;
		while (queue.size() != 0) {
				// Dequeue a city from queue and print it
				s = queue.poll();
			String n;
			i = adj.get(s).listIterator();

			// Get all adjacent cities of the dequeued City s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext()) {
				n = i.next();

				// If this adjacent City is the destination City,
				// then return true
				if (n.equals(d))
					return YES;

				// if City n is not visited 
				if (!visited.get(n)) {
					// Mark the city  n as visited and enqueue it
					visited.put(n, true);
					queue.add(n);
				}
			}
		}
		return NO;
	}
	
	public final boolean containsCity(String city) {
		return CITIES.contains(city);
	}

	public abstract String isConnected(String s, String d);
	
}
