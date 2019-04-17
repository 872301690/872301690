package com.gupaoedu.spring.framework.context;

import com.gupaoedu.spring.framework.annotation.GPAutowired;
import com.gupaoedu.spring.framework.aop.GPAopProxy;
import com.gupaoedu.spring.framework.aop.GPJDKAopProxy;
import com.gupaoedu.spring.framework.aop.config.GPAOPConfig;
import com.gupaoedu.spring.framework.aop.support.GPAdvisedSupport;
import com.gupaoedu.spring.framework.beans.GPBeanWapper;
import com.gupaoedu.spring.framework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.framework.beans.config.GPBeanPostProcessor;
import com.gupaoedu.spring.framework.beans.suppot.GPDefautListableApplicationContext;
import com.gupaoedu.spring.framework.context.suppot.GPBeanDefinitionReader;
import com.gupaoedu.spring.framework.core.GPBeanFacory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GPApplicationContext extends GPDefautListableApplicationContext implements GPBeanFacory {

    private String[] configs;

    private Map<String, GPBeanWapper> signtonObject = new ConcurrentHashMap();
    private Map<String,GPBeanWapper> objects = new ConcurrentHashMap();

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
                try {
                    getBean(beanDefinition.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    public Object getBean(String name)  {

        GPBeanDefinition beanDefinition = this.definitionMap.get(name);
        try {


            //缓存中没有
            Object instance = null;
            GPBeanPostProcessor processor = new GPBeanPostProcessor();

            processor.postProcessBeforeInitialization(instance,name);
            //创建Bean
            instance = doCreateBean(beanDefinition,name);

            GPBeanWapper beanWapper = new GPBeanWapper(instance);
            signtonObject.put(name,beanWapper);
            signtonObject.put(instance.getClass().getName(),beanWapper);
            objects.put(name,beanWapper);
            processor.postProcessAfterInitialization(instance,name);

            populateBean(name,beanDefinition,beanWapper);

        }catch (Exception e){
            e.printStackTrace();
        }

        return this.objects.get(name).getInstances();
    }

    private void populateBean(String name, GPBeanDefinition beanDefinition, GPBeanWapper beanWapper) throws IllegalAccessException, InstantiationException {
        Object instances = null;
        if(beanDefinition.isSington()){
            instances =  beanWapper.getInstances();
        }else{
            instances = beanWapper.getWappedClass().newInstance();
        }

        Field[] fields = instances.getClass().getDeclaredFields();

        for(int i = 0 ; i < fields.length ; i++){
            Field field = fields[i];
            GPAutowired autowired = field.getAnnotation(GPAutowired.class);
            if(autowired == null) continue;

            String value = autowired.value();
            if(value == null || value.equals("")){
                //按类型注入
               value = field.getType().getName();

            }
            field.setAccessible(true);
            if(this.objects.get(value) == null){ continue; }

            field.set(instances,objects.get(value).getInstances());

        }

    }

    private Object doCreateBean(GPBeanDefinition beanDefinition, String name) throws Exception {
         if(beanDefinition.isSington()){
            GPBeanWapper beanWapper = signtonObject.get(name);
            if(beanWapper != null) return beanWapper.getInstances();
        }else{
            GPBeanWapper beanWapper = objects.get(name);
            if(beanWapper != null) return beanWapper.getWappedClass().newInstance();
        }

        Object instance = null;
        String className = beanDefinition.getClassName();
        Class<?> aClass = Class.forName(className);
        instance = aClass.newInstance();

        GPAdvisedSupport config = new GPAdvisedSupport();
        config.setTarget(instance);
        config.setTargetClass(aClass);

        if(config.pointCutMatch()){
            instance = createProxy(config).getProxy();
        }

        return instance;
    }

    private GPAopProxy createProxy(GPAdvisedSupport config) {
        if(config.getTargetClass().getInterfaces().length > 0){
            return new GPJDKAopProxy(config);
        }
        return null;
    }


    public  Set<String> getBeanDefinitionNames(){
        return definitionMap.keySet();
    }

    public static void main(String[] args) throws Exception {
        GPApplicationContext context = new GPApplicationContext("classPath:application.properties");
        context.getBean("demoAction");
    }

    public Properties getConfig(){
        return  reader.getConfig();
    }
}
