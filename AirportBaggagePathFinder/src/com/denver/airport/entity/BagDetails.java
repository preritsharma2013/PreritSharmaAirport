package com.denver.airport.entity;

public class BagDetails {
	
	private String bagNumber;
	private Node entryPoint;
	private String flightId;
	
	public String getBagNumber() {
		return bagNumber;
	}
	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}
	public Node getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(Node entryPoint) {
		this.entryPoint = entryPoint;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

}
