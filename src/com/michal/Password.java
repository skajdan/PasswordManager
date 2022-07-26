package com.michal;


public class Password{
    private final String name;
    private final String password;
    private final String category;


    public Password(String name, String password, String category) {
        this.name = name;
        this.password = password;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                '}';
    }




}
