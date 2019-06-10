package com.gupao.vip.student;
import java.io.File;
public class IOUtil {
    public static boolean delete(String... filenames) {
        boolean deleteAll = true;
        for (String fileName : filenames){
           if(!new File(fileName).delete())
               deleteAll = false;
        }
        return deleteAll;
    }
}
