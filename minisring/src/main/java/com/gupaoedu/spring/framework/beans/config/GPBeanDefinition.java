package com.gupaoedu.spring.framework.beans.config;

public class GPBeanDefinition {
    private String className;
    private boolean isLazyInit;
    private String name;
    private boolean isSington = true;

    public boolean isSington() {
        return isSington;
    }

    public void setSington(boolean sington) {
        isSington = sington;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isLazyInit() {
        return isLazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        isLazyInit = lazyInit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
