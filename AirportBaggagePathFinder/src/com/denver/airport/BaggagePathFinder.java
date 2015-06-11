package com.denver.airport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.denver.airport.business.impl.BaggagePathImpl;
import com.denver.airport.entity.BagDetails;
import com.denver.airport.entity.Edge;
import com.denver.airport.entity.FlightDetails;
import com.denver.airport.entity.Node;
import com.denver.airport.intf.BaggagePath;


public class BaggagePathFinder
{
	private final static Logger logger = Logger.getLogger(BaggagePathFinder.class.getName());
	private final static BaggagePath baggagePathImpl = new BaggagePathImpl();
	private static Map<String,FlightDetails> flightDetailMap = new HashMap<String,FlightDetails>();
	private static Map<String,Node> nodeMap = new HashMap<String, Node>();
	
	static
	{
		Node concourseATicketing = new Node("Concourse_A_Ticketing");
        Node A1 = new Node("A1");
        Node A2 = new Node("A2");
        Node A3 = new Node("A3");
        Node A4 = new Node("A4");
        Node A5 = new Node("A5");
        Node A6 = new Node("A6");
        Node A7 = new Node("A7");
        Node A8 = new Node("A8");
        Node A9 = new Node("A9");
        Node A10 = new Node("A10");
        Node baggageClaim = new Node("BaggageClaim");
        
        nodeMap.put(A1.name, A1);
        nodeMap.put(A2.name, A2);
        nodeMap.put(A3.name, A3);
        nodeMap.put(A4.name, A4);
        nodeMap.put(A5.name, A5);
        nodeMap.put(A6.name, A6);
        nodeMap.put(A7.name, A7);
        nodeMap.put(A8.name, A8);
        nodeMap.put(A9.name, A9);
        nodeMap.put(A10.name, A10);
        nodeMap.put(concourseATicketing.name, concourseATicketing);
        nodeMap.put(baggageClaim.name, baggageClaim);
	}
	

    public static void main(String[] args)
    {
    	InputStream in = BaggagePathFinder.class.getResourceAsStream("Conveyor_System.txt");
        BufferedReader br =new BufferedReader(new InputStreamReader(in));
        try
        {
        	 String coonveyorInput = null;
             while ((coonveyorInput = br.readLine()) != null) {
             	String[] splitString = coonveyorInput.split(" ");
             	if(splitString!=null) {
     				String entryNodeName = splitString[0];
     				String targetNodename = splitString[1];
     				Node entryNode = nodeMap.get(entryNodeName);
     				Node targetNode = nodeMap.get(targetNodename);
     				if(entryNode.adjacencies!=null)
     				{
     					entryNode.adjacencies.add(new Edge(targetNode, Double.parseDouble(splitString[2])));
     				}
     				else
     				{
     					List<Edge> list = new ArrayList<Edge>();
     					list.add(new Edge(targetNode, Double.parseDouble(splitString[2])));
     					entryNode.adjacencies = list;
     				}
     				if(targetNode.adjacencies!=null)
     				{
     					targetNode.adjacencies.add(new Edge(entryNode, Double.parseDouble(splitString[2])));
     				}
     				else
     				{
     					List<Edge> list = new ArrayList<Edge>();
     					list.add(new Edge(entryNode, Double.parseDouble(splitString[2])));
     					targetNode.adjacencies = list;
     				}
     			}
             }
             
             in = BaggagePathFinder.class.getResourceAsStream("Departures.txt");
             br =new BufferedReader(new InputStreamReader(in));
             String departureInput = null;
             while ((departureInput = br.readLine()) != null) {
             	String[] splitString = departureInput.split(" ");
             	flightDetailMap.put(splitString[0], new FlightDetails(splitString[0],splitString[2],nodeMap.get(splitString[1]),null));
             }
             in = BaggagePathFinder.class.getResourceAsStream("Bags.txt");
             br =new BufferedReader(new InputStreamReader(in));
             String bagsInput = null;
             while ((bagsInput = br.readLine()) != null) {
            	
             	String[] splitString = bagsInput.split(" ");
             	BagDetails bagDetails = new BagDetails();
             	bagDetails.setBagNumber(splitString[0]);
                 bagDetails.setEntryPoint(nodeMap.get(splitString[1]));
                 bagDetails.setFlightId(splitString[2]);
                 baggagePathImpl.computePaths(bagDetails.getEntryPoint());
                 List<Node> path = null;
                 if(splitString[2].equalsIgnoreCase("ARRIVAL"))
                 {
                	 path = baggagePathImpl.getShortestPathTo(nodeMap.get("BaggageClaim"),bagDetails);
                 }
                 else
                 {
                	 path = baggagePathImpl.getShortestPathTo(flightDetailMap.get(splitString[2]).getNode(),bagDetails);
                 }
                 for(Node v : nodeMap.values()) {
                    v.reset();
                }
             }
        }
        catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                br.close();
            } catch (IOException ex) {
            	logger.log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
     
}