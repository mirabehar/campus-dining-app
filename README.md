# Team 2C

### Iteration 1: Access-Menus 

##Functionality
This project allows a user to interact with the interface to display
their desired menu according to their preferences.

##Process
The interface first displays a welcome message before asking
for the username and password of the user. The user can
provide their username and password and the interface fetches a
profile object that contains the user's set preferences.
The user provides the location and date of the menu they want
and the interface fetches a menu from a menu catalogue.
The interface filters the menu based on the preferences of the user
before displaying it for the user. Menus are filtered to only include
food items which have properties that match the preferences listed
in the user's profile.

##Assumptions
During this iteration we assume that the user profile has already been
created. 

##Limitations
1.The application does not currently pull menu data from the API.
In a later iteration, we will request data from the API and store a week's worth
of menus in a MenuCatalogue object. Currently, there is only one hard coded
menu that can be displayed for users.\
2.User is unable to create a new profile\
3.The application does not currently access pre-existing profiles given the username, and does not authenticate users. In a later iteration, we will store all profiles in a ProfileCatalogue object which the user will need to provide with their username and password to access their profile. Currently, there is only one hard coded profile that is used to filter the menu items.\
4.The user cannot change the preferences on their profile. In a later iteration we will address this use case and allow users to alter the FoodProperties associated with their Profile. Currently, there are set preferences hard coded into the hard coded profile.\
5.User must sign in to access menu. In future iteration we will add functionality to display a general menu if the user does not sign in.

To run the program:
- Run the main method in the UserInterface class


### Iteration 2: Access-Menu, Make and Edit Profile, and Set Preferences

## Functionality
The user can interact with the Android interface to display the desired
menu based on location and date and filtered by their profile preferences.
The user can also access their profile and edit their preferences, or create a new profile.

##Process
Upon opening the app the main page is displayed, prompting the user
to enter a date of the form yyyy-mm-dd and within one day before and six days after today's date (in this version "today's date" is always 2021-10-20).
The user should then select a desired location. From the main page, the user can also access the log in page, which
takes the login credentials for an existing account or alternatively,
gives the user the option of creating a new profile. The user can return to the main page after adjusting their
profile settings.
When a valid date is selected, clicking the buttons for the dining locations
will display a list of food items corresponding to the specified menu, and filtered by the profile preferences if the user is logged in.
The user can go back to the main page from a menu, log in page, or profile by selecting the back button.

##Assumptions
For this iteration we assume that there is a working API to retrieve menu data from our
database. We also assume that users will enter their correct login credentials (i.e doesn't check that the password is correct when logging in), and that
this will connect them to their account.

##Limitations
1. The application does not currently pull data from the API, and a few hardcoded menus are currently
being used for testing. 
   These menus are: Deece 2021-10-20, Deece 2021-10-21, Retreat 2021-10-22, Express 2021-10-23, StreetEats 2021-10-24.
2. There is no authentication of existing profile log in attempts, or verification of username availability for profile creation attempts, as there is not currently a database of profiles.
   The current available profiles for testing are: 
   {username = arca10000, password = password}, {username = seaqueen, password = 123456}, 
   {username = badbunny, password = elconejomalo}. However, any password will work with these usernames since we do not have authentication implemented yet.
   If the user creates a profile this will also be added to the catalogue for that session, so the user can log out and log back in to their new profile.
3. Once the app is exited, profile data is not maintained.
4. App does not store data to bundles yet, and so data may disappear if app is left running in background for too long, and the app will crash if the screen is rotated.

To run the program:
Create a simulated Android device configuration and run the program.

### Iteration 3: Using dining API, data persistence, RateItems use case

## Functionality
The user can interact with the Android interface to display the desired
menu based on location and date and filtered by their profile preferences,
and can then leave a rating on any food item on that menu. 
The user can also access their profile and edit their preferences,
or create a new profile.

## Process
Upon opening the app the main page is displayed, prompting the user
to enter a date of the form yyyy-mm-dd and within one day before and six days after today's date
(in this version "today's date" is always 2021-10-09).
The user should then select a desired location. From the main page, the user can also access the log in page, which
takes the login credentials for an existing account or alternatively,
gives the user the option of creating a new profile. The user can return to the main page after adjusting their
profile settings.
When a valid date is selected, clicking the buttons for the dining locations
will display a list of food items with average ratings from all users corresponding to the specified menu,
and filtered by the profile preferences if the user is logged in.
The user can select the rating button next to any food item on a menu, which allows them to leave a rating
(a whole number out of 5, indicated with stars).
The user can go back to the main page from a menu, log in page, or profile by selecting the back button.

## Assumptions
We assume that the database and API will always be operational during a session of the application.

## Limitations
1. The API we are using stops having available data from 2021-10-16 onwards, 
   so unfortunately we have had to hardcode "today's date" to be 2021-10-09. However,
   a few quick comments/uncomments would allow full date functionality if the API were to
   have the data. (Note: some menus are also unavailable because that dining
   location is not open on that date. E.g. the Retreat is not open on weekends).
2. There are only a few preexisting profiles and due to security consraints
   we can't save their passwords to the database. If you want to test a profile
   from the database you can use the username "mdestefano" and the password "hello".
   Once you create your own profile it should also be stored on the database and be
   accessible in later sessions.
3. There are a few display formatting issues, but they are mostly minor and only aesthetic issues.
   The main functional constraint is that if the name of a food item is too long
   (for example most of the food truck items), the rate item button no longer fits on the screen,
   and so these items essentially cannot be rated through user interaction (though they could
   be rated with a system test).
4. Since the program may at times take a long time to get the menu from the API source, the 
   addAndCheckRating system test may at times fail, but it passes in subsquent running.

To run the program:
Create a simulated Android device configuration and run the program.