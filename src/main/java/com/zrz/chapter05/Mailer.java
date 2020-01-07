package com.zrz.chapter05;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mailer {

    public boolean checkMail(Set<String> hosts,long timeout, TimeUnit unit) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasMail = new AtomicBoolean();
        try{
            for(final String host:hosts){
                exec.execute(new Runnable() {
                    public void run() {
                        if(checkMail(host)){
                            hasMail.set(true);
                        }
                    }
                });
            }
        }finally{
            exec.shutdown();
            exec.awaitTermination(timeout,unit);
        }
        return hasMail.get();
    }

    public boolean checkMail(String host){
        return true;
    }
}
