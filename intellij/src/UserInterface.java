package edu.vassar.cmpu203.campusdining.model.intellij.src;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;
class UserInterface { //will be replaced by android

    void createProfile(){ //not used in this iteration, will be implemented when we address Make Profile use case
    }

    String askUsername(){ // this would be where we add the option for null profile/general menu
        System.out.println("Please enter your username:");
        Scanner in = new Scanner(System.in);
        // returns username
        return in.next(); //need to add in error catching?
    }

    String askPassword(){ //not for this iteration, used for authentication
        return null;
    }

    String askUserDate(){ //ask user for date in particular format
        LocalDate currentDate = java.time.LocalDate.now();
        Scanner in = new Scanner(System.in);
        //checks for yyyy-mm-dd format
        LocalDate inDate = null;
        LocalDate startRange;
        LocalDate endRange;
        String inputDate = "";
        while(inDate == null) {
            System.out.println("Please enter the date for which you would like to see the menu (yyyy-mm-dd): \n (For now please enter 2021-10-15)");
            inputDate = in.next();
            try {
                inDate = LocalDate.parse(inputDate);
                //makes range of valid dates (one day before to 6 days after currentDate)
                startRange = currentDate.plusDays(-1);
                endRange = currentDate.plusDays(6);
                if(inDate.isBefore(startRange)||inDate.isAfter(endRange)) {
                    System.out.println("Error: Date out of range");
                    inDate = null;
                }
            } catch (DateTimeParseException d) {
                System.out.println("Error: Date must be in yyyy-mm-dd format");
            }
        }
        return inputDate;
    }

    String askUserLocation(){ //ask user for location
        Scanner in = new Scanner(System.in);
        int loc = 0;
        String ret = "";
        while(loc == 0){
            System.out.println("Please indicate the location for which you would like to see the menu \n (Enter an integer 1-4: 1=Gordon Commons; 2=Retreat; 3=Express; 4=Street Eats): \n (For now please select 1)");
            try {
                loc = in.nextInt();
                if(loc == 1){
                    ret = "GordonCommons";
                }else if(loc == 2){
                    ret = "Retreat";
                }else if(loc == 3){
                    ret = "Express";
                }else if(loc == 4){
                    ret = "StreetEats";
                }else{
                    loc = 0;
                }
            }catch(NumberFormatException e) { //means input contains non-int characters
                System.out.println("Error: input must be an integer between 1 and 4");
            }
        }
        return ret;
    }

    public static void main(String[] args){
        UserInterface UI = new UserInterface();
        // Scanner s = new Scanner(System.in); I think we don't need this scanner since we make a new one in each method call
        //welcome user
        System.out.println("Welcome to the Vassar Menu App!"); // welcome message
        MenuController menCon = new MenuController(); //creates menu controller, with men and prof catalogues
        String username = UI.askUsername();// ask for username
        menCon.initializeProfile(username);//menu controller initializes profile
        //above would be the step in which we authenticate eventually
        String date = UI.askUserDate(); // ask for date
        String location = UI.askUserLocation(); // ask for location
        menCon.initializeMenu(location, date);//menu controller initializes menu
        menCon.filter(menCon.getMenu(), menCon.getProf());//menu controller filters menu by profile
        //menu controller gives menu to UI? for now just prints out menu on screen
        System.out.println(menCon.getMenu().toString());
    }
}