package com.gupao.decorator;

public class PancakeWithAgg extends  Pancake{

    private Pancake pancake;
    public PancakeWithAgg (Pancake pancake){
        this.pancake = pancake;
    }

    public String getName(){
        return pancake.getName() + "+1个蛋";
    }

    public int getPrice(){
        return pancake.getPrice() + 1;
    }
}
