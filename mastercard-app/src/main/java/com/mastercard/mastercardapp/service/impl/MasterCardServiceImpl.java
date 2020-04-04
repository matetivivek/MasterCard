package com.mastercard.mastercardapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.mastercardapp.service.MasterCardService;
import com.mastercard.mastercardapp.service.RoadMapService;

@Service
public class MasterCardServiceImpl implements MasterCardService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoadMapService roadMapService;

	@Override
	public String isConnected(String s, String d) {
		
		return roadMapService.isConnected(s, d);
	}

}
