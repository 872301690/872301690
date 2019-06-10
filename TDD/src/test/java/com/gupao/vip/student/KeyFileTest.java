package com.gupao.vip.student;

import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class KeyFileTest {

    public final static String FILENAME = "keyfiletest.idx";
    public final static String KEY = "key";
    public final static long POSITION = 1;
    public final static int LENGTH = 100;

    private KeyFile keyFile;

    @Test
    public void init(){
        TestUtil.delete(FILENAME);
        keyFile = new KeyFile(FILENAME);
    }
    @After
    public void after() throws IOException {
        TestUtil.delete(FILENAME);
        keyFile.close();
    }

    @Test
    public void create(){
        Assert.assertEquals(0,keyFile.size());
    }

    @Test
    public void addEntey(){
        keyFile.add(KEY,POSITION,LENGTH);

        Assert.assertEquals(1,keyFile.size());
        Assert.assertTrue(keyFile.containsKey(KEY));
        Assert.assertEquals(POSITION,keyFile.getPosition(KEY));
        Assert.assertEquals(LENGTH,keyFile.getLength(KEY));
    }

    @Test
    public void reopen() throws IOException {
        keyFile.add(KEY, POSITION,LENGTH);
        keyFile.close();

        keyFile = new KeyFile(FILENAME);
        Assert.assertEquals(1,keyFile.size());
        Assert.assertEquals(POSITION,keyFile.getPosition(KEY));
        Assert.assertEquals(LENGTH,keyFile.getLength(KEY));
    }
}
