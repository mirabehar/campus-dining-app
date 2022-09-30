package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * A test class designed to unit test the MealLabel class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */
class MealLabelTest {

    /**
     * Tests adding stations to a mealLabel
     */
    @Test
    void addStation() {
        //Create two different mealLabels, one with 2 more stations than the other
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("apples", "stocks", "breakfast", properties2);
        List<FoodItem> list1 = new LinkedList<>();
        list1.add(foodItem);
        list1.add(foodItem2);

        List<FoodItem> list2 = new LinkedList<>();
        list2.add(foodItem);
        list2.add(foodItem2);


        Station oasis = new Station("oasis", list1);
        Station stocks = new Station("stocks", list2);

        Map<String,Station> oasisMap = new HashMap<>();
        oasisMap.put("oasis",oasis);
        oasisMap.put("stocks", stocks);

        List<String> stations = new LinkedList<>();
        stations.add("oasis");
        stations.add("stocks");



        MealLabel label1 = new MealLabel("breakfast", oasisMap,stations);
        MealLabel label2 = new MealLabel("breakfast");

        //Check the two mealLabels are not equal
        assertNotEquals(label1,label2);

        //Add the two missing stations
        label2.addStation(oasis);
        label2.addStation(stocks);

        //Check the two mealLabels are equal
        assertEquals(label1,label2);



    }

    /**
     * Tests the equals method in the mealLabel class.
     */
    @Test
    void testEquals() {
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("apples", "stocks", "breakfast", properties2);
        List<FoodItem> list1 = new LinkedList<>();
        list1.add(foodItem);
        list1.add(foodItem2);

        List<FoodItem> list2 = new LinkedList<>();
        list2.add(foodItem);
        list2.add(foodItem2);


        Station oasis = new Station("oasis", list1);
        Station stocks = new Station("stocks", list2);

        Map<String,Station> oasisMap = new HashMap<>();
        oasisMap.put("oasis",oasis);
        oasisMap.put("stocks", stocks);

        List<String> stations = new LinkedList<>();
        stations.add("oasis");
        stations.add("stocks");



        MealLabel label1 = new MealLabel("breakfast", oasisMap,stations);
        MealLabel label2 = new MealLabel("breakfast");

        assertNotEquals(label1,label2);

        label2.addStation(oasis);
        label2.addStation(stocks);

        assertEquals(label1,label2);

        MealLabel label3 = new MealLabel("lunch", oasisMap,stations);

        assertNotEquals(label1,label3);
    }
}