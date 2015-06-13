package edu.ecnu.teisei.algo.sort;

/**
 * This class offer Merge Sort Algorithm.
 * Created by dingcheng on 2015/3/16.
 */
public class MergeSort<T extends Comparable<? super T>> {
    /**
     * Merge Sort algorithm.
     */
    @SuppressWarnings("unchecked")
    public void mergeSort(T[] a) {
        T[] tmpArray = (T[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Internal method that makes recursive calls. *
     */
    private void mergeSort(T[] a,
                           T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;

            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.*
     */
    private void merge(T[] a,
                       T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        // Copy rest of left half
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }

        // Copy rest of right half
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        // Copy tmpArray back
        for (int i = 0; i < numElements; i++, --rightEnd) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }
}
