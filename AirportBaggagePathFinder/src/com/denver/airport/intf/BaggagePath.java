package com.denver.airport.intf;

import java.util.List;

import com.denver.airport.entity.BagDetails;
import com.denver.airport.entity.Node;

public interface BaggagePath {
	
	public void computePaths(Node source);
	public List<Node> getShortestPathTo(Node target,BagDetails bagDetails);

}
