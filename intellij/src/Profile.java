package edu.vassar.cmpu203.campusdining.model.intellij.src;

class Profile {
    String name;
    String username;
    String password;
    FoodProperties preferences;

    Profile(String name, String username, String password, FoodProperties preferences){
        this.name = name;
        this.username = username;
        this.password = password;
        this.preferences = preferences;
    }
}