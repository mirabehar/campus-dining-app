package edu.vassar.cmpu203.campusdining.model.intellij.src;

class FoodProperties { //default is all false

    boolean vegetarian;
    boolean vegan;
    boolean glutenFree;
    boolean halal;
    boolean kosher;
    boolean humane;
    boolean seafood;
    boolean farmToFork;

    FoodProperties(boolean vegetarian, boolean vegan, boolean glutenFree, boolean halal, boolean kosher, boolean humane,
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

    //edits the value of a Properties in order to set Profile preferences
    void changePropVal(int i){ // takes in integer that indicates which preference they want to change
        //maybe use an enum somehow?
        switch (i) {
            case 0: this.vegetarian = !this.vegetarian;
            break;
            case 1: this.vegan = !this.vegan;
            break;
            case 2: this.glutenFree = !this.glutenFree;
            break;
            case 3: this.halal = !this.halal;
            break;
            case 4: this.kosher = !this.kosher;
            break;
            case 5: this.humane = !this.humane;
            break;
            case 6: this.seafood = !this.seafood;
            break;
            case 7: this.farmToFork = !this.farmToFork;
            break;
        }
    }

    // even though compiler says we can simplify if statements, we can b/c we are using a one directional conditional
    public boolean compareProperties(FoodProperties p) { //checks if p.whatever is false when this.whatever is true, meaning preferences don't match
        if (p.vegetarian && !this.vegetarian) {// need to have all these cases b/c conditional is only one directional (i.e. true & false bad, true & true fine, false & false fine, false & true fine)
                return false;
        }
        else if (p.vegan && !this.vegan) {
            return false;
        }
        else if(p.glutenFree && !this.glutenFree){
                return false;
        }
        else if(p.halal && !this.halal){
                return false;
        }
        else if(p.kosher && !this.kosher){
                return false;
        }
        else if(p.humane && !this.humane){
                return false;
        }
        else if(p.seafood && !this.seafood){
                return false;
        }
        else if(p.farmToFork && !this.farmToFork){
                return false;
        }
        else{
            return true; // only returns true if all previous conditions have been met
        }
    }

    public String toString(){
        String res = ""; //String to hold result
        if(vegetarian){
        res += "Vegetarian, ";
        }
        if(vegan){
        res += "Vegan, ";
        }
        if(glutenFree){
        res += "Gluten Free, ";
        }
        if(halal){
        res += "Halal, ";
        }
        if(kosher){
        res += "Kosher, ";
        }
        if(humane){
        res += "Humane, ";
        }
        if(seafood){
        res += "Seafood, ";
        }
        if(farmToFork){
        res += "Farm To Fork, ";
        }

        if(res == ""){//if there aren't any food properties, return an empty string
            return "";
        }
        else {//else change the last punctuation mark to a fullstop
            res = res.substring(0, res.length() - 2);
            res += ".";
        }
        return res;
    }
}


