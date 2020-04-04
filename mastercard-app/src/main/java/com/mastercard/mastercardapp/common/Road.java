package com.mastercard.mastercardapp.common;

public class Road {
	
	private String source;
	private String destination;
	
	public Road(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	public String getSource() {
		return source;
	}
	public String getDestination() {
		return destination;
	}
}
