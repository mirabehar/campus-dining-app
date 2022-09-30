package edu.vassar.cmpu203.campusdining.model.intellij.src;

import java.util.*;
class MenuController { //mediates interaction between UI and domain, incl. updating profile fields

    MenuCatalogue menCat;
    ProfileCatalogue profCat;
    Profile prof;
    Menu menu;

    MenuController(){// eventually want to access these from elsewhere so we only have one of each
        this.menCat = new MenuCatalogue();
        this.profCat = new ProfileCatalogue();
    }

    //takes Menu from MenuCatalogue using MenuIdentifier
    void initializeMenu(String location, String date){ //temporary- hardcode menu
        MenuIdentifier mID = new MenuIdentifier(location, date);
        this.menu = menCat.getMenu(mID);
    }

    Menu getMenu(){
        return menu;
    }

    void initializeProfile(String username){
        this.prof = profCat.getProfile(username);
    }

    Profile getProf(){
        return prof;
    }

    void filter(Menu m, Profile p){ //takes properties from Menu's items, updates tempMenu based on Profile's preferences
        Menu filteredMenu = new Menu(m.location, m.date, new LinkedList<FoodItem>());
        for(FoodItem item : m.menuItems){
            if(item.properties.compareProperties(p.preferences)){
                filteredMenu.menuItems.add(item);
            }
        }
        this.menu = filteredMenu;
    }

    //getting and verifying the username and password should be done in UI? or in another method in controller?

    // given name username and password (and optionally preferences), creates a profile
    Profile makeProfile(String name, String username, String password, FoodProperties prefs){
        Profile p;
        if(prefs != null){
            p = new Profile(name, username, password, prefs);
        }
        else {
            p = new Profile(name, username, password, new FoodProperties(false, false, false, false, false, false, false, false));
        }
        return p;
        // ultimately then want to save this profile to the database and log user in
    }

    void setPreference(Profile p, int i){ //one preference at a time, adds or removes
        p.preferences.changePropVal(i); // i will come from enum? maybe? it should indicate which preference the user wants to set
    }

//will implement below in future iteration, when we address the make/edit profile use case
    void changeUsername(String newUsername, Profile p){ // not needed for this iteration
        p.username = newUsername;
    }

    void changePassword(String newPassword, Profile p){ // not needed for this iteration
        p.password = newPassword;
    }
}