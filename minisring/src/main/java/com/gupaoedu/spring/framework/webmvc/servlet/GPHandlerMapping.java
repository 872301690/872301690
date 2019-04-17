package com.gupaoedu.spring.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by Tom on 2019/4/13.
 */
public class GPHandlerMapping {

    private String url;
    private Method method;
    private Object controller;
    public GPHandlerMapping(String url, Method method, Object controller) {
        this.url = url;
        this.method = method;
        this.controller = controller;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
