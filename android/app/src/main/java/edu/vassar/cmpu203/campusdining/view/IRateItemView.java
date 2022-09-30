package edu.vassar.cmpu203.campusdining.view;

import edu.vassar.cmpu203.campusdining.model.FoodItem;

public interface IRateItemView {
    interface Listener{
        void onBackToMenu();
        void onSaveRating(FoodItem foodItem, int rating);
    }
}
