package com.thread.executetask;

import java.util.Date;
import java.util.concurrent.*;

public class executeTaskTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
                (r, pool) -> System.out.println(Thread.currentThread().getName() + " discard task"));

        for (int i=0;i<20;i++){
            int num =i;
            threadPoolExecutor.execute(() -> {
                try{
                    System.out.println(Thread.currentThread().getName() +","+ num +" running" + System.currentTimeMillis());
                    Thread.sleep(1000*2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

    }
}
