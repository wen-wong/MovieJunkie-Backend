Feature: Log out
  As a user, I would like to delete my account so that I can remove my information
  from the application if I stop using it

  Background:
    Given the following accounts exist in the system:
      | username   | firstName | lastName | email                   | password   |
      | kreeves    | Keanu     | Reeves   | keanureeves@email.com   | 01MATRIX01 |
      | bradcooper | Bradley   | Cooper   | bradleycooper@email.com | StarIsBorn |

  Scenario: Successfully deleting an information
    Given the user "kreeves" is logged in
    When the user attempts to log out
    Then user "kreeves" shall not be logged in