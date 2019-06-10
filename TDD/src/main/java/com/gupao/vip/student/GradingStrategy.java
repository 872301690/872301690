package com.gupao.vip.student;


import com.gupao.vip.student.Student;

public interface GradingStrategy {
    int getGradePointFor(Student.Grade grade);
}
