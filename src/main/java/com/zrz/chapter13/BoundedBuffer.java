package com.zrz.chapter13;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    //条件谓词：not-full (!isFull)
    //条件谓词：not-empty(!isEmpty)
    public BoundedBuffer(int size){super(size);}

    //阻塞并直到 not-full
    public synchronized void put(V v)throws InterruptedException{
         while(isFull()){
             wait();//当前线程请求操作系统，将自己挂起进入阻塞状态（等待），释放拥内置锁。等待特定信号，再次醒来，并且再次持有锁。
         }
         doPut(v);
         notifyAll();
    }

    //阻塞并直到not-empty
    public synchronized  V take()throws InterruptedException{
         while(isEmpty()){
             wait();
         }
         V v = doTake();
         notifyAll();
         return v;
    }
}
