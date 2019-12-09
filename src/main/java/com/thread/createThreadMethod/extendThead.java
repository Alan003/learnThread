package com.thread.createThreadMethod;

/**
 * 创建线程的方式 1
 * 继承Thread类，重写run方法
 */
public class extendThead extends Thread {
    @Override
    public void run() {
        System.out.println(getName()+" extendThread is running");

    }
}
