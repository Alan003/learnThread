package com.thread.threadlife;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLifeTest {
    public static void main(String[] args) {
        Object o = new Object();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            synchronized(o){
                try {
                    System.out.println("thread1 waiting");
                    o.wait(1000*5);
                    //Thread.sleep(1000*2);
                    System.out.println("thread1 after waiting");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"Thread1").start();

        new Thread(() ->{
            synchronized (o) {
                try {
                    System.out.println("thread2 waiting");
                    //o.notify();
                    Thread.sleep(1000*6);
                    System.out.println("thread2 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread2").start();
    }
}
