package com.gupao.delegate.simple;

public class EmployeeA extends Emlioyee {

    public EmployeeA() {
        MAP.put("加密",this);
    }

    @Override
    public String doing(String commond) {

        return "我是员工A，我正在做" + commond + "工作";
    }
}
