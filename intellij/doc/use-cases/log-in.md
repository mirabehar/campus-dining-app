# Log In


**Scope**: Campus dining app

**Level**: User goal

**Primary actor**: People on Vassar college meal plan

**Stakeholders and interest**:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Students: Want to be able to access their personal profile to get personalized menus\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Campus dining services: Want students to navigate their dining experience faster and better

**Preconditions**: N/A

**Postconditions**: User is logged in to their profile, and later actions will take this into account

**Main success scenario**:
1. User selects to log in to their profile.
2. User provides their username and system looks for and retrieves that profile.
3. User provides their password.
4. User is shown their profile page, and system temporarily stores profile.

**Extensions**: \
2a. Profile is not found:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System asks user to enter a different username.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  2. Process repeats until valid username is provided.

3a. Password is not correct:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. System prompts user to reenter password.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Process repeats until correct password is provided.

*a. Application crashes at any time
1. System displays error message
2. System is reconfigured to previous state

**Special Requirements**: N/A

**Technology and Data Variations list**: N/A

**Frequency of Occurrence**: Every time the app is opened (if the user wants to log in)

**Miscellaneous**: