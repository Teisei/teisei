package edu.ecnu.teisei.algo.graph;

import java.util.List;

/**
 * Created by Teisei on 2015/3/27.
 */
public interface ShortestPath {
    public abstract <V, E> List<V> getShortestPath(Graph<V, E> graph, V sourse, V dest, boolean directionSensitive);
}
