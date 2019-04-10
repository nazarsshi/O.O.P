package com.company;

public class StopThread extends Thread {
    private long TimeLimit;

    public StopThread(long timeLimit){
        TimeLimit = timeLimit;
    }
    @Override
    public void run(){
        long start_time = System.currentTimeMillis();
        while (TimeLimit > System.currentTimeMillis() - start_time){
        }
        System.out.println("Кінець роботи програми...");
        System.exit(0);
    }
}
