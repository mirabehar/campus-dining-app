package edu.vassar.cmpu203.campusdining.model.intellij.src;

import java.util.*;
class Menu {

    String location; //same as cafe in database
    String date; //format: yyyy-mm-dd
    LinkedList<FoodItem> menuItems;

    Menu(String location, String date, LinkedList<FoodItem> menuItems){
        this.location = location;
        this.date = date;
        this.menuItems = menuItems;
    }

    public String toString(){
        String menuToDisplay = "" + location + "\n" + date + "\n"; // writes out location and date of menu
        String label = null;
        String stat = null;
        for(FoodItem foodIt : menuItems){
            menuToDisplay = foodIt.toStringMealLabel(label, stat, menuToDisplay); //calls helper method to determine whether to display mealLabel and Station
            label = foodIt.mealLabel;
            stat = foodIt.station;
        }
        return menuToDisplay;
    }
}