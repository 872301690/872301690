package com.gupaoedu.spring.framework.aop.aspect;

import java.lang.reflect.*;
public interface GPJointPoint {
    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
