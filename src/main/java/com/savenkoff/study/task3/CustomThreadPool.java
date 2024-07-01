package com.savenkoff.study.task3;

import java.util.concurrent.*;

public class CustomThreadPool {

    private final ThreadPoolExecutor threadPoolExecutor;

    /**
     * Create a new my {@code ThreadPoolExecutor} with the given initial
     * parameters.
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code poolSize < 1}<br>
     */
    public CustomThreadPool(int poolSize) {
        if (poolSize < 1)
            throw new IllegalArgumentException("poolSize variable most be greater than 1!");

        threadPoolExecutor = new ThreadPoolExecutor(
                  poolSize
                , poolSize
                , 1
                , TimeUnit.MINUTES
                , new LinkedBlockingQueue<>(poolSize * 4)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
        System.out.println("CustomThreadPool created: ");
        System.out.println("corePoolSize: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("poolSize: " + threadPoolExecutor.getPoolSize());
    }

    public void shutdown() {
        System.out.println("Shutting down thread pool...");
        threadPoolExecutor.shutdown();
        System.out.println("Shutting down thread pool complete!");
    }

    public boolean awaitTermination() throws InterruptedException {
        return threadPoolExecutor.awaitTermination(0L,TimeUnit.NANOSECONDS);
    }

    public void execute(Runnable runnable) {
        if (threadPoolExecutor.isShutdown())
            throw new IllegalStateException("CustomThreadPool is shutdown!");
        threadPoolExecutor.execute(runnable);
    }
}
