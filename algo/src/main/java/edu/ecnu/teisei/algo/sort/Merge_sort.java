package edu.ecnu.teisei.algo.sort;

/**
 * MergeSort:
 * Created by dingcheng on 2015/3/11.
 */
public class Merge_sort implements MySort {

    public static int[] a_copy;
    public static Comparable[] a_copy2;
    protected static boolean isDes;//是否是降序

    public Merge_sort(boolean isDes) {
        this.isDes = isDes;
    }

    public Merge_sort(int[] a, boolean isDes) {
        a_copy = new int[a.length];
        this.isDes = isDes;
    }

    /* 増序 */
    public void sort(int[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(a, start, mid);
            sort(a, mid + 1, end);
            merge(a, start, end);
        }
    }
    /* merge */
    public void merge(int[] a, int start, int end) {
        int cursor = start;
        int cursor1 = start;
        int cursor2 = (start + end) / 2 + 1;
        while (cursor1 <= (start + end) / 2 && cursor2 <= end) {
            if (isDes) {
                if (a[cursor1] >= a[cursor2]) {
                    a_copy[cursor++] = a[cursor1++];
                } else {
                    a_copy[cursor++] = a[cursor2++];
                }
            } else {
                if (a[cursor1] <= a[cursor2]) {
                    a_copy[cursor++] = a[cursor1++];
                } else {
                    a_copy[cursor++] = a[cursor2++];
                }
            }
        }
        while (cursor1 <= (start + end) / 2)
            a_copy[cursor++] = a[cursor1++];
        while (cursor2 <= end)
            a_copy[cursor++] = a[cursor2++];
        for (int i = start; i <= end; i++) {
            a[i] = a_copy[i];
        }
    }

    /* 増序 */
    public void sort(Comparable[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(a, start, mid);
            sort(a, mid + 1, end);
            merge(a, start, end);
        }
    }
    /* merge */
    public void merge(Comparable[] a, int start, int end) {
        int cursor = start;
        int cursor1 = start;
        int cursor2 = (start + end) / 2 + 1;
        while (cursor1 <= (start + end) / 2 && cursor2 <= end) {
            if (isDes) {
                if (a[cursor1].compareTo(a[cursor2]) >= 0) {
                    a_copy2[cursor++] = a[cursor1++];
                } else {
                    a_copy2[cursor++] = a[cursor2++];
                }
            } else {
                if (a[cursor1].compareTo(a[cursor2]) <= 0) {
                    a_copy2[cursor++] = a[cursor1++];
                } else {
                    a_copy2[cursor++] = a[cursor2++];
                }
            }
        }
        while (cursor1 <= (start + end) / 2)
            a_copy2[cursor++] = a[cursor1++];
        while (cursor2 <= end)
            a_copy2[cursor++] = a[cursor2++];
        for (int i = start; i <= end; i++) {
            a[i] = a_copy2[i];
        }
    }


    @Override
    public int[] sort(int[] a, int n) {
        a_copy = new int[a.length];
        sort(a, 0, a.length - 1);
        return a;
    }

    @Override
    public void sortObject(Comparable[] a, int n) {
        a_copy2 = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
}
