package com.gupao.adapter;

import javax.sound.midi.Soundbank;

public class LoginController {

    public void longin(String userName,String password){
        System.out.println("登陆");
    }
    public void register(String userName,String password){
        System.out.println("注册");
        longin(userName,password);
    }
}
