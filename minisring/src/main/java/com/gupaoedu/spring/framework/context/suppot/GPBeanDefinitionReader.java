package com.gupaoedu.spring.framework.context.suppot;

import com.gupaoedu.spring.framework.annotation.GPController;
import com.gupaoedu.spring.framework.annotation.GPService;
import com.gupaoedu.spring.framework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class GPBeanDefinitionReader {

    private Properties config = new Properties();
    private static  final String  SCAN_PACKAGE = "scanPackage";
    private List<String> registerClass = new ArrayList<>();

    public Properties getConfig(){
        return config;
    }
    public GPBeanDefinitionReader(String[] configs) {
        for (int i = 0; i < configs.length;i++){
            String config = configs[i];
            if(config.startsWith("classPath:")){
                config = config.replaceAll("classPath:","");
            }
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(config);
            try {
                this.config.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        doScan(this.config.getProperty(SCAN_PACKAGE));
    }

    private void doScan(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File file = new File(url.getFile());
        if (!file.isDirectory()){return;}

        File[] files = file.listFiles();
        for (File f : files){
            if(f.isDirectory()) doScan(scanPackage+"."+f.getName());
            else {
                if (!f.getName().endsWith(".class")) continue;
                registerClass.add(scanPackage + "." + f.getName().replaceAll(".class",""));
            }
        }
    }

    public List<GPBeanDefinition> doLoadBeanDefinition() {
        List<GPBeanDefinition> list = new ArrayList<>();
        for(String className : registerClass)
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isInterface()) continue;

                Annotation controller = clazz.getAnnotation(GPController.class);
                Annotation service = clazz.getAnnotation(GPService.class);
                if(controller == null && service == null) continue;
                String name = toLowerFirstCase(clazz.getSimpleName());
                GPBeanDefinition beanDefinition = createBeanDefinition(name,className);
                list.add(beanDefinition);

                Class<?>[] interfaces = clazz.getInterfaces();
                for(Class inter : interfaces){
                    list.add(createBeanDefinition(inter.getName(),className));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return list;
    }

    private GPBeanDefinition createBeanDefinition(String name, String className) {
        GPBeanDefinition beanDefinition = new GPBeanDefinition();
        beanDefinition.setClassName(className);
        beanDefinition.setName(name);
        return beanDefinition;

    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] = (char)(chars[0] + 32);
        return new String(chars);
    }
}
