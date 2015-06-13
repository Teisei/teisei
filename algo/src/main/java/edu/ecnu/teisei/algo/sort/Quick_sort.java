package edu.ecnu.teisei.algo.sort;

/**
 * QuickSort:O(nlogn)
 * Created by dingcheng on 2015/3/12.
 */
public class Quick_sort implements MySort {

    protected boolean isDes = false;

    /* Constructor: if the sort using the descecing order */
    public Quick_sort(boolean isDes) {
        this.isDes = isDes;
    }

    public void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int[] sort(int[] a, int n) {
        sort(a, 0, a.length - 1);
        return a;
    }
    public void sort(int[] a, int left, int right) {
        if (left < right) {
            int i = left, j = right;
            int x = a[i];
            while (i < j) {
                while (i < j && a[j] >= x)
                    j--;
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] <= x)
                    i++;
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            //判断i的位置
            sort(a, left, i - 1);
            sort(a, i + 1, right);
        }
    }

    @Override
    public void sortObject(Comparable[] a, int n) {
        sortObject(a, 0, n);
    }
    public void sortObject(Comparable a[], int left, int right) {
        if (left < right) {
            int i = left, j = right;
            Comparable x = a[i];
            while (i < j) {
                while (i < j && a[j].compareTo(x) >= 0)
                    j--;
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i].compareTo(x) <= 0)
                    i++;
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            //判断i的位置
            sortObject(a, left, i - 1);
            sortObject(a, i + 1, right);
        }
    }
}
