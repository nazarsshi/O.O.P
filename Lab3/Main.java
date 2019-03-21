package com.company;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("uk", "UA"));
        Scanner scan = new Scanner(System.in);
        ArrayList<SellPoint> sellPoints = new ArrayList<>();
        System.out.println("~~~Привіт!~~~");
        byte command;
        while(true){
            System.out.println("Це меню, з яким ви можете працювати.\n");
            System.out.println("1 - додати нову торгівельну точку");
            System.out.println("2 - показати наявні торгівельні точки");
            System.out.println("3 - видалити існуючу торгівельну точку");
            System.out.println("4 - перейти до спеціального меню управління певною торгівельною точкою");
            System.out.println("5 - записати до XML файлу");
            System.out.println("6 - зчитати з XML файлу");
            System.out.println("7 - вихід");
            System.out.print("\n");
            System.out.print("Введіть будь-ласка номер бажаної команди: ");
            command = scan.nextByte();
            switch (command){
                case 1:
                    System.out.println("Додайте торгівельну точку. Для цього необхідно ввести її назву,адресу та спеціалізацію.");
                    scan.nextLine();
                    sellPoints.add(new SellPoint(scan.nextLine(),scan.nextLine(),scan.nextLine()));
                    System.out.println("Зазначте індекс доданої точки ");
                    int mainIndex = scan.nextInt();
                    if(mainIndex > sellPoints.size() || mainIndex < 0){
                        System.err.println("Помилкове введення індексу!");
                        break;
                    }
                    System.out.println("Тепер введіть дійсні телефонні номери доданої торгівельної точки та їхню кількість");
                    int numbAmount = scan.nextInt();
                    String num = scan.nextLine();
                    int j =0;
                    while (!(num.equals("/stop")) && j < numbAmount ){
                        num = scan.nextLine();
                        sellPoints.get(mainIndex-1).setTelNumbers(num);
                        j++;
                    }
                    System.out.println("Чудово, ваш контейнер тепер містить " + sellPoints.size() + " (к-сть торгівельних точок)");
                    break;
                case 2:
                    System.out.println("Перегляньте всі наявні торгівельні точки");
                    if(sellPoints.size() == 0){
                        System.err.println("Не знайдено жодної торгівельної точки");
                        break;
                    }
                    System.out.println("Cписок усіх торгівельних точок з основними вказаними параметрами:\n");
                    for (int i = 0; i < sellPoints.size(); i++){
                        System.out.println(sellPoints.get(i).getPointName());
                        System.out.println(sellPoints.get(i).getPointAdress());
                        System.out.println(sellPoints.get(i).getSpecialization());
                        System.out.print("\n");
                    }
                    System.out.println("Зроблено!\n");
                    break;
                case 3:
                    System.out.println("Тепер давайте видалим будь яку з існуючих торгівельних точок.Введіть її індекс");
                    scan.nextLine();
                    int delIndex = scan.nextInt();
                    if(delIndex > sellPoints.size() || delIndex < 1){
                        System.err.println("Помилкове введення індексу!Спробуйте ще раз");
                        break;
                    }
                    sellPoints.remove(delIndex-1);
                    System.out.println("Готово! Вибрана торгівельна точка була видалена");
                    break;
                case 4:
                    System.out.println("Вам необхідно ввести індекс торгівельної точки, щоб отримати доступ до меню управління");
                    scan.nextLine();
                    int menuIndex = scan.nextInt();
                    if( menuIndex > sellPoints.size() || menuIndex < 1){
                        System.err.println("Помилкове введення індексу! Спробуйте ще раз");
                        break;
                    }
                    System.out.println("Перед вами меню управління. Ви беріть бажану опцію \n");
                    System.out.println("~~~|||~~~");
                    System.out.println("1 - показати назву торгівельної точки");
                    System.out.println("2 - показати адресу торгівельної точки");
                    System.out.println("3 - показати спеціалізацію торгівельної точки");
                    System.out.println("4 - змінити назву торгівельної точки");
                    System.out.println("5 - змінити адресу торгівельної точки");
                    System.out.println("6 - змінити спеціалізацію торгівельної точки");
                    System.out.println("7 - дізнатись години роботи торгівельної точки згідно з днем тижня");
                    System.out.println("8 - дізнатись дні роботи торгівельної точки згідно вказаних годин");
                    System.out.println("9 - показати усі наявні телефонні номери торгівельної точки");
                    System.out.println("10 - додати інші телефонні номери для поточної торгівельної точки");
                    System.out.println("11 - вийти з меню управління");
                    System.out.println("~~~|||~~~");
                    System.out.print("\n");
                    scan.nextLine();
                    System.out.print("Введіть опцію в це поле: ");
                    byte task = scan.nextByte();
                    switch (task){
                        case 1:
                            System.out.println(" Назва даної торгівельної точки - " + sellPoints.get(menuIndex-1).getPointName());
                            break;
                        case 2:
                            System.out.println(" Адреса даної торгівельної точки - " + sellPoints.get(menuIndex-1).getPointAdress());
                            break;
                        case 3:
                            System.out.println(" Спеціалізація даної торгівельної точки - " + sellPoints.get(menuIndex-1).getSpecialization());
                            break;
                        case 4:
                            System.out.println(" Окрім того, ви можете редагувати назву даної торгівельної точки");
                            scan.nextLine();
                            String newName = scan.nextLine();
                            sellPoints.get(menuIndex-1).setPointName(newName);
                            System.out.println("Готово!\n");
                            break;
                        case 5:
                            System.out.println("Також можливо змінити адресу даної торгівельної точки");
                            scan.nextLine();
                            String newAdress = scan.nextLine();
                            sellPoints.get(menuIndex-1).setPointAdress(newAdress);
                            System.out.println("Готово!\n");
                            break;
                        case 6:
                            System.out.println(" Змініть спеціалізацію даної торгівельної точки");
                            scan.nextLine();
                            String newSpecialization = scan.nextLine();
                            sellPoints.get(menuIndex-1).setSpecialization(newSpecialization);
                            System.out.println("Готово!\n");
                            break;
                        case 7:
                            System.out.println("Введіть день тижня, який вас цікавить");
                            scan.nextLine();
                            String day = scan.nextLine();
                            if(!(day.equals("Понеділок")) && !(day.equals("Вівторок")) && !(day.equals("Середа")) && !(day.equals("Четвер"))
                               && !(day.equals("П`ятниця")) && !(day.equals("Субота")) && !(day.equals("Неділя"))){
                                System.err.println("Помилкове введення дня тижня! Спробуйте ще раз");
                                break;
                            }
                            System.out.println("В цей день робочий час є таким : " + sellPoints.get(menuIndex-1).workingTime(day));
                            System.out.println("Готово!\n");
                            break;
                        case 8:
                            System.out.println("Введіть робочий час, який вас цікавить");
                            scan.nextLine();
                            String time = scan.nextLine();
                            sellPoints.get(menuIndex-1).workingDay(time);
                            System.out.println("Готово!\n");
                            break;
                        case 9:
                            System.out.println("Наявні телефонні номери торгівельної точки " + sellPoints.get(menuIndex-1).getPointName()
                                    +":" + sellPoints.get(menuIndex-1).getTelNumbers());
                            System.out.println("Готово!\n");
                            break;
                        case 10:
                            System.out.println("Додайте бажану кількість телефонних номерів");
                            int changeAmount = scan.nextInt();
                            String num2 = scan.nextLine();
                            int k = 0;
                            while (!(num2.equals("/stop")) && k < changeAmount){
                                num2 = scan.nextLine();
                                sellPoints.get(menuIndex-1).setTelNumbers(num2);
                                k++;

                            }
                            System.out.println("Готово!\n");
                            break;
                        case 11:
                            break;
                        default:
                            System.err.println("Помилкове введення опції!");
                    }
                    break;
                case 5:
                    String str = PathGetter.getPath(scan).toString();
                    System.out.println("Серіалізація в XML...");
                    System.out.println(str);
                    try (
                            XMLEncoder encoder = new XMLEncoder(
                                    new BufferedOutputStream(
                                            new FileOutputStream(str)))
                            )
                    {
                       Integer size = sellPoints.size();
                       encoder.writeObject(size);
                        for (SellPoint i: sellPoints) {
                            encoder.writeObject(i);
                        }
                    }
                    catch (Exception exc){
                        break;
                    }
                    System.out.print("Зроблено!\n");
                    break;
                case 6:
                    System.out.println("Десеріалізація в об`єкт(и)...");
                    try (XMLDecoder decoder = new XMLDecoder(
                            new BufferedInputStream(
                                    new FileInputStream(PathGetter.getPath(scan).toString())))
                    )
                    {
                        Integer size = (Integer) decoder.readObject();
                        sellPoints = new ArrayList<>();
                        for( int i = 0; i < size; i++){
                            sellPoints.add((SellPoint) decoder.readObject());
                        }

                    }
                    catch (FileNotFoundException exception){
                        System.err.println(exception.getMessage());
                        break;
                    }
                    catch (NullPointerException e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 7:
                    System.out.println("Процес завершено. Дякую за користування");
                    System.exit(0);
                default:
                    System.err.println("Помилкове введення команди! Спробуйте ще");
            }
        }
    }
}