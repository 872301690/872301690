package com.gupao.vip.performance;

public class Performance {
    private int[] tests ;

    public void setNumOfTests(int num) {
        tests = new int[num];
    }

    public void set(int test, int score) {
        tests[test] = score;
    }

    public int get(int test) {
        return tests[test];
    }

    public double getAvg() {
        if (tests.length == 0)
        return 0;
        double total = 0;
        for (int score : tests){
            total += score;
        }
        return total / tests.length;
    }

    public void setScores(int... score) {
        tests = score;
    }
}
