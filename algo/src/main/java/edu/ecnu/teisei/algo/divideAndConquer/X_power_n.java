package edu.ecnu.teisei.algo.divideAndConquer;

/**
 * calculate: x^n
 * (1)general way: O(N)
 * (2)divide and conquer: O(logN)
 * Created by dingcheng on 2015/3/13.
 */
public class X_power_n {
    static long cache[] = new long[30];
    static int time = 0;
    static int time2 = 0;

    public static long getN(long x, int n) {
        if (cache[n] != 0) {
            return cache[n];
        }else if (n == 1) {
            cache[n] = x;
            return x;
        } else if (n % 2 == 0) {
            cache[n] = getN(x, n / 2) * getN(x, n / 2);
            time++;
            return cache[n];
        } else {
            cache[n] = getN(x, n / 2) * getN(x, n / 2 + 1);
            time++;
            return cache[n];
        }
    }

    public static long getN2(long x, int n) {
        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= x;
            time2++;
        }
        return res;
    }
    public static void main(String args[]) {
        long x = 3;
        int n = 20;
        System.out.println(getN(x, n) + "  " + time);
        System.out.println(getN2(x, n) + "  " + time2);
    }
}
