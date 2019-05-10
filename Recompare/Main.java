package com.company;

import java.util.Scanner;

public class Main {
    private static void sort(double[] array, Comparat comp) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                double val = array[i];
                int j;
                for (j = i; j >= gap && comp.compare(array[j - gap], val) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = val;
            }
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        double[] array = new double[amount];
        for (int i = 0; i < array.length ; i++) {
            array[i] =  Math.random() * 77;
        }
        class Recomparator extends Comparat{
            @Override
            public int compare(double a, double b){
                if (a < b) {
                    return 1;
                } else if (a == b){
                    return 0;
                } else {
                    return -1;
                }
            }
        }

        Recomparator recomparator =  new Recomparator();
        sort(array,recomparator);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
