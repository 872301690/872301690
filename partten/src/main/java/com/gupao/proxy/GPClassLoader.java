package com.gupao.proxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GPClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name/**Proxy*/) {
        String rootPath = GPClassLoader.class.getResource("").getPath();
        File parent = new File(rootPath);
        if(parent.exists() && parent.isDirectory()){
            String path = rootPath+"Proxy.class";
            String className = GPClassLoader.class.getPackage().getName() + ".Proxy";
            File f = new File(path);
            try {
            FileInputStream in = null;
                in = new FileInputStream(f);
            byte[] b = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int l = 0;
            while (( l =in.read(b)) != -1){
                out.write(b,0,l);
            }
            in.close();
            out.close();
            return defineClass(className,out.toByteArray(),0,out.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(GPClassLoader.class.getResource("").getPath());
    }
}
