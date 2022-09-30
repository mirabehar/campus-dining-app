package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;


/**
 * A test class designed to unit test the Menu class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */


class MenuTest {

    /**
     * Tests whether the equals() method returns the desired output
     */
    @Test
    void testEquals() {
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,true,false,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("oranges", "home", "breakfast", properties2);
        LinkedList<FoodItem> list1 = new LinkedList<>();
        LinkedList<FoodItem> list2 = new LinkedList<>();
        list1.add(foodItem);
        list1.add(foodItem2);
        list2.add(foodItem);
        list2.add(foodItem2);

        Station oasis = new Station("Oasis", list1);
        Station stocks = new Station("Stocks", list2);

        Map<String, Station> stations = new HashMap<String,Station>();
        stations.put("oasis", oasis);
        stations.put("stocks", stocks);

        List<String> statNames = new ArrayList<String>();
        statNames.add("Stocks");
        statNames.add("Home");
        statNames.add("Grill");
        statNames.add("Brick Oven");

        MealLabel breakfast = new MealLabel("Breakfast", stations, statNames);

        Map<String, MealLabel> mealLabels = new HashMap<String, MealLabel>();
        mealLabels.put("Breakfast", breakfast);
        List<String> labelNames = new ArrayList<String>();
        labelNames.add("Breakfast");
        labelNames.add("Lunch");
        labelNames.add("Dinner");
        labelNames.add("Brunch");

        Map<String, MealLabel> mealLabels2 = new HashMap<String, MealLabel>();
        mealLabels2.put("Breakfast", breakfast);
        List<String> labelNames2 = new ArrayList<String>();
        labelNames2.add("Breakfast");
        labelNames2.add("Lunch");
        labelNames2.add("Dinner");
        labelNames2.add("Brunch");

        //Test for similar menus
        Menu m1 = new Menu("deece", "2021-12-12", mealLabels,labelNames);
        Menu m2 = new Menu("deece", "2021-12-12", mealLabels2,labelNames2);
        assertTrue(m1.equals(m1));
        assertTrue(m1.equals(m2));
        assertEquals(m1, m2);

        Map<String, MealLabel> mealLabels3 = new HashMap<String, MealLabel>();
        mealLabels3.put("Dinner", breakfast);
        List<String> labelNames3 = new ArrayList<String>();
        labelNames3.add("Breakfast");
        labelNames3.add("Lunch");
        labelNames3.add("Brunch");


        Menu m3 = new Menu("express", "2021-12-12", mealLabels,labelNames);
        Menu m4 = new Menu("express", "2021-12-12", mealLabels3,labelNames3);

        //Test for similar menus with different locations
        assertFalse(m1.equals(m3));

        //Test for different menus
        assertFalse(m1.equals(m4));

        //Test for similar menus with different mealLabels
        assertFalse(m3.equals(m4));

    }

    /**
     * Tests the addMealLabel method
     */
    @Test
    void testAddMealLabel(){
        //Create two menus, one with one less mealLabel than the other
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,true,false,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("oranges", "home", "breakfast", properties2);

        List<FoodItem> list1 = new ArrayList<FoodItem>();
        List<FoodItem> list2 = new ArrayList<FoodItem>();

        list1.add(foodItem);
        list1.add(foodItem2);
        list2.add(foodItem);
        list2.add(foodItem2);

        Station oasis = new Station("Oasis", list1);
        Station stocks = new Station("Stocks", list2);

        Map<String, Station> stations = new HashMap<String,Station>();
        stations.put("oasis", oasis);
        stations.put("stocks", stocks);

        List<String> statNames = new ArrayList<String>();
        statNames.add("Stocks");
        statNames.add("Home");
        statNames.add("Grill");
        statNames.add("Brick Oven");

        MealLabel breakfast = new MealLabel("Breakfast", stations, statNames);
        Map<String, MealLabel> mealLabels = new HashMap<String, MealLabel>();
        mealLabels.put("Breakfast", breakfast);
        List<String> labelNames = new ArrayList<String>();
        labelNames.add("Breakfast");
        labelNames.add("Lunch");
        labelNames.add("Dinner");
        labelNames.add("Brunch");

        Map<String, MealLabel> mealLabels2 = new HashMap<String, MealLabel>();
        List<String> labelNames2 = new ArrayList<String>();
        labelNames2.add("Breakfast");
        labelNames2.add("Lunch");
        labelNames2.add("Dinner");
        labelNames2.add("Brunch");

        Menu m1 = new Menu("deece", "2021-12-12", mealLabels,labelNames);
        Menu m2 = new Menu("deece", "2021-12-12", mealLabels2,labelNames2);

        //Check that they are not equal
        assertNotEquals(m1,m2);

        //Check that they are equal after adding the missing mealLabel
        m2.addMealLabel(breakfast);

        assertEquals(m1,m2);
    }

    /**
     * Tests the addItem method
     */
    @Test
    void testAddItem(){
        //Create two menus that are the same
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,true,false,false,true);

        FoodProperties properties3 = new FoodProperties(false, false, true,
                true, false, true, false, false);


        FoodItem foodItem = new FoodItem("apples", "oasis", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("oranges", "stocks", "breakfast", properties2);
        FoodItem foodItem3 = new FoodItem("rice", "home", "lunch", properties3);

        List<FoodItem> list1 = new ArrayList<FoodItem>();
        List<FoodItem> list2 = new ArrayList<FoodItem>();

        list1.add(foodItem);
        list2.add(foodItem2);


        Station oasis = new Station("oasis", list1);
        Station stocks = new Station("stocks", list2);

        Map<String, Station> stations = new HashMap<String,Station>();
        stations.put("oasis", oasis);
        stations.put("stocks", stocks);

        List<String> statNames = new ArrayList<String>();
        statNames.add("oasis");
        statNames.add("stocks");


        MealLabel breakfast = new MealLabel("breakfast", stations, statNames);
        Map<String, MealLabel> mealLabels = new HashMap<String, MealLabel>();
        mealLabels.put("breakfast", breakfast);
        List<String> labelNames = new ArrayList<String>();
        labelNames.add("breakfast");

        Map<String, MealLabel> mealLabels2 = new HashMap<String, MealLabel>();
        mealLabels2.put("breakfast", breakfast);
        List<String> labelNames2 = new ArrayList<String>();
        labelNames2.add("breakfast");


        Menu m1 = new Menu("deece", "2021-12-12", mealLabels,labelNames);
        Menu m2 = new Menu("deece", "2021-12-12", mealLabels2,labelNames2);

        assertEquals(m1,m2);

        //Add an item and check that they are not equal
        m2.addItem(foodItem3);

        assertNotEquals(m1,m2);

    }
}