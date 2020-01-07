package com.zrz.chapter03;

import java.awt.*;
import java.util.EventListener;

public class SafeListener {
    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener(){
            public void onEvent(Event e){
               //doSomething(e);
            }
        };
    }

//    public static SafeListener newInsstance(EventSource source){
//        SafeListener safe = new SafeListener();
//        //source.registerListener(safe.listener);
//        return safe;
//    }
    //使用工厂方法来防止this引用在构造过程中逸出。
}
