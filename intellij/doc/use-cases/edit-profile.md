# Edit Profile

**Scope**: Dining app

**Level**: User goal

**Primary Actor**: Students and Faculty on the meal plan

**Stakeholders and Interests**:\
-Student/Faculty: Wants to change their login information/display name

**Preconditions**:\
-Student has signed in to their profile.

**Postconditions**:\
-Profile details are updated

**Main Success Scenario**:
1. User selects the edit profile option.
2. User selects to change their username.
3. User enters new username.
4. User's profile is updated with this change.

**Extensions**:\
2a. User selects to change their display name:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. User enters new display name.

2b. User selects to change their password:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. User provides their desired password.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. User verifies their new password.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2a. Password does not match:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System prompts user to reenter password.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Process repeats until matching password is provided.


3a. Username is already taken:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System asks user to enter a different username.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  2. Process repeats until unique username is provided.

*a. At any time, system crashes:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    1. System displays error message.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    2. System reconstructs prior state.

**Special Requirements**:\
-Text must be readable on smartphone screen

**Technology and data variations list**:\

**Frequency of Occurrence**:\
Likely not often if ever.

**Miscellaneous**: 
