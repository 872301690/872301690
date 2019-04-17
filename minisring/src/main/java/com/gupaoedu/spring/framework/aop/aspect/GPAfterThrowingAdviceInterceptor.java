package com.gupaoedu.spring.framework.aop.aspect;

import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInterCeptor;
import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInvocation;

import java.lang.reflect.Method;

public class GPAfterThrowingAdviceInterceptor extends GPAbstactAspectAdvice implements GPAdvice, GPMethodInterCeptor {


    private String ThrowingName;
    public GPAfterThrowingAdviceInterceptor(Method method, Object newInstance) {
        
        super(method,newInstance);
    }

    public void setThrowName(String aspectAfterThrowingName) {
        ThrowingName = aspectAfterThrowingName;
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }
}
