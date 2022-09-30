package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;

public class Profile implements Serializable {
    String name;
    String username;
    AuthKey authKey;
    FoodProperties preferences;
    //Strings for saving edits to database
    public static final String NAME = "name";
    public static final String UNAME = "username";
    public static final String AKEY = "authKey";
    public static final String PREFS = "preferences";

    public Profile(){}

    public Profile(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.authKey = new AuthKey(password);
        this.preferences = new FoodProperties(false, false, false, false, false, false, false, false);
    }

    public String getName(){
        return this.name;
    }
    public void setName(String nm){
        this.name = nm;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String un){
        this.username = un;
    }

    public AuthKey getAuthKey(){
        return this.authKey;
    }
    public void makeAuthKey(String pw){
        this.authKey = new AuthKey(pw);
    }

    public boolean validatePassword(String password){
        return this.authKey.validatePassword(password);
    }

    public FoodProperties getPreferences(){
        return this.preferences;
    }
    public void setPreferences(FoodProperties pref){
        this.preferences = pref;
    }
}