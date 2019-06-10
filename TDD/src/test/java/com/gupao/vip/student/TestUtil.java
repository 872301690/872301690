package com.gupao.vip.student;

import org.junit.Assert;

import java.io.File;
public class TestUtil {
    public static void assertGone(String... filenames) {
        for(String fileName : filenames){
            Assert.assertTrue(new File(fileName).exists());
        }
    }

    public static void delete(String filename) {
        File file = new File(filename);
        if (file.exists())
            Assert.assertTrue(file.delete());
    }
}
