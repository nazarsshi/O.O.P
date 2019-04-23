package com.company;

public class ExitThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(10000);

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
