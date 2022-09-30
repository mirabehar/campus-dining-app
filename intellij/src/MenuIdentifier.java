package edu.vassar.cmpu203.campusdining.model.intellij.src;

class MenuIdentifier {
    String location;
    String date;

    MenuIdentifier(String location, String date){
        this.location = location;
        this.date = date;
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }
}