package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Station implements Serializable {
    private String station;
    private List<FoodItem> items;

    public Station(){}

    public Station(String station){
        this.station = station;
        this.items = new LinkedList<>();
    }

    public Station(String station, List<FoodItem> items){
        this.station = station;
        this.items = items;
    }

    public void addItem(FoodItem item){
        this.items.add(item);
    }

    public List<FoodItem> getItems(){
        return this.items;
    }

    public String getStation(){
        return this.station;
    }

    public boolean equals(Object o) {
        Station bigStation = (Station) o;
        return this.station.equals(bigStation.station) && this.items.equals(bigStation.items);
    }
}
