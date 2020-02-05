package com.zrz.chapter12;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {
    private final Semaphore availableItems,availableSpaces;
    private final E[] items;
    private int putPosition=0,takePosition=0;
    public BoundedBuffer(int capacity){
        this.availableItems = new Semaphore(0);
        this.availableSpaces = new Semaphore(capacity);
        items=(E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return availableItems.availablePermits()==0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits()==0;
    }

    public void put(E x)throws InterruptedException{
        this.availableSpaces.acquire();
        doInsert(x);
        this.availableItems.release();
    }
    public E take()throws InterruptedException {
        this.availableItems.acquire();
        E item=doExtract();
        this.availableSpaces.release();
        return item;
    }
    private synchronized void doInsert(E x){
        int i=putPosition;
        items[i]=x;
        putPosition=(++i==items.length)?0:i;
    }

    private synchronized  E doExtract(){
        int i=takePosition;
        E x=items[i];
        items[i]=null;
        takePosition=(++i==items.length)?0:i;
        return x;
    }
}
