package edu.ecnu.teisei.algo.heap;

/**
 * Created by Teisei on 2015/3/30.
 */
public class SingleHeap {
    int a[];// do not use a[0]
    int _END_POS_ = -1;
    int _Heap_Size_ = -1;

    public SingleHeap() {
        new SingleHeap(new int[0], 0);
    }
    public SingleHeap(int[] a, int end_pos) {
        this.a = a;
        _END_POS_ = end_pos;
        _Heap_Size_ = _END_POS_;
    }


    public int getParent(int i) {
        return i >> 1;
    }

    public int getLeft(int i) {
        return i << 1;
    }

    public int getRight(int i) {
        return (i << 1) + 1;
    }

    /*
    *   T(n) <= T(2n/3)+O(1).
    *   O( lg n )
    */
    public void MAX_HEAPIFY(int i) {

        int l = getLeft(i);
        int r = getRight(i);
        int largest = i;
        if (l <= _Heap_Size_ && a[l] > a[largest])
            largest = l;
        if (r <= _Heap_Size_ && a[r] > a[largest])
            largest = r;
        if (largest != i) {
            int tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;
            MAX_HEAPIFY(largest);
        }
    }

    /*
    *   O(n)
    */
    public void BUILD_MAX_HEAP() {
        _Heap_Size_ = _END_POS_;
        for (int i = _Heap_Size_ >> 1; i >=1; i--) {
            MAX_HEAPIFY(i);
        }
    }

    public void HEAPSORT() {
        BUILD_MAX_HEAP();//O(n)
        for (int i = _Heap_Size_; i >= 2; i--) {
            int tmp = a[i];
            a[i] = a[1];
            a[1] = tmp;
            _Heap_Size_ -= 1;
            MAX_HEAPIFY(1);//O(lgn)
        }
    }

    public void print() {
        for (int i=1;i<a.length;i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int[] sort = new int[]{-1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12, 17, 34, 11};
        SingleHeap heap = new SingleHeap(sort,sort.length-1);

        heap.print();
        heap.HEAPSORT();
        heap.print();

    }
}
