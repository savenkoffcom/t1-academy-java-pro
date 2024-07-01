package com.savenkoff.study.task3;

public class CustomTask implements Runnable {

    private final int taskNum;

    @Override
    public void run() {
        long sleepTime = (long) (Math.random()*10*1000);
        System.out.println("Run task #" + taskNum + ", sleeping " + sleepTime/1000F + " seconds...");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End task #" + taskNum + "!");
    }

    public CustomTask(int taskNum) {
        this.taskNum = taskNum;
    }
}
