package com.gupao.vip.password;

import java.util.Random;

public class PasswordGenerator {
    private static final int LOW_END_PASSWORD_CHAR = 48;
    private static final int HIGH_END_PASSWORD_CHAR = 122;
    private static final int PASSWORD_LENGTH = 8;
    private Random random = new Random();
    private String password;
    public void setRandom(Random random) {
        this.random = random;
    }

    public String generatorPassword() {
        StringBuffer sb = new StringBuffer(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++){
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    private char getRandomChar() {
        final char max = HIGH_END_PASSWORD_CHAR - LOW_END_PASSWORD_CHAR;
        return (char) (random.nextInt(max) + LOW_END_PASSWORD_CHAR);
    }

    static class MockRandom extends Random {
        private int i;
        public MockRandom(char startCharValue) {
            i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
        }
        protected int next(){
            return i++;
        }
    }
}
