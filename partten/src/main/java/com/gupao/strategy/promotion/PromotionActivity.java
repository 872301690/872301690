package com.gupao.strategy.promotion;

import java.util.*;

public class PromotionActivity {

    private static final Map<String,PromotionStrategy> MAP = new HashMap<>();
    private PromotionStrategy strategy = null;
    public PromotionActivity( PromotionStrategy promotionStrategy) {
        this.strategy = promotionStrategy;
    }

    public PromotionActivity(){
        MAP.put(PromotionKey.CASH,new CashBackPromotion());
        MAP.put(PromotionKey.GROUP,new GroupPromotion());
        MAP.put(null,new EmptyPromotion());
    }

    public void execute(String promotionKey){
       // strategy.doPromotion();
        MAP.get(promotionKey).doPromotion();
    }
}
