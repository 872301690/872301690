package com.gupaoedu.spring.framework.aop.aspect;

import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInterCeptor;
import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInvocation;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class GPAfterReturningAdviceInterceptor extends GPAbstactAspectAdvice
                implements GPAdvice, GPMethodInterCeptor {
    private GPJointPoint jointPoint;
    public GPAfterReturningAdviceInterceptor(Method method, Object o) {
        super(method,o);
    }


    private void afterReturning(Object proceed, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.jointPoint,proceed,null);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        Object proceed = mi.proceed();
        this.jointPoint = mi;
        this.afterReturning(proceed,mi.getMethod(),mi.getArguments(),mi.getThis());
        return proceed;
    }
}
