package com.gupao.proxy;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GPMeiPo implements GPInvocationHandler {

    private Object target;

    public  Object getProxy(Object o) throws Exception {
        this.target = o;
        return GPProxy.getProxy(new GPClassLoader(),o.getClass().getInterfaces()[0],this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        System.out.println("你找什么条件的啊");
        System.out.println("帮你物色");
        Object o = method.invoke(target,args);
        System.out.println("找到了，开始办事吧");
        return o;
    }
}
