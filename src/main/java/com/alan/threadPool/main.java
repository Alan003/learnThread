package com.alan.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class main {
    public static void main(String[] args) {
        MytThreadPoolExecutor threadPool = new MytThreadPoolExecutor("threadPool",
                5, 10, new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i=0;i<100;i++){
            threadPool.execute(()->{
                System.out.println("running"+atomicInteger.incrementAndGet());
            });
        }
    }
}
