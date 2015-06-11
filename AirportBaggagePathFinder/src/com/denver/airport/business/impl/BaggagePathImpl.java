package com.denver.airport.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.denver.airport.entity.BagDetails;
import com.denver.airport.entity.Edge;
import com.denver.airport.entity.Node;
import com.denver.airport.intf.BaggagePath;

public class BaggagePathImpl implements BaggagePath{

	@Override
	public void computePaths(Node source) {
        source.minDistance = 0.;
        PriorityQueue<Node> vertexQueue = new PriorityQueue<Node>();
        vertexQueue.add(source);

	    while (!vertexQueue.isEmpty()) {
	        Node u = vertexQueue.poll();
	
	            // Visit each edge exiting u
	            for (Edge e : u.adjacencies)
	            {
	                Node v = e.target;
	                double weight = e.weight;
	                double distanceThroughU = u.minDistance + weight;
	                
			        if (distanceThroughU < v.minDistance) {
			            vertexQueue.remove(v);
			
			            v.minDistance = distanceThroughU ;
			            v.previous = u;
			            vertexQueue.add(v);
			        }
	            }
	        }
	}

	@Override
	public List<Node> getShortestPathTo(Node target,BagDetails bagDetails) {
	        List<Node> path = new ArrayList<Node>();
	        String nodeNames = "";
	        double shortestDistance =0.0;
	        for (Node vertex = target; vertex != null; vertex = vertex.previous)
        	{
	        	path.add(vertex);
        	}
	        Collections.reverse(path);
	        
	        for(int i=0;i<path.size();i++)
	        {
	        	Node current = path.get(i);
	        	Node next = (i+1<path.size())?path.get(i+1):null;
	        	List<Edge> list = current.adjacencies;
	        	if(list!=null)
	        	{
	        		for (Edge edge : list) {
						if(next !=null && edge.target.name.equalsIgnoreCase(next.name))
						{
							shortestDistance+=edge.weight;
							break;
						}
					}
	        	}
	        	nodeNames+=current.name+" ";
	        }
	        System.out.println(bagDetails.getBagNumber()+ " " +nodeNames+" : "+ shortestDistance);
	        return path;
	}

}
