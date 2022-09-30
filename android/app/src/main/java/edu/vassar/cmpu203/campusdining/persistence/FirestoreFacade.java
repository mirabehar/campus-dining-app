package edu.vassar.cmpu203.campusdining.persistence;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.Profile;

public class FirestoreFacade implements IPersistenceFacade {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String PROFILES = "profiles";
    private static final String FOOD_ITEMS = "foodItems";

    @Override
    public void saveProfile(Profile profile) {
        String uname = profile.getUsername();
        this.db.collection(PROFILES).document(uname).set(profile);
    }

    @Override
    public void saveEditedProfile(Profile profile) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(Profile.NAME, profile.getName());
        userMap.put(Profile.AKEY, profile.getAuthKey());
        userMap.put(Profile.UNAME, profile.getUsername());
        userMap.put(Profile.PREFS, profile.getPreferences());
        db.collection(PROFILES).document(profile.getUsername()).set(userMap, SetOptions.merge());
    }

    @Override
    public void removeProfile(String username) {
        this.db.collection(PROFILES).document(username).delete();
    }

    @Override
    public void retrieveProfiles(ProfileListener listener) {
        this.db.collection(PROFILES)
                .get()
                .addOnSuccessListener(qSnap -> {
                    Map<String, Profile> profs = new HashMap<>();
                    for (DocumentSnapshot dSnap : qSnap) {
                        Profile profile = dSnap.toObject(Profile.class);
                        profs.put(profile.getUsername(), profile);
                    }
                    listener.onProfilesReceived(profs);
                });
    }

    @Override
    public void saveFoodItem(FoodItem foodItem) {
        String name = foodItem.getName();
        //this line is to avoid errors with creating path name for document
        name = name.replace("1/2", "half");
        this.db.collection(FOOD_ITEMS).document(name).set(foodItem);
    }

    @Override
    public void retrieveFoodItems(FoodItemListener listener) {
        this.db.collection(FOOD_ITEMS)
                .get()
                .addOnSuccessListener(qSnap -> {
                    Map<String, FoodItem> items = new HashMap<>();
                    for (DocumentSnapshot dSnap : qSnap) {
                        FoodItem foodItem = dSnap.toObject(FoodItem.class);
                        items.put(foodItem.getName(), foodItem);
                    }
                    listener.onFoodItemsReceived(items);
                });
    }

    @Override
    public void updateFoodItemRating(FoodItem foodItem) {
        int sum = foodItem.getSumRatings();
        int num = foodItem.getNumRatings();
        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(FoodItem.SUM_RATINGS, sum);
        rateMap.put(FoodItem.NUM_RATINGS, num);
        this.db.collection(FOOD_ITEMS).document(foodItem.getName()).set(rateMap, SetOptions.merge());
    }
}
