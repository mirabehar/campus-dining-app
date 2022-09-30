package edu.vassar.cmpu203.campusdining.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.util.HashMap;
import java.util.Map;

import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.FoodItemCatalogue;
import edu.vassar.cmpu203.campusdining.model.FoodProperties;
import edu.vassar.cmpu203.campusdining.model.Menu;
import edu.vassar.cmpu203.campusdining.model.MenuCatalogueApi;
import edu.vassar.cmpu203.campusdining.model.MenuIdentifier;
import edu.vassar.cmpu203.campusdining.model.ProfileCatalogue;
import edu.vassar.cmpu203.campusdining.persistence.FirestoreFacade;
import edu.vassar.cmpu203.campusdining.persistence.IPersistenceFacade;
import edu.vassar.cmpu203.campusdining.view.ILogInView;
import edu.vassar.cmpu203.campusdining.view.IMenuDisplayView;
import edu.vassar.cmpu203.campusdining.view.IRateItemView;
import edu.vassar.cmpu203.campusdining.view.LogInFragment;
import edu.vassar.cmpu203.campusdining.view.MainPageFragment;
import edu.vassar.cmpu203.campusdining.model.Profile;
import edu.vassar.cmpu203.campusdining.view.IMainPageView;
import edu.vassar.cmpu203.campusdining.view.IMainView;
import edu.vassar.cmpu203.campusdining.view.IProfileDisplayView;
import edu.vassar.cmpu203.campusdining.view.MainView;
import edu.vassar.cmpu203.campusdining.view.MenuDisplayFragment;
import edu.vassar.cmpu203.campusdining.view.ProfileDisplayFragment;
import edu.vassar.cmpu203.campusdining.view.RateItemFragment;


public class ControllerActivity extends AppCompatActivity implements IProfileDisplayView.Listener, IMainPageView.Listener, IMenuDisplayView.Listener, ILogInView.Listener, IPersistenceFacade.ProfileListener, IPersistenceFacade.FoodItemListener, IMenuCatalogueCallback, IRateItemView.Listener {


    private Profile p;
    private IMainView mainView;
    private Menu menu;
    MenuCatalogueApi menCat;
    ProfileCatalogue profCat;
    FoodItemCatalogue itemCat;
    private boolean loggedIn;
    private String date;
    private String curPassword;
    // for retrieving menus
    public static final String DEECE_STRING = "GordonCommons";
    public static final String FOOD_TRUCK_STRING = "StreetEats";
    public static final String EXPRESS_STRING = "Express";
    public static final String RETREAT_STRING = "Retreat";
    // for saving state
    private static final String CUR_MENU = "curMenu";
    private static final String CUR_PROF = "curProf";
    private static final String CUR_MENCAT = "curMenCat";
    private static final String CUR_PROFCAT = "curProfCat";
    private static final String CUR_ITEMCAT = "curItemCat";
    private static final String LOGGED_IN = "loggedIn";
    private static final String CUR_DATE = "curDate";
    private static final String CUR_PASS = "curPassword";
    // for persistence
    private final IPersistenceFacade persistenceFacade = new FirestoreFacade();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentFactory fragmentFactory = new DiningAppFragFactory(this);
        this.getSupportFragmentManager().
                setFragmentFactory(fragmentFactory);

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            this.menu = (Menu) savedInstanceState.getSerializable(CUR_MENU);
            this.p = (Profile) savedInstanceState.getSerializable(CUR_PROF);
            this.menCat = (MenuCatalogueApi) savedInstanceState.getSerializable(CUR_MENCAT);
            this.profCat = (ProfileCatalogue) savedInstanceState.getSerializable(CUR_PROFCAT);
            this.itemCat = (FoodItemCatalogue) savedInstanceState.getSerializable(CUR_ITEMCAT);
            this.loggedIn = savedInstanceState.getBoolean(LOGGED_IN);
            this.date = savedInstanceState.getString(CUR_DATE);
            this.curPassword = savedInstanceState.getString(CUR_PASS);
        } else {
            this.menu = null;
            this.p = new Profile("", "", "");
            this.menCat = new MenuCatalogueApi();
            this.profCat = new ProfileCatalogue();
            this.itemCat = new FoodItemCatalogue();
            this.loggedIn = false;
            this.date = "2001-01-26";
            this.curPassword = "";
        }
        this.persistenceFacade.retrieveProfiles(this);
        this.persistenceFacade.retrieveFoodItems(this);
        this.mainView = new MainView(this);
        setContentView(this.mainView.getRootView());
        if (savedInstanceState == null) {
            this.mainView.displayFragment(new MainPageFragment(this));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CUR_MENU, this.menu);
        outState.putSerializable(CUR_PROF, this.p);
        outState.putSerializable(CUR_MENCAT, this.menCat);
        outState.putSerializable(CUR_PROFCAT, this.profCat);
        outState.putSerializable(CUR_ITEMCAT, this.itemCat);
        outState.putBoolean(LOGGED_IN, this.loggedIn);
        outState.putString(CUR_DATE, this.date);
        outState.putString(CUR_PASS, this.curPassword);
    }

    @Override
    public void onSaveEdits(String name, String username, String password, FoodProperties fp) {
        this.p.setName(name);
        this.p.setUsername(username);
        this.p.makeAuthKey(password);
        this.curPassword = password;
        this.p.setPreferences(fp);
        if (usernameUnavailable(username)) { //i.e. didn't edit username
            this.profCat.profiles.replace(username, p);
            persistenceFacade.saveEditedProfile(p);
        } else {//if username was changed, controller already deleted old one from catalogue in profileDisplay
            this.profCat.profiles.put(username, p);
            persistenceFacade.saveProfile(p);
        }
    }

    @Override
    public void removeOldProfile(String username) {
        this.profCat.profiles.remove(username);
        persistenceFacade.removeProfile(username);
    }

    @Override
    public boolean usernameUnavailable(String username) {
        return this.profCat.profiles.containsKey(username);
    }

    @Override
    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    @Override
    public void onExitProfile() {
        Bundle args = MainPageFragment.makeArgsBundle(this.date);
        Fragment f = new MainPageFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public void updateProfileDetails(IProfileDisplayView profileDisplay) {
        profileDisplay.updateDisplay(
                p.getName(), p.getUsername(), this.curPassword,
                p.getPreferences().getPropVal(0),
                p.getPreferences().getPropVal(1),
                p.getPreferences().getPropVal(2),
                p.getPreferences().getPropVal(3),
                p.getPreferences().getPropVal(4),
                p.getPreferences().getPropVal(5),
                p.getPreferences().getPropVal(6),
                p.getPreferences().getPropVal(7)
        );
    }

    @Override
    public void onDeece() {
        retrieveMenu(DEECE_STRING, this.date);
    }

    @Override
    public void onFoodTruck() {
        retrieveMenu(FOOD_TRUCK_STRING, this.date);
    }

    @Override
    public void onRetreat() {
        retrieveMenu(RETREAT_STRING, this.date);
    }

    @Override
    public void onExpress() {
        retrieveMenu(EXPRESS_STRING, this.date);
    }

    @Override
    public void onLogIn() {
        this.mainView.displayFragment(new LogInFragment(this));
    }

    @Override
    public void onProfile() {
        this.loggedIn = true;
        this.mainView.displayFragment(new ProfileDisplayFragment(this));
    }

    @Override
    public void onLogOut() {
        this.p = new Profile("", "", "");
        this.curPassword = "";
        this.loggedIn = false;
        Bundle args = MainPageFragment.makeArgsBundle(this.date);
        Fragment f = new MainPageFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public void onBackToMain() {
        this.loggedIn = false;
        Bundle args = MainPageFragment.makeArgsBundle(this.date);
        Fragment f = new MainPageFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public void onCreateProfile(String name, String username, String password) {
        this.p.setName(name);
        this.p.setUsername(username);
        this.p.makeAuthKey(password);
        this.curPassword = password;
        this.profCat.profiles.put(username, p);
        this.persistenceFacade.saveProfile(p);
    }

    @Override
    public void onOptions() {
        //didn't get a chance to add any other options
    }

    @Override
    public void onMealLabelSelected(String mealLabel, IMenuDisplayView menuDisplayView) {
        //wanted to do a recycler/card view, but didn't have time
    }


    @Override
    public void onExitMenu() {
        Bundle args = MainPageFragment.makeArgsBundle(this.date);
        Fragment f = new MainPageFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    void retrieveMenu(String location, String date) {
        MenuIdentifier mID = new MenuIdentifier(location, date);
        menCat.getMenu(mID, this);
    }

    @Override
    public void retrieveProfile(String username) {
        this.p = profCat.getProfile(username);
    }

    @Override
    public Profile getProfile() {
        return p;
    }

    public void setProfile(Profile p) {
        this.p = p;
    }

    void filter(Menu m, Profile p) { //takes properties from Menu's items, updates tempMenu based on Profile's preferences
        Menu filteredMenu = new Menu(m.getLocation(), m.getDate());
        for (String label : m.getMealLabelNames()) {
            for (String station : m.getMealLabel(label).getStationNames()) {
                for (FoodItem item : m.getMealLabel(label).getStation(station).getItems()) {
                    if (item.getProperties().compareProperties(p.getPreferences())) {
                        filteredMenu.addItem(item);
                    }
                }
            }
        }
        this.menu = filteredMenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void signalMenuCreated(MenuIdentifier identifier) {
        // retrieve menu from menuCatalogue hashmap
        HashMap<MenuIdentifier, Menu> menus = menCat.getBasicMenus();
        this.menu = menus.get(identifier);
        // update view
        if (menu != null) {
            filter(this.menu, this.p);
        }
        Bundle args = MenuDisplayFragment.makeArgsBundle(this.menu);
        Fragment f = new MenuDisplayFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public FoodItem saveOrRetrieveItem(FoodItem item) {
        FoodItem foundItem = itemCat.getItem(item.getName());
        if (foundItem != null) {
            return foundItem;
        } else {
            this.persistenceFacade.saveFoodItem(item);
            this.itemCat.addItem(item);
            return item;
        }
    }

    @Override
    public void onRateItem(FoodItem foodItem) {
        Bundle args = RateItemFragment.makeArgsBundle(foodItem);
        Fragment f = new RateItemFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public void onProfilesReceived(Map<String, Profile> profs) {
        this.profCat.setProfiles(profs);
    }

    @Override
    public void onFoodItemsReceived(Map<String, FoodItem> items) {
        this.itemCat.setItems(items);
    }

    @Override
    public void onBackToMenu() {
        Bundle args = MenuDisplayFragment.makeArgsBundle(this.menu);
        Fragment f = new MenuDisplayFragment(this);
        f.setArguments(args);
        this.mainView.displayFragment(f);
    }

    @Override
    public void onSaveRating(FoodItem foodItem, int rating) {
        foodItem.addRating(rating);
        this.persistenceFacade.updateFoodItemRating(foodItem);
    }

    @Override
    public void setCurPassword(String logInPass) {
        this.curPassword = logInPass;
    }
}