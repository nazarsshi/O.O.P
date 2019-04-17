package com.company;

import static java.lang.Thread.sleep;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            sleep(1000);

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
