package com.gupao.vip.rosterReporter;

import com.gupao.vip.course.CourseSession;
import com.gupao.vip.course.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseReport {
    private List<Session> SessionList = new ArrayList<>();
    public void add(Session course) {
        SessionList.add(course);
    }

    public String report() {
        Collections.sort(SessionList);
        StringBuilder builder = new StringBuilder();
        for (Session course : SessionList){
            builder.append(course.getDepartment() + " " + course.getNumber() + ReportConstant.NEW_LINE);
        }
        return builder.toString();
    }
}
