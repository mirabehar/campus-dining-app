# Make Profile

**Scope**: Campus dining app

**Level**: User goal

**Primary actor**: People on Vassar college meal plan

**Stakeholders and interest**:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Students: Want to be able to store their preferences for a more personalized dining experience\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Campus dining services: Want students to navigate their dining experience faster and better

**Preconditions**: N/A

**Postconditions**: Creates and stores new profile with given name, username, password, and preferences

**Main success scenario**:
1. User selects to make a new profile.
2. User provides their desired username and system checks if username is available.
3. User provides their desired password.
4. User verifies their new password.
5. User provides the name they wish to be displayed on their profile.
6. Profile is saved to database.

**Extensions**: \
2a. Username is already taken:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System asks user to enter a different username.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  2. Process repeats until unique username is provided.

4a. Password does not match:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System prompts user to reenter password.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Process repeats until matching password is provided.


*a. Application crashes at any time
1. System displays error message
2. System is reconfigured to previous state

**Special Requirements**: N/A

**Technology and Data Variations list**: N/A

**Frequency of Occurrence**: Once per user

**Miscellaneous**: