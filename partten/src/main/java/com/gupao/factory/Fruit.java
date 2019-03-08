package com.gupao.factory;

public abstract class Fruit {

    public String name(){
        String simpleName = this.getClass().getSimpleName();
        return "I am " + simpleName;
    }
}
