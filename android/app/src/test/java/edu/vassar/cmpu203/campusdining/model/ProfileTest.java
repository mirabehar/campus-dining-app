package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A test class designed to unit test the profile class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */
class ProfileTest {

    /**
     * Tests password validation in the profile class
     */
    @Test
    void validatePassword() {
        //Create a profile
        Profile p1 = new Profile("Joram", "jbosire","Password");

        //Check that passwords match when required and vice versa
        assertFalse(p1.validatePassword("wordpass"));
        assertTrue(p1.validatePassword("Password"));
    }
}