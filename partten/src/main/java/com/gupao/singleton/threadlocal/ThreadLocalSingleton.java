package com.gupao.singleton.threadlocal;


public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    public static final ThreadLocalSingleton getInstance(){
        return THREAD_LOCAL.get();
    }
}
