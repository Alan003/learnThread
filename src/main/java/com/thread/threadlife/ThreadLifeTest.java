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
                    //Thread.sleep(1000*20);
                    System.out.println("thread1 after waiting");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"Thread1").start();

        /** Thread.sleep(1000*6);不会释放锁，
         * Thread2线程继续持有锁，等待6秒后，先输出“thread2 after waiting”，Thread2线程运行完后释放锁，
         * Thread1线程获得锁后继续运行，输出“thread1 after waiting”
         */

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
