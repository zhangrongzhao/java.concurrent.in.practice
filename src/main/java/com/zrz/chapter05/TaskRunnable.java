package com.zrz.chapter05;

import java.util.concurrent.BlockingQueue;

class Task{}


public class TaskRunnable implements Runnable {
   BlockingQueue<Task> queue;

   public void run(){
       try{
           queue.take();
       }catch(InterruptedException e){
           //捕获异常后，线程撤销终端标志
           //interrupt恢复中断状态。
           Thread.currentThread().interrupt();
       }
   }
}

//=>5.5同步工具类