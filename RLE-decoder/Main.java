package com.company;

public class Main {

    private static boolean isNumeric( char c){
        return Character.isDigit(c);
    }
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        if(args[0].equals("") && args.length == 1) {
            System.out.println(args[0]);
        }
        String str = args[0];
        int counter = 0;

        if((args[0] != null && !(args[0].equals(""))) && args.length == 1) {
            for (int i = 0; i < str.length() - 1; i++) {
                if(isNumeric(str.charAt(0))){
                    System.out.println("");
                    return;
                }
                if (str.charAt(i) == str.charAt(i + 1)) {
                    System.out.println("");
                    return;

                } else if (isNumeric(str.charAt(i)) && isNumeric(str.charAt(i + 1))) {
                    System.out.println("");
                    return;
                }
            }

                    for (int i = 0; i < str.length(); i++) {

                        if (isNumeric(str.charAt(i)) && i != 0) {
                            counter = str.charAt(i) - '0';
                            if (counter > 9) System.out.println("");
                            else {
                                for (int j = 0; j < counter - 1; j++) {
                                    stringBuilder.append(str.charAt(i - 1));
                                }
                            }
                        } else {
                            stringBuilder.append(str.charAt(i));
                        }
                    }

            System.out.println(stringBuilder.toString());
        }
    }
}
