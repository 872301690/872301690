package com.gupao.factory.factorymethod;

import org.junit.Test;

public class FactoryMethodTest {

    @Test
    public void test(){
        Factory factory = new AppleFactory();
        System.out.println(factory.create().name());

        factory = new OrangeFactory();
        System.out.println(factory.create().name());
    }
}
