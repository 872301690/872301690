package com.gupao.vip.course;

import com.gupao.vip.exception.SessionException;
import com.gupao.vip.student.Student;

import javax.sound.midi.Soundbank;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.*;

public abstract class Session implements Comparable<Session>,Iterable<Student>, Serializable {

    private static int count;
    private  Course course;

    private List<Student> students = new ArrayList<>();
    private Date startDate;
    private int numOfCredits;
    private URL url;
    protected Session(Course course, Date startDate) {
        incrementCount();
       this.course = course;
        this.startDate = startDate;
    }

     static void incrementCount() {
        count ++;
    }



    public static void resetCount() {
        count = 0;
    }

    public String getDepartment() {
        return course.getDepartMent();
    }

    public int getNumber() {
        return course.getNum();
    }

    public int getNumOfStudent() {
        return students.size();
    }

    public void enorll(Student student) {
        student.addCredits(numOfCredits);
        students.add(student);
    }

    public List<Student> getAllStudents() {

        return students;
    }

    public Student get(int i) {
        return students.get(i);
    }

    public Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        final  int dayOfWeek  = 7;
        final  int dayOfFriToMan = 3;

        int numIfday = getLessionLength() * dayOfWeek - dayOfFriToMan;
        calendar.add(Calendar.DAY_OF_YEAR,numIfday);
        return calendar.getTime();
    }

    protected abstract int getLessionLength();

    public Date getStartDate(){
        return startDate;
    }


    public static int getCount() {
        return count;
    }


    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    @Override
    public int compareTo(Session o) {
        int result = this.getDepartment().compareTo(o.getDepartment());
        if(result == 0) result = this.getNumber() - o.getNumber();
        return result;
    }


    public double getAvgGpaForPartTimeStudent() {
        int partTimeStudentCount = 0;
        double total = 0.0;
        for (int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            if(student.isFullTime()) continue;

            total += student.getGpa();
            partTimeStudentCount ++;
        }

        return count == 0 ? 0 : total / partTimeStudentCount;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public  void setUrl(String url) throws SessionException {
        try {

            this.url = new URL(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new SessionException(e);
        }
    };

    public  URL getUrl(){
        return url;
    };

    public  long getNumOfCredits(){
        return this.numOfCredits;
    }
}
