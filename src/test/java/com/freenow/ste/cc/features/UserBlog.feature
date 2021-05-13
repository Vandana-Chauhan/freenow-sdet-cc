@EmailValidationFlow
Feature: User Blog Feature

  #Assumptions:
  #API response should be "No user Found".If user does not exist but it  is empty now.
  #API response should be "No Post Found".If user is new but it  is empty now.
  #API response should be "No Commnets  Found".If Post is new but it  is empty now.
  #User name can not be duplicate as in blogs you cannot create duplicate userName
  #UserName should not have space in between name
  #Posts cannot not have same title
  #Should accept user name without caps lock check.As of now its not accepting caps lock except for first letter.
  Scenario Outline: Search for a valid input user with no details.
    Given GetUser payload is provided with "<User>".
    When User calls "getuserAPI" to search
    And I verify the "User" should be an alphabet
    Then I should see response status code as "<httpStatusCode>"
    Then I vaildate  username  is null for invalid user

    Examples: 
      | User   | httpStatusCode |
      | Samaha |            200 |

  Scenario Outline: Search for a valid input user and verify it details are matched with the correct posts.
    Given User payload is provided with "<User>".
    When User calls "getuserAPI" to search
    And I verify the "User" should be an alphabet
    Then I should see response status code as "<httpStatusCode>"
    Then I validate that the  details of user are correctly matched.
    And I set the post request
    When User calls "getPostsApI" to search post
    Then I validate that the  posts data of user are correctly matched with the details.
    When User calls "getCommentsAPT" to search comments
    Then I validate the comments belongs to the specific post" .
    Then I get the emails from all the comments and validate the email format

    Examples: 
      | User     | httpStatusCode |
      | Samantha |            200 |

  Scenario Outline: Search for a new  user and verify it has no posts.
    Given Post payload is provided with "<PostID>".
    When User calls "getPostsApI" to search post
    Then I should see response status code as "<httpStatusCode>"
    Then I validate that the  posts data is empty for any new user.

    Examples: 
      | User     | httpStatusCode |
      | Samantha |            200 |

  Scenario Outline: Search for a new user with a recent posts has no comments .
    Given User payload is provided with "<User>".
    When User calls "getuserAPI" to search
    And I verify the "User" should be an alphabet
    Then I should see response status code as "<httpStatusCode>"
    Then I validate that the  details of user are correctly matched.
    And I set the post request
    When User calls "getPostsApI" to search post
    Then I validate that the  posts data of user are correctly matched with the details.
    When User calls "getCommentsAPT" to search comments for new user
    Then I validate that the  comments  data is empty for very recent post.

    Examples: 
      | User     | httpStatusCode |
      | Samantha |            200 |
