package com.gupao.vip.course;

import java.util.Calendar;
import java.util.Date;

public class SummerCourseSession extends Session {
    public SummerCourseSession(Course course, Date startDate) {
        super(course,startDate);
    }

    public static SummerCourseSession create(Course course, Date startDate){
        return new SummerCourseSession(course,startDate);
    }

    @Override
    protected int getLessionLength() {
        return 8;
    }
}
