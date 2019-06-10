package com.gupao.vip.rosterReporter;

import com.gupao.vip.course.CourseSession;
import com.gupao.vip.student.Student;

import java.io.*;
import java.util.List;

public class RosterReporter {

    public static final String ROSTER_REPORT_HEADER = "Student%n-%n";

    public static final String ROSTER_REPORT_FOOTER = "%n# students =%d%n";
    private CourseSession course;
    private Writer writer;
    public RosterReporter(CourseSession course) {
        this.course = course;
    }


    public void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    private void writeFooter() throws IOException {
        writer.write(String.format(ROSTER_REPORT_FOOTER , course.getNumOfStudent() ));
    }


    private void writeBody() throws IOException {
        List<Student> students = course.getAllStudents();
        for (Student student : students){
            writer.write(String.format(student.getName() + "%n"));

        }
    }

    private void writeHeader() throws IOException {
        writer.write(String.format(ROSTER_REPORT_HEADER));
    }

    public void writeReport(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try {
            writeReport(writer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
