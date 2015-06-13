package edu.ecnu.teisei.algo.sort;


/**
 * 希尔排序:O(n^1.2)
 * Created by dingcheng on 2015/3/10.
 */
public class Shells_sort implements MySort {
    public int[] sort(int[] a, int n) {
        /* 步长 */
        for (int gap = n / 2; gap > 0; gap /= 2) {
            /* 直接插入排序 */
            for (int start = 0; start < gap; start++) {
                /* 每一组进行插入排序 */
                for (int j = start + gap; j < n; j += gap) {
                    if (a[j] < a[j - gap]) {
                        //从 j 往前
                        int temp = a[j];
                        int k = j - gap;
                        while (k >= 0 && temp < a[k]) {
                            a[k + gap] = a[k];
                            k -= gap;
                        }
                        //结束之后，将a[j]定位
                        a[k + gap] = temp;
                    }
                }
            }
        }
        return a;
    }

    @Override
    public void sortObject(Comparable[] a, int n) {
        /* 步长 */
        for (int gap = n / 2; gap > 0; gap /= 2) {
            /* 直接插入排序 */
            for (int start = 0; start < gap; start++) {
                /* 每一组进行插入排序 */
                for (int j = start + gap; j < n; j += gap) {
                    if (a[j].compareTo(a[j-gap]) < 0) {
                        //从 j 往前
                        Comparable temp = a[j];
                        int k = j - gap;
                        while (k >= 0 && temp.compareTo(a[k]) < 0) {
                            a[k + gap] = a[k];
                            k -= gap;
                        }
                        //结束之后，将a[j]定位
                        a[k + gap] = temp;
                    }
                }
            }
        }
    }
}
