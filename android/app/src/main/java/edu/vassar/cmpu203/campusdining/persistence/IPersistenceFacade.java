package edu.vassar.cmpu203.campusdining.persistence;

import java.util.Map;

import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.Profile;

public interface IPersistenceFacade {
    interface ProfileListener{
        void onProfilesReceived(Map<String, Profile> profiles);
    }
    interface FoodItemListener{
        void onFoodItemsReceived(Map<String, FoodItem> items);
    }
    void saveProfile(Profile profile);
    void saveEditedProfile(Profile profile);
    void removeProfile(String username);
    void retrieveProfiles(ProfileListener listener);
    void saveFoodItem(FoodItem foodItem);
    void retrieveFoodItems(FoodItemListener listener);
    void updateFoodItemRating(FoodItem foodItem);
}
