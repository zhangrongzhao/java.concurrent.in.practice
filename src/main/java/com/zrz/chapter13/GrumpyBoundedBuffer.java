package com.zrz.chapter13;

/**将前提条件的失败传递给调用者**/
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int size){super(size);}

    public synchronized void put(V v)throws BufferFullException{
         if(isFull()){//缓存已满，并不是一个异常条件，抛出异常，并不合适。就像“红灯”并不表示交通信号出了问题。
             throw new BufferFullException();
         }
         doPut(v);
    }

    public synchronized V take()throws BufferEmptyException{
        if(isEmpty()){
            throw new BufferEmptyException();
        }
        return doTake();
    }

    public static void main(String[] args){
        GrumpyBoundedBuffer<Integer> buffer=new GrumpyBoundedBuffer<Integer>(10);
        while(true){
            try{
                Integer item=buffer.take();
                break;
            }catch(BufferEmptyException e){
                try{
                    Thread.sleep(SLEEP_GRANULARITY);
                }
                catch(InterruptedException ex){

                }
            }
        }
    }
}
