package com.mastercard.mastercardapp.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph{
	
	public static final String YES= "yes";
	public static final String NO= "no";
	private Set<String> V; // Total Unique Cities

	private Map<String, LinkedList<String>> adj;

	// Constructor
	public Graph(Set<String> v){
		V = v;
		adj = new HashMap<>();

		v.forEach(city -> adj.put(city, new LinkedList()));

	}

//Function to add road between two cities 
	public void addRoad(String v, String w) {

		adj.get(v).add(w);
		// Add w to v road
		adj.get(w).add(v);
		// Add v to w road
		
	}

// BFS traversal from a given source s 
	public String isReachable(String s, String d) {
		// Mark all the vertices as not visited(By default set
		// as false)
		Map<String, Boolean> visited = new HashMap<String, Boolean>();

		V.forEach(city -> visited.put(city, false));
		// Create a queue for BFS
		LinkedList<String> queue1 = new LinkedList<String>();

		// Mark the current city as visited and enqueue it
		visited.put(s, true);
		
		queue1.add(s);

		// 'i' will be used to get all adjacent vertices of a vertex
		Iterator<String> i;
		while (queue1.size() != 0) {
			if(queue1.size() != 0)
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
				if (n == d)
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
		if(V.contains(s) && V.contains(d)) {
			return isReachable(s, d);
		}else {
			return NO;
		}
		
	}

	public static void main(String args[]) {

		Set<String> set = Arrays.asList("Boston", "New York", "Philadelphia", "Newark", "Trenton", "Albany").stream()
				.collect(Collectors.toSet());
		Graph g = new Graph(set);

		g.addRoad("Boston", "New York");
		g.addRoad("Philadelphia", "Newark");
		g.addRoad("Newark", "Boston");
		g.addRoad("Trenton", "Albany");

		System.out.println(g.isReachable("Boston","Newark"));
		
		System.out.println(g.isReachable("Boston","Philadelphia"));

		System.out.println(g.isReachable("Philadelphia","Albany"));
	}

}