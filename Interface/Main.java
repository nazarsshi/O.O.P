package com.company;

public class Main {

    public static void main(String[] args) {
        if (args == null || args.length == 0 || args.length > 2)
            System.out.println("Error");
        else if (args.length == 1) {
            switch (args[0]) {
                case "help":
                    new Help().execute();
                    break;
                case "exit":
                    new Exit().execute();
                    break;
                default:
                    System.out.println("Error");
            }
        }
        else {
            switch (args[0]) {
                case "echo":
                    new Echo(args[1]).execute();
                    break;
                case "date":
                    if (args[1].equals("now"))
                        new Date().execute();
                    else
                        System.out.println("Error");
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }
}
