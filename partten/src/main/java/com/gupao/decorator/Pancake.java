package com.gupao.decorator;

/**
 * 装饰器模式是同跟同源，即对父对象进行包装，补充父对象的功能
 *
 * 适配器模式并不是同根同源的，只是对不同算法进行包装，
 * 是系统能够在运行时能够选择合适的算法，所包装的算法之间可以没有任何关系
 */
public class Pancake {

    String name = "一个煎饼";

    int price = 5;

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}
