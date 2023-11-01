package com.oselish.dijkstra;

import java.util.Vector;

public class SortResult {
    public final Vector<Node> neighbours;
    public final Vector<Double> distances;

    public SortResult(Vector<Node> neighbours, Vector<Double> distances) {
        this.distances = distances;
        this.neighbours = neighbours;
    }
}
