package com.mastercard.mastercardapp.controller;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mastercard.mastercardapp.resource.CityRoadResource;

@Controller
public class MastercardController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CityRoadResource RoadResource;
	
	@GetMapping("/connected")
	@ResponseBody
	public ResponseEntity<?> connected(
			@RequestParam @NotBlank(message = "origin cannot be blank") String origin,
			@RequestParam @NotBlank(message = "destination cannot be blank") String destination) throws Exception {
		
								
//		LinkedHashMap<String, String> venue = venueManagementService.getVenueByName(venueName, requestedBy, cid, authToken);

		return ResponseEntity.ok("yes");
	}

}
