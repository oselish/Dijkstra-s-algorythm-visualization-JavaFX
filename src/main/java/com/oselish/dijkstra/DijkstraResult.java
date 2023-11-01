package com.oselish.dijkstra;

import java.util.Vector;

public class DijkstraResult {
    private Vector<Node> way;
    private Double wayLength;
    
    public DijkstraResult(Vector<Node> way, Double wayLength) {
        this.way = way;
        this.wayLength = wayLength;
    }
    
    public Vector<Node> getWay() {
        return way;
    }
    
    public Double getWayLength() {
        return wayLength;
    }
}
