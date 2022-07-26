package com.michal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FileOperations {
    Encryption encryption = new Encryption();


    public void putPassword(int amount, String filePath) throws IOException {


        Scanner scanner = new Scanner(System.in);
        String name;
        String password;
        String category;
        FileWriter fileWriter = new FileWriter(filePath,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i <amount ; i++) {
            System.out.println("Set name: ");
            name = scanner.nextLine();
            System.out.println("Set password: ");
            password = scanner.nextLine();
            System.out.println("Set category: ");
            category = scanner.nextLine();

            bufferedWriter.write(encryption.encryption(name) + " " + encryption.encryption(password)
                    + " " + encryption.encryption(category) + "\n");

        }
        bufferedWriter.close();
    }

    public int sumLines(String filePaht) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePaht));
        String lines = reader.readLine();
        int currentSum = 0;
        while(lines != null){

            currentSum++;
            lines = reader.readLine();

        }
        return currentSum;
    }

    public List<Password> toArrayList(String filePath) throws IOException {
        List<Password> passwordList = new ArrayList();
        IntStream
                .range(1,sumLines(filePath))
                .forEach(x -> {
                    try {
                        String line = Files.readAllLines(Paths.get(filePath)).get(x);
                        String[] values = line.split(" ");


                            Password password = new Password(encryption.dencryption(values[0]),
                                    encryption.dencryption(values[1]),
                                    encryption.dencryption(values[2]));
                            passwordList.add(password);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return passwordList;
    }

    public  void listToFile(List<Password> list, String fileName, String mainPassword) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(mainPassword + System.lineSeparator());
        for (Password password: list){
            fileWriter.write(encryption.encryption(password.getName())
                    + " " + encryption.encryption(password.getPassword())
                    + " " + encryption.encryption(password.getCategory())
                    + " " + System.lineSeparator());

        }
        fileWriter.close();


    }


}
