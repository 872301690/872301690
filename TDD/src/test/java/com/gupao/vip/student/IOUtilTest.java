package com.gupao.vip.student;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class IOUtilTest {

    static final String FILENAME1 = "IOUtilTest1.txt";
    static final String FILENAME2 = "IOUtilTest2.txt";

    @Test
    public void deleteSingleFile() throws IOException {
        create(FILENAME1);
        Assert.assertTrue(IOUtil.delete(FILENAME1));
        TestUtil.assertGone(FILENAME1);
    }

    @Test
    public void deleteMuliteFile() throws IOException {
        create(FILENAME1,FILENAME2);
        Assert.assertTrue(IOUtil.delete(FILENAME1,FILENAME2));
        TestUtil.assertGone(FILENAME1,FILENAME2);
    }

    @Test
    public void deleteNoFile(){
        TestUtil.delete(FILENAME1);
        Assert.assertTrue(IOUtil.delete(FILENAME1));
    }

    @Test
    public void deletePartiallySuccess() throws IOException {
        create(FILENAME1);
        TestUtil.delete(FILENAME2);
        Assert.assertFalse(IOUtil.delete(FILENAME1,FILENAME2));
        TestUtil.assertGone(FILENAME1);


    }

    private void create(String... filenames) throws IOException {
        for(String fileName : filenames){
            TestUtil.delete(fileName);
            new File(fileName).createNewFile();
        }
    }
}
