package com.company;
import java.io.Serializable;
import java.util.Iterator;

public class MyArrayList <T> implements Iterable <T>, Serializable {
    private class Node implements Serializable {
        private T data;
        private Node next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
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

        public void clear(){
            first = new Node();
        }

        public void add(T data){
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

        public T get(int index){
            Node temp = first;
            int counter = 0;
            while (temp != null) {
                if (counter++ == index) {
                    return (T) temp.getData();
                }
                temp = temp.getNext();
            }
            return null;
        }

        public boolean remove(int index){
            if(size <= 0){
                return false;
            }
            Node prev = null;
            Node temp = first;
            int counter = 0;
            while (temp != null){
                if(counter++ == index){
                    if(prev == null){
                        first = temp.getNext();
                    }
                    else if(temp.getNext() == null){
                        prev.setNext(null);
                    }
                    else prev.setNext(temp.getNext());
                    size--;
                    return true;
                }
                prev = temp;
                temp = temp.getNext();
            }
            return false;
        }

        public boolean remove(T data){
            if(size <= 0){
                return false;
            }
            Node prev = null;
            Node temp = first;
            while (temp != null){
                if (temp.getData().equals(data)){
                    if(prev == null){
                        first = temp.getNext();
                    }
                    else if (temp.getNext() == null){
                        prev.setNext(null);
                    }
                    else{
                        prev.setNext(temp.getNext());
                    }
                    size--;
                    return true;
                }
                prev = temp;
                temp = temp.getNext();
            }
            return false;
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

        public T[] toArray(){
            T[] array = (T[]) new Object[size()];
            for (int i = 0; i < size(); i++) {
                array[i] = get(i);
            }
            return array;
        }

        public boolean contains(T value){
            boolean checker = false;
            for (T i: this) {
                if(value.equals(i)){
                    checker = true;
                    return checker;
                }
            }
            return false;
        }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter;

            {
                counter = 0;
            }

            @Override
            public boolean hasNext() {
                return counter < size();
            }

            @Override
            public T next() {
                return get(counter++);
            }
        };
    }
}
