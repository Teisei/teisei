package edu.ecnu.teisei.algo.hash.functions;

import org.junit.Test;

/**
 * Created by Teisei on 2015/3/23.
 */
public class NumberHashFuncs {

    double A;
    int m;
    int prime;

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public int division(int key) {
        return key % prime;
    }

    public int multiplication(int key) {
        return (int) Math.floor(((double) key * A % 1) * m);
    }

    @Test
    public void testOne() {
        int key = 3;
        double A = 1.11111;
        double res = key * A;
        System.out.println(res);
        res = res - Math.floor(res);
        System.out.println(res);
        double temp = (double) key * A % 1;
        System.out.println(temp);
    }
}
