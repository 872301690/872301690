package com.gupao.vip.course;

import com.gupao.vip.student.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CourseSession extends Session {

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
    }

    public static  CourseSession create(Course course, Date startDate){

        return  new CourseSession(course,startDate);
    }
    protected int getLessionLength(){
        return 16;
    }
}
