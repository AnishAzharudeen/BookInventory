Feature: User Login
  Test Login functionality

  Scenario: Successful login with valid credentials
    Given I navigate to the application
    And I enter following login details
      |username|password|
      |admin1 |securePassword|
    Then I should see welcome message
      |username|
      |admin1|

  Scenario: Login should fail with invalid credentials
    Given I navigate to the application
    And I enter invalid username and valid password
      |username|password|
      |admin   |securePassword|
    Then I should see error message



  Scenario: Verify user is returned to the login page after logout
    Given I navigate to the application
    And I enter following login details
      |username|password|
      |admin1   |securePassword|
    Then I should see welcome message
      |username|
      |admin1|
    When I click the logout button
    Then I should be redirected to the login page


    