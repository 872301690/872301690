package com.gupao.vip.score;

public class Score {
    public int score(String score) {
        return new Integer(score);
    }

    public boolean isValid(String score) {
        try {
            Integer.parseInt(score);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
