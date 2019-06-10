package com.gupao.vip.rosterReporter;

import com.gupao.vip.course.Course;
import com.gupao.vip.course.CourseSession;
import com.gupao.vip.course.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class CourseReportTest {

    @Test
    public void report(){
        final  Date date = new Date();
        CourseReport courseReport = new CourseReport();
        courseReport.add(create("CZEC",220,date));
        courseReport.add(create("ENGL",101,date));
        courseReport.add(create("ITAL",401,date));
        courseReport.add(create("CZEC",200,date));
        courseReport.add(create("ITAL",410,date));
        Assert.assertEquals("CZEC 200" + ReportConstant.NEW_LINE
                + "CZEC 220" + ReportConstant.NEW_LINE
                + "ENGL 101" + ReportConstant.NEW_LINE
                + "ITAL 401" + ReportConstant.NEW_LINE
                + "ITAL 410" + ReportConstant.NEW_LINE,courseReport.report());
    }

    private Session create(String ital, int i, Date date) {
        return CourseSession.create(new Course(ital,i),date);
    }


}
