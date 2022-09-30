package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;
import java.util.*;
public class ProfileCatalogue implements Serializable {
    public Map<String, Profile> profiles; //key is username string



    public ProfileCatalogue(){
        this.profiles = new HashMap<>();
        //profiles is populated with database contents
    }

    public Profile getProfile(String username){
        return profiles.get(username);
    }

    public void setProfiles(Map<String, Profile> profs){
        this.profiles = profs;
    }
}