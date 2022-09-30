package edu.vassar.cmpu203.campusdining.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class MenuAccess {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cafe")
    @Expose
    private String cafe;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("meal_label")
    @Expose
    private String mealLabel;
    @SerializedName("menu_item")
    @Expose
    private MenuItem menuItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getMealLabel() {
        return mealLabel;
    }

    public void setMealLabel(String mealLabel) {
        this.mealLabel = mealLabel;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String toString(){
        String s = "Date: " + date + "\nCafe: " + cafe + "\nMenuItem: " + menuItem + "\n";
        return s;

    }
}
