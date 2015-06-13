package edu.ecnu.teisei.algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teisei on 2015/3/27.
 */
public class DijkstraShortestPath{
    public static <V, E> List<V> getShortestPath(Graph<V, E> graph, V source, V dest, boolean directionSensitive) {
        List<E> dist = new ArrayList<E>();
        Map<V, E> outEdge = graph.getOutEdge(source);
        return null;
    }

}
