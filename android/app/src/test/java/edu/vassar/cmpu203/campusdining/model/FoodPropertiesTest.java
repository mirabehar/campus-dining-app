package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A test class designed to unit test the FoodProperties class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */
class FoodPropertiesTest {

    /**
     * Tests whether properties are changed correctly
     */
    @Test
    void testChangePropVal() { //passed
        FoodProperties properties = new FoodProperties(true,false,true,
                false,false,true,false,true);
        properties.changePropVal(0);
        assertFalse(properties.getPropVal(0));
        properties.changePropVal(1);
        assertTrue(properties.getPropVal(1));
    }

    /**
     * Tests whether the properties of the food match with the preferences of the profile
     * based on the comparison definition in the FoodProperties class
     */
    @Test
    void testCompareProperties() { //passed
        FoodProperties preferences = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties foodProperties = new FoodProperties(false,false,true,
                false,false,true,false,false);
        assertTrue(preferences.compareProperties(preferences));
        assertTrue(preferences.compareProperties(foodProperties));
        preferences = new FoodProperties(false,false,false,
                false,false,false, false,false);
        foodProperties = new FoodProperties(true,true,true,true,true,
                true,true,true);
        assertFalse(preferences.compareProperties(foodProperties));
        assertTrue(foodProperties.compareProperties(preferences));


    }

    /**
     * Tests whether the toString method returns the desired value.
     */
    @Test
    void testToString() { //passed
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        String expectedString = "V, VG, GF, H, FTF.";
        assertEquals(expectedString,properties.toString());

        properties = new FoodProperties(true,true,true,
                true,true,true,true,true);
        expectedString = "V, VG, GF, Ha, K, H, SF, FTF.";
        assertEquals(expectedString,properties.toString());

        properties = new FoodProperties(false,false,false,
                false,false,false,false,false);
        expectedString = "";
        assertEquals(expectedString,properties.toString());

        properties.changePropVal(0);
        expectedString = "V.";
        assertEquals(expectedString,properties.toString());

    }

    /**
     * Tests equality between food properties
     */
    @Test
    void testEquals(){
        FoodProperties properties = new FoodProperties(true,true,true,
                false,false,true,false,true);
        FoodProperties properties2 = new FoodProperties(true,true,true,
                false,false,true,false,true);
        assertEquals(properties, properties2);

        FoodProperties properties3 = new FoodProperties(false,true,true,
                false,false,false,false,true);

        assertNotEquals(properties3,properties2);
    }
}