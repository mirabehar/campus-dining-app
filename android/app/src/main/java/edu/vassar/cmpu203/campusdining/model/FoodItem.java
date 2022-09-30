package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;

public class FoodItem implements Serializable {

    private String name;
    private String station;
    private String mealLabel; //meal_label in database
    private FoodProperties properties;
    int sumRatings;
    int numRatings;
    public static final String SUM_RATINGS = "sumRatings";
    public static final String NUM_RATINGS = "numRatings";

    public FoodItem() {
    }

    public FoodItem(String name, String station, String mealLabel, FoodProperties properties) {
        this.name = name;
        this.station = station;
        this.mealLabel = mealLabel;
        this.properties = properties;
        this.sumRatings = 0;
        this.numRatings = 0;
    }

    public String getName() {
        return this.name;
    }

    public FoodProperties getProperties() {
        return this.properties;
    }

    public String toString() {
        return name + "    " + properties + "\n";
    }

    public String getMealLabel() {
        return this.mealLabel;
    }

    public String getStation() {
        return this.station;
    }

    public int getSumRatings() {
        return this.sumRatings;
    }

    public int getNumRatings() {
        return this.numRatings;
    }

    public void addRating(int rating) {
        this.sumRatings = this.sumRatings + rating;
        this.numRatings += 1;
    }

    public float computeRating() {
        if (numRatings == 0) {
            return 0;
        } else {
            double rating = (double) this.sumRatings / this.numRatings;
            rating = Math.round(rating * 10.0) / 10.0;
            return (float) rating;
        }
    }

    public boolean equals(Object o) {
        FoodItem fdIt = (FoodItem) o;
        return this.name.equals(fdIt.name) && this.station.equals(fdIt.station) && this.mealLabel.equals(fdIt.mealLabel) && this.properties.equals(fdIt.properties);
    }

}