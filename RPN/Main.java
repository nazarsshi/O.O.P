package com.company;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Main {

    private static boolean numeralOrNot(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        int commaCount = 0;
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '.') {
                commaCount++;
            }
            if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') || commaCount > 1) {
                return false;
            }
        }

        return true;
    }

    public static double parse(String rpnString) throws RPNParserException{
        Deque<Double> deque = new LinkedList<>();
        double res;
        int Numcounter = 0, Charcounter = 0;

        String[] strings = rpnString.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].charAt(0) == '.') throw new RPNParserException();
            if (numeralOrNot(strings[i])) {
                Numcounter++;
                try {
                    deque.push(Double.parseDouble(strings[i]));
                }catch (NumberFormatException E){
                    throw new RPNParserException();
                }
            }
            else {
                if(!deque.isEmpty()) {
                    try {
                        try {
                            switch (strings[i].charAt(0)) {
                                case '+':
                                    Charcounter++;
                                    res = deque.pop() + deque.pop();
                                    deque.push(res);
                                    break;
                                case '-':
                                    Charcounter++;
                                    res = deque.pop() - deque.pop();
                                    deque.push(res);
                                    break;
                                case '*':
                                    Charcounter++;
                                    res = deque.pop() * deque.pop();
                                    deque.push(res);
                                    break;
                                case '/':
                                    Charcounter++;
                                    double a = deque.pop();
                                    double b = deque.pop();
                                    if (a != 0 & b != 0) {
                                        if (b == 0) {
                                            throw new ArithmeticException();
                                        } else {
                                            deque.push(a / b);
                                        }
                                    } else {
                                        throw new ArithmeticException();
                                    }
                                    break;
                                default:
                                    throw new RPNParserException();
                            }
                        }catch (StringIndexOutOfBoundsException e){
                            throw new RPNParserException();
                        }
                    }catch (NoSuchElementException e){
                        throw new RPNParserException();
                    }
                }
                else {
                    throw new RPNParserException();
                }
            }
        }
        if((Numcounter -Charcounter) > 1 || Charcounter > Numcounter){
            throw  new RPNParserException();
        }

        try {
            return deque.peek();
        }catch (NullPointerException e){
            throw new RPNParserException();
        }
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("");
        }
        else {
            String str = args[0];
            System.out.println(parse(str));
        }
    }
}
