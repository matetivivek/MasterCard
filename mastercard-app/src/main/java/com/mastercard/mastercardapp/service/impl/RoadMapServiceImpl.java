package com.mastercard.mastercardapp.service.impl;

import java.io.File;
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
import com.mastercard.mastercardapp.service.impl.algorithm.PathFinderAlgorithm;

@Service
public class RoadMapServiceImpl extends PathFinderAlgorithm implements RoadMapService, Constants {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
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
			setUpRoads(roads);

	}

	public String isConnected(String s, String d) {
		if (!containsCity(s) ) {
			return NO;
		}else if(!containsCity(d)){
			return NO;
		}else{
			return isReachable(s, d);
		} 

	}

}