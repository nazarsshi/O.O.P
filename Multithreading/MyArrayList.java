package com.company;

import java.util.Iterator;
import java.lang.Math;

    public class MyArrayList  implements Iterable {
        private class Node {
            private double data;
            private Node next;

            public double getData() {
                return data;
            }

            public void setData(double data) {
                this.data = data;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

        }
        private Node first;
        private int size = 0;

        public int size(){
            return size;
        }


        public void add(double data){
            Node init = new Node();
            init.setData(data);
            if (first != null){
                Node temp = first;
                while ((temp.getNext())!= null){
                    temp = temp.getNext();
                }
                temp.setNext(init);
            }
            else {
                first = init;
            }
            size++;
        }

        public double get(int index){
            Node temp = first;
            int counter = 0;
            while (temp != null) {
                if (counter++ == index) {
                    return  temp.getData();
                }
                temp = temp.getNext();
            }
            return 0;
        }


        public String toString(){
            StringBuilder result = new StringBuilder();
            Node temp = first;
            while (temp != null){
                result.append(temp.getData());
                temp = temp.getNext();
            }
            return new String(result);
        }


        public double[] MinorMax(){
            double min = first.getData();
            double max = first.getData();
            double[] arr = new double[2];
            Node temp = first;
            while( temp.getNext() != null){
                if(min > temp.getData()){
                    min = temp.getData();
                    arr[0] = min;
                }
                if( max < temp.getData()){
                    max = temp.getData();
                    arr[1] = max;
                }
                temp = temp.getNext();
            }
            return arr;
        }

        public double Average(){
            double sum = 0;
            int count = 0;
            Node temp = first;
            while (temp.getNext() != null){
                sum += temp.getData();
                count++;
                temp = temp.getNext();
            }
            double average = (sum/count);
            return average;
        }

        public void Random(int len){
            for (int i = 0; i < len ; i++) {
                double adder = Math.random() * 100;
                this.add(adder);
            }
        }

        public int isBigger(double d){
            int counter = 0;
            Node temp = first;
            while (temp.getNext()!= null){
                if(temp.getData() > d){
                    counter++;
                }
                temp = temp.getNext();
            }
            return counter;
        }


        @Override
        public Iterator iterator() {
            return new Iterator() {
                int counter;

                {
                    counter = 0;
                }

                @Override
                public boolean hasNext() {
                    return counter < size();
                }

                @Override
                public Double next() {
                    return get(counter++);
                }
            };
        }
    }

