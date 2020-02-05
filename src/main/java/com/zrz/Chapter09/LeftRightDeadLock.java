package com.zrz.Chapter09;


import javax.naming.InsufficientResourcesException;

public class LeftRightDeadLock {
    private final Object left=new Object();
    private final Object right=new Object();
    public void leftRight(){
        synchronized(left){
            try{
                Thread.currentThread().sleep(1000);
                synchronized (right){
                    //doSomething
                }
            }catch(Exception e){

            }finally{

            }

        }
    }

    public void rightLeft(){
        synchronized (right){
            try{
                Thread.currentThread().sleep(1000);
                synchronized (left){
                    //doSomething
                }
            }catch(Exception e){

            }finally{

            }
        }
    }

    public static void main(String[] args){
       final LeftRightDeadLock deadLock=new LeftRightDeadLock();
       Thread threadA=new Thread(new Runnable() {
           public void run() {
               deadLock.leftRight();
           }
       });

       Thread threadB=new Thread(new Runnable() {
           public void run() {
               deadLock.rightLeft();
           }
       });

       threadA.start();
       threadB.start();

    }



}
