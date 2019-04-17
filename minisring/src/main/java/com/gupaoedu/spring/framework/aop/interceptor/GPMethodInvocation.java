package com.gupaoedu.spring.framework.aop.interceptor;

import com.gupaoedu.spring.framework.aop.aspect.GPJointPoint;

import java.lang.reflect.Method;
import java.util.List;

public class GPMethodInvocation  implements GPJointPoint {
    private Object proxy;
    private Method method;
    private Object target;
    private Object [] arguments;
    private List interceptorsAndDynamicMethodMatchers;
    private Class<?> targetClass;


    private int currentInterceptorIndex = -1;
    public GPMethodInvocation(
            Object proxy, Object target, Method method, Object[] arguments,
            Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

        this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }


    public Object proceed() throws Exception{
        if(this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() -1){
            return  method.invoke(target,arguments);
        }
        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++currentInterceptorIndex);

        if(interceptorOrInterceptionAdvice instanceof  GPMethodInterCeptor){
            GPMethodInterCeptor mi = (GPMethodInterCeptor)interceptorOrInterceptionAdvice;
            return  mi.invoke(this);
        }else {
          return   proceed();
        }

    }

    @Override
    public Object getThis() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public void setUserAttribute(String key, Object value) {

    }

    @Override
    public Object getUserAttribute(String key) {
        return null;
    }
}
