package com.gupaoedu.demo.mvc.action;

import com.gupaoedu.demo.service.IDemoService;
import com.gupaoedu.spring.framework.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private IDemoService iDemoService;

    @GPRequestMapping("/query")
    public void query(HttpServletResponse response, HttpServletRequest request,
                     @GPRequestParam("name") String name){
            String result = "My name is " + name;
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("add")
    public void add(HttpServletRequest request,HttpServletResponse response,
                    @GPRequestParam("a")Integer a,  @GPRequestParam("b")Integer b){
        Integer sum = a+b;
        try {
            response.getWriter().write(a + "+" + b + "=" + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
