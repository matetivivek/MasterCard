package com.mastercard.mastercardapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mastercard.mastercardapp.common.Constants;
import com.mastercard.mastercardapp.controller.MastercardController;
import com.mastercard.mastercardapp.service.RoadMapService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MastercardControllerTests implements Constants{
	
	@InjectMocks
	MastercardController mastercardController = new MastercardController();

	@Mock
	private RoadMapService roadMapService;
	
	
	private MockMvc mockMvc;
	
	
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mastercardController).build();
	}
	@Test
	public void testConnected() throws Exception {
		String origin ="A";
		String destination = "B";
		
		when(roadMapService.isConnected(origin, destination))
		.thenReturn(YES);
		mockMvc.perform(
				get("/connected?origin=" + origin + "&destination=" + destination))
				.andExpect(status().is(200)).andReturn();
	}

}
