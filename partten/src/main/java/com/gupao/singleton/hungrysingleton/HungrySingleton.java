package com.gupao.singleton.hungrysingleton;


public class HungrySingleton  {

    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();
    private  HungrySingleton(){}

    public static  final  HungrySingleton getInstance(){
        return  HUNGRY_SINGLETON;
    }
}
