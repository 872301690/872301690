package com.gupao.strategy.pay;


import java.util.*;

public class PaySrategy {
    public static final String ALI_PAY = "ALI";
    public static final String WECHAT_PAY ="WECHAT";
    public static final String JD_PAY = "JD";
    public static final String UNINON_PAY = "UNINON";
    public static final String DEFAULT = ALI_PAY;

    public static final Map<String,PayMent> MAP = new HashMap();
    static {
        MAP.put(ALI_PAY,new AliPay());
        MAP.put(WECHAT_PAY,new WeChatPay());
        MAP.put(JD_PAY,new JDPay());
        MAP.put(UNINON_PAY,new UninonPay());
    }



}
