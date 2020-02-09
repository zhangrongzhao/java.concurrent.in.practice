package com.zrz.chapter13;

public class ThreadGate {
    //条件谓词 opened-since(n)(isOpen||generation>n)

    private boolean isOpen;
    private int generation;

    public synchronized void close(){
        this.isOpen=false;
    }

    public synchronized void open(){
        ++generation;
        isOpen=true;
        notifyAll();
    }

    //阻塞并直到opened-since(generation on entry)
    public synchronized  void await()throws InterruptedException{
        int arrivalGeneration=generation;
        while(!isOpen&&arrivalGeneration==generation){
            wait();
        }
    }
}
