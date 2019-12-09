package com.alan.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 */
public class MytThreadPoolExecutor implements Executor {

    /**
     * 线程名称
     */
    private String name;

    /**
     * 核心线程数
     */
    private int coreSize;

    /**
     * 最大线程数
     */
    private int maxSize;

    /**
     * 任务队列
     */
    private BlockingQueue<Runnable> taskQueue;

    /**
     * 拒绝策略
     */
    private RejectPolicy rejectPolicy;

    /**
     * 线程序列号
     */
    private AtomicInteger sequence = new AtomicInteger(0);

    /**
     * 运行线程数
     */
    private AtomicInteger runningCount = new AtomicInteger(0);

    public MytThreadPoolExecutor(String threadName, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = threadName;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable task) {
        //获取正在运行线程数
        int count = runningCount.get();
        if (count < coreSize){
            if (addWorker(task,true)){
                return;
            }
        }
        if (taskQueue.offer(task)){
            //taskQueue.add(task);
        }else {
            if (!addWorker(task,false)){
                rejectPolicy.reject(task,this);
            }
        }
    }

    private boolean addWorker(Runnable task, boolean core) {
        for (;;){
            int count = runningCount.get();
            int max = core? coreSize : maxSize;
            if (count >=max){
                return false;
            }
            if (runningCount.compareAndSet(count,count+1)){
                String threadName = (core? "core_": "")+ name+ sequence.incrementAndGet();
                new Thread(() -> {
                    System.out.println("thread name"+Thread.currentThread().getName());
                    Runnable newTask = task;
                    while (newTask !=null || (newTask = getTask()) !=null){
                        try {
                            newTask.run();
                        }finally {
                            newTask = null;
                        }

                    }
                },threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try{
            return taskQueue.take();
        }catch (InterruptedException e){
            runningCount.decrementAndGet();
            return null;
        }

    }
}
