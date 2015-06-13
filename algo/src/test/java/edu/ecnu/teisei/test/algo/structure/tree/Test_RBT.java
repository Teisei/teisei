package edu.ecnu.teisei.test.algo.structure.tree;

import edu.ecnu.teisei.algo.tree.RedBlackTree;
import org.junit.Test;

import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * test Red Black tree.
 * Created by dingcheng on 2015/3/16.
 */
public class Test_RBT {
    @Test
    public void testRBT() {
        int N = 1000000;
        Integer[] nums = new Integer[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }
        RedBlackTree<Integer> tree2 = new RedBlackTree<Integer>();
        TreeSet<Integer> tree1 = new TreeSet<Integer>();
        Date start;
        System.out.println("############### insert RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.insert(nums[i]);
            if (i % 10 == 0) {
            }
        }
        System.out.println("Root: "+tree2.root.element);
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### insert TreeSet ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.add(nums[i]);
            if (i % 10 == 0) {
            }
        }
        System.out.println("Root: "+tree1.first());
        System.out.println(new Date().getTime() - start.getTime());



        System.out.println("############### search RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.search(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### search TreeSet ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.contains(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());



        System.out.println("############### delete RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.delete(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### delete TreeSet ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.remove(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
    }

    @Test
    public void testTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        TreeMap<Integer, String> map =new TreeMap<Integer, String>();
    }
}
