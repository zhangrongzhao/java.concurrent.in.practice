package com.zrz.chapter07;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService executorService){
        this.exec = Executors.newCachedThreadPool();
    }

    public List<Runnable> getCancelledTasks(){
        if(exec.isTerminated()){
            throw new IllegalStateException();
        }
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    public void execute(final Runnable runnable){
        exec.execute(new Runnable() {
            public void run() {
                try{
                    runnable.run();
                }finally{
                    //如果线程池关闭，或者当前线程中止，当前正在执行的任务，保存到set中。
                    if(isShutdown() && Thread.currentThread().isInterrupted()){
                         tasksCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    public boolean isShutdown() {
        return exec.isShutdown();
    }

    public boolean isTerminated() {
        return exec.isTerminated();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }
}
