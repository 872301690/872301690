package com.gupaoedu.mvcframework.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPReauestMapping {
    String value() default "";
}
