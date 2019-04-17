package com.gupaoedu.spring.framework.webmvc.servlet;

import java.net.URL;
import java.util.Locale;
import java.io.File;
/**
 * Created by Tom on 2019/4/13.
 */
public class GPViewResolver {

    private File templateRoot;

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    public GPViewResolver(String templateRoot) {
        URL resource = this.getClass().getClassLoader().getResource(templateRoot);
        this.templateRoot = new File(resource.getFile());
    }

    public GPView resolver(String viewName, Locale locale) throws Exception{
        if (viewName == null || "".equals(viewName.trim())) return null;
        if(viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX)) {

           viewName = viewName.replaceAll("/+","/");
        }else{

            viewName = (viewName + DEFAULT_TEMPLATE_SUFFIX).replaceAll("/+","/");
        }

        return new GPView(new File(templateRoot,viewName));
    }

}
