package edu.vassar.cmpu203.campusdining.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A test class designed to unit test the ProfileCatalogue class.
 * Only the relevant non-trivial methods in the class are tested
 *
 * @author Team-2c
 * @version 11/20/2021
 *
 */

class ProfileCatalogueTest {

    /**
     * Tests accessing profiles from the profile catalogue
     */
    @Test
    void getProfile() {
        //Create new profile catalogue
        ProfileCatalogue pCat = new ProfileCatalogue();

        //Create a profile that we will use to compare to
        Profile p1 = new Profile("Arca","arca10000", "password");
        FoodProperties f1 = new FoodProperties(true, true, false, false, false, false, false, false);
        p1.setPreferences(f1);

        String username = "arca10000";

        //Compare the created profile to a profile with the same username in the profilecatalogue
        assertEquals(p1.getUsername(), pCat.getProfile(username).getUsername());
        assertEquals(f1.toString(),pCat.getProfile(username).getPreferences().toString());

        username = "badbunny";

        //Compare with different profiles in the catalogue
        assertNotEquals(username,pCat.getProfile("arca10000").getUsername());
        assertNotEquals(pCat.getProfile("badbunny").getPreferences().toString(),
                pCat.getProfile("arca10000").getPreferences().toString());

        assertEquals(username,pCat.getProfile("badbunny").getUsername());


    }
}