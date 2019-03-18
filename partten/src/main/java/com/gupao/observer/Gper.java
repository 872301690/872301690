package com.gupao.observer;

import java.util.Observable;

public class Gper extends Observable {


    public void publish(String msg){
        setChanged();
        notifyObservers(msg);
    }


}
