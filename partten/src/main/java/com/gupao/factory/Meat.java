package com.gupao.factory;

public abstract class Meat {

    public String name(){
        String simpleName = this.getClass().getSimpleName();
        return "I am " + simpleName;
    }
}
