package com.gupao.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface   GPInvocationHandler {

   Object invoke(Object proxy, Method method,Object[] args)
           throws InvocationTargetException, IllegalAccessException;
}
