package com.gupaoedu.spring.framework.beans;

public class GPBeanWapper {
    private Object instances;
    private Class wappedClass;

    public GPBeanWapper(Object instance) {
        this.instances = instance;
        this.wappedClass = instance.getClass();
    }

    public Object getInstances() {
        return instances;
    }

    public void setInstances(Object instances) {
        this.instances = instances;
    }

    public Class getWappedClass() {
        return wappedClass;
    }

    public void setWappedClass(Class wappedClass) {
        this.wappedClass = wappedClass;
    }
}
