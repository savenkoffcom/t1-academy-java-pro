package com.savenkoff.study.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CustomThreadPool {
    private final LinkedBlockingQueue<Runnable> blockingQueue;
    private final List<Thread> threadsList;
    private volatile boolean isShutdown;
    private volatile boolean isTerminate;

    /**
     * Create a new my {@code ThreadPoolExecutor} with the given initial
     * parameters.
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code poolSize < 1}<br>
     *         {@code queueCapacity < 1}<br>
     */
    public CustomThreadPool(int poolSize, int queueCapacity) {
        if (poolSize < 1)
            throw new IllegalArgumentException("poolSize variable most be greater than 1!");
        if (queueCapacity < 1)
            throw new IllegalArgumentException("queueCapacity variable most be greater than 1!");

        threadsList = new ArrayList<>(poolSize);
        blockingQueue = new LinkedBlockingQueue<>(queueCapacity);

        for (int i = 0; i < poolSize; i++) {
            CustomThread newThread = new CustomThread();
            threadsList.add(newThread);
            newThread.start();
        }

        System.out.println("CustomThreadPool created, poolSize " + poolSize);
    }

    public void shutdown() {
        System.out.println("Shutting down thread pool...");
        isShutdown = true;
        for (Thread thread: threadsList) {
            System.out.println("Stopping " + thread.getName() + " ...");
            thread.interrupt();
        }
        System.out.println("Shutting down thread pool complete!");
    }

    public void awaitTermination() throws InterruptedException {
        System.out.println("Await terminate thread pool...");
        while (true) {
            for (Thread thread: threadsList) {
                isTerminate = (blockingQueue.isEmpty()) && (thread.getState() == Thread.State.WAITING);
            }
            Thread.sleep(100);
            if (isTerminate)
                break;
        }
        System.out.println("CustomThreadPool is terminated!");
    }

    public void execute(Runnable runnable) {
        if (isShutdown)
            throw new IllegalStateException("CustomThreadPool is shutdown!");
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private class CustomThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    blockingQueue.take().run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if ((isShutdown | isTerminate) & blockingQueue.isEmpty())
                    break;
            }
        }
    }
}


