package edu.ecnu.teisei.test.algo.test_java_structure;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Teisei on 2015/3/29.
 */
public class test_Tree_set {
    public static void main(String args[]) {
        TreeSet<Integer> set = new TreeSet<>();
        int a[] = {3, 50, 2, 1, 4,};
        for (int each : a) {
            set.add(each);
        }
        print(set);

        set.add(5);
        print(set);

        set.pollFirst();
        print(set);
    }

    public static void print(TreeSet<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
