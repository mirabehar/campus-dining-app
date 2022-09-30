package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;
import java.util.Map;

public class FoodItemCatalogue implements Serializable {
    public Map<String, FoodItem> items;

    public FoodItemCatalogue(){
    }

    public FoodItem getItem(String name){
        return this.items.get(name);
    }

    public void addItem(FoodItem item){
        this.items.put(item.getName(), item);
    }

    public void setItems(Map<String, FoodItem> items){
        this.items = items;
    }
}
