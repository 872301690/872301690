package com.gupao.vip.course;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    private String departMent;
    private int num;
    public Course(String departMent, int num) {
        this.departMent = departMent;
        this.num = num;

    }

    public String getDepartMent() {
        return departMent;
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return num == course.num &&
                Objects.equals(departMent, course.departMent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departMent, num);
    }
}
