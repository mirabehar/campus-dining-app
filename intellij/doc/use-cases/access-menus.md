# Access Menus

**Scope**: Campus dining app

**Level**: User goal

**Primary actor**: People on Vassar college meal plan

**Stakeholders and interest**:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Students: Want to know what food is available beforehand to plan for meals\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Campus dining services: Want to streamline serving in the dining hall and reduce waiting times

**Preconditions**: N/A

**Postconditions**: Displays menu depending on specifications i.e either the personalized or the general menu

**Main success scenario**:
1. User logs onto personal campus dining account.
2. User selects the date they want to view.
3. User selects the location they want to view.
4. Personalized menu is displayed.

**Extensions**: \
1a. Error logging into the dining app:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. User enters log-in details.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  2. System signals error while logging in.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   3. User refreshes the application.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  4. User reenters log-in credentials.

1b. User doesn't log in:
1. User picks date and location.
2. General menu with all items is displayed for corresponding location.

*a. Application crashes at any time:
  1. System displays error message.
  2. System is reconfigured to previous state.

**Special Requirements**: Well written and formatted menus for ease of understanding when displayed on screens

**Technology and Data Variations list**:  

**Frequency of Occurrence**: Whenever the user needs details on available meals

**Miscellaneous**: