package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(new Locale("uk", "UA"));
        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть бажану ксть потоків: ");
        int Threads = scan.nextInt();
        int len = 50000;
        System.out.print("Введіть бажаний час у мілісекундах, після якого програма припинить виконання: ");
        long stop_time = scan.nextLong();
        StopThread thread = new StopThread(stop_time);
        thread.start();
        long start_time = System.currentTimeMillis(), parTime,seqTime;
        ArrayList<double[]> results = new ArrayList<>();
        ArrayList<Future<double[]>> future_res = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(Threads);
        for (int i = 0; i <Threads ; i++) {
            future_res.add(executorService.submit(new MyCallable(len)));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException ex) {
            ex.getMessage();
        }
        parTime = System.currentTimeMillis() - start_time;
        start_time = System.currentTimeMillis();
        MyCallable SequentThread = new MyCallable(len);
        for (int i = 0; i <Threads ; i++) {
            results.add(SequentThread.call());
        }
        seqTime = System.currentTimeMillis() - start_time;
        double[]res = {0};
        double[]res2 = {0};
        System.out.println("+---------------+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "Тест", "Час виконання","К-сть потоків","К-сть даних");
        System.out.println("+---------------+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#1", parTime,Threads,len);
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#2", seqTime,Threads,len);
        System.out.println("+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("Результати:");
        System.out.println("+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-35s|%-30s|%-25s|%-15s|%n","Потік|","Мін.знач.|","Макс.знач.|","Сер.знач.|","К-сть по певному критерію");
        System.out.println("+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");
        for (int i = 0; i <Threads ; i++) {
             res = future_res.get(i).get();
        }
        System.out.format("|%-15s|%-30s|%-30s|%-35s|%-10s%n","Паралельний|", res[1]+"|", res[2]+"|" ,res[0]+"|" ,res[3]+"|");
        for (int j = 0; j <Threads ; j++) {
             res2 = results.get(j);
        }
        System.out.format("|%-15s|%-30s|%-30s|%-35s|%-10s%n","Пoслідовний|", res2[1]+"|" ,res2[2]+"|" ,res2[0]+"|" ,res2[3]+"|");
        System.out.println("+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");
    }
}