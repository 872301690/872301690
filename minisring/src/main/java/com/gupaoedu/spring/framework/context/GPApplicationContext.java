package com.gupaoedu.spring.framework.context;

import com.gupaoedu.spring.framework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.framework.beans.suppot.GPDefautListableApplicationContext;
import com.gupaoedu.spring.framework.context.suppot.GPBeanDefinitionReader;
import com.gupaoedu.spring.framework.core.GPBeanFacory;

import java.util.List;

public class GPApplicationContext extends GPDefautListableApplicationContext implements GPBeanFacory {

    private String[] configs;

    private GPBeanDefinitionReader reader;
    public GPApplicationContext(String ...configs)  {
        this.configs = configs;
        try {
            this.onfresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onfresh() throws Exception {
        reader = new GPBeanDefinitionReader(configs);
        List<GPBeanDefinition> list = reader.doLoadBeanDefinition();
        doRegisterBeanDefinition(list);
        doAutowired();
    }

    private void doAutowired() {
        for (String key : definitionMap.keySet()){
            GPBeanDefinition beanDefinition = definitionMap.get(key);
            if(!beanDefinition.isLazyInit()){
                getBean(beanDefinition.getName());
            }
        }
    }

    private void doRegisterBeanDefinition(List<GPBeanDefinition> list) throws Exception {
        for (GPBeanDefinition beanDefinition : list){
            if(definitionMap.get(beanDefinition.getName()) != null){
                throw  new Exception( "The " + beanDefinition.getName() + " is exists!!");
            }

            definitionMap.put(beanDefinition.getName(),beanDefinition);
        }
    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    public static void main(String[] args) {
        GPApplicationContext context = new GPApplicationContext("classPath:application.properties");
    }
}
