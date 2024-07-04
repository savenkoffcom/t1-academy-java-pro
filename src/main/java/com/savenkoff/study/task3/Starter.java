package com.savenkoff.study.task3;

public class Starter {
    public static void main(String[] args) {

        int taskCount = Math.max(Long.valueOf(Math.round(Math.random()*100)).intValue(),1);

        int taskFinallyNumb = Math.max(Long.valueOf(Math.round(taskCount /3F)).intValue(),1);

        CustomThreadPool threadPool = new CustomThreadPool(4, 20);

        System.out.println("Tasks to run: " + taskCount + ". Final task # " + taskFinallyNumb);

        for (int i = 1; i < (taskCount+1); i++) {
            threadPool.execute(new CustomTask(i));
            if (i == taskFinallyNumb)
            {
                //                threadPool.shutdown();
                try {
                    threadPool.awaitTermination();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
