package com.alan.threadPool;

public class DiscardRejectPolicy implements RejectPolicy {
    @Override
    public void reject(Runnable task, MytThreadPoolExecutor mytThreadPoolExecutor) {
        System.out.println("discard one task");
    }
}
