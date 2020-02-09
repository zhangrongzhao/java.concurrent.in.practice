package com.zrz.chapter13;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

//二元闭锁
public class OneShotLatch {
    private final Sync sync=new Sync();

    public void signal(){sync.releaseShared(0);}

    public void await()throws InterruptedException{
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer{
        protected  int tryAcquireShared(int ignored){
            //如果闭锁是开的(state==1),那么这个操作将成功，否则将失败
            return (getState()==1)?1:-1;
        }

        protected  boolean tryReleaseShared(int ignored){
            setState(1);//打开闭锁
            return true;//现在其他的线程可以获取该闭锁
        }
    }
}
