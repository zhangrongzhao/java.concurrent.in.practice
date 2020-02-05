package com.zrz.Chapter09;


import jdk.jfr.Threshold;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class TransferMoney {
    public void trandssferMoney(Account  fromAccount,Account toAccount,DollarAmount amount)throws InsufficientFundsException{
        synchronized (fromAccount){
            synchronized (toAccount){
                if(fromAccount.getBalance().compareTo(amount)<0){
                    throw new InsufficientFundsException("");
                }else{
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

//    public boolean transferMoney(Account fromAccount,
//                              Account toAccount,
//                              DollarAmount amount,
//                              long timeout, TimeUnit unit)throws InsufficientFundsException,InterruptedException{
//        long fixedDelay=getFixedDelayComponentNanos(timeout,unit);
//        long randMod=getRandomDelayModuleNanos(timeout,unit);
//        long stopTime=System.nanoTime()+unit.toNanos(timeout);
//        while(true){
//            if(fromAccount.lock.tryLock()){
//                try{
//                    if(toAccount.lock.tryLock()){
//                        try{
//                            if(fromAccount.getBalance().compareTo(amount)<0){
//                                throw new InsufficientFundsException("");
//                            }
//                            fromAccount.debit(amount);
//                            toAccount.credit(amount);
//                            return true;
//                        }finally {
//                            toAccount.lock.unlock();
//                        }
//                    }
//                }finally {
//                    fromAccount.lock.unlock();
//                }
//            }
//            if(System.nanoTime()<stopTime){ return false;}
//            NANOSECONDS.sleep(fixedDelay+rnd.nextLong()%randMod);
//        }
//    }
    public static void main(String[] args){
        final Account myAccount=new Account();
        final Account yourAccount=new Account();
        final DollarAmount da=new DollarAmount(10);
        final TransferMoney tm=new TransferMoney();
        Thread myThread=new Thread(new Runnable() {
            public void run() {
                try{
                    tm.trandssferMoney(myAccount,yourAccount,da);
                }catch(InsufficientFundsException ex){

                }
            }
        });
        Thread yourThread=new Thread(new Runnable() {
            public void run() {
                try{
                    tm.trandssferMoney(yourAccount,myAccount,da);
                }catch(InsufficientFundsException ex){

                }
            }
        });
        myThread.start();
        yourThread.start();
    }

//    public synchronized void transferCredits(Account from,Account to,int amount){
//        from.setBalance(from.getBalance()-amount);
//        if(random.nextInt(1000)> Threshold){
//            Thread.yield();
//        }
//        to.setBlance(to.getBalance()+amount);
//    }
}


class Account{
    public Lock lock=new ReentrantLock();
    private double balance;
    public DollarAmount getBalance(){
        return new DollarAmount( this.balance);
    }
    public DollarAmount debit(DollarAmount amount){
        return new DollarAmount( this.balance+amount.getBalance());
    }
    public DollarAmount credit(DollarAmount amount){
        return new DollarAmount( this.balance-amount.getBalance());
    }

}

class DollarAmount{
    private double balance;
    public DollarAmount(double balance){
        this.balance=balance;
    }

    public double getBalance(){return this.balance;}
     public int compareTo(DollarAmount amount){
         return (int)(this.balance-amount.balance);
     }
}

class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
