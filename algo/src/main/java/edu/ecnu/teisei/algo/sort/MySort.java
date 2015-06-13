package edu.ecnu.teisei.algo.sort;

/**
 * Created by dingcheng on 2015/3/10.
 */
public interface MySort {
    public int[] sort(int a[], int n);

    /**
     * sort object[] which is implemented by Comparable
     */
    public void sortObject(Comparable[] a, int n);
}
