package com.gupao.observer.guava;

import com.google.common.eventbus.EventBus;

public class Gper extends EventBus {

    public void push(Teacher teacher,Question question){
        register(teacher);
        post(question);
    }
}
