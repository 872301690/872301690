package com.gupao.singleton.register;

public enum  EnumSingleton {

    INSTANCE;
    private Object date;

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
