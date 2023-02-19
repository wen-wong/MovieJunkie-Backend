Feature: Delete user account
  As a user, I would like to delete my account so that I can remove my information
  from the application if I stop using it

  Background:
    Given the following accounts exist in the system:
      | username   | firstName | lastName | email                   | password   |
      | kreeves    | Keanu     | Reeves   | keanureeves@email.com   | 01MATRIX01 |
      | bradcooper | Bradley   | Cooper   | bradleycooper@email.com | StarIsBorn |

  Scenario: Successfully deleting an information
    When the user "kreeves" attempts to delete their account
    Then there shall be 1 accounts in the system
    Then there shall not exist an account with the username "kreeves"