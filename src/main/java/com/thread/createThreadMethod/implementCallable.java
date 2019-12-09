package com.thread.createThreadMethod;

import java.util.concurrent.Callable;

public class implementCallable implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" callable");
        return Thread.currentThread().getId();
    }
}
