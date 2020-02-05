//package com.zrz.chapter12;
//
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.junit.Assert.assertEquals;
//
//public class PutTakeTest {
//    private static final ExecutorService pool= Executors.newCachedThreadPool();
//    private final AtomicInteger putSum=new AtomicInteger(0);
//    private final AtomicInteger takeSum=new AtomicInteger(0);
//    private final CyclicBarrier barrier;
//    private final BoundedBuffer<Integer> bb;
//    private final BarrierTimer timer;
//    private final int nTrials,nPairs;
//
//    static int xorShift(int y){
//        y^=(y<<6);
//        y^=(y>>>21);
//        y^=(y<<7);
//        return y;
//    }
//
//    public static void main(String[] args)throws Exception{
//        //new PutTakeTest(10,10,10000).test();
//        //pool.shutdown();
//
//        int tpt=100000;//每个线程中的测试次数
//        for(int cap=1;cap<=1000;cap*=10){
//            System.out.println("Capacity: "+cap);
//            PutTakeTest putTakeTest=new PutTakeTest(cap,npairs,tpt);
//            System.out.print("Pairs: "+pairs+"\t");
//            t.test();
//            System.out.print("\t");
//            Thread.sleep(1000);
//            t.test();
//            System.out.println();
//            Thread.sleep(1000);
//        }
//        pool.shutdown();
//    }
//
//    PutTakeTest(int capacity,int npairs,int ntrials){
//        this.bb=new BoundedBuffer<Integer>(capacity);
//        this.nTrials=ntrials;
//        this.nPairs=npairs;
//        this.timer=new BarrierTimer();
//        this.barrier=new CyclicBarrier(npairs*2+1);
//    }
//
//    public void test(){
//       try{
//           timer.clear();
//           for(int i=0;i<nPairs;i++){
//               pool.execute(new Producer());
//               pool.execute(new Consumer());
//           }
//           barrier.await();//等待所有的线程就绪
//           barrier.await();//等待所有的线程执行完成
//           long nsPerItem=timer.getTime()/(nPairs*(long)nTrials);
//           System.out.println("Throught: "+nsPerItem+"ns/item");
//           assertEquals(putSum.get(),takeSum.get());
//       }catch(Exception e){
//           throw new RuntimeException(e);
//       }
//    }
//
//    class Producer implements Runnable{
//        public void run(){
//            try{
//                int seed=(this.hashCode()^(int)System.nanoTime());
//                int sum=0;
//                barrier.await();
//                for(int i=nTrials;i>0;--i){
//                    bb.put(seed);
//                    sum+=seed;
//                    seed=xorShift(seed);
//                }
//                putSum.getAndAdd(sum);
//                barrier.await();
//            }catch(Exception e){
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    class Consumer implements Runnable{
//         public void run(){
//             try{
//                 barrier.await();
//                 int sum=0;
//                 for(int i=nTrials;i>0;--i){
//                     sum+=bb.take();
//                 }
//                 takeSum.getAndAdd(sum);
//                 barrier.await();
//             }catch(Exception e){
//                 throw new RuntimeException(e);
//             }
//         }
//    }
//
//
//}
