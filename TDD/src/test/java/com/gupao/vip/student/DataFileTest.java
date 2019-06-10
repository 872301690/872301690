package com.gupao.vip.student;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

public class DataFileTest {
    private static final String ID1 = "123456";
    private static final String ID2 = "234567";
    private static final String FAILBASE = "DataFileTest";
    private DataFile db ;
    private TestData testData1;
    private TestData testData2;

    @Before
    public void init(){
        db = DataFile.create(FAILBASE);
        Assert.assertEquals(0,db.size());

        testData1 = new TestData(ID1,"datumla",1);
        testData2 = new TestData(ID2,"datumla",2);
    }

    @After
    public void after(){
        db.close();
        db.deleteFile();
    }

    @Test
    public void add(){
        db.add(ID1,testData1);
        Assert.assertEquals(1,db.size());

        db.add(ID2,testData2);
        Assert.assertEquals(2,db.size());

        assertTestDataEquals(testData1,(TestData)db.findById(ID1));
        assertTestDataEquals(testData2,(TestData)db.findById(ID2));
    }

    @Test
    public void persistence(){
        db.add(ID1,testData1);
        db.add(ID2,testData2);
        db.close();


        db = DataFile.open(FAILBASE);
        Assert.assertEquals(2,db.size());

        assertTestDataEquals(testData1,(TestData)db.findById(ID1));
        assertTestDataEquals(testData2,(TestData)db.findById(ID2));

        db = DataFile.create(FAILBASE);
        Assert.assertEquals(0,db.size());
    }
    @Test
    public void keyNotFound(){
        Assert.assertNull(db.findById(ID2));
    }
    private void assertTestDataEquals(TestData expected,TestData actual){
        Assert.assertEquals(expected.id,actual.id);
        Assert.assertEquals(expected.filed1,actual.filed1);
        Assert.assertEquals(expected.filed2,actual.filed2);
    }

    private static   class TestData implements Serializable {
        public String id;
        public String filed1;
        public int filed2;

        public TestData(String id, String filed1, int filed2) {
            this.id = id;
            this.filed1 = filed1;
            this.filed2 = filed2;
        }
    }
}
