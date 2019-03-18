package com.gupao.observer.guava;

public class Test {

    public static void main(String[] args) {
        Gper gper = new Gper();
        Question question = new Question();
        question.setUser("小明");
        question.setMsg("老师，能具体讲讲spring源码么");

        Teacher teacher = new Teacher("tom");
        gper.push(teacher,question);

    }
}
