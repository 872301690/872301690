package com.gupao.delegate.simple;

public class Test {

    public static void main(String[] args) {
        new Boss().commond("加密",new Leader());
    }
}
