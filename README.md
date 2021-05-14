Task:


The task is to perform the validations for the comments for the post made by a specific usernamed“​Samantha”​ using the tools specified
● Search for the user.
● Use the details fetched to make a search for the posts written by the user.
● For each post, fetch the comments and validate if the emails in the comment
section are in the proper format.
● Think of various scenarios for the test workflow, all the things that can go wrong.
● Upload your test to Github
● Execute the tests successfully on Circle CI.



Solution:
Please read all the assumptions.

#Assumptions:


  #API response should be "No user Found".If user does not exist but it  is empty now. So i have asserted with empty response.
  #API response should be "No Post Found".If user is new but it  is empty now.So i have asserted with empty response.
  #API response should be "No Commnets  Found".If Post is new but it  is empty now.So i have asserted with empty response.
  #User name can not be duplicate as in blogs you cannot create duplicate userName.
  #UserName should not have space in between name
  #Posts cannot not have same title
  #Generally search field should accept user name without caps lock check.As of now its not accepting caps lock except for first letter.
  
  I have validated the comments for the Posts made by a specific usernamed“​Samantha”​
Solution :In comments the name appears as a title. I have not automated it as I do not no the "Names"  filed lenght  checks .

 Process:
 1)Searched a user : 
   a)User not found.
   b)User found with details.
   
 2)Post searched for the user found.
    a)Post are not available.
    b)Post are available and are mapped correctly with user found.
    
 3)Searched for user comments for available posts.
   a)Comments are not available.
   b)Comments available and are correctly mapped with the user posts.
   c)Verify the email format available in comments.
