package com.gupao.vip.chess;

import com.gupao.vip.chess.pieces.Pawn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BoardTest {

    private Board  board = null;

    @Before
    public void setUp(){
         board = new Board();
    }

    @Test
    public void boardTest(){
        Assert.assertEquals(16,board.getPawnSize());
        Assert.assertEquals("........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n" ,board.printBoard());
    }
}
