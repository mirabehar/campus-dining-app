# Exclude Item

**Scope**: Campus dining app

**Level**: User goal

**Primary actor**: People on Vassar college meal plan

**Stakeholders and interest**:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Students: Want to be able to see menu that doesn't include items they don't like\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Campus dining services: Want students to navigate their dining experience faster and better

**Preconditions**: User has logged in to their account

**Postconditions**: Stores preference against item in user's profile

**Main success scenario**:
1. User selects the "Do Not Show Item" option from within an item on a displayed menu.
2. Profile is updated to include exclusion of given item.
3. Change to profile preferences is saved to database.

**Extensions**: \
1a. User selects "Do Not Show Item" option from the main page.:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1. User enters the item they do not wish to see again.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2. Profile is updated to include exclusion of given item.
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3. Change to profile preferences is saved to database.

*a. Application crashes at any time:
1. System displays error message.
2. System is reconfigured to previous state.

**Special Requirements**: N/A

**Technology and Data Variations list**: N/A

**Frequency of Occurrence**: Whenever a user dislikes an item enough to exclude it from their menu

**Miscellaneous**: