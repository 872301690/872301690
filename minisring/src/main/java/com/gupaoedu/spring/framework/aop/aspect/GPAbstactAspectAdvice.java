package com.gupaoedu.spring.framework.aop.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public abstract class GPAbstactAspectAdvice implements GPAdvice {

    private Method apsectMethod;
    private Object apsectTarget;
    public GPAbstactAspectAdvice(Method method, Object o) {
        this.apsectMethod = method;
        this.apsectTarget = o;
    }

    protected  Object invokeAdviceMethod(GPJointPoint jointPoint, Object proceed, Throwable o) throws Throwable{
        Class<?> [] paramTypes = this.apsectMethod.getParameterTypes();
        if(null == paramTypes || paramTypes.length == 0){
            return this.apsectMethod.invoke(apsectTarget);
        }else{
            Object [] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i ++) {
                if(paramTypes[i] == GPJointPoint.class){
                    args[i] = jointPoint;
                }else if(paramTypes[i] == Throwable.class){
                    args[i] = o;
                }else if(paramTypes[i] == Object.class){
                    args[i] = proceed;
                }
            }
            return this.apsectMethod.invoke(apsectTarget,args);
        }
    }
}
