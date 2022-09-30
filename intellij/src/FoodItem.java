package edu.vassar.cmpu203.campusdining.model.intellij.src;

class FoodItem {

    int id;
    String name;
    String station;
    String mealLabel; //meal_label in database
    FoodProperties properties;

    FoodItem(int id, String name, String station, String mealLabel, FoodProperties properties){
        this.id = id;
        this.name = name;
        this.station = station;
        this.mealLabel = mealLabel;
        this.properties = properties;
    }


    public String toString(){
        return name + "\nProperties: " + properties;
    }

    public String toStringStation(String stat, String s){ //helper method for toStringMealLabel
        if(!station.equals(stat)){ // if not at the correct station
            stat = station; // sets stat to the correct station
            s += "------\n" + station + "\n------\n"; // displays correct station
        }
        s += toString() + "\n"; // adds food item
        return s;
    }

    public String toStringMealLabel(String label, String stat, String s){ // helper method for Menu: toString()
        if(!mealLabel.equals(label)){ //if not at the correct meal
            label = mealLabel; // sets label to the correct meal
            s += "============\n" + mealLabel + "\n"; // displays correct mealLabel
        }
        return toStringStation(stat, s); //calls helper for station determination
    }
}