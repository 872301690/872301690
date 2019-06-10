package com.gupao.vip.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public  static Date createDate(int year,int mouth, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,mouth - 1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        return  calendar.getTime();
    }
}
