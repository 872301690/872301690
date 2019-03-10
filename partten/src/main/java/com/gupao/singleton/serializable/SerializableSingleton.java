package com.gupao.singleton.serializable;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private SerializableSingleton() {}
    private static final SerializableSingleton SERIALIZABLE_SINGLETON = new SerializableSingleton();
    public static final SerializableSingleton getInstance(){
        return SERIALIZABLE_SINGLETON;
    }


    private Object readresolver(){
        return SERIALIZABLE_SINGLETON;
    }

}
