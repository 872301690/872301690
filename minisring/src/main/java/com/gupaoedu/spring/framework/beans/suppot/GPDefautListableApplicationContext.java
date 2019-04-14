package com.gupaoedu.spring.framework.beans.suppot;

import com.gupaoedu.spring.framework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.framework.context.suppot.GPAbstactApplicationContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GPDefautListableApplicationContext extends GPAbstactApplicationContext {
    protected Map<String, GPBeanDefinition> definitionMap = new ConcurrentHashMap();

    @Override
    protected void onfresh() throws Exception {

    }
}
