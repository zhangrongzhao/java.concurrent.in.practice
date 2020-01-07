package com.zrz.chapter05;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CrawlerTest {
    private static int BOUND=10;
    private static int N_CONSUMERS=100;
    public static void startIndexing(File[] roots){
        BlockingQueue<File> queue=new LinkedBlockingQueue<File>(BOUND);
        FileFilter filter =new FileFilter() {
            public boolean accept(File pathname) {
                return true;
            }
        };

        for(File root:roots){
            new Thread(new FileCrawler(queue,filter,root)).start();
        }

        for(int i=0;i<N_CONSUMERS;i++){
            new Thread(new Indexer(queue)).start();
        }
    }
}
