package com.gupao.strategy.pay;

public class WeChatPay extends PayMent {
    @Override
    public String name() {
        return "微信支付";
    }

    @Override
    public int queryBalance() {
        return 500;
    }
}
