package edu.vassar.cmpu203.campusdining.model.intellij.src;

import java.util.*;
public class ProfileCatalogue {
    HashMap<String, Profile> profiles; //key is username string, not used in this iteration.

    public ProfileCatalogue(){
    }

    Profile getProfile(String username){
        // will eventually use the hashtable to get the profile, for now hardcode profile
        FoodProperties fp = new FoodProperties(false, false, false, false, false, false, false, false);
        return new Profile("Name", username, "Password", fp);
    }
}