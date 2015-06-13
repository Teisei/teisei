package edu.ecnu.teisei.algo.divideAndConquer;

/**
 * fibonaci: 1,1,2,3,5,8,13,21,34,...
 * (1)getN: O(n)
 * (2)getN2: exponcial
 * Created by dingcheng on 2015/3/13.
 */
public class Fibonaci {
//    static int fibonaci[] = new int[1000];

    public long getN(int n) {
        long fibonaci[] = new long[n+10];
        fibonaci[1] = 1;
        fibonaci[2] = 1;
        if (fibonaci[n] != 0) {
            return fibonaci[n];
        }else {
            for (int i = 3; i < n + 1; i++) {
                fibonaci[i] = fibonaci[i - 1] + fibonaci[i - 2];
            }
            return fibonaci[n];
        }
    }

    public long getN2(long n) {
        if (n == 1 || n == 2) {
            return 1;
        }else {
            return getN2(n - 1) + getN2(n - 2);
        }
    }
    public static void main(String args[]) {
        Fibonaci fi = new Fibonaci();
        int n = 43;
        System.out.println(fi.getN(n));
        System.out.println(fi.getN2(n));

    }
}
