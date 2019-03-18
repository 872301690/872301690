package com.gupao.adapter.v1;

import com.gupao.adapter.LoginController;

public class LoginForThirdController extends LoginController {

    public void loginForQQ(String qq){
        String pass = "";
        System.out.println("qq授权");
        System.out.println("设计默认密码");
        register(qq,pass);
    }

    public void loginForWeChat(String openId){
        String pass = "";
        System.out.println("微信授权");
        System.out.println("设计默认密码");
        register(openId,pass);
    }
    public void loginFoeTel(String tel){
        String pass = "";
        System.out.println("手机登陆");
        System.out.println("设计默认密码");
        register(tel,pass);
    }
}
