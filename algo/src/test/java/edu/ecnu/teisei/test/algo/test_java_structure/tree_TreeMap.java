package edu.ecnu.teisei.test.algo.test_java_structure;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Teisei on 2015/3/29.
 */
public class tree_TreeMap {
    public static void main(String args[]) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int a[] = {3, 50, 2, 1, 4,};
        int b[] = {1, 2, 3, 4, 5};
        for (int i=0;i<a.length;i++) {
            map.put(a[i], b[i]);
        }
        print(map);

        map.put(5, 6);
        print(map);

        map.put(5, 6);
        print(map);
//        map.pollFirst();
//        print(map);
    }

    public static void print(TreeMap<Integer,Integer> map) {
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();
    }
}
