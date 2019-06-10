package com.gupao.vip.student.grading;

import com.gupao.vip.student.GradingStrategy;
import com.gupao.vip.student.Student;

public class BasicGradingStrategy implements GradingStrategy {
    @Override
    public int getGradePointFor(Student.Grade grade) {
       return grade.getPoint();
    }
}
