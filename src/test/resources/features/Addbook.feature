Feature: Add a Book
  As a user
  I want to add a book to the library system
  So that the book is available for others to view
  @Test
  Scenario: Successfully add a book
    Given I navigate to the application
    And I enter following login details
      | username | password       |
      | admin1   | securePassword |
    Then I should see options to Addbook
    When I enter the following book details
      | title            | author              | genre   | isbn          | published | price |
      | The Great Gatsby | F. Scott Fitzgerald | Fiction | 9780743273565 | 6/4/18    | 6.99  |
    And the book I added  should appear in the book list
      | title            |
      | The Great Gatsby |


  Scenario: Fail to add a book when the Title field is empty
    Given I navigate to the application
    And I enter following login details
      | username | password       |
      | admin1   | securePassword |
    Then I should see options to Addbook
    When I enter the following book details
      | title | author              | genre   | isbn          | published | price |
      | | Mahdiyyah| Fiction | 9780743273565 | 6/4/18    | 6.99  |
    Then I should see an error message
      | field | message               |
      | Title | Title is required. |

  Scenario: Unauthenticated users cannot add a book
    Given I am not authenticated
    When I navigate to the "Add Book" page
    Then I should be redirected to the login page

