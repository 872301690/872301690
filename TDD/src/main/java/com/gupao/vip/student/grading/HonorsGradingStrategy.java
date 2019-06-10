package com.gupao.vip.student.grading;

import com.gupao.vip.student.GradingStrategy;
import com.gupao.vip.student.Student;

public class HonorsGradingStrategy extends BasicGradingStrategy{


    @Override
    public int getGradePointFor(Student.Grade grade) {
        int points = super.getGradePointFor(grade);
        if (points > 0)
            points++;
        return points;
    }
}
