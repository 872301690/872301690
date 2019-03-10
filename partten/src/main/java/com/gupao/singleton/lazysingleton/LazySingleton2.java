package com.gupao.singleton.lazysingleton;

public class LazySingleton2 {

    private static LazySingleton2 lazySingleton2 = null;
    private LazySingleton2(){}
    public static final LazySingleton2 getInstance(){
        if(lazySingleton2 != null){
            synchronized (LazySingleton2.class){
                if (lazySingleton2 != null){
                    lazySingleton2 = new LazySingleton2();
                }
            }
        }

        return lazySingleton2;
    }
}
