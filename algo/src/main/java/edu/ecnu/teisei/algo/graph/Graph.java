package edu.ecnu.teisei.algo.graph;

import java.util.Map;

/**
 * Created by Teisei on 2015/3/27.
 */
public interface Graph<V, E> {
    public abstract Map<V, E> getOutEdge(V v);

    public abstract void add(V source, V dest, E data);

    public abstract void add(V v);

    public abstract boolean removeEdge(V source, V dest);

    public abstract boolean removeEdge(V source, V dest, E data);


}
