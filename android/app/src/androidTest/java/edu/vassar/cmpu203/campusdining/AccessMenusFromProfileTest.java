package edu.vassar.cmpu203.campusdining;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.annotation.NonNull;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import android.util.Log;
import android.view.View;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.security.*;
import java.util.*;


import edu.vassar.cmpu203.campusdining.controller.ControllerActivity;

/**
 * A test class designed to system test views in the application.
 *
 * @author Team-2c
 * @version 12/10/2021
 *
 */
public class AccessMenusFromProfileTest {
    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);

    /**
     * Tests logging in to a profile
     */
    @org.junit.Test
    public void testLogin(){
        // click log in button
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());

        // check that the view changes to log in fragment
        // (by checking that the createProfileButton is present)
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).check(matches(isDisplayed()));

        // type username
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText("jbosire"));

        // type password
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("joram"));

        // click log in
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        // check that the view changes after logging in (to profile display fragment)
        // by checking that the showPasswordButton is present)
        Espresso.onView(ViewMatchers.withId(R.id.showPasswordButton)).check(matches(isDisplayed()));

        //check that profile is correct one
        Espresso.onView(ViewMatchers.withId(R.id.profileUsername)).check(ViewAssertions.matches(ViewMatchers.withText("jbosire")));
    }

    /**
     * Tests logging out of a profile
     */
    @org.junit.Test
    public void testLogOut(){
        //log in
        this.testLogin();
        //click log out button
        Espresso.onView(ViewMatchers.withId(R.id.logOutButton)).perform(ViewActions.click());
        //check view is in main page
        Espresso.onView(ViewMatchers.withId(R.id.optionsButton)).check(matches(isDisplayed()));

    }

    /**
     * Tests creating a new profile
     */
    @org.junit.Test
    public void testCreateProfile(){
        //click log in button
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        //click create profile
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(ViewActions.click());
        // type in name, username, password x2
        String username = generateUsername();
        Espresso.onView(ViewMatchers.withId(R.id.loginName)).perform(ViewActions.typeText("Sarita"));
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText(username));
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("password"));
        //mismatched passwords
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.typeText("password2"));
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());
        //error message
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.snackbar_text), withText("Passwords do not match, try again"))).check(
                matches(isDisplayed()));
        //correct the password
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.clearText());
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.typeText("password"));
        //Click done button
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());
        // check that the view changes view after logging in (to profile display fragment)
        // by checking that the showPasswordButton is present)
       Espresso.onView(ViewMatchers.withId(R.id.showPasswordButton)).check(matches(isDisplayed()));

        //The following process tries to create a new profile with the same username as before

        //Log out of profile
        Espresso.onView(ViewMatchers.withId(R.id.logOutButton)).perform(getViewAction());
        //click log in button
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(getViewAction());
        //click create profile
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());

        //Create profile with same username
        Espresso.onView(ViewMatchers.withId(R.id.loginName)).perform(ViewActions.typeText("Joram"));
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText(username));
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("pass"));
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.typeText("pass"));
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());

        //Check for error message
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.snackbar_text), withText("Username already taken, please enter a unique username"))).check(
                matches(isDisplayed()));
    }

    /**
     * Tests changing and setting preferences on profile
     */
    @org.junit.Test
    public void testCheckAndSetPreferences(){
       //Create a new profile
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(ViewActions.click());
        String username = generateUsername();
        Espresso.onView(ViewMatchers.withId(R.id.loginName)).perform(ViewActions.typeText("Mira"));
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText(username));
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("Mia"));
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.typeText("Mia"));
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());

        //check if preferences are the same ones in hardcoded profile
        Espresso.onView(ViewMatchers.withId(R.id.glutenFreeSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.farmToForkSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.vegetarianSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.veganSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.halalSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.seafoodSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.kosherSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.humaneSwitch)).check(matches(isNotChecked()));

        //click edit profile; change some preferences (click buttons); and save changes
        Espresso.onView(ViewMatchers.withId(R.id.editProfileButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.glutenFreeSwitch)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.glutenFreeSwitch)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.farmToForkSwitch)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.vegetarianSwitch)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.humaneSwitch)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.editProfileButton)).perform(ViewActions.click());

        //go back to main page
        Espresso.onView(ViewMatchers.withId(R.id.leaveProfileButton)).perform(ViewActions.click());

        //go back to profile; check if changes are still there
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.glutenFreeSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.farmToForkSwitch)).check(matches(isChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.vegetarianSwitch)).check(matches(isChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.humaneSwitch)).check(matches(isChecked()));

        //click log out button
        Espresso.onView(ViewMatchers.withId(R.id.logOutButton)).perform(ViewActions.click());

        // click log in button
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());

        // type username
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText(username));

        // type password
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("Mia"));

        // click log in
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        //check that changes are still there
        Espresso.onView(ViewMatchers.withId(R.id.glutenFreeSwitch)).check(matches(isNotChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.farmToForkSwitch)).check(matches(isChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.vegetarianSwitch)).check(matches(isChecked()));
        Espresso.onView(ViewMatchers.withId(R.id.humaneSwitch)).check(matches(isChecked()));
    }


    /**
     * Tests exiting the profile
     */
    @org.junit.Test
    public void testBackButton(){
        //Create a new profile
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(ViewActions.click());
        String username = generateUsername();
        Espresso.onView(ViewMatchers.withId(R.id.loginName)).perform(ViewActions.typeText("Mia"));
        Espresso.onView(ViewMatchers.withId(R.id.loginUsername)).perform(ViewActions.typeText(username));
        Espresso.onView(ViewMatchers.withId(R.id.loginPassword)).perform(ViewActions.typeText("Sarita"));
        Espresso.onView(ViewMatchers.withId(R.id.secondLoginPassword)).perform(ViewActions.typeText("Sarita"));
        Espresso.onView(ViewMatchers.withId(R.id.createProfileButton)).perform(getViewAction());

        //click the back button
        Espresso.onView(ViewMatchers.withId(R.id.leaveProfileButton)).perform(getViewAction());

        // check that it brings you back to main page (by checking if optionsButton is displayed)
        Espresso.onView(ViewMatchers.withId(R.id.optionsButton)).check(matches(isDisplayed()));

    }

    /**
     * Tests selecting and displaying menus
     * @throws InterruptedException
     */
    @org.junit.Test
    public void testDisplayMenu() throws InterruptedException {
        // enter invalid date and click enter
        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.clearText());
        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.typeText("2020-10-07"));
        Espresso.onView(ViewMatchers.withId(R.id.setDateButton)).perform(getViewAction());
        //click location

        Espresso.onView(ViewMatchers.withId(R.id.deeceButton)).perform(getViewAction());

        // check for invalid date message
       Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.snackbar_text), withText(
                "Please select a date between yesterday and 6 days from today's date (format yyyy-mm-dd)"))).check(
                                matches(isDisplayed()));

        //enter valid date

        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.clearText());

        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.typeText("2021-10-11"));
        Espresso.onView(ViewMatchers.withId(R.id.setDateButton)).perform(ViewActions.click());

        //click invalid location
        Espresso.onView(ViewMatchers.withId(R.id.expressButton)).perform(getViewAction());

        Thread.sleep(3000);
        // check for invalid location message
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.validDateButUnavailable), withText(R.string.valid_date_but_unavailable))).check(
                matches(isDisplayed()));
        // exit back to main page
        Espresso.onView(ViewMatchers.withId(R.id.exitMenuButton)).perform(ViewActions.click());

        //click valid location
        Espresso.onView(ViewMatchers.withId(R.id.deeceButton)).perform(getViewAction());

        Thread.sleep(3000);
        // check if correct menu is displayed (check if location and date are correct)
        ViewInteraction location = Espresso.onView(ViewMatchers.withId(R.id.menuLocation));
        ViewInteraction date = Espresso.onView(ViewMatchers.withId(R.id.menuDate));

        location.check(ViewAssertions.matches(ViewMatchers.withText(ControllerActivity.DEECE_STRING)));
        date.check(ViewAssertions.matches(ViewMatchers.withText("2021-10-11")));

        //Check if the back button is functional
        Espresso.onView(ViewMatchers.withId(R.id.exitMenuButton)).perform(getViewAction());
        Espresso.onView(ViewMatchers.withId(R.id.setDateButton)).check(matches(isDisplayed()));
    }




    /**
     * Tests rating a food item
     * @throws InterruptedException
     */
    @org.junit.Test
    public void testAddAndCheckRating() throws InterruptedException {
        // Get the menu
        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.clearText());
        Espresso.onView(ViewMatchers.withId(R.id.menuDateSelect)).perform(ViewActions.typeText("2021-10-11"));
        Espresso.onView(ViewMatchers.withId(R.id.setDateButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.deeceButton)).perform(getViewAction());
        Thread.sleep(5000);

        //Click rate button
        Espresso.onView(ViewMatchers.withId(1)).perform(getViewAction());

        //Check if rating bar is on the screen
        Espresso.onView(ViewMatchers.withId(R.id.ratingBar2)).check(matches(isDisplayed()));

        //Click the rating button
        Espresso.onView(ViewMatchers.withId(R.id.ratingBar2)).perform(getViewAction());

        //Submit the rating
        Espresso.onView(ViewMatchers.withId(R.id.submitRatingButton)).perform(getViewAction());
        Thread.sleep(3000);

        //Check if you're back to the menu
        ViewInteraction location = Espresso.onView(ViewMatchers.withId(R.id.menuLocation));
        location.check(ViewAssertions.matches(ViewMatchers.withText(ControllerActivity.DEECE_STRING)));
    }

    //Method to help in creating random usernames for testing
    private static String generateUsername(){
        byte[] username = new byte[20];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(username);
        } catch (NoSuchAlgorithmException e) {
            Log.e("NextGenPos", "Error generating authentication salt", e);
        }
        String user = Base64.getEncoder().encodeToString(username);
        return user.replace("/","");
    }

    // the ViewAction of clicking some buttons wasn't working, so we added our own method to fix this problem
    @NonNull
    private ViewAction getViewAction() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isEnabled(); // no constraints, they are checked above
            }

            @Override
            public String getDescription() {
                return "click plus button";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
            }
        };
    }
}
