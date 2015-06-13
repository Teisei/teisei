package edu.ecnu.teisei.algo.sort;


/**
 * Straight_insertion_sort:O(n*n)
 * Created by dingcheng on 2015/3/10.
 */
public class Straight_insertion_sort implements MySort {
    @Override
    public int[] sort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                int j = i - 1;
                int x = a[i];
                a[i] = a[i - 1];
                while (x < a[j]) {
                    a[j + 1] = a[j];
                    j--;
                    if(j<0)
                        break;
                }
                a[j + 1] = x;
            }
        }
        return a;
    }

    /**
     * Sort Object[]
     */
    @Override
    public void sortObject(Comparable[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[i - 1]) <0) {
                int j = i - 1;
                Comparable x = a[i];
                while (x.compareTo(a[j]) <0) {
                    a[j + 1] = a[j];
                    j--;
                    if (j < 0) {
                        break;
                    }
                }
                a[j + 1] = x;
            }
        }
    }

}
