package edu.vassar.cmpu203.campusdining.model;

public class FoodProperties { //default is all false

    boolean vegetarian;
    boolean vegan;
    boolean glutenFree;
    boolean halal;
    boolean kosher;
    boolean humane;
    boolean seafood;
    boolean farmToFork;
    static String KEY = "V         Vegetarian\n" + "VG      Vegan\n" + "GF      Gluten Free\n" + "Ha      Halal\n" + "K        Kosher\n" + "H        Humane\n" + "SF      Seafood\n" + "FTF    FarmToFork\n";

    public FoodProperties(){}

    public FoodProperties(boolean vegetarian, boolean vegan, boolean glutenFree, boolean halal, boolean kosher, boolean humane,
                          boolean seafood, boolean farmToFork) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.halal = halal;
        this.kosher = kosher;
        this.humane = humane;
        this.seafood = seafood;
        this.farmToFork = farmToFork;

    }

    public boolean getPropVal(int i) {
        switch (i) {
            case 0:
                return this.vegetarian;
            case 1:
                return this.vegan;
            case 2:
                return this.glutenFree;
            case 3:
                return this.halal;
            case 4:
                return this.kosher;
            case 5:
                return this.humane;
            case 6:
                return this.seafood;
            case 7:
                return this.farmToFork;
        }
        return false;
    }

    //edits the value of a Properties in order to set Profile preferences
    public void changePropVal(int i) { // takes in integer that indicates which preference they want to change
        switch (i) {
            case 0:
                this.vegetarian = !this.vegetarian;
                break;
            case 1:
                this.vegan = !this.vegan;
                break;
            case 2:
                this.glutenFree = !this.glutenFree;
                break;
            case 3:
                this.halal = !this.halal;
                break;
            case 4:
                this.kosher = !this.kosher;
                break;
            case 5:
                this.humane = !this.humane;
                break;
            case 6:
                this.seafood = !this.seafood;
                break;
            case 7:
                this.farmToFork = !this.farmToFork;
                break;
        }
    }

    /*
    THIS object represents foodItem properties and P object represents profile's preferences
     */
    public boolean compareProperties(FoodProperties p) { //if preference is true, then property of food must also be true, but if preference is false, we don't care about property of food
        if (!this.vegetarian && p.vegetarian) {
            return false;
        } else if (!this.vegan && p.vegan) {
            return false;
        } else if (!this.glutenFree && p.glutenFree) {
            return false;
        } else if (!this.halal && p.halal) {
            return false;
        } else if (!this.kosher && p.kosher) {
            return false;
        } else if (!this.humane && p.humane) {
            return false;
        } else if (!this.seafood && p.seafood) {
            return false;
        } else if (!this.farmToFork && p.farmToFork) {
            return false;
        } else {
            return true; // only returns true if all previous conditions have been met
        }
    }

    public String toString() {
        String res = "";
        if (vegetarian) {
            res += "V, ";
        }
        if (vegan) {
            res += "VG, ";
        }
        if (glutenFree) {
            res += "GF, ";
        }
        if (halal) {
            res += "Ha, ";
        }
        if (kosher) {
            res += "K, ";
        }
        if (humane) {
            res += "H, ";
        }
        if (seafood) {
            res += "SF, ";
        }
        if (farmToFork) {
            res += "FTF, ";
        }

        if (res.equals("")) {
            return "";
        } else {
            res = res.substring(0, res.length() - 2);
            res += ".";
        }
        return res;
    }

    public static String getKey() {
        return KEY;
    }

    public boolean equals(Object o) {
        FoodProperties fp = (FoodProperties) o;
        return this.vegetarian == fp.vegetarian && this.vegan == fp.vegan && this.glutenFree == fp.glutenFree && this.halal == fp.halal && this.kosher == fp.kosher && this.humane == fp.humane && this.seafood == fp.seafood && this.farmToFork == fp.farmToFork;
    }

    public boolean getVegetarian(){
        return this.vegetarian;
    }
    public boolean getVegan(){
        return this.vegan;
    }
    public boolean getGlutenFree(){
        return this.glutenFree;
    }
    public boolean getHalal(){
        return this.halal;
    }
    public boolean getKosher(){
        return this.kosher;
    }
    public boolean getHumane(){
        return this.humane;
    }
    public boolean getSeafood(){
        return this.seafood;
    }
    public boolean getFarmToFork(){
        return this.farmToFork;
    }
}


