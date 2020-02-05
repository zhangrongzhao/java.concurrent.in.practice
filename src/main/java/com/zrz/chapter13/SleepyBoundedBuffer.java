package com.zrz.chapter13;

/***轮询和休眠实现简单的阻塞**/
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public SleepyBoundedBuffer(int size){super(size);}

    public void put(V v)throws InterruptedException{
        while(true){
            synchronized (this){
                if(!isFull()){
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take()throws InterruptedException{
        while(true){
            synchronized (this){
                if(!isEmpty()){
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
