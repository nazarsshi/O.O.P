package com.company;

public class Main {

    public static void main(String[] args) {
            StringBuilder stringBuilder = new StringBuilder();
            if(args[0].equals("") && args.length == 1){
                System.out.println(args[0]);
            }
            if(args[0] != null && !(args[0].equals(""))) {
                String str = args[0];
            char c = str.charAt(0);
            int counter = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == c && counter < 9)
                    counter++;
                else if (counter == 1) {
                    stringBuilder.append(c);
                    c = str.charAt(i);
                    counter = 1;
                } else {
                    stringBuilder.append(c);
                    stringBuilder.append(counter);
                    c = str.charAt(i);
                    counter = 1;
                }
            }
            stringBuilder.append(c);
            if (counter > 1)
                stringBuilder.append(counter);
        }
        System.out.println(stringBuilder.toString());
    }
}
