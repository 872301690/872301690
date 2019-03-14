package com.gupao.strategy.promotion;

public class ConponPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("优惠券，更加实惠");
    }
}
