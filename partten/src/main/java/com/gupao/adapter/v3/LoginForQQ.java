package com.gupao.adapter.v3;

public class LoginForQQ implements LoginForThird {
    @Override
    public boolean process(Object o) {
        return o instanceof  LoginForQQ;
    }

    @Override
    public Object executer(String id) {
        if(!process(this)){
            throw new RuntimeException(LoginForThird.class.getSimpleName()+"不支持这种登陆方式");
        }
        System.out.println("qq授权");
        return null;
    }
}
