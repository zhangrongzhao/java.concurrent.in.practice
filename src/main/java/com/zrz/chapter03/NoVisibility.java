package com.zrz.chapter03;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        public void run(){
            synchronized (NoVisibility.class){
                while(!ready){
                    System.out.println("not ready...");
                    Thread.yield();
                }
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args)throws InterruptedException{
        new ReaderThread().start();
        synchronized (NoVisibility.class){
            ready=true;
            Thread.sleep(5000);
            number=42;
        }
    }
}
