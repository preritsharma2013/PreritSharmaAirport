package com.denver.airport.entity;

public class Edge
{
    public final Node target;
    public final double weight;
    public Edge(Node argTarget, double argWeight)
    { 
    	target = argTarget;
    	weight = argWeight;
    }
}