package com.zrz.chapter03;

public class SynchronizedInteger {

    private int value;
    public synchronized int getValue(){return this.value;}
    public synchronized void setValue(int value){this.value=value;}

    private volatile boolean asleep;
    public void doSomething(){
        while(!asleep){
            countSomeSheep();
        }
    }

    public void countSomeSheep(){}

    //volatile 只能确保可见性，不能保证原子性。
}
