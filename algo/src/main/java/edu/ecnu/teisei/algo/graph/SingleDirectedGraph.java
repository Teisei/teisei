package edu.ecnu.teisei.algo.graph;

import java.util.*;

/**
 * using dijstra,bellman-ford, SPFA;
 * Created by Teisei on 2015/3/27.
 */
public class SingleDirectedGraph{
    final int _MAX_INT_ = 1 << 30;
    Map<Integer, Map<Integer, Integer>> outEdges = new HashMap<Integer, Map<Integer, Integer>>();
    Map<Integer, Integer> vertex = new HashMap<>();

    public void add(int s, int t, int weight) {
        if (!vertex.containsKey(s)) {
            vertex.put(s, 0);
        }
        if (!vertex.containsKey(t)) {
            vertex.put(t, 0);
        }

        Map<Integer, Integer> outEdge = outEdges.get(s);
        if (outEdge == null) {
            outEdges.put(s, new HashMap<Integer, Integer>());
        }
        outEdge = outEdges.get(s);
        if (outEdge.containsKey(t)) {
            outEdge.put(t, outEdge.get(t) + weight);
        } else {
            outEdge.put(t, weight);
        }
    }

    public int getShortestPath_Disjstra(int s, int t) {
        /* < u , dist[u] > */
        HashMap<Integer, Integer> dist = new HashMap<>();
        HashMap<Integer, Integer> pre = new HashMap<>();

        /* initial the dist, iterate each node */
        Iterator<Map.Entry<Integer, Integer>> node_iter = vertex.entrySet().iterator();
        while (node_iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = node_iter.next();
            dist.put(entry.getKey(), _MAX_INT_);
            pre.put(entry.getKey(), -1);
        }
        dist.put(s, 0);

        int start = s;
        /*
        * O(v*v)
        */
        while (!dist.isEmpty()) {
            if (start == t) {
                break;
            }
            /*
                from u,update every connected vertex
                O(e)
            */
            Map<Integer, Integer> outEdge = outEdges.get(start);
            if (outEdge != null) {
                /* every edge of node(start) */
                Iterator<Map.Entry<Integer, Integer>> edge_iter = outEdge.entrySet().iterator();
                while (edge_iter.hasNext()) {
                    Map.Entry<Integer, Integer> one_edge = edge_iter.next();
                    int end = one_edge.getKey();
                    if (dist.containsKey(end)) {
                        int weight = one_edge.getValue();

                        if (dist.get(start) + weight < dist.get(end)) {
                        /* update the dist of Node 'end' */
                            dist.put(end, dist.get(start) + weight);
                            pre.put(end, start);
                        }
                    }
                }
            }
            dist.remove(start);
            /*
                pick up the minimun one in the dist
                O(v), can be optimized using a Que
            */
            int min_index = -1;
            int min = _MAX_INT_;
            node_iter = dist.entrySet().iterator();
            while (node_iter.hasNext()) {
                Map.Entry<Integer, Integer> one_node = node_iter.next();
                int key = one_node.getKey();
                int value = one_node.getValue();
                if (value < min) {
                    min_index = key;
                    min = value;
                }
            }
            start = min_index;
        }
        System.out.println("*** print the shortest path ***");
        System.out.print("t:" + t);
        int pre_node = pre.get(t);
        while (pre_node != -1) {
            System.out.print(" <-- " + pre_node);
            pre_node = pre.get(pre_node);
        }
        System.out.println("");

        return dist.get(t);
    }


    /**
     * Can check if there is a negative circle in the graph
     */
    public int getShortestPath_BellmanFord(int s, int t) {
        /* < u , dist[u] > */
        HashMap<Integer, Integer> dist = new HashMap<>();
        HashMap<Integer, Integer> pre = new HashMap<>();

        /* initial the dist, iterate each node */
        Iterator<Map.Entry<Integer, Integer>> node_iter = vertex.entrySet().iterator();
        while (node_iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = node_iter.next();
            dist.put(entry.getKey(), _MAX_INT_);
            pre.put(entry.getKey(), -1);
        }
        dist.put(s, 0);

        for (int i = 0; i < vertex.size(); i++) {

            /* iterate each edge */
            Iterator<Map.Entry<Integer, Map<Integer, Integer>>> outedges_iter = outEdges.entrySet().iterator();
            while (outedges_iter.hasNext()) {
                Map.Entry<Integer, Map<Integer, Integer>> each = outedges_iter.next();
                int start = each.getKey();
                Iterator<Map.Entry<Integer, Integer>> outedges_iter2 = each.getValue().entrySet().iterator();
                while (outedges_iter2.hasNext()) {
                    Map.Entry<Integer, Integer> each_2 = outedges_iter2.next();
                    int end = each_2.getKey();
                    int weight = each_2.getValue();

                    /* relax */
                    if (dist.get(end) > dist.get(start) + weight) {
                        dist.put(end, dist.get(start) + weight);
                        pre.put(end, start);
                    }
                }
            }
        }
        /* check if there is a negative circle */
        boolean flag = true;
        Iterator<Map.Entry<Integer, Map<Integer, Integer>>> outedges_iter = outEdges.entrySet().iterator();
        while (outedges_iter.hasNext()) {
            Map.Entry<Integer, Map<Integer, Integer>> each = outedges_iter.next();
            int start = each.getKey();
            Iterator<Map.Entry<Integer, Integer>> outedges_iter2 = each.getValue().entrySet().iterator();
            while (outedges_iter2.hasNext()) {
                Map.Entry<Integer, Integer> each_2 = outedges_iter2.next();
                int end = each_2.getKey();
                int weight = each_2.getValue();

                    /* relax */
                if (dist.get(end) > dist.get(start) + weight) {
                    flag = false;
                }
            }
        }

        if (!flag) {
            return -1;
        }
        return dist.get(t);
    }


    public int getShortestPath_SPFA(int s, int t) {
        /* < u , dist[u] > */
        HashMap<Integer, Integer> dist = new HashMap<>();
        HashMap<Integer, Integer> pre = new HashMap<>();
        /* the time of a node be added to the queue */
        HashMap<Integer, Integer> vst = new HashMap<>();


        /* initial the dist, iterate each node */
        Iterator<Map.Entry<Integer, Integer>> node_iter = vertex.entrySet().iterator();
        while (node_iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = node_iter.next();
            dist.put(entry.getKey(), _MAX_INT_);
            pre.put(entry.getKey(), -1);
        }
        dist.put(s, 0);
        vst.put(s, 1);


        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        boolean flag = true;
        while (!queue.isEmpty()) {
            int start = queue.poll();

            if (vst.get(start) > vertex.size()) {
                flag = false;
                break;
            }
            Map<Integer, Integer> outedges = outEdges.get(start);
            if (outedges == null) {
                continue;
            }
            Iterator<Map.Entry<Integer, Integer>> outedges_iter = outEdges.get(start).entrySet().iterator();
            while (outedges_iter.hasNext()) {
                Map.Entry<Integer, Integer> each_2 = outedges_iter.next();
                int end = each_2.getKey();
                int weight = each_2.getValue();

                    /* relax */
                if (dist.get(end) > dist.get(start) + weight) {
                    dist.put(end, dist.get(start) + weight);
                    pre.put(end, start);
                    queue.add(end);
                    if (vst.containsKey(end)) {
                        vst.put(end, vst.get(end) + 1);
                    } else {
                        vst.put(end, 1);
                    }
                }
            }
        }

        if (!flag) {
            return -1;
        }
        return dist.get(t);
    }


    public class Node implements Comparable<Node> {
        int key;
        int value;

        public int getKey() {
            return key;
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
            if (this.value < o.getValue()) {
                return -1;
            }else if (this.value == o.getValue()) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    public static void main(String args[]) {
        SingleDirectedGraph gp = new SingleDirectedGraph();
        gp.add(1, 2, 100);
        gp.add(1, 3, 1);
        gp.add(2, 4, 2);
        gp.add(3, 4, 1000);
//        gp.add(2, 5, 50);
//        gp.add(5, 2, -100);
//        gp.add(5, 4, 1);
                System.out.println(gp.getShortestPath_Disjstra(1, 4));
                System.out.println(gp.getShortestPath_BellmanFord(1, 4));
                System.out.println(gp.getShortestPath_SPFA(1, 4));
    }
}
