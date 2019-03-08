package com.gupao.factory.abstractfactory;

import com.gupao.factory.Beef;
import com.gupao.factory.Fruit;
import com.gupao.factory.Meat;
import com.gupao.factory.Orange;

public class GuangzhouFactroy implements AbstractFactory {
    @Override
    public Fruit createFruit() {
        return new Orange();
    }

    @Override
    public Meat createMeat() {
        return new Beef();
    }
}
