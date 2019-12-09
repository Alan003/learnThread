package com.thread.createThreadMethod;

/**
 * 创建线程方式 2
 * 实现Runnable接口，实现run方法
 */
public class implementRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" runnable is running");
    }
}
