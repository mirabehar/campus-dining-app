package edu.vassar.cmpu203.campusdining.model;

import android.util.Log;

import java.io.Serializable;
import java.util.*;

import edu.vassar.cmpu203.campusdining.controller.IMenuCatalogueCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MenuCatalogueApi implements IMenuCatalogue, Serializable {

    HashMap<MenuIdentifier, Menu> basicMenus;
    static final String BASE_URL = "https://dazzling-grand-teton-21768.herokuapp.com/";
    ApiInterface apiService;
    Retrofit retrofit;

    public MenuCatalogueApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiInterface.class);
        basicMenus = new HashMap<MenuIdentifier, Menu>();
    }

    @Override
    public Menu getMenu(MenuIdentifier id) {
        return null;
    }

    @Override
    public void getMenu(MenuIdentifier identifier, IMenuCatalogueCallback callback) {

        String date = identifier.getDate();
        String cafe = identifier.getLocation();

        // check if menu is in basicMenus
        if (basicMenus.containsKey(identifier)) {
            callback.signalMenuCreated(identifier);
        } else {
            Call<ArrayList<MenuAccess>> call = apiService.getInfo(date, cafe);
            call.enqueue(new Callback<ArrayList<MenuAccess>>() {
                @Override
                public void onResponse(Call<ArrayList<MenuAccess>> call, Response<ArrayList<MenuAccess>> response) {
                    int statusCode = response.code();
                    ArrayList<MenuAccess> menuContents = response.body();
                    MenuItem menuItem;
                    FoodProperties properties;
                    FoodItem foodItem;
                    // menu access to menu to hash map code here
                    List<FoodItem> itemsList = new LinkedList<>();
                    Menu m = new Menu(identifier);
                    for (int i = 0; i < menuContents.size(); i++) {
                        menuItem = menuContents.get(i).getMenuItem();
                        // convert MenuItem to FoodItem object
                        properties = new FoodProperties(menuItem.isVegetarian(), menuItem.isVegan(),
                                menuItem.isGlutenFree(), menuItem.isHalal(), menuItem.isKosher(),
                                menuItem.isHumane(), menuItem.isSeafood(), menuItem.isFarmToFork());
                        foodItem = new FoodItem(menuItem.getName(),
                                menuContents.get(i).getStation(), menuContents.get(i).getMealLabel(),
                                properties);
                        //gets item from (local) database, or saves and returns it if not found
                        foodItem = callback.saveOrRetrieveItem(foodItem);
                        //adds returned item to menu
                        m.addItem(foodItem);
                    }
                    //puts the finished menu in the (local, temporary) database of menus
                    if(m.getMealLabelNames().size() != 0) {
                        putMenu(m);
                    }
                    //tells the controller that the menu has been created and it should filter and display it
                    //Note: menu created is null if there was no menu for that day on the API
                    callback.signalMenuCreated(identifier);
                }

                @Override
                public void onFailure(Call<ArrayList<MenuAccess>> call, Throwable t) {
                    Log.i("DiningApp", "failed to retrieve menu from dining API");
                }
            });
        }
    }

    @Override
    public HashMap<MenuIdentifier, Menu> getBasicMenus() {
        return basicMenus;
    }

    public void putMenu(Menu m) {
        basicMenus.put(m.toID(), m);
    }
}