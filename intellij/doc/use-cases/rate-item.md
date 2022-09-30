# Rating and voting on menu items

**Scope**: Vassar campus dining application

**Level**: User goal

**Primary actor**: People on Vassar meal plan

**Stakeholders and interest**:\
-Students and faculty on Vassar meal plan: want ability to give constructive feedback to dining admin, to improve meals and dining experience. Want to see others opinions on meals so they choose wisely.\
-Dining staff/vassar admin: want to receive constructive feedback in order to improve dining experience for students/faculty\
-Bon appetit: want to have better communication with Vassar dining to better match the consumer needs.

**Preconditions**: User has logged in and accessed a menu

**Postconditions**:\
-Uploads star rating, and comment\
-Display collective star rating average for individual items\
-Display comments with corresponding menu item

**Main success scenario**:
1. User selects a menu item.
2. User gives the item a star rating, and leaves a comment.
3. Average star rating for the item is updated, appears publicly.
4. Comment appears publicly.

**Extensions**:

2a. Comment exceeds word limit:
1. System signals error and does not upload.
2. User edits comment to fit word limit and uploads.
3. Repeat 1 and 2 until comment is an acceptable size, then uploads.

2b. Comment is omitted:
1. Comment space is left blank.
2. Only star rating is uploaded.

**Special requirements**:

**Technology and data variations list**:

**Frequency of occurrence**: Whenever user desires, most likely after meals

**Miscellaneous**:
