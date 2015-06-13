package edu.ecnu.teisei.algo.sort;

/**
 * Created by dingcheng on 2015/3/12.
 */
public class test {

    static double c = 500.0;
    static double f = 4.0;
    static double x = 2000.0;

    public static void main(String args[]) {
        for (int i = 2; i < 5; i++) {
            getRes(i);
        }
    }

    public static void getRes(int n) {

        double time = 0.0;
        for (int i = 0; i < n; i++) {
            time += c / (2 + i * f);
        }
        time += x / (2 + n * f);
        System.out.println("n = " + n + " " + time);
    }
}
