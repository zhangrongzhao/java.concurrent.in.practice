package com.zrz.chapter07;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 将异常写入日志的UncaughtExceptionHandler
 * */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t,Throwable e){
        Logger  logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"Thread terminated with exception: "+t.getName(),e);
    }
}
