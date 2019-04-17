package com.gupaoedu.spring.framework.webmvc.servlet;

import java.util.*;
/**
 * Created by Tom on 2019/4/13.
 */
public class GPModelAndView {
    private String viewName;
    private Map<String,Object> model;
    public GPModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public GPModelAndView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
