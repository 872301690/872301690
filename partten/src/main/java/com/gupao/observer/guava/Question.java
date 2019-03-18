package com.gupao.observer.guava;


public class Question {

    private String user;

    private String msg;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return user + "的问题是：" + msg ;
    }
}
