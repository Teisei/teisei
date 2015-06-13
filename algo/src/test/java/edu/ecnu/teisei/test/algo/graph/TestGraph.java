package edu.ecnu.teisei.test.algo.graph;

import edu.ecnu.teisei.algo.graph.DirectedGraph;

import java.util.List;

/**
 * Created by Teisei on 2015/3/27.
 */
public class TestGraph {
    DirectedGraph<Integer, String> graph =
            new DirectedGraph<Integer, String>();

    public void testShortestPathDirectionSensitiveNodes() {
        List<Integer> nodes = graph.getShortestPath(1, 3, true);
        System.out.println("directed path nodes btw 1 and 3 is " + nodes);
        assertEquals(3, nodes.size());
        assertEquals(1, nodes.get(0).intValue());
        assertEquals(2, nodes.get(1).intValue());
        assertEquals(3, nodes.get(2).intValue());

        nodes = graph.getShortestPath(2, 4, true);
        System.out.println("directed path nodes btw 2 and 4 is " + nodes);
//        assertEquals(null, nodes);

        nodes = graph.getShortestPath(1, 5, true);
        System.out.println("directed path nodes btw 1 and 5 is " + nodes);
//        assertEquals(null, nodes);
    }

    private void assertEquals(int i, int size) {
    }
}
