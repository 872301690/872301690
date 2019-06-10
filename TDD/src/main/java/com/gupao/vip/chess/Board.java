package com.gupao.vip.chess;

import com.gupao.vip.chess.pieces.Pawn;

import java.util.*;

public class Board {
    private int numOfPawns;
    private Map<Integer,List<Pawn>> map = null;
    private final int BOARD_ROW = 8;

    public Board() {
        map = new HashMap<>();
        List<Pawn> line2 = new ArrayList<>();
        List<Pawn> line7 = new ArrayList<>();

        for(int i = 0; i < 8;i++ ){
            Pawn white = new Pawn(Pawn.WAITE,"p");
            Pawn blank = new Pawn(Pawn.BLANK,"P");
            line2.add(blank);
            line7.add(white);
        }
        map.put(2,line2);
        map.put(7,line7);
        numOfPawns = 16;
    }

    public int getPawnSize() {
        return numOfPawns;
    }



    public List<Pawn> getPawns(int lineNo) {
        return map.get(lineNo);
    }

    public String printBoard() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1 ; i <= BOARD_ROW; i++){
            List<Pawn> pawns = map.get(i);
            printLine(sb,pawns);
        }
        return sb.toString();
    }

    private void printLine(StringBuffer sb, List<Pawn> pawns) {
        if(pawns == null || pawns.size() == 0){
            sb.append("........\n");
        }else{
            for (Pawn pawn : pawns){
                sb.append(pawn.printPlace());
            }
            sb.append("\n");
        }
    }
}
