package com.gupao.vip.rosterReporter;

import com.gupao.vip.student.Student;
import java.util.*;

public class ReportCard {
    public static final String A_MESSAGE = "Excellent";
    public static final String B_MESSAGE = "Very Good";
    public static final String C_MESSAGE = "Hmmmm";
    public static final String D_MESSAGE = "You're not trying";
    public static final String F_MESSAGE = "Loser";

    private Map<Student.Grade,String> map = null;

    public String getMessage(Student.Grade grade) {
        return getMessage().get(grade);
    }

    private Map<Student.Grade,String> getMessage() {
        if (map == null)
            loadMessage();
        return map;
    }

    private void loadMessage() {
        map = new EnumMap(Student.Grade.class);
        map.put(Student.Grade.A,A_MESSAGE);
        map.put(Student.Grade.B,B_MESSAGE);
        map.put(Student.Grade.C,C_MESSAGE);
        map.put(Student.Grade.D,D_MESSAGE);
        map.put(Student.Grade.F,F_MESSAGE);
    }
}
