package com.company;

import java.io.*;
import java.util.Iterator;

public class Container implements Iterable<String>, Serializable {

    private String[] data;
    private int currentLen=0;

    Container(int length) {
        if (length > 0) {
            data = new String[length];
        } else {
            throw new ExceptionInInitializerError();

        }
    }

    public String toString() {
        if (currentLen == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < currentLen; i++) {
            result.append(data[i]);
        }
        return new String(result);

    }

    public String[] toArray() {
        String[] newStr = new String[currentLen-1];
        int sourcePos = 0;
        int destPos = 0;
        System.arraycopy(data, sourcePos, newStr, destPos, currentLen-1);
        return newStr;
    }

    void clear() {
        for (int i = 0; i < currentLen; i++) {
            data[i] = null;
        }
        currentLen = 0;
    }

    void add(String s) {
        data[currentLen++] = s;
    }
    String get(int index){
        if(index > currentLen){
            return null;
        }
        else{
            return data[index];
        }
    }

    public int size() {
        return currentLen;
    }

    boolean remove(String s) {
        boolean checker = false;
        for( int i = 0; i<currentLen; i++) {
            if(checker)data[i-1] = data[i];
            else if(s.equals(data[i])){
                checker = true;
                currentLen--;
            }
        }
        return checker;
    }

    boolean contains(String str) {
        for (int i = 0; i < currentLen; i++) {
            if (str.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    boolean containsAll(Container container) {
        boolean found = false;
        for (String i : container) {
            for (int j = 0; j < currentLen; j++){
                if(i.equals(data[j])){
                    found = true;
                    break;
                }
            }
            if(!found) {
                return false;
            }
            found = false;
        }
        return true;
    }

    int elementsCompare(int index1, int index2){
        String str1 = data[index1];
        String str2 = data[index2];
        int length1 = str1.length();
        int length2 = str2.length();

        for(int i = 0; i < length1 && i < length2; i++) {
            int str1_amount = (int) str1.charAt(i);
            int str2_amount = (int) str2.charAt(i);
            if ((str1_amount!=str2_amount)){
                return str1_amount - str2_amount;
            }
        }
        if(length1!=length2){
            return length1-length2;
        }
        else{
            return  0;
        }
    }
    public String getIndexOfelem(int index){
        if(index > currentLen) {
            return null;
        }
        return data[index];
    }
    public int indexOfStr(String str){
        for(int i=0 ; i < currentLen ; i++){
            if(str.equals(data[i])){
                return i;
            }
            else {
                return -1;
            }
        }
        return -1;
    }

    public void quicksort(String arr[], int first, int last) {
        int i = first, j = last;
        int m = (first + last) / 2;
        String swap;
        do {
            while (elementsCompare(i, m) > 0) i++;
            while (elementsCompare(j, m) < 0) j--;
            if(i <= j) {
                if (i < j) {
                    swap=arr[i];
                    arr[i]=arr[j];
                    arr[j]=swap;
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (i < last)
            quicksort(arr, i, last);
        if (first < j)
            quicksort(arr, first,j);
    }

    void sort() {
        quicksort(data, 0, currentLen-1);
    }
    int maxSize(){
        return data.length;
    }
    boolean serialization(String nameOfFile){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(nameOfFile);

        }
        catch (FileNotFoundException ex){
            System.err.println(ex.getMessage());
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            return true;
        }
        catch (IOException exc){
            System.err.println(exc.getMessage());
            return false;
        }
    }

    boolean deserialization ( String nameOfFile){
        FileInputStream fis;
        try {
            fis = new FileInputStream(nameOfFile);
        }
        catch (FileNotFoundException ex){
            System.err.println(ex.getMessage());
            return false;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Container container = (Container)ois.readObject();
            return true;
        }
        catch (IOException exception){
            System.err.println(exception.getMessage());
            return false;
        }
        catch (ClassNotFoundException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new MyIterator(data,currentLen);
    }
}


