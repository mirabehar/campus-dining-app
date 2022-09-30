package edu.vassar.cmpu203.campusdining.view;

import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.Menu;

public interface IMenuDisplayView {
    interface Listener{

        // when meal label/time is chosen for the given menu
        void onMealLabelSelected(String mealLabel, IMenuDisplayView menuDisplay);

        // when the user exits the menu to return to the main screen
        void onExitMenu();

        Menu getMenu();
        void onRateItem(FoodItem foodItem);
    }

    public void updateDisplay(String mealLabel);
}
