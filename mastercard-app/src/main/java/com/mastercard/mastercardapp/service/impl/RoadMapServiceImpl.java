package com.mastercard.mastercardapp.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mastercard.mastercardapp.common.Constants;
import com.mastercard.mastercardapp.common.Road;
import com.mastercard.mastercardapp.service.RoadMapService;

@Service
public class RoadMapServiceImpl implements RoadMapService, Constants {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private Set<String> CITIES; // Total Unique Cities

	private Map<String, LinkedList<String>> adj;
	
	
	@Override
	public void setRoads(List<Road> roads) {
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
		adj.get(v).add(w);
		// Add w to v road
		adj.get(w).add(v);
		// Add v to w road to make two way path 
	}
	
	public String isReachable(String s, String d) {
		// Mark all the vertices as not visited(By default set
		// as false)
		Map<String, Boolean> visited = new HashMap<String, Boolean>();

		CITIES.forEach(city -> visited.put(city, false));
		// Create a queue for BFS
		LinkedList<String> queue1 = new LinkedList<String>();

		// Mark the current city as visited and enqueue it
		visited.put(s, true);

		queue1.add(s);
		
		// 'i' will be used to get all adjacent vertices of a vertex
		Iterator<String> i;
		while (queue1.size() != 0) {
				// Dequeue a vertex from queue and print it
				s = queue1.poll();
			String n;
			i = adj.get(s).listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext()) {
				n = i.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n.equals(d))
					return YES;

				// Else, continue to do BFS
				if (!visited.get(n)) {
					visited.put(n, true);
					queue1.add(n);
				}
			}
		}
		return NO;
	}

	

	public String isConnected(String s, String d) {
		if (CITIES.contains(s) && CITIES.contains(d)) {
			return isReachable(s, d);
		} else {
			return NO;
		}

	}

	RoadMapServiceImpl()throws Exception {
		String fileName = "static/city.txt";
		List<Road> roads = new ArrayList<>();
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());

		// File is found
		LOGGER.info("File Found : " + file.exists());

		// Read File Content
		String content = null;
			content = new String(Files.readAllBytes(file.toPath()));
			String[] paths = content.split("\n");
			Arrays.stream(paths).forEach(e -> {
				String[] path = e.split("\\s*,\\s*");
				roads.add(new Road(path[0], path[1]));
			});
			roads.forEach(road -> LOGGER.info("Road :: " + road.getSource() + "," + road.getDestination()));
			this.setRoads(roads);

	}

}