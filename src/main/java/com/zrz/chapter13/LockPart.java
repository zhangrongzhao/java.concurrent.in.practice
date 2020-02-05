package com.zrz.chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class LockPart {

    Lock lock=new ReentrantLock();

    //ReadWriteLock

    public boolean trySendOnSharedLine(String message, long timeout, TimeUnit unit)throws InterruptedException{
        long nanosToLock=1000L;
        if(!lock.tryLock(nanosToLock,TimeUnit.NANOSECONDS)){return false;}
        try{
            return sendOnSharedLine(message);
        }finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String message)throws InterruptedException{
        lock.lockInterruptibly();
        try{
            return cancellableSendOnSharedLine(message);
        }finally {
            lock.unlock();
        }
    }
    private boolean cancellableSendOnSharedLine(String message)throws InterruptedException{ return true;}
}
