package com.gupao.vip.performance;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceTest {

    private final static double tolerance = 0.005;
    @Test
    public void avg(){
        Performance performance = new Performance();
        performance.setNumOfTests(4);
        performance.set(0,98);
        performance.set(1,92);
        performance.set(2,81);
        performance.set(3,72);

        Assert.assertEquals(92,performance.get(1));
        Assert.assertEquals(85.75,performance.getAvg(),tolerance);
    }

    @Test
    public void init (){
        Performance performance = new Performance();
        performance.setScores(75,72,90,60);
        Assert.assertEquals(74.25,performance.getAvg(),tolerance);

        performance.setScores(90,60);
        Assert.assertEquals(75,performance.getAvg(),tolerance);
    }
}
