package com.gupao.adapter.v3;

public class LoginForTel implements LoginForThird {
    @Override
    public boolean process(Object o) {
        return o instanceof LoginForTel;
    }

    @Override
    public Object executer(String id) {
        if(!process(this)){
            throw new RuntimeException(LoginForThird.class.getSimpleName()+"不支持这种登陆方式");
        }
        System.out.println("手机登录");
        return null;
    }
}
