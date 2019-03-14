package com.gupao.strategy.pay;

import javax.sound.midi.Soundbank;

public class Order {
    public int id;
    public Object data;
    public int amount;

    public Order(int id, Object data, int amount) {


        this.id = id;
        this.data = data;
        this.amount = amount;
    }

    public void pay(PayMent payMent){
        PayResult pay = payMent.pay(amount);
        System.out.println(pay);
    }
}
