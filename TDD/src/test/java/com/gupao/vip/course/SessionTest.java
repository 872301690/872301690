package com.gupao.vip.course;

import com.gupao.vip.exception.SessionException;
import com.gupao.vip.student.Student;
import com.gupao.vip.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Date;

public abstract class SessionTest {


    private Session course ;
    @Before
    public void setUp(){

        Date startDate =  DateUtil.createDate(2003,1,6);
        course =  createSession(new Course("ENGL", 101), startDate);
        course.setNumOfCredits(CourseSessionTest.CREDITS);
    }
    @Test
    public void courseSessionTest(){

        Assert.assertEquals("ENGL",course.getDepartment());
        Assert.assertEquals(101,course.getNumber());

        Assert.assertEquals(0,course.getNumOfStudent());
    }

    @Test
    public void enrollStudents(){


        Student student1 = new Student("zhangsan");
        course.enorll(student1);
        Assert.assertEquals(CourseSessionTest.CREDITS,student1.getCredits());
        Assert.assertEquals(1,course.getNumOfStudent());
        Assert.assertEquals(student1,course.get(0));

        Student student2 = new Student("lisi");
        course.enorll(student2);
        Assert.assertEquals(CourseSessionTest.CREDITS,student1.getCredits());
        Assert.assertEquals(2,course.getNumOfStudent());
        Assert.assertEquals(student2,course.get(1));
    }

    @Test
    public void courceDate(){

        Date endDate = DateUtil.createDate(2003,4,25);
        Assert.assertEquals(endDate,course.getEndDate());

    }

    @Test
    public  void count(){
        CourseSession.resetCount();
        createSession(new Course("ENGL", 101), new Date());
        Assert.assertEquals(1,course.getCount());
        createSession(new Course("ENGL", 101), new Date());
        Assert.assertEquals(2,course.getCount());

    }

    abstract Session createSession(Course course, Date startDate);
    @Test
    public void sessionLength(){
        Assert.assertTrue(course.getLessionLength() > 0);
    }

    @Test
    public void avgGpaPartTimeStudent(){
        course.enorll(createFullTimeStudent());

        Student partStudent1 = new Student("1");
        partStudent1.addGrade(Student.Grade.A);
        course.enorll(partStudent1);

        course.enorll(createFullTimeStudent());

        Student partStudent2 = new Student("1");
        partStudent2.addGrade(Student.Grade.B);
        course.enorll(partStudent2);
        Assert.assertEquals(3.5,course.getAvgGpaForPartTimeStudent(),0.005);
    }

    protected  Student createFullTimeStudent(){
        Student student = new Student("a");
        student.addCredits(Student.CREDITS_REQUIRED_FOR_FULL_TIME);
        return student;
    }

    @Test
    public void sessionUrl()  {
        final  String url = "http://course.langsoft.com/cmsc300";
        try {
            course.setUrl(url);
        } catch (SessionException e) {
            Throwable t = e.getCause();
            Assert.assertEquals(MalformedURLException.class,t.getClass());
        }
        Assert.assertEquals(url,course.getUrl().toString());
    }
}
