package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A test class designed to unit test the AuthKey class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */

class AuthKeyTest {

    /**
     * Tests password validation in the authkey class
     */
    @Test
    void validatePassword() {
        //Create a new authkey with a particular password
        AuthKey a1 = new AuthKey("Password");
        String rightPass = "Password";
        String wrongPass = "wordpass";

        //Test it against different passwords
        assertTrue(a1.validatePassword(rightPass));
        assertFalse(a1.validatePassword(wrongPass));
    }
}