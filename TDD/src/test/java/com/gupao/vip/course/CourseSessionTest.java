package com.gupao.vip.course;

import com.gupao.vip.util.DateUtil;

import java.util.Date;

public class CourseSessionTest extends SessionTest {
     final static int CREDITS = 3;

    @Override
     Session createSession(Course course, Date startDate) {
        return  CourseSession.create(course,startDate);
    }


}
