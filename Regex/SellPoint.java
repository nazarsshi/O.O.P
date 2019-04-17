package com.company;
        import java.io.*;
        import java.util.ArrayList;

public class SellPoint implements Serializable {
    private String pointName;
    private String pointAdress;
    private ArrayList <String> TelNumbers;
    private String specialization;

    public SellPoint(){

    }

    public SellPoint(String pointName , String pointAdress , String specialization ) {
        this.pointName = pointName;
        this.pointAdress = pointAdress;
        this.specialization = specialization;
        this.TelNumbers = new ArrayList<>();
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointAdress() {
        return pointAdress;
    }

    public void setPointAdress(String pointAdress) {
        this.pointAdress = pointAdress;
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
}
