package com.gupao.factory.abstractfactory;

import com.gupao.factory.Fruit;
import com.gupao.factory.Meat;

public interface AbstractFactory {
    Fruit createFruit();

    Meat createMeat();
}
