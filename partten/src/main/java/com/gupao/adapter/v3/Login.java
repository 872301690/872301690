package com.gupao.adapter.v3;

import com.gupao.adapter.LoginController;

public class Login extends LoginController  {

    public void loginForQQ(String qq){
        LoginForThird loginForThird = getLoginForThird(LoginForQQ.class);
        loginForThird.executer(qq);
    }

    public void loginForWeChat(String openId){
        LoginForThird loginForThird = getLoginForThird(LoginForWeChat.class);
        loginForThird.executer(openId);
    }
    public void loginFoeTel(String tel){
        LoginForThird loginForThird = getLoginForThird(LoginForTel.class);
        loginForThird.executer(tel);
    }

    private LoginForThird getLoginForThird(Class<? extends  LoginForThird> clazz){
        try {
            return  clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
