package com.oselish.dijkstra;

import java.util.Vector;

public class Dijkstra {
    private Node[] nodes;
    
    
    public Dijkstra(Node... nodes) {
        for (Node node : nodes)
            node.setWeight(Double.POSITIVE_INFINITY);
        this.nodes = nodes;
    }
    
    public DijkstraResult calculateWay(int startIndex, int finishIndex) throws Exception {
        Node start = getNode(startIndex);
        Node finish = getNode(finishIndex);

        if (start == null || finish == null) {
            return null;
        }
        else {
            resetNodesData(nodes);
            
            start.setWeight(0);
            start.addLastNode(start);
            Node current = start;
            while (true) {
                current.sortNeighbours();
                int visitedCount = 0;
                for (var node : current.getNeighbours()) {
                    if (!node.isVisited) {
                        var index = node.getIndex();
                        var weight = current.getWeight() + current.getDistanceTo(index);
                        var way = current.getWay();
                        way.add(node);
                        if (weight < node.getWeight()) {
                            node.setWeight(weight);
                            node.setWay(way);
                        }
                        
                    }
                    else visitedCount++;
                }
                current.isVisited = true;
                current = current.getNeighbours().get(0);
                if (current == null) break;
                if (visitedCount == current.getNeighbours().size()) break;
            }
        }
        Node finishNode = getNode(finishIndex);
        var distance = finishNode.getWeight();
        var result = new DijkstraResult(finishNode.getWay(), finishNode.getWeight());
        
        resetNodesData(nodes);


        if (distance == Double.POSITIVE_INFINITY) {
            throw new Exception(String.format("There's no way between nodes %d and %d", start.getIndex(), finish.getIndex()));
        }

        return result;
    }
    
    private Node getNode(int index){
        for (var node : nodes) {
            if (node.getIndex() == index)
                return node;
        }
        return null;
    }
    
    private void resetNodesData(Node[] nodes) {
        for (var node : nodes) {
            node.setWeight(Double.POSITIVE_INFINITY);
            node.isVisited = false;
            node.setWay(new Vector<>());
        }
    }
}
