package edu.vassar.cmpu203.campusdining.model.intellij.src;

import java.util.*;

public class MenuCatalogue {

    HashMap<MenuIdentifier, Menu> basicMenus; //<hashkey, itemstored> //will use this in later iteration

    public MenuCatalogue(){
        //convert json files into objs using library, for now create sample menu manually

    }

    Menu getMenu(MenuIdentifier identifier){ //hardcoded temporarily, in the future pull from json files
        FoodProperties p1 = new FoodProperties(true, true, true, true, true, false, false, false);
        FoodItem f1 = new FoodItem(1, "Tofu", "ROOT", "Lunch", p1);
        FoodProperties p2 = new FoodProperties(false, false, false, false, false, true, false, true);
        FoodItem f2 = new FoodItem(1, "Burger", "GRILL", "Dinner", p2);
        FoodProperties p3 = new FoodProperties(true, true, true, true, true, false, false, false);
        FoodItem f3 = new FoodItem(1, "Fruit", "GRILL", "Dinner", p3);
        LinkedList<FoodItem> testItemsList = new LinkedList<>();
        testItemsList.add(f1);
        testItemsList.add(f2);
        testItemsList.add(f3);
        return new Menu("Deece", "2021-10-15", testItemsList);
    }

    void updateMenus(){//will deal with this once we have the actual menus
    }
}