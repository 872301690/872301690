package com.gupao.factory.factorymethod;

import com.gupao.factory.Fruit;
import com.gupao.factory.Orange;

public class OrangeFactory implements Factory {
    @Override
    public Fruit create() {
        return new Orange();
    }
}
