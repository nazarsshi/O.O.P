package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Container> contElementsAmount = new ArrayList<>();
        byte task;
        while (true) {
            System.out.println("///------------///");
            System.out.println("1-make a container");
            System.out.println("2-fill a container");
            System.out.println("3-show a container");
            System.out.println("4-clear a container");
            System.out.println("5-show a container menu");
            System.out.println("6-exit");
            System.out.println("///------------///");
            task = scan.nextByte();
            switch (task) {
                case 1:
                    System.out.println("Put container`s max length:");
                    scan.nextLine();
                    contElementsAmount.add(new Container(scan.nextInt()));
                    System.out.println("Well done! Your main container`s length is " + contElementsAmount.size());
                    break;
                case 2:
                    System.out.println("Its time to fill your container now.Choose an index");
                    scan.nextLine();
                    int index = scan.nextInt();
                    if (index > contElementsAmount.size() || index < 1) {
                        System.err.println("Error! Wrong input of the index, try again");
                        break;
                    }
                    System.out.println("Enter " + contElementsAmount.get(index-1).maxSize() + " strings:");
                    scan.nextLine();
                    String str = scan.nextLine();
                    int i = 0;
                    while (!(str.equals("/stop")) && i < contElementsAmount.get(index-1).maxSize()) {
                        contElementsAmount.get(index - 1).add(str);
                        str = scan.nextLine();
                        i++;
                    }
                    System.out.println("Well done! Your container is filled");
                    break;
                case 3:
                    System.out.println("Now lets look at any part of your container. Choose an index");
                    scan.nextLine();
                    int lookIndex = scan.nextInt();
                    if (lookIndex > contElementsAmount.size() || lookIndex < 1) {
                        System.err.println("Error! Wrong input of the index, try again");
                        break;
                    }
                    System.out.println(contElementsAmount.get(lookIndex-1));
                    break;
                case 4:
                    System.out.println("Now lets clear your container");
                    scan.nextLine();
                    int clearIndex = scan.nextInt();
                    if (clearIndex > contElementsAmount.size() || clearIndex < 1) {
                        System.err.println("Error! Wrong input of the index, try again");
                        break;
                    }
                    contElementsAmount.get(clearIndex-1).clear();
                    System.out.println("Your container is cleared");
                    break;
                case 5:
                    System.out.println("Now to work with a container menu, you need to put an index of an element of your container you want to work with:");
                    int menuIndex = scan.nextInt();
                    if (menuIndex > contElementsAmount.size() || menuIndex < 1) {
                        System.err.println("Error! Wrong input of the index, try again");
                        break;
                    }
                    System.out.println("Choose an option now");
                    System.out.println("///------------///");
                    System.out.println("1 - add element");
                    System.out.println("2 - remove element");
                    System.out.println("3 - convert to array and iterate through");
                    System.out.println("4 - current size");
                    System.out.println("5 - max size");
                    System.out.println("6 - check whether a string is equal");
                    System.out.println("7 - check any sub container");
                    System.out.println("8 - get element by index");
                    System.out.println("9 - get element's index");
                    System.out.println("10 - sort");
                    System.out.println("11 - iterate through container (foreach)");
                    System.out.println("12 - iterate through container (while)");
                    System.out.println("13 - lets check how the serialization works");
                    System.out.println("14 - lets check how the deserialization works");
                    System.out.println("15 -lets complete the task from a previous lab");
                    System.out.println("16 - return");
                    System.out.println("///------------///");
                    byte task1;
                    task1 = scan.nextByte();
                    switch (task1){
                        case 1:
                            System.out.println("Lets add an element to your container");
                            scan.nextLine();
                            contElementsAmount.get(menuIndex-1).add(scan.nextLine());
                            System.out.println("Success! An element has been added");
                            break;
                        case 2:
                            System.out.println("Now its time to remove an element");
                            System.out.println("Choose an element");
                            scan.nextLine();
                            String remElem = scan.nextLine();
                            if(contElementsAmount.get(menuIndex-1).remove(remElem)) {
                                System.out.println("Success!A chosen element has been removed");
                            }
                            else{
                                System.err.println("Error!");
                            }
                            break;
                        case 3:
                            System.out.println("Lets check how we can convert your container to an array:");
                            System.out.println("Some magic is happening...");
                            String[] arr = contElementsAmount.get(menuIndex-1).toArray();
                            System.out.println();
                            System.out.println();
                            System.out.println("Well done!Lets check it now");
                            for(int k=0 ; k < arr.length; k++){
                                System.out.println(arr[k]);
                            }
                            break;
                        case 4:
                            System.out.println("We also can check a current size of your container element if you want:");
                            System.out.println("The current length is:" + contElementsAmount.get(menuIndex-1).size());
                            break;
                        case 5:
                            System.out.println("Wanna see a maximum length of your container`s element? EZ");
                            System.out.println("It is " + contElementsAmount.get(menuIndex-1).maxSize());
                            break;
                        case 6:
                            System.out.println("Lets check any string on equality to your container`s element");
                            scan.nextLine();
                            System.out.println(contElementsAmount.get(menuIndex-1).contains(scan.nextLine()));
                            System.out.println("Done!");
                            break;
                        case 7:
                            System.out.println("Lets also check other container on equality.Choose an index");
                            scan.nextLine();
                            int otherContIndex = scan.nextInt();
                            System.out.println(contElementsAmount.get(otherContIndex-1));
                            if(otherContIndex > contElementsAmount.size() || otherContIndex < 1){
                                System.err.println("Error! Wrong input of the index, try again");
                                break;
                            }
                            System.out.println("And the result is: " + contElementsAmount.get(menuIndex-1).containsAll( contElementsAmount.get(otherContIndex-1)));
                            System.out.println();
                            break;
                        case 8:
                            System.out.println("Try to get an element by putting its index");
                            scan.nextLine();
                            int elIndex = scan.nextInt();
                            if(elIndex > contElementsAmount.get(menuIndex-1).size() || elIndex < 1) {
                                System.err.println("Error! Wrong input of the index, try again");
                                break;
                            }
                            System.out.println(contElementsAmount.get(menuIndex-1).getIndexOfelem(elIndex));
                            System.out.println("Nice job");
                            break;
                        case 9:
                            System.out.println("Apart from this, we can get an index by putting its element");
                            scan.nextLine();
                            String checkStr = scan.nextLine();
                            System.out.println(contElementsAmount.get(menuIndex-1).indexOfStr(checkStr));
                            System.out.println("Done");
                            break;
                        case 10:
                            System.out.println("Okay new lets try to sort your container");
                            contElementsAmount.get(menuIndex-1).sort();
                            System.out.println("Well done!");
                            break;
                        case 11:
                            System.out.println("Lets iterate your container using for each loop");
                            for(String j: contElementsAmount.get(menuIndex-1)){
                                System.out.println(j);
                            }
                            System.out.println("Nice,its done");
                            break;
                        case 12:
                            System.out.println("Now lets iterate it using while loop ");
                            MyIterator iterator = (MyIterator) contElementsAmount.get(menuIndex-1).iterator();
                            while (iterator.hasNext()){
                                System.out.println(iterator.next());
                            }
                            break;
                        case 13:
                            System.out.println("Its time to serialize your container");
                            System.out.println();
                            System.out.println("Enter the name of the file:");
                            scan.nextLine();
                            String fileName = scan.nextLine();
                            System.out.println(contElementsAmount.get(menuIndex-1).serialization(fileName));
                            System.out.println("The data has been submitted");
                            break;
                        case 14:
                            System.out.println("Deserialization now...");
                            System.out.println();
                            scan.nextLine();
                            String filename = scan.nextLine();
                            System.out.println(contElementsAmount.get(menuIndex-1).deserialization(filename));
                            System.out.println("The data has been recovered");
                            break;
                        case 15:
                            System.out.println("The calculation starts now...");
                            String string = contElementsAmount.get(menuIndex-1).toString();
                            char [] array = string.toCharArray();
                            for(int k = 0; k < array.length; k++){
                                System.out.print(array[k] + " ");
                            }
                            for( int j = 0; j< array.length ; j++){
                                System.out.print((int)array[j] + " ");
                            }
                            break;
                        case 16:
                            break;
                        default:
                            System.err.println("Wrong input!");
                    }
                    break;

                case 6:
                    System.out.println("The process is finished");
                    System.exit(0);
                default:
                    System.err.println("Wrong input!");
            }
        }
    }
    }
