package com.zrz.chapter03;

import java.util.HashSet;
import java.util.Set;

class Secret{}

public class PublishObject {
    public static Set<Secret> knowsSecrets;
    public void initialize(){
        knowsSecrets=new HashSet<Secret>();
    }
}
