package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;
import java.util.*;

public class Menu implements Serializable {

    String location; //same as cafe in database
    String date; //format: yyyy-mm-dd
    private Map<String, MealLabel> mealLabels;
    private List<String> mealLabelNames;

    public Menu() {
    }

    public Menu(MenuIdentifier mID) {
        this.location = mID.getLocation();
        this.date = mID.getDate();
        this.mealLabels = new HashMap<>();
        this.mealLabelNames = new LinkedList<>();
    }

    public Menu(String location, String date) {
        this.location = location;
        this.date = date;
        this.mealLabels = new HashMap<>();
        this.mealLabelNames = new LinkedList<>();
    }

    public Menu(String location, String date, Map<String, MealLabel> mealLabels, List<String> mealLabelNames) {
        this.location = location;
        this.date = date;
        this.mealLabels = mealLabels;
        this.mealLabelNames = mealLabelNames;
    }

    public String getDate() {
        return this.date;
    }

    public String getLocation() {
        return this.location;
    }

    public Map<String, MealLabel> getMealLabels() {
        return this.mealLabels;
    }

    public List<String> getMealLabelNames() {
        return this.mealLabelNames;
    }

    public void addMealLabel(MealLabel mealLabel) {
        this.mealLabels.put(mealLabel.getLabel(), mealLabel);
        this.mealLabelNames.add(mealLabel.getLabel());
    }

    public MealLabel getMealLabel(String label) {
        return this.mealLabels.get(label);
    }

    public void addItem(FoodItem item) {
        MealLabel curLabel;
        Station curStation;
        if (this.mealLabelNames.contains(item.getMealLabel())) {
            curLabel = this.getMealLabel(item.getMealLabel());
            if (curLabel.getStationNames().contains(item.getStation())) {
                curStation = curLabel.getStation(item.getStation());
                curStation.addItem(item);
            } else {
                curStation = new Station(item.getStation());
                curStation.addItem(item);
                curLabel.addStation(curStation);
            }
        } else {
            curLabel = new MealLabel(item.getMealLabel());
            curStation = new Station(item.getStation());
            curStation.addItem(item);
            curLabel.addStation(curStation);
            this.addMealLabel(curLabel);
        }
    }

    public boolean equals(Object o) {
        Menu m = (Menu) o;
        return this.location.equals(m.location) && this.date.equals(m.date) && this.mealLabels.equals(m.mealLabels);
    }

    public MenuIdentifier toID() {
        return new MenuIdentifier(location, date);
    }
}