package com.gupao.vip.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class StudentDirectory {
    private final String DIR_BASENAME = "studentDir";
    private Map<String,Student> directoty = new HashMap();
    private DataFile db;

    public StudentDirectory() throws FileNotFoundException {
        db = DataFile.open(DIR_BASENAME);
    }

    public void addStudent(Student student) throws IOException {
        db.add(student.getId(),student);
    }

    public Student findById(String id) throws IOException {
        return (Student) db.findById(id);
    }

    public void close() throws IOException {
        db.close();
    }

    public void remove() {
        db.deleteFile();
    }
}
