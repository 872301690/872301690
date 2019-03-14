package com.gupao.delegate.simple;


import java.util.*;

public abstract class Emlioyee {

    public static final Map<String,Emlioyee> MAP = new HashMap<>();

    public abstract String doing(String commond);

}
