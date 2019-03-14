package com.gupao.strategy.promotion;

public class CashBackPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("购买返现到支付宝");
    }
}
