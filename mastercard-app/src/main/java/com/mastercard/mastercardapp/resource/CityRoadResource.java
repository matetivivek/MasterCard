package com.mastercard.mastercardapp.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import  com.mastercard.mastercardapp.common.City;


@Component
public class CityRoadResource {
	
	private Set<String> cities;
	
	private List<City> roads;
	
	public List<City> getRoads() {
		return roads;
	}

	CityRoadResource(){
		String fileName = "static/city.txt";
		roads= new ArrayList<>();
		cities = new HashSet<>();
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		 
		File file = new File(classLoader.getResource(fileName).getFile());
		 
		//File is found
		System.out.println("File Found : " + file.exists());
		 
		//Read File Content
		String content = null;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] paths =  content.split("\n");
		Arrays.stream(paths).forEach(e->{
			String[] path = e.split(", ");
			roads.add(new City(path[0], path[1]));
			cities.add(path[0]);
			cities.add(path[1]);
		}); 
		
       roads.forEach(city -> System.out.println(city.getSource()+","+city.getDestination()));
		
		
	}
	
}

