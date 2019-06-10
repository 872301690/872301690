package com.gupao.vip.rosterReporter;

import com.gupao.vip.course.Course;
import com.gupao.vip.course.CourseSession;
import com.gupao.vip.student.Student;
import com.gupao.vip.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

public class RosterReporterTest {
    private CourseSession course;
    @Before
    public void init(){
        Date startDate = DateUtil.createDate(2003,1,6);
        course =  CourseSession.create(new Course("ENGL",200), startDate);
        course.enorll(new Student("A"));
        course.enorll(new Student("B"));

    }

    @Test
    public void rosterRepoeterTest() throws IOException {

        Writer writer = new StringWriter();
        new RosterReporter(course).writeReport(writer);
        System.out.println(writer.toString());
        assertReportContent( writer.toString());
    }

    @Test
    public void filedReport() throws IOException {
        final String fileName = "filedReport.txt";
        try {
            delete(fileName);
            new RosterReporter(course).writeReport(fileName);

            StringBuffer sb = new StringBuffer();
            String line;

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null ){
                sb.append(String.format(line + "%n"));
            }
            assertReportContent(sb.toString());
        }finally {
            delete(fileName);
        }

    }

    private void delete(String fileName) {
        File file = new File(fileName);
        if (file.exists()){
            Assert.assertTrue("unable to delete " + fileName,file.delete());
        }
    }

    private void assertReportContent(String report) {
        Assert.assertEquals(String.format(RosterReporter.ROSTER_REPORT_HEADER
                + "A%n"
                + "B%n"
                + RosterReporter.ROSTER_REPORT_FOOTER,2) ,report);
    }


}
