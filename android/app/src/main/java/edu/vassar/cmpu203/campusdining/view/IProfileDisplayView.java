package edu.vassar.cmpu203.campusdining.view;

import edu.vassar.cmpu203.campusdining.model.FoodProperties;

public interface IProfileDisplayView {
    interface Listener {

        void onSaveEdits(String name, String username, String password, FoodProperties fp);

        void onExitProfile(); // when the user exits the profile to return to the main screen

        void updateProfileDetails(IProfileDisplayView profileDisplay);

        void onLogOut();

        boolean usernameUnavailable(String username);

        void removeOldProfile(String username);
    }

    void displayEditMode();

    void displayShownPass();

    void updateDisplay(String name, String username, String password, boolean vegetarian, boolean vegan, boolean glutenFree, boolean halal, boolean kosher, boolean humane, boolean seafood, boolean farmToFork);
}
