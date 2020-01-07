package com.zrz.chapter05;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;
    public Indexer(BlockingQueue<File> queue){
        this.queue=queue;
    }

    public void run(){
           try{
               IndexFile(queue.take());
           }catch(InterruptedException e){
               Thread.currentThread().interrupt();
           }
    }

    public void IndexFile(File file){
        //index file
    }
}
