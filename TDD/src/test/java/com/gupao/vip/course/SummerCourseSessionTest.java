package com.gupao.vip.course;

import com.gupao.vip.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SummerCourseSessionTest extends SessionTest{

    @Test
    public void endDate(){
        Date startDate = DateUtil.createDate(2003,6,9);
        Session course =  SummerCourseSession.create(new Course("ENGL",200),startDate);
        Date eightWeeksOut = DateUtil.createDate(2003,8,1);
        Assert.assertEquals(eightWeeksOut,course.getEndDate());
    }

    @Override
    Session createSession(Course course, Date startDate) {
        return  SummerCourseSession.create(course,startDate);
    }
}
