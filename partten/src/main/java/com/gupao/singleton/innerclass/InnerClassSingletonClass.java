package com.gupao.singleton.innerclass;

public class InnerClassSingletonClass {

    private InnerClassSingletonClass(){
        if (innerClass.INNER_CLASS_SINGLETON  != null){
            throw  new RuntimeException("不允许创建多个实例");
        }
    }
    public static final InnerClassSingletonClass getInstance(){
        return innerClass.INNER_CLASS_SINGLETON;
    }

    static class innerClass{
        public static  final InnerClassSingletonClass INNER_CLASS_SINGLETON
                = new InnerClassSingletonClass();
    }
}
