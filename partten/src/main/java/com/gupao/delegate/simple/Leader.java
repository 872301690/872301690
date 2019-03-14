package com.gupao.delegate.simple;

public class Leader {
    Leader() {
        new EmployeeA();
        new EmployeeB();
    }

    public void doCommond(String commond){
        System.out.println(Emlioyee.MAP.get(commond).doing(commond));
    }
}
