package com.gupaoedu.spring.framework.webmvc.servlet;

import com.gupaoedu.mvcframework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * Created by Tom on 2019/4/13.
 */
public class GPHandlerAdapter {

    public boolean suppot(Object handler){
        return  handler instanceof  GPHandlerMapping;
    }
    GPModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        return null;
    }

    public GPModelAndView handler(HttpServletRequest req, HttpServletResponse resp, GPHandlerMapping handler) throws InvocationTargetException, IllegalAccessException {

        Method method = handler.getMethod();
        Object controller = handler.getController();

        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for(int i = 0; i < parameters.length;i++){
            Parameter parameter = parameters[i];

            if(parameter.getParameterizedType().getTypeName().equals(HttpServletRequest.class.getName())){
                args[i] = req;
            }else if(parameter.getParameterizedType().getTypeName().equals(HttpServletResponse.class.getName())){
                args[i] = resp;
            }else {
                GPRequestParam annotation = parameter.getAnnotation(GPRequestParam.class);
                if (annotation == null) continue;
                String name = annotation.value();
                Object value = convert(req.getParameter(name),parameter.getParameterizedType());
                args[i] = value;
            }

        }

        Object result = method.invoke(controller, args);
        if(result == null || method.getReturnType() == void.class )
        return null;

        boolean isModelAndView = method.getReturnType() == GPModelAndView.class;
        if(isModelAndView){
            return (GPModelAndView)result;
        }

        return null;
    }

    private Object convert(String value, Type parameterizedType) {
        if(parameterizedType == String.class){
            return value;
        }else if(parameterizedType == int.class || parameterizedType == Integer.class){
            return  Integer.valueOf(value);
        }

        return null;
    }
}
