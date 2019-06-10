package com.gupao.vip.log;


import com.gupao.vip.course.Course;
import com.gupao.vip.course.CourseSession;
import com.gupao.vip.course.Session;
import com.gupao.vip.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CourseCatalogTest {

    private CourseCatalog catalog ;
    private Session session1;
    private Session session2;
    private Course course1;
    private Course course2;

    @Before
    public void init(){
        catalog = new CourseCatalog();
        course1 = new Course("a",1);
        course2 = new Course("a",1);
        session1 = CourseSession.create(course1, DateUtil.createDate(2015,1,15));
        session1.setNumOfCredits(5);
        session2 = CourseSession.create(course2, DateUtil.createDate(2015,1,17));
        session2.setNumOfCredits(5);

        catalog.add(session1);
        catalog.add(session2);
    }

    @Test
    public void storeAndLoad() throws IOException {
        final String fileName = "CourseCatalogTest.testAdd.txt";
        catalog.store(fileName);
        catalog.clearAll();
        Assert.assertEquals(0,catalog.getSessions().size());
        catalog.load(fileName);
        List<Session> sessionList = catalog.getSessions();

        Assert.assertEquals(2,sessionList.size());
        assertSession(session1,sessionList.get(0));
        assertSession(session2,sessionList.get(1));
    }

    private void assertSession(Session expected, Session retrieved) {
        Assert.assertNotSame(expected,retrieved);
        Assert.assertEquals(expected.getNumOfCredits(),retrieved.getNumOfCredits());
        Assert.assertEquals(expected.getStartDate(),retrieved.getStartDate());
        Assert.assertEquals(expected.getDepartment(),retrieved.getDepartment());
        Assert.assertEquals(expected.getNumber(),retrieved.getNumber());
    }

}
