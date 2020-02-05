package com.zrz.chapter12;

import junit.framework.TestCase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoundedBufferTest extends TestCase {
    private final Long LOCKUP_DETECT_TIMEOUT=1000L;

   public void testIsEmptyWhenConstructed(){
        BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

   public void testIfFullAfterPuts()throws InterruptedException{
        BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);
        for(int i=0;i<10;i++){
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }
    public void testBlocksWhenEmpty(){
       final BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);

       Thread taker=new Thread(){
           public void run(){
               try{
                   int unused=bb.take();
                   fail();//如果执行到这里，表示出现了一个错误
               }catch(InterruptedException ex){

               }
           }
       };
       try{
           taker.start();
           Thread.sleep(LOCKUP_DETECT_TIMEOUT);
           taker.interrupt();
           taker.join(LOCKUP_DETECT_TIMEOUT);
           assertFalse(taker.isAlive());
       }catch(Exception unexpected){
           fail();
       }
    }
}
