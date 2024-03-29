Feature: Create a User account
  As a user, I would like to create an account so that I can interact
  with friends, post reviews, and create playlists.

  Background:
    Given the following accounts exist in the system:
      | username | email              | password  |
      | robsab   | robsab@email.com   | ECSE428   |
      | bradpitt | bradpitt@email.com | FightClub |

  Scenario Outline: Successfully creating an account
    When an account is created with the following information: "<username>", "<email>", and "<password>"
    Then there shall be 3 accounts in the system
    And there shall exist an account with the username "<username>"

    Examples:
      | username   | email                | password     |
      | tomholland | tomholland@email.com | IamSpiderman |
      | chrispratt | chrispratt@email.com | starLORD     |


  Scenario Outline: Attempting to create an account but the username or email is already taken
    When an account is created with the following information: "<username>", "<email>", and "<password>"
    Then there shall be 2 accounts in the system
    And the error message shall be "<error>"

    Examples:
      | username  | email                    | password  | error                   |
      | robsab    | robertsabourin@email.com | ECSE428   | username already exists |
      | bradpitt2 | bradpitt@email.com       | FightClub | email already in use    |
