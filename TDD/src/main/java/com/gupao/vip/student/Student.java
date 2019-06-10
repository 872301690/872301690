package com.gupao.vip.student;

import com.gupao.vip.exception.StudentNameFormatException;
import com.gupao.vip.student.grading.BasicGradingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {

    private GradingStrategy gradingStrategy = new BasicGradingStrategy();
    private String firstName = "";
    private String lastName ;
    private String middleName = "";
    private List<Integer> charges = new ArrayList<>();
    private String id;
    public static final String TOO_MANY_NAME_PARTS_MSG =
             "Student name '%s' contains more than %d parts";

    private int settings = 0x0;
    public void setFlag(Flag... flags) {
        for (Flag flag : flags){
            settings |= flag.mask;
        }
    }

    public void unset(Flag... flags) {
        for (Flag flag : flags){
            settings &= -flag.mask;
        }
    }

    public boolean isOff(Flag flag) {
        return !isOn(flag);
    }

    public boolean isOn(Flag flag) {
        return (settings & flag.mask) == flag.mask;
    }

    public enum Flag {
        TAX_EXEMPT(2),
        MINOR(4),
        TROUBLEMAKER(8),
        ON_CAMPUS(1);
        private int mask;
        Flag(int mask){
            this.mask = mask;
        }
    }


    public enum Grade{
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);
        private int point;
        Grade(int point){
            this.point = point;
        }
        public int getPoint(){
            return point;
        }
    };
    public static final String IN_STATE = "CO";
    public static int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    private String name;
    private int credits;
    private String state;
    private List<Grade> grades = new ArrayList<>();
    private boolean honor;
    public Student(String name)
    {
        this.name = name;
        credits = 0;
        List<String> nameParts = split(name);
        final int maxSizeOfName = 3;
        if (nameParts.size() > maxSizeOfName){

            throw new StudentNameFormatException(String.format(TOO_MANY_NAME_PARTS_MSG,name,maxSizeOfName));
        }
        setName(nameParts);
    }

    private void setName(List<String> nameParts) {
       lastName = removeLast(nameParts);
       String name = removeLast(nameParts);
       if(name.isEmpty()) firstName = name;
       else {
           middleName = name;
           firstName = removeLast(nameParts);
       }
    }

    private String removeLast(List<String> nameParts) {
        if (nameParts.isEmpty()) return "";
        return nameParts.remove(nameParts.size() - 1);
    }

    private List<String> split(String name) {
        List<String> list = new ArrayList<>();
        for (String str : name.split(" ")){
            list.add(str);
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return  credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int i) {
        credits += i;
    }


    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    public boolean isInState() {
        return Student.IN_STATE.equals(state);

    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getGpa() {
        double total = 0.0;
        if (grades.isEmpty()) return total;

        for (Grade grade : grades){
            total += gradingStrategy.getGradePointFor(grade);
        }
        return  total/grades.size();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getMiddleName() {
        return middleName;
    }

    public void setHonor() {
        this.honor = true;
    }
    public void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }
    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int getTotalCharge() {
        int total = 0;
        for (int charge : charges){
            total += charge;
        }
        return total;

    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }


}
