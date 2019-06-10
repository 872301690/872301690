package com.gupao.vip.student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDirectoryTest {
    private StudentDirectory directory ;

    @Before
    public void init(){
        directory = new StudentDirectory();
    }

    protected void trarDown(){
        directory.close();
        directory.remove();
    }
    @Test
    public void storeAndRetriver(){
        final int numOfStudent = 10;
        for (int i = 0; i < numOfStudent; i++){
            addStudent(directory,i);
        }
        directory.close();

        directory = new StudentDirectory();
        for (int i = 0; i < numOfStudent; i++){
            verifyStudentLookUp(directory,i);
        }
    }

    private void verifyStudentLookUp(StudentDirectory directory, int i) {
        String id = i + "";
        Student student = directory.findById(id);
        Assert.assertEquals(id,student.getLastName());
        Assert.assertEquals(id,student.getId());
        Assert.assertEquals(i,student.getCredits());

    }

    private void addStudent(StudentDirectory directory, int i) {
        Student student = new Student("" + i);
        student.setId(i + "");
        student.addCredits(i);
        directory.addStudent(student);
    }
}
