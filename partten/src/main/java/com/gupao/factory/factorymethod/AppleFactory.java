package com.gupao.factory.factorymethod;

import com.gupao.factory.Apple;
import com.gupao.factory.Fruit;

public class AppleFactory implements Factory {
    @Override
    public Fruit create() {
        return new Apple();
    }
}
