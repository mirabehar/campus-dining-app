package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * A test class designed to unit test the Station class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */
class StationTest {

    /**
     * Tests the equals method in the station class.
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

        List<FoodItem> list3 = new LinkedList<>();
        list3.add(foodItem);
        list3.add(foodItem2);

        Station station1 = new Station("stocks", list1);
        Station station2 = new Station("stocks", list2);
        Station station3 = new Station("home", list1);
        Station station4 = new Station("stocks", list3);

        //Same names, different food items
        assertNotEquals(station1,station2);
        //Different names, same food items
        assertNotEquals(station1,station3);
        //Same names, same food items
        assertEquals(station1,station4);
    }
}