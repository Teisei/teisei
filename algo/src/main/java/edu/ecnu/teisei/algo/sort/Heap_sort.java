package edu.ecnu.teisei.algo.sort;

/**
 * Created by dingcheng on 2015/3/10.
 */
public class Heap_sort implements MySort {
    private static int[] sort = new int[]{1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12, 17, 34, 11};

    private static void buildMaxHeapify(int[] data){
        //û���ӽڵ�Ĳ���Ҫ�������ѣ������һ���ĸ��ڵ㿪ʼ
        int startIndex = getParentIndex(data.length - 1);
        //��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�
        for (int i = startIndex; i >= 0; i--) {
            maxHeapify(data, data.length, i);
        }
    }

    private static void buildMaxHeapify(int[] data, int last) {
        //û���ӽڵ�Ĳ���Ҫ�������ѣ������һ���ĸ��ڵ㿪ʼ
        int startIndex = getParentIndex(last);
        //��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�
        for (int i = startIndex; i >= 0; i--) {
            maxHeapify(data, last+1, i);
        }
    }

    /**
     * ��������
     * @param data
     * @param heapSize ��Ҫ�������ѵĴ�С��һ����sort��ʱ���õ�����Ϊ���ֵ����ĩβ��ĩβ�Ͳ��ٹ���������
     * @param index ��ǰ��Ҫ�������ѵ�λ�á���ĳ�����ڵ㣩
     */
    private static void maxHeapify(int[] data, int heapSize, int index) {
        // ��ǰ���������ӽڵ�Ƚ�
        int left = getChildLeftIndex(index);
        int right = getChildRightIndex(index);

        int largest = index;
        if (left < heapSize && data[index] < data[left]) {
            largest = left;
        }
        if (right < heapSize && data[largest] < data[right]) {
            largest = right;
        }
        //�õ����ֵ�������Ҫ��������������ˣ����ӽڵ���ܾͲ��������ˣ���Ҫ���µ���
        if (largest != index) {
            int temp = data[index];
            data[index] = data[largest];
            data[largest] = temp;
            maxHeapify(data, heapSize, largest);
        }
    }

    /* �������ֵ����ĩβ��data��Ȼ�����ѣ��������ͳ��˵����� */
    private static void heapSort(int[] data, int n) {
        //ĩβ��ͷ�������������������
        for (int i = n; i > 0; i--) {
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            maxHeapify(data, i, 0);
        }
    }

    /* �����Ѷ�Ԫ�� */
    private static int pop(int[] data, int n) {
        int res = data[0];
        data[0] = data[n];
        buildMaxHeapify(data, n-1);
        heapSort(data, n-1);
        return res;
    }

    /* ���ڵ�λ�� */
    private static int getParentIndex(int current){
        return (current - 1) >> 1;
    }

    /* ���ӽڵ�positionע�����ţ��ӷ����ȼ����� */
    private static int getChildLeftIndex(int current){
        return (current << 1) + 1;
    }

    /* ���ӽڵ�position */
    private static int getChildRightIndex(int current){
        return (current << 1) + 2;
    }

    private static void print(int[] data){
        int pre = -2;
        for (int i = 0; i < data.length; i++) {
            if (pre < (int)getLog(i+1)) {
                pre = (int)getLog(i+1);
                System.out.println();
            }
            System.out.print(data[i] + " |");
        }
    }

    /* ��2Ϊ�׵Ķ��� */
    private static double getLog(double param){
        return Math.log(param)/ Math.log(2);
    }

    @Override
    public int[] sort(int[] a, int n) {
        buildMaxHeapify(a);
        heapSort(a, a.length-1);
        return a;
    }

    @Override
    public void sortObject(Comparable[] a, int n) {

    }

    public static void main(String args[]) {
//        int sort[] = {0, 2, 1};
        System.out.println("\n*********************");
        print(sort);
        buildMaxHeapify(sort);
        System.out.println("\n*********************");
        print(sort);
        heapSort(sort, sort.length - 1);
        System.out.println("\n*********************");
        print(sort);

        System.out.println("\n*********************");
        int res = pop(sort, sort.length - 1);
        System.out.println("pop() = " + res);
        print(sort);
    }
}
