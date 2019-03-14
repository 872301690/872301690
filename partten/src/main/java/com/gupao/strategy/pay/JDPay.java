package com.gupao.strategy.pay;

public class JDPay extends PayMent {
    @Override
    public String name() {
        return "京东白条";
    }

    @Override
    public int queryBalance() {
        return 220;
    }
}
