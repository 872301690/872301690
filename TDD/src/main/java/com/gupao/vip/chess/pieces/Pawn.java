package com.gupao.vip.chess.pieces;

public class Pawn {

    private String color;
    public final static String WAITE = "white";
    public final static String BLANK = "blank";
    private String place;
    public Pawn(String color, String place) {
        this.color = color;
        this.place = place;
    }

    public Pawn() {
        color = WAITE;
    }

    public String getColor() {
        return color;
    }

    public String printPlace(){
        return  place;
    }

}
