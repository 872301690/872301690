package com.gupao.strategy.promotion;

public class GroupPromotion implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("团购优惠");
    }
}
