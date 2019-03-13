package com.gupao.proxy;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GPProxy {

    private static final String  ln = "\r\n";

    public static Object getProxy(GPClassLoader classLoader,
                                  Class inter,GPInvocationHandler h) throws Exception {


        String code = build(inter);
        File file = new File(GPProxy.class.getResource("").getPath()+"/Proxy.java");
        FileOutputStream out = new FileOutputStream(file);
        out.write(code.getBytes());

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, iterable);


        task.call();
        manager.close();
        out.close();

        Class<?> proxy = classLoader.findClass("Proxy");
        Constructor<?> constructor = proxy.getConstructor(GPInvocationHandler.class);
        return constructor.newInstance(h);
    }

    public static void main(String[] args) {
        System.out.println(GPProxy.class.getPackage().getName());
    }

    private static String build(Class inter) {

        StringBuffer sb = new StringBuffer();
        sb.append("package com.gupao.proxy;" + ln);
        sb.append("import  java.lang.reflect.*;" + ln);
        sb.append("public class Proxy implements " + inter.getSimpleName() + "{"+ln);
        sb.append("private GPInvocationHandler h;"+ln);
        sb.append("public Proxy(GPInvocationHandler h){"+ln);
          sb.append("this.h = h;"+ln);
        sb.append("}"+ln);

        for (Method m : inter.getMethods()){
            sb.append("public "+ m.getReturnType().getName()+" "+m.getName()+"(){"+ln);
                sb.append("try{"+ln);
                    sb.append("Method m = "+inter.getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
                    sb.append("h.invoke(this,m,null);"+ln);
                sb.append("}catch(Throwable t){"+ln);
                    sb.append("t.printStackTrace();"+ln);
                sb.append("}"+ln);
            sb.append("}"+ln);
        }

        sb.append("}" + ln);


        return sb.toString();
    }
}
