package com.company;


import java.util.concurrent.Callable;
import java.lang.Math;

public class MyCallable implements Callable{
    private int Length;

    public MyCallable(int len){
        Length = len;
    }

    @Override
    public double[] call() throws Exception {
        MyArrayList ListedArray = new MyArrayList();
        ListedArray.Random(Length);
        double[] MM_arr = ListedArray.MinorMax();
        double[] results = new double[4];
        int count = ListedArray.isBigger(Math.random() * 77);
        results[0] = ListedArray.Average();
        results[1] = MM_arr[0];
        results[2] = MM_arr[1];
        results[3] = count;
        return results;
    }
}
