package com.gupaoedu.spring.framework.webmvc.servlet;

import com.gupaoedu.mvcframework.annotation.GPReauestMapping;
import com.gupaoedu.spring.framework.annotation.GPController;
import com.gupaoedu.spring.framework.annotation.GPRequestMapping;
import com.gupaoedu.spring.framework.context.GPApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GPDispatcherServlet extends HttpServlet {

    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";
    private GPApplicationContext context;

    private List<GPHandlerMapping> handlerMappings = new ArrayList();

    private List<GPViewResolver> viewResolvers = new ArrayList<>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new ConcurrentHashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
         context = new GPApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
         initStrategies(context);
    }

    private void initStrategies(GPApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);
        //handlerMapping，必须实现
        initHandlerMappings(context);
        //初始化参数适配器，必须实现
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);
        //初始化视图转换器，必须实现
        initViewResolvers(context);
        //参数缓存器
        initFlashMapManager(context);
    }

    private void initLocaleResolver(GPApplicationContext context) {

    }

    private void initHandlerMappings(GPApplicationContext context) {
        Set<String> beanDefinitionNames = context.getBeanDefinitionNames();
        for(String beanName : beanDefinitionNames){
            Object controller = context.getBean(beanName);
            Class<?> aClass = controller.getClass();

            GPController controller1 = aClass.getAnnotation(GPController.class);
            if(controller1 == null) continue;
            GPRequestMapping controllerRequestMapping = aClass.getAnnotation(GPRequestMapping.class);
            String url = "/";
            if (controllerRequestMapping != null) url += controllerRequestMapping.value().replaceAll("/+","/");

            Method[] methods = aClass.getMethods();
            for (Method method : methods){
                GPRequestMapping annotation = method.getAnnotation(GPRequestMapping.class);
                if(annotation == null) continue;

                url = (url + annotation.value().replaceAll("/+","/")).replaceAll("/+","/");
                handlerMappings.add(new GPHandlerMapping(url,method,controller));
            }

        }
    }

    private void initThemeResolver(GPApplicationContext context) {
    }

    private void initHandlerAdapters(GPApplicationContext context) {
        for(GPHandlerMapping mapping : handlerMappings){
            this.handlerAdapters.put(mapping,new GPHandlerAdapter());
        }

    }

    private void initViewResolvers(GPApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String file = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(file);
        String[] list = templateRootDir.list();
        for(String str : list){
            viewResolvers.add(new GPViewResolver(str));
        }
    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(GPApplicationContext context) {
    }

    private void initFlashMapManager(GPApplicationContext context) {
    }

    private void initMultipartResolver(GPApplicationContext context) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {

            doDispatcher(req,resp);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        GPHandlerMapping handler = getHadler(req);

        if(handler == null){
            processDispatchResult(req,resp,new GPModelAndView("404"));
            return;
        }

        GPHandlerAdapter ha = getHandlerAdapter(handler);

        GPModelAndView mv = ha.handler(req, resp, handler);

        processDispatchResult(req,resp,mv);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, GPModelAndView mv) throws Exception {
        if(mv == null) return;
        for(GPViewResolver resolver : viewResolvers){
            GPView view = resolver.resolver(mv.getViewName(),null);
            view.render(mv.getModel(),req,resp);
            return;
        }
    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handler) {

        if(handlerAdapters.isEmpty()) return null;
        GPHandlerAdapter adapter = handlerAdapters.get(handler);
        if(adapter.suppot(handler)) return adapter;

        return null;
    }

    private GPHandlerMapping getHadler(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = uri.replace(contextPath, "").replaceAll("/+", "/");

        GPHandlerMapping hadler = null;
        for(GPHandlerMapping hd : handlerMappings){
            if(url.equals(hd.getUrl())){
                hadler = hd;
                break;
            }

        }
        return  hadler;
    }
}
