package com.gupao.factory.simplefactory;

import com.gupao.factory.Apple;
import com.gupao.factory.Orange;
import org.junit.Test;

public class SimpleFactoryTest {

    @Test
    public void test(){
        SimpleFactory factory = new SimpleFactory();
        System.out.println(factory.create("apple").name());
        System.out.println(factory.create("orange").name());
    }

    @Test
    public void test1(){
        SimpleFactory factory = new SimpleFactory();
        System.out.println(factory.create(Apple.class).name());
        System.out.println(factory.create(Orange.class).name());
    }
}
