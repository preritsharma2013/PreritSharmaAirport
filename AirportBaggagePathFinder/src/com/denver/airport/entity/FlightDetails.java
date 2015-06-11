package com.denver.airport.entity;

import java.sql.Date;

public class FlightDetails {
	
	public FlightDetails() {
		super();
	}
	private String flightId;
	private String flightGate;
	private Node destination;
	private Date flightTime;
	
	
	
	public FlightDetails(String flightId, String flightGate,
			Node destination, Date flightTime) {
		super();
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.destination = destination;
		this.flightTime = flightTime;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightGate() {
		return flightGate;
	}
	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}
	public Node getNode() {
		return destination;
	}
	public void setNode(Node destination) {
		this.destination = destination;
	}
	public Date getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

}
