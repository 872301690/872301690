package com.gupao.adapter.v2;

import com.gupao.adapter.LoginController;

public class LoginForThirdController  {

    private LoginController loginController;

    public LoginForThirdController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void loginForQQ(String qq){
        String pass = "";
        System.out.println("qq授权");
        System.out.println("设计默认密码");
        loginController.register(qq,pass);
    }

    public void loginForWeChat(String openId){
        String pass = "";
        System.out.println("微信授权");
        System.out.println("设计默认密码");
        loginController. register(openId,pass);
    }
    public void loginFoeTel(String tel){
        String pass = "";
        System.out.println("手机登陆");
        System.out.println("设计默认密码");
        loginController.register(tel,pass);
    }

    public void longin(String userName,String password){
        loginController.longin(userName,password);
    }
    public void register(String userName,String password){
        loginController.register(userName,password);

    }
}
