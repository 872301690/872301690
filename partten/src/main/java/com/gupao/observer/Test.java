package com.gupao.observer;

import com.gupao.decorator.Pancake;
import com.gupao.decorator.PancakeWithAgg;
import com.gupao.decorator.PancakeWithWust;

public class Test {

    @org.junit.Test
    public  void main() {
        Pancake pancake = new Pancake();
        pancake  = new PancakeWithAgg(pancake);
        pancake  = new PancakeWithWust(pancake);
        pancake  = new PancakeWithWust(pancake);
        pancake  = new PancakeWithWust(pancake);
        pancake  = new PancakeWithAgg(pancake);
        pancake  = new PancakeWithAgg(pancake);

        System.out.println(pancake.getName()+"的价格是："+pancake.getPrice());
    }


    @org.junit.Test
    public void test(){
        Gper gper = new Gper();
        Teacher teacher = new Teacher("tom");

        gper.addObserver(teacher);
        gper.publish("设计模式不太懂，帮忙梳理一下");
    }
}
