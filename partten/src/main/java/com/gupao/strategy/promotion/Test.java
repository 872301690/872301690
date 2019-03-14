package com.gupao.strategy.promotion;

public class Test {

   /* public static void main(String[] args) {
        PromotionActivity activity1 = new PromotionActivity(new CashBackPromotion());
        PromotionActivity activity2 = new PromotionActivity(new GroupPromotion());

        activity1.execute();
        activity2.execute();
    }*/

    /*public static void main(String[] args) {
        PromotionStrategy strategy = null;
        String promotionKey = "cash";

        if(promotionKey.equals("cash")){
            strategy = new CashBackPromotion();
        }else if(promotionKey.equals("group")){
            strategy = new GroupPromotion();
        }

        new PromotionActivity(strategy).execute();
    }*/

    public static void main(String[] args) {
        new PromotionActivity().execute(PromotionKey.GROUP);
    }
}
