package com.denver.airport.entity;

import java.util.List;





public class Node implements Comparable<Node>, Cloneable
{
    public final String name;
    public List<Edge> adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public double lowestDistance = 0;
    public Node previous;
    public Node(String argName) { name = argName; }
    public String toString() { return name; }
    @Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	public int compareTo(Node other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

	public void reset () {
	    this.minDistance = Double.POSITIVE_INFINITY;
	    this.previous=null;
	}
}



