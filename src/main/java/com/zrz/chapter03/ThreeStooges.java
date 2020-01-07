package com.zrz.chapter03;

import java.util.HashSet;
import java.util.Set;

public final class ThreeStooges {
    private final Set<String> stooages = new HashSet<String>();
    public ThreeStooges(){
        stooages.add("Moe");
        stooages.add("Larry");
        stooages.add("Curly");
    }

    public boolean isStooge(String name){
        return stooages.contains(name);
    }

    public void add(){
        stooages.add("Lucy");
    }

    public static void main(String[] args){
        ThreeStooges ts=new ThreeStooges();
        ts.add();
    }
}
