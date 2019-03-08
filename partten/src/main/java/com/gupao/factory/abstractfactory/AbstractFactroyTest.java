package com.gupao.factory.abstractfactory;

import org.junit.Test;

public class AbstractFactroyTest {

    @Test
    public void test(){
        AbstractFactory factory = new BeijingFactroy();
        System.out.println(factory.createFruit().name());
        System.out.println(factory.createMeat().name());

    }

    @Test
    public void test1(){
        AbstractFactory factory = new GuangzhouFactroy();
        System.out.println(factory.createFruit().name());
        System.out.println(factory.createMeat().name());

    }
}
