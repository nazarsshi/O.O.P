
package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class SellPoint implements Serializable, Comparator<SellPoint>, Comparable<SellPoint> {
    private String pointName;
    private String pointAddress;
    private ArrayList <String> TelNumbers;
    private String specialization;
    private String ID;

    public SellPoint(){

    }

    public SellPoint(String ID, String pointName , String pointAdress , String specialization ) {
        this.ID = ID;
        this.pointName = pointName;
        this.pointAddress = pointAdress;
        this.specialization = specialization;
        this.TelNumbers = new ArrayList<String>();
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public String getTelNumbers() {
        return TelNumbers.toString();
    }

    public void setTelNumbers(String telNumber) {
        TelNumbers.add(telNumber);
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String workingTime(String dayOfWeek){
        String wrk1 = "8:00 - 20:00";
        String wrk2 = "10:00 - 16:00";
        if(dayOfWeek.equals("Субота") || dayOfWeek.equals("Неділя")){
            return wrk2;
        }
        return wrk1;
    }
    public String workingDay(String workingTime){
        if(workingTime.equals("8:00 - 20:00")){
            System.out.println(" Робочі дні,з даним графіком : Понеділок,Вівторок,Середа,Четвер,П`ятниця");
        }
        else if( workingTime.equals("10:00 - 16:00")){
            System.out.println("Робочі дні,з даним графіком : Cубота,Неділя");
        }
        else{
            System.err.println("Дана торгівельна точка не працює за таким розкладом у жоден із днів тижня");
        }
        return  null;
    }

    @Override
    public int compareTo(SellPoint o) {
        return 0;
    }

    @Override
    public int compare(SellPoint o1, SellPoint o2) {
        return 0;
    }

    @Override
    public  String toString(){
        return "|" + this.pointName + "|" + this.pointAddress + "|" + this.specialization + "|";
    }

    public static Comparator<SellPoint> NameComparator = new Comparator<SellPoint>() {
        @Override
        public int compare(SellPoint o1, SellPoint o2) {
            return o1.pointName.compareTo(o2.pointName);
        }
    };

    public static Comparator<SellPoint> AddressComparator = new Comparator<SellPoint>() {
        @Override
        public int compare(SellPoint o1, SellPoint o2) {
            return o1.pointAddress.compareTo(o2.pointAddress);
        }
    };

    public static Comparator<SellPoint> SpecializationComparator = new Comparator<SellPoint>() {
        @Override
        public int compare(SellPoint o1, SellPoint o2) {
            return o1.specialization.compareTo(o2.specialization);
        }
    };
}