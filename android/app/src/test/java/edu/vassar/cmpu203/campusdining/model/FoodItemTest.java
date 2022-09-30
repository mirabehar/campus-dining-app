package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A test class designed to unit test the FoodItem class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */

class FoodItemTest {

    /**
     * Tests whether the equals() method returns the desired output
     */
    @Test
    void testEquals() {
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        FoodItem foodItem2 = new FoodItem("apples", "stocks", "breakfast", properties2);
        assertEquals(foodItem, foodItem2);

        FoodItem foodItem3 = new FoodItem("rice", "oasis", "breakfast", properties2);

        assertNotEquals(foodItem3,foodItem2);
    }

    /**
     * Tests the addRating method
     */
    @Test
    void testAddRating(){
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);

        foodItem.addRating(3);
        foodItem.addRating(4);

        assertEquals(foodItem.getNumRatings(), 2);
        assertEquals(foodItem.getSumRatings(),7);
    }

    /**
     * Tests the computeRating method
     */
    @Test
    void testComputeRating(){
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);

        foodItem.addRating(4);
        foodItem.addRating(4);
        foodItem.addRating(7);

        assertEquals(foodItem.computeRating(), 5);

    }

    /**
     * Tests the toString method of the fooditem class
     */
    @Test
    void testToString(){

        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodItem foodItem = new FoodItem("apples", "stocks", "breakfast", properties);
        String expected = "apples    V, VG, GF, H, FTF.\n";

        assertEquals(foodItem.toString(),expected);
    }



}