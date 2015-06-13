package edu.ecnu.teisei.algo.sort;

/**
 * 冒泡排序：O(n*n),稳定
 * Created by dingcheng on 2015/3/11.
 */
public class Bubble_sort implements MySort {

    /* 单向冒泡 */
    @Override
    public int[] sort(int[] a, int n) {
        int end = n - 1;
        while (end > 0) {
            int pos = 0;
            for (int j = 0; j < end; j++) {
                if (a[j] > a[j + 1]) {//増序
                    pos = j;
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            end = pos;
        }
        return a;
    }

    /* 双向冒泡 */
    public int[] Bubble_2(int a[], int n) {
        int low = 0;
        int high = n - 1; //设置变量的初始值
        int tmp, j;
        while (low < high) {
            for (j = low; j < high; ++j) //正向冒泡,找到最大者
                if (a[j] > a[j + 1]) {
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            --high;                 //修改high值, 前移一位
            for (j = high; j > low; --j) //反向冒泡,找到最小者
                if (a[j] < a[j - 1]) {
                    tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            ++low;                  //修改low值,后移一位
        }
        return a;
    }


    @Override
    public void sortObject(Comparable[] a, int n) {
        int low = 0;
        int high = n - 1; //设置变量的初始值
        Comparable tmp;
        int j;
        while (low < high) {
            for (j = low; j < high; ++j) //正向冒泡,找到最大者
                if (a[j].compareTo(a[j+1]) > 0) {
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            --high;                 //修改high值, 前移一位
            for (j = high; j > low; --j) //反向冒泡,找到最小者
                if (a[j].compareTo(a[j-1]) < 0) {
                    tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            ++low;                  //修改low值,后移一位
        }
    }
}
