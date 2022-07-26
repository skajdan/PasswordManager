package com.michal;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Encryption encryption = new Encryption();
        FileOperations fileOperations = new FileOperations();

        System.out.println("Select file:");

        Scanner scanner = new Scanner(System.in);
        String fileName = "D:\\Users\\gurtm\\IdeaProjects\\PasswordManager\\src\\com\\michal\\" + scanner.nextLine();
        File file = new File(fileName);

        while(true){
            if(file.exists())
                break;
            else {
                System.out.println("Try agin");
                fileName = "D:\\Users\\gurtm\\IdeaProjects\\PasswordManager\\src\\com\\michal\\" + scanner.nextLine();
                file = new File(fileName);

            }
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String mainPassword = bufferedReader.readLine();

        while (true){
            System.out.println("Insert Main Password: ");
            String password = scanner.nextLine();
            if (password.equals(encryption.dencryption(mainPassword))) {
                File file1 = new File("D:\\Users\\gurtm\\IdeaProjects\\PasswordManager\\src\\com\\michal\\login_data.txt");
                BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
                String lastLogin = bufferedReader1.readLine();
                System.out.println("Last successful login: " + lastLogin );
                Date date = new Date();
                PrintWriter printWriter = new PrintWriter("src/com/michal/login_data.txt");
                printWriter.println(new Timestamp(date.getTime()));
                printWriter.close();
                break;
            }
            System.out.println("Try agin!");
        }

        while (true){
            System.out.println("1.Enter new password ");
            System.out.println("2.Download passwords ");
            System.out.println("3.Exit");
            int choice = scanner.nextInt();

            if (choice==1) {
                    System.out.println("How many passwords would you like to insert?: ");
                    choice = scanner.nextInt();

                    fileOperations.putPassword(choice,fileName);


                }

                else if (choice==2){
                    List<Password> passwordList = fileOperations.toArrayList(fileName);
                    System.out.println("1: Search for passwords from matching category ");
                    System.out.println("2: Sort passwords ");
                    System.out.println("3: Delete password ");
                    int choice2 = scanner.nextInt();

                    if (choice2==1) {
                        System.out.println("Type in category: ");
                        String category;
                        scanner.nextLine();
                        category = scanner.nextLine();
                        listFilter(passwordList, category);
                    }

                    else if (choice2 == 2){
                        System.out.println("By what would you like to sort passwords? ");
                        System.out.println("1:Names ");
                        System.out.println("2:Category ");
                        scanner.nextLine();
                        int category = scanner.nextInt();

                        while (category > 2 || category < 1){

                                System.out.println("Wrong parameter, try agin: ");
                                category = scanner.nextInt();
                                System.out.println(category);




                        }

                        System.out.println(category);
                        if (category == 1)
                            sortList(passwordList,"Names");
                        else
                           sortList(passwordList,"Category");

                        System.out.println("\n\n\n\n");


                    }

                    else if (choice2 == 3){
                        System.out.println("4:Delete an single password ");
                        System.out.println("5:Delete passwords by category");
                        choice2 = scanner.nextInt();

                        while (choice2 > 5 || choice2 < 4){

                            System.out.println("Wrong parameter, try agin: ");
                            choice2 = scanner.nextInt();


                        }

                        if (choice2 == 4){
                            printList(passwordList);
                            int deletePassword = scanner.nextInt();
                            passwordList.remove(deletePassword);

                        }

                        else{
                            System.out.println("Choose category: ");
                            scanner.nextLine();
                            String category = scanner.nextLine();

                            passwordList.removeIf(password -> password.getCategory().equals(category));

                        }
                        fileOperations.listToFile(passwordList,fileName,mainPassword);
                        System.out.println("\n\n\n\n");


                    }

                }


                else if (choice==3){
                        System.exit(0);
                }
                else{
                    System.out.println("Wrong choice! ");
                }
            }
        }



    public static void listFilter(List<Password> list, String category) {

        for (Password password : list) {
            if (password.getCategory().equals(category))
                System.out.println(password);


        }

    }


    public static void sortList(List<Password> list, String category){
        if (category.equals("Names")) {
            list.sort(Comparator.comparing(Password::getName));
            for (Password password : list){
                System.out.println(password.getName() + " " + password.getPassword() + " " + password.getCategory());
            }
        }

        else {
            if (category.equals("Category")) {
                list.sort(Comparator.comparing(Password::getCategory));
                for (Password password : list){
                    System.out.println(password.getName() + " " + password.getPassword() + " " + password.getCategory());
                }
            }

        }

    }


    public static void printList(List<Password> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(i + "." + list.get(i) + " ");

        }
    }



}


