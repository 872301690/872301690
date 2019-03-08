package com.gupao.factory.simplefactory;

import com.gupao.factory.Apple;
import com.gupao.factory.Fruit;
import com.gupao.factory.Orange;

public class SimpleFactory {

    public Fruit create(String name){
        if(name.equals("apple")){
            return  new Apple();
        }else if (name.equals("orange")){
            return  new Orange();
        }else{
            throw  new RuntimeException("无法识别的水果");
        }
    }


    public  Fruit create(Class clazz){
        try {
            return (Fruit) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
