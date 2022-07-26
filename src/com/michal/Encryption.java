package com.michal;

public class Encryption {

    public String encryption(String str) {
        char[] chars = str.toCharArray();
        String encrypted = "";
        for (char c : chars){
            c+=1;
            encrypted+=c;
        }
        return encrypted;
    }


    public String dencryption(String str) {
        char[] chars = str.toCharArray();
        String dencrypted = "";
        for (char c : chars){
            c-=1;
            dencrypted+=c;
        }
        return dencrypted;
    }
}



