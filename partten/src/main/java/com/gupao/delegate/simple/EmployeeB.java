package com.gupao.delegate.simple;

public class EmployeeB extends Emlioyee {

    public EmployeeB() {
        MAP.put("算法",this);
    }

    @Override
    public String doing(String commond) {

        return "我是员工B，我正在做" + commond + "工作";
    }
}
