package com.savenkoff.study.task3;

public class Starter {
    public static void main(String[] args) {

        int taskCount = Long.valueOf(Math.round(Math.random()*100)).intValue();

        int taskFinallyNumb = Long.valueOf(Math.round(taskCount /3F)).intValue();

        CustomThreadPool threadPool = new CustomThreadPool(4);

        System.out.println("Tasks to run: " + taskCount + ". Final task # " + taskFinallyNumb);

        for (int i = 0; i < taskCount; i++) {
            threadPool.execute(new CustomTask(i));
            // После трети отправленных на выполнение тасков останавливаем пул, для получения IllegalStateException
            if (i == taskFinallyNumb)
                threadPool.shutdown();
        }
    }
}
