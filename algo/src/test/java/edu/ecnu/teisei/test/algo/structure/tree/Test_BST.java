package edu.ecnu.teisei.test.algo.structure.tree;

import edu.ecnu.teisei.algo.tree.BinarySearchTree;
import edu.ecnu.teisei.algo.tree.RedBlackTree;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dingcheng on 2015/3/16.
 */
public class Test_BST {
    @Test
    public void testBST() {
        int N = 10000;
        Integer[] nums = new Integer[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();

        Date start;
        System.out.println("############### insert BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.insert(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### search BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.search(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### delete BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.delete(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
    }


    /**
     * Compare general Binary Search tree and
     * Red Black tree.
     */
    @Test
    public void testCompareBSTandRBT() {
        int N = 10000;
        Integer[] nums = new Integer[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
        RedBlackTree<Integer> tree2 = new RedBlackTree<Integer>();

        Date start;
        System.out.println("############### insert BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.insert(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### search BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.search(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### delete BST ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree1.delete(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());






        System.out.println("############### insert RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.insert(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());

        System.out.println("############### search RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.search(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println("############### delete RBT ###############");
        start = new Date();
        for (int i = 0; i < nums.length; i++) {
            tree2.delete(nums[i]);
        }
        System.out.println(new Date().getTime() - start.getTime());

    }
}
