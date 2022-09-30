package edu.vassar.cmpu203.campusdining.model;

public class MenuIdentifier {
    public String location;
    public String date;

    public MenuIdentifier(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MenuIdentifier)) {
            return false;
        }
        MenuIdentifier Id = (MenuIdentifier) o;
        return Id.location.equals(this.location) && Id.date.equals(this.date);
    }

    @Override
    public int hashCode() {
        return (location + date).hashCode();
    }
}