package edu.vassar.cmpu203.campusdining.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/menu-items")
    Call<ArrayList<MenuAccess>> getInfo(@Query("date") String date, @Query("cafe") String cafe);

}
