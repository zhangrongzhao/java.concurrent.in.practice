package com.zrz.chapter05;

import java.util.Vector;

public class SynchronizedContainer {
    public static Object getLast(Vector list){
        synchronized (list){
            int lastIndex=list.size()-1;
//            try{
//                Thread.sleep(1000);
//            }catch(InterruptedException ex){
//
//            }

            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list){
        synchronized (list){
            int lastIndex=list.size()-1;
            list.remove(lastIndex);
        }
    }

    public static void foreach(Vector vector){
        synchronized (vector){
            for(int i=0;i<vector.size();i++){
                vector.get(i);
            }
        }
    }

    public static void main(String[] args){
        final Vector<Integer> integerVector=new Vector<Integer>();
        integerVector.add(1);
        Thread writeThread=new Thread(new Runnable() {
            public void run() {
                deleteLast(integerVector);
            }
        });

        Thread readThread=new Thread(new Runnable() {
            public void run() {
                getLast(integerVector);
            }
        });

        readThread.start();
        writeThread.start();


        try{
            Thread.sleep(5000);
        }catch(InterruptedException ex){

        }

    }
}
