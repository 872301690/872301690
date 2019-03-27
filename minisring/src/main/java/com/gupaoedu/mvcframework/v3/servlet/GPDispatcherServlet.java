package com.gupaoedu.mvcframework.v3.servlet;

import com.gupaoedu.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {

    private Properties config = new Properties();

    private List<String> list = new ArrayList<String>();
    
    private Map<String,Object> ioc = new HashMap();

    private List<Handler> handlerlist = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = uri.replace(contextPath,"");

        Handler handler = getHandler(url);
        if(handler == null){
            resp.getWriter().write("404 NOT FOUND PAGE");
        }

        Map<Integer, Class> typeMap = handler.getTypeMap();
        Map<Integer, String> name = handler.getName();

        Object[] args = new Object[typeMap.size()];
        for(int i = 0; i < args.length;i++){
            if(typeMap.get(i) == HttpServletRequest.class) {
                args[i] = req;
            }else if(typeMap.get(i) == HttpServletResponse.class) {
                args[i] = resp;
            }else{
                args[i] = convertType(typeMap.get(i),req.getParameter(name.get(i)));
            }
        }

        try {
            Object returnValue = handler.getMethod().invoke(handler.getObject(), args);
            if(returnValue == null || returnValue instanceof Void){ return; }
            resp.getWriter().write(returnValue.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private Handler getHandler(String url) {
        for(Handler handler:handlerlist){
            if(handler.getUrl().equals(url)){
                return handler;
            }
        }
        return null;
    }

    private Object convertType(Type parameterizedType, String  o) {
        if(parameterizedType == String.class){
            return o;
        }else if(parameterizedType == Integer.class){
            return Integer.valueOf(o);
        }
        return null;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.包扫描
        doScan(this.config.getProperty("scanPackage"));
        //3.创建实例
        doNewInstance();
        //4.依赖注入
        doAutowired();
        //5.url映射
        doHandlerMapping();

    }

    private void doHandlerMapping() {
        if(ioc.isEmpty()) return;

        for( String key : ioc.keySet()){
            Object o = ioc.get(key);

            if(!o.getClass().isAnnotationPresent(GPController.class))continue;
            GPReauestMapping annotation = o.getClass().getAnnotation(GPReauestMapping.class);
            String controllerMapping = "";
            if(annotation != null){
                controllerMapping = annotation.value().replaceAll("/+","/");
            }

            Method[] methods = o.getClass().getMethods();
            for(Method method : methods){
                GPReauestMapping reauestMapping = method.getAnnotation(GPReauestMapping.class);
                
                if(reauestMapping == null) continue;
                handlerlist.add(new Handler(controllerMapping,method,o));
            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()) return;

        for( String key : ioc.keySet()){
            Object instance = ioc.get(key);
            Field[] declaredFields = instance.getClass().getDeclaredFields();
            for(Field field : declaredFields){
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                if(autowired == null) continue;

                String name = autowired.value();
                Object o = null;
                if(!"".equals(name)){
                    //按名字赋值
                    o = ioc.get(name);
                }else{
                    //按类型赋值
                    String type = field.getGenericType().getTypeName();
                     o = ioc.get(type);
                }
                field.setAccessible(true);
                try {
                    field.set(instance,o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private void doNewInstance() {
        if(list.isEmpty()) return;

       try {
           for (String className : list){
               Class<?> clazz = Class.forName(className);

                if(clazz.getAnnotation(GPController.class) != null){
                    Object o = clazz.newInstance();
                    String defaultName = toLowerStr(clazz.getSimpleName());
                    ioc.put(defaultName,o);
                    GPController controller = clazz.getAnnotation(GPController.class);
                    String defineName = controller.value();
                    if(!"".equals(defaultName)){
                        ioc.put(defaultName,o);
                    }
                }else if(clazz.getAnnotation(GPService.class) != null){
                    Object o = clazz.newInstance();
                    String defaultName = toLowerStr(clazz.getSimpleName());
                    ioc.put(defaultName,o);

                    GPService controller = clazz.getAnnotation(GPService.class);
                    String defineName = controller.value();
                    if(!"".equals(defaultName)){
                        ioc.put(defaultName,o);
                    }

                    //用类别注册
                    for(Class inter : clazz.getInterfaces()){
                        String interName = inter.getName();
                        ioc.put(interName,o);
                    }
                }


           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private String toLowerStr(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    private void doScan(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"
                + scanPackage.replaceAll("\\.","/"));
        File parentFile = new File(url.getPath());
        File[] files = parentFile.listFiles();
        for (File file : files){

            if(file.isDirectory()){doScan(scanPackage+"."+file.getName());}
            else{

                if(!file.getName().endsWith(".class")) continue;
                String className = scanPackage +"."+ file.getName().replaceAll("\\.class","");
                this.list.add(className);
            }
        }


    }

    private void doLoadConfig(String contextConfigLocation) {
        if(contextConfigLocation.contains("classpath:")){
            contextConfigLocation=contextConfigLocation.replaceAll("classpath:","");

        }
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class Handler{
        private String url;
        private Method method;
        private Object object;
        private Map<Integer,Class> typeMap = new HashMap<>();
        private Map<Integer,String> nameMap = new HashMap<>();

        public Map<Integer, Class> getTypeMap() {
            return typeMap;
        }

        public void setTypeMap(Map<Integer, Class> typeMap) {
            this.typeMap = typeMap;
        }

        public Map<Integer, String> getName() {
            return nameMap;
        }

        public void setName(Map<Integer, String> nameMap) {
            this.nameMap = nameMap;
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

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Handler(String url, Method method, Object object) {

            this.method = method;
            this.object = object;

            GPReauestMapping annotation = method.getAnnotation(GPReauestMapping.class);
            String mapping = annotation.value();
            String realUrl = url + "/" + mapping;
            this.url = realUrl.replaceAll("/+","/");


            initMethodParam(method);

        }

        private void initMethodParam(Method method){
            Class<?>[] parameterTypes = method.getParameterTypes();
            for(int i = 0; i < parameterTypes.length;i++){
                typeMap.put(i,parameterTypes[i]);
            }

            Parameter[] parameters = method.getParameters();
            for(int i = 0; i < parameters.length;i++){
                Parameter parameter = parameters[i];
                GPRequestParam annotation = parameter.getAnnotation(GPRequestParam.class);
                if(annotation == null) continue;
                String name = annotation.value();
                nameMap.put(i,name);
            }
        }
    }
}
