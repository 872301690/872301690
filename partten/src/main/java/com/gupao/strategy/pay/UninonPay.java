package com.gupao.strategy.pay;

public class UninonPay extends PayMent {
    @Override
    public String name() {
        return "银联支付";
    }

    @Override
    public int queryBalance() {
        return 100;
    }
}
