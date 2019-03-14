package com.gupao.strategy.pay;

public class AliPay extends PayMent {
    @Override
    public String name() {
        return "支付宝支付";
    }

    @Override
    public int queryBalance() {
        return 900;
    }
}
