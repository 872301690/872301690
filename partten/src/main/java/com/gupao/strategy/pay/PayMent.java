package com.gupao.strategy.pay;

public abstract class PayMent  {
    public abstract String name();
    public abstract int queryBalance();

    public PayResult pay(int mount){
        System.out.println(name());
        if (queryBalance() < mount){
            return new PayResult(500,"支付失败","余额不足");
        }
        return new PayResult(200,"支付成功","欢迎下次使用");
    }
}
