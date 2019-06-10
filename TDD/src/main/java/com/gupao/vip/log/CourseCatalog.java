package com.gupao.vip.log;

import com.gupao.vip.course.Course;
import com.gupao.vip.course.CourseSession;
import com.gupao.vip.course.Session;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseCatalog {
    private List<Session> sessionList = new ArrayList<>();
    public void add(Session session) {
        sessionList.add(session);
    }

    public void clearAll() {
        sessionList.clear();
    }

    public List<Session> getSessions() {
        return sessionList;
    }

    public void store(String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
       try {
           out.writeObject(sessionList);
       }finally {
           out.close();
       }

    }

    public void load(String fileName) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        try {
            sessionList = (List<Session>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }
}
