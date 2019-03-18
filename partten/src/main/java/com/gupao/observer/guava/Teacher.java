package com.gupao.observer.guava;

import com.google.common.eventbus.Subscribe;

public class Teacher {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Subscribe
    public void recevier(Question question){
        System.out.println("我是" + name + ",收到" + question.getUser()+"的问题。");
        System.out.println(question.toString());
    }
}
