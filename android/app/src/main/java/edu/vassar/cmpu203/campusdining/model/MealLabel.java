package edu.vassar.cmpu203.campusdining.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MealLabel implements Serializable {
    private String label;
    private Map<String, Station> stations;
    private List<String> stationNames;

    public MealLabel(){}

    public MealLabel(String label){
        this.label = label;
        this.stations = new HashMap<>();
        this.stationNames = new LinkedList<>();
    }

    public MealLabel(String label, Map<String, Station> stations, List<String> stationNames){
        this.label = label;
        this.stations = stations;
        this.stationNames = stationNames;
    }

    public void addStation(Station station){
        this.stations.put(station.getStation(), station);
        this.stationNames.add(station.getStation());
    }

    public Map<String, Station> getStations(){
        return this.stations;
    }

    public Station getStation(String station){
        return this.stations.get(station);
    }

    public List<String> getStationNames(){
        return this.stationNames;
    }

    public String getLabel(){
        return this.label;
    }

    public boolean equals(Object o) {
        MealLabel mealLabel = (MealLabel) o;
        return this.label.equals(mealLabel.label) && this.stations.equals(mealLabel.stations) && this.stationNames.equals(mealLabel.stationNames);
    }
}
