package com.gupao.singleton.register;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    private static final Map<String ,Object> MAP = new ConcurrentHashMap<>();

    private ContainerSingleton(){}

    public  static final Object getInstance(String name){
        if (!MAP.containsKey(name)){
            synchronized (ContainerSingleton.class){
                if (!MAP.containsKey(name)){
                    try {
                        MAP.put(name,Class.forName(name).newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return  MAP.get(name);
    }

}
