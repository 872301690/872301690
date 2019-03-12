package com.gupao.prototype;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Protopyte {

    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        UserPersonalEntity entity = new UserPersonalEntity();
        entity.setId(111l);
        entity.setIsOpenOnepassport(true);
        entity.setCredNum("123456789");
        String json = gson.toJson(entity);
        System.out.println(json);


        PersonalInfoModel personalInfoModel = gson.fromJson(json, PersonalInfoModel.class);
        System.out.println(personalInfoModel.getCredNum());
        System.out.println(personalInfoModel.getUserId());
        System.out.println(personalInfoModel.isOpenOnePassport());
    }
}
