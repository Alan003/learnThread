package com.thread.createThreadMethod;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //继承Thread类
        new extendThead().start();

        //实现Runnable接口
        new Thread(new implementRunnable()).start();

        //匿名内部类
        new Thread(){
            @Override
            public void run() {
                System.out.println(getName()+" 匿名继承Thread");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" 匿名实现runnable");
            }
        }).start();

        new Thread(
                () -> System.out.println(Thread.currentThread().getName()+" 匿名实现runnable,lambda方式")
        ).start();

        //实现callable接口
        FutureTask<Long> futureTask = new FutureTask<>(new implementCallable());
        new Thread(futureTask).start();
        Long result = futureTask.get();
        System.out.println(result);

        //线程池方式
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i=0;i<100;i++){
            threadPool.execute(()->System.out.println(Thread.currentThread().getName()+" threadPool"));
        }

        //定时器方式
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ " timer");
            }
        },0,1000);
    }
}
