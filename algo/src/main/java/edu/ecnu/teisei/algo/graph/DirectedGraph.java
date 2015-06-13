package edu.ecnu.teisei.algo.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Teisei on 2015/3/27.
 */
public class DirectedGraph<V, E> implements Graph<V, E> {

    HashMap<V, HashMap<V, E>> outEdges;
//    HashMap<V, HashMap<V, E>> inEdges;

    public DirectedGraph() {
        outEdges = new HashMap<V, HashMap<V, E>>();
    }


    @Override
    public Map<V, E> getOutEdge(V v) {
        return outEdges.get(v);
    }

    @Override
    public void add(V source, V dest, E data) {
        Map<V, E> tempOutEdge = outEdges.get(source);
        if (tempOutEdge == null) {
            outEdges.put(source, new HashMap<V, E>());
        }
        tempOutEdge = outEdges.get(source);
        if (tempOutEdge.get(dest) != null) {
            // this edge exists
            System.out.println("this edge exists!!!");
        } else {
            tempOutEdge.put( dest, data);
        }
    }

    /**
     * compute shortest path from source to dest.
     * premise: E(edge) is comparable.
     */
    public List<V> getShortestPath(V source, V dest, boolean directionSensitive) {
        if (!outEdges.containsKey(source) || !outEdges.containsKey(dest)) {
            return null;
        }
        return DijkstraShortestPath.getShortestPath(this, source, source, directionSensitive);
    }
    public List<V> getShortestPath(V source, V dest) {
        return getShortestPath(source, dest, false);
    }


    @Override
    public void add(V v) {

    }

    @Override
    public boolean removeEdge(V source, V dest) {
        return false;
    }

    @Override
    public boolean removeEdge(V source, V dest, E data) {
        return false;
    }

    public static void main(String args[]) {
        DirectedGraph<Integer, String> graph = new DirectedGraph<Integer, String>();

        graph.add(1, 2, "1->2");
        graph.add(2, 3, "2->3");
        graph.add(1, 4, "1->4");
        // cyclic
        graph.add(4, 1, "4->1");
//        graph.addVertex(5);
        graph.add(5, 6, "5->6");
        graph.add(7, 6, "7->6");
//        graph.addVertex(7);
//        graph.addVertex(8);
        graph.add(9, 10, "9->10");
    }
}
