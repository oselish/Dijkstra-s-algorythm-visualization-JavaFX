package com.oselish.dijkstra;

import java.util.Collections;
import java.util.Vector;

public class Node {
    public Boolean isVisited = false;
    private Vector<Node> neighbours = new Vector<>();
    private Vector<Double> distances = new Vector<>();
    private int index;
    private double weight;
    private Vector<Node> way;

    public Node(int index) {
        this.index = index;
        this.weight = Double.POSITIVE_INFINITY;
        this.way = new Vector<>();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setNeighbours(Node... neighbours) {
        Collections.addAll(this.neighbours, neighbours);
    }
    
    public void setNeighbours(Vector<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public Vector<Node> getNeighbours() {
        return neighbours;
    }
    
    public Node clone(){
        Node result = new Node(index);
        result.isVisited = isVisited;
        result.setNeighbours(neighbours);
        result.setDistances(distances);
        result.index = index;
        result.weight = weight;
        result.setWay(way);
        return result;
    }
    
    public Node getNeighbour(int index) {
        for (var node : neighbours) {
            if (node.index == index)
                return (Node)node.clone();
        }
        return null;
    }

    public void setDistances(double... wayLengths) {
        var temp = new Vector<Double>();
        for (var elem : wayLengths){
            this.distances.add(elem);
        }
    }
    
    public void setDistances(Vector<Double> distances) {
        this.distances = distances;
    }

    public Vector<Double> getDistances() {
        return (Vector<Double>)distances.clone();
    }

    public Double getDistanceTo(int index) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).index == index) {
                return distances.get(i);
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public Node getNearestNotVisited() {
        Double minDistance = Double.POSITIVE_INFINITY;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < neighbours.size(); i++){
            if (!neighbours.get(i).isVisited){
                var neighbourIndex = neighbours.get(i).getIndex();
                if (getDistanceTo(neighbourIndex) < minDistance) {
                    minDistance = getDistanceTo(neighbourIndex);
                    index = neighbourIndex;
                }
            }
        }
        return getNeighboutByIndex(index);
    }

    public Node getNeighboutByIndex(int index) {
        for (var n : neighbours) {
            if (n.index == index)
                return n;
        }
        return null;
    }

    public void sortNeighbours(){
        for (int i = 0; i < neighbours.size() - 1; i++) {
            for (int j = neighbours.size() - 1; j > i; j--) {
                if (distances.get(j - 1) > distances.get(j)) {
                    var tmpN = neighbours.get(j - 1);
                    neighbours.set(j - 1, neighbours.get(j));
                    neighbours.set(j, tmpN);

                    var tmpD = distances.get(j - 1);
                    distances.set(j - 1, distances.get(j));
                    distances.set(j, tmpD);
                }
            }
        }
    }
    
    public Vector<Node> getWay() {
        return (Vector<Node>)way.clone();
    }
    
    public void setWay(Vector<Node> way) {
        this.way = way;
    }
    
    public void addLastNode(Node node) {
        this.way.add(node);
    }
}
