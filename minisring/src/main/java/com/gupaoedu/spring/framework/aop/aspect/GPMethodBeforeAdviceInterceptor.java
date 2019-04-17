package com.gupaoedu.spring.framework.aop.aspect;

import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInterCeptor;
import com.gupaoedu.spring.framework.aop.interceptor.GPMethodInvocation;

import java.lang.reflect.Method;

public class GPMethodBeforeAdviceInterceptor extends GPAbstactAspectAdvice implements GPAdvice, GPMethodInterCeptor {
    private GPJointPoint jointPoint;
    public GPMethodBeforeAdviceInterceptor(Method method, Object o) {
        super(method,o);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        this.jointPoint = mi;
        before(mi.getMethod(),mi.getArguments(),mi.getThis());
        return mi.proceed();
    }

    private void before(Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.jointPoint,null,null);
    }
}
