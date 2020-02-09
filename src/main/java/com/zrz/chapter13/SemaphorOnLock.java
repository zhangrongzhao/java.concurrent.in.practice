package com.zrz.chapter13;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//非真实实现方式
public class SemaphorOnLock {

    //AbstractQueuedSynchronizer
    private final Lock lock=new ReentrantLock();
    //条件谓词：permitsAvailable(permits>0)
    private final Condition permitsAvalilable=lock.newCondition();
    private int permits;

    SemaphorOnLock(int initialPermits){
        lock.lock();
        try{
            permits=initialPermits;
        }finally {
            lock.unlock();
        }
    }

    public void acquire()throws InterruptedException{
        lock.lock();
        try{
            while(permits<=0){
                permitsAvalilable.await();
            }
            --permits;
        }finally {
            lock.unlock();
        }
    }

    public void release()throws InterruptedException{
        lock.lock();
        try{
            ++permits;
            permitsAvalilable.signal();
        }finally {
            lock.unlock();
        }
    }
}
