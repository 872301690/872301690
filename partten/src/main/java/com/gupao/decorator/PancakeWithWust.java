package com.gupao.decorator;

public class PancakeWithWust extends Pancake {

    private Pancake pancake;

    public PancakeWithWust(Pancake pancake) {

        this.pancake = pancake;
    }

    @Override
    public String getName() {
        return pancake.getName() + "+1根肠";
    }

    @Override
    public int getPrice() {
        return pancake.getPrice() + 2;
    }
}
