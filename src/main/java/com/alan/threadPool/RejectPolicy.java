package com.alan.threadPool;

public interface RejectPolicy {
    void reject(Runnable task,MytThreadPoolExecutor mytThreadPoolExecutor);
}
