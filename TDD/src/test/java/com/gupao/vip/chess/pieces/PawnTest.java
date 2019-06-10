package com.gupao.vip.chess.pieces;

import org.junit.Assert;
import org.junit.Test;

public class PawnTest {

    @Test
    public void testPawn(){
        final String white = "white";
        Pawn pawn = new Pawn(white,"p");
        String color = pawn.getColor();
        Assert.assertEquals(white,color);

        final String blank = "blank";
        Pawn secondPawn = new Pawn("blank","P");
        String secondColor = secondPawn.getColor();
        Assert.assertEquals(blank,secondColor);
    }

    @Test
    public void Constact(){
        Pawn pawn = new Pawn();
    }
}
