package com.mastercard.mastercardapp.service;

import java.util.List;
import java.util.Set;

import com.mastercard.mastercardapp.common.Road;

public interface RoadMapService {

	String isConnected(String s, String d);

	String isReachable(String s, String d);

	void setRoads(List<Road> roads);
	
	void setCities(Set<String> v);
	
	

}
