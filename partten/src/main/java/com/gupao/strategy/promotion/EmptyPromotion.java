package com.gupao.strategy.promotion;

public class EmptyPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("没有优惠活动");
    }
}
