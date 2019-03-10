package com.gupao.singleton.lazysingleton;

public class LazySingleton1 {

    private static LazySingleton1 lazySingleton1 = null;
    private LazySingleton1(){}
    public synchronized final static LazySingleton1 getInstance(){
        if(lazySingleton1 != null){
            lazySingleton1 = new LazySingleton1();
        }
        return lazySingleton1;
    }
}
