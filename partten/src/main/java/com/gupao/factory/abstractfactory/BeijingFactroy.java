package com.gupao.factory.abstractfactory;

import com.gupao.factory.Apple;
import com.gupao.factory.Fruit;
import com.gupao.factory.Meat;
import com.gupao.factory.Pork;

public class BeijingFactroy implements AbstractFactory {
    @Override
    public Fruit createFruit() {
        return new Apple();
    }

    @Override
    public Meat createMeat() {
        return new Pork();
    }
}
