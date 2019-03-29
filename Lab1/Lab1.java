package com.company;
import java.util.Scanner;

public class Lab1{

    public static void main(String[] args) {
        boolean debug = false;
        boolean help = false;
        if(args != null){
            for(String i : args){
                if(i.equals("-h") || i.equals("-help"))
                    help = true;
                if(i.equals("-d") || i.equals("-debug"))
                    debug = true;
            }
            if(help){
                System.out.println("I lab | Nazarsshi | CS-109 | 5");
                System.out.println("---");
                System.out.println("5. Ввести текст. У тексті кожну літеру замінити її номером в алфавіті." +
                        "Вивести результат наступним чином: в одному рядку друкувати текст з" +
                        "двома пропусками між буквами, в наступному рядку під кожною буквою" +
                        "друкувати її номер.");
                System.out.println("---");

            }
        }
        Scanner sc= new Scanner(System.in);
        String str=null;
        char arr[]=null;
        while(true) {
            System.out.println("1-input");
            System.out.println("2-show input");
            System.out.println("3-calculate");
            System.out.println("4-show result");
            System.out.println("5-exit");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Input your data:");
                    sc.nextLine();
                    str = sc.nextLine();
                    if(debug)
                        Debugger.debugger(str);
                    break;
                case 2:
                    System.out.println("Your input is:" + str);
                    if(debug)
                        Debugger.debugger(str);
                    break;
                case 3:
                    System.out.println("The calculation starts now...");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("The result of the calculation:");
                    if(debug)
                        Debugger.debugger(str);
                    arr = str.toCharArray();
                    for (int i = 0; i < arr.length; i++) {
                        System.out.print( arr[i] + "   ");
                    }
                    System.out.println();
                    for (int j = 0; j < arr.length; j++) {
                        System.out.print( (int) arr[j] + " ");
                    }
                    System.out.println();
                    break;
                case 5:
                    if(debug)
                        Debugger.debugger(str);
                    System.out.println("The program is done");
                    System.exit(0);
                    break;
                 default:
                     System.out.println("Wrong input or any other mistake. Try again");

            }
        }
    }
}
