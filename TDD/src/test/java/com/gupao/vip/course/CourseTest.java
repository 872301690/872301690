package com.gupao.vip.course;

import org.junit.Assert;
import org.junit.Test;

public class CourseTest {

    @Test
    public void create(){
        Course course = new Course("CMSC",120);
        Assert.assertEquals("CMSC",course.getDepartMent());
        Assert.assertEquals(120,course.getNum());
    }

    @Test
    public void equality(){
        Course course1 = new Course("CMSC",120);
        Course course2 = new Course("CMSC",120);
        Assert.assertEquals(course1,course2);
    }


}
