package com.zrz.chapter13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    private final static int BUFFER_SIZE=10;
    protected final Lock lock = new ReentrantLock();
    //条件谓词：notFull (count<items.length)
    private final Condition notFull=lock.newCondition();
    //条件谓词：notEmpty(count>0)
    private final Condition notEmpty=lock.newCondition();

    private final T[] items=(T[])new Object[BUFFER_SIZE];
    private int tail,head,count;

    public void put(T t)throws InterruptedException{
        lock.lock();
        try{
            while(count==items.length){
                notFull.await();
            }
            items[tail]=t;
            if(++tail==items.length){
                tail=0;
            }
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take()throws InterruptedException{
        lock.lock();
        try{
            while(count<=0){
                notEmpty.await();
            }
            T t=items[head];
            if(++head==items.length){
                head=0;
            }
            --count;
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }


}
