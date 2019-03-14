package com.gupao.strategy.pay;

public class Test {

    public static void main(String[] args) {
        new Order(1,"法拉利",200).pay(PaySrategy.MAP.get(PaySrategy.UNINON_PAY));

    }
}
