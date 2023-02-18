Feature: Create a User account
  As a user, I would like to create an account so that I can interact
  with friends, post reviews, and create playlists.

  Background:
    Given the following accounts exist in the system:
      | username | firstName | lastName | email              | password  |
      | robsab   | Robert    | Sabourin | robsab@email.com   | ECSE428   |
      | bradpitt | Brad      | Pitt     | bradpitt@email.com | FightClub |

  Scenario Outline: Successfully creating an account with username "<username>"
    When an account is created with the following information: "<username>", "<firstName>", "<lastName>", "<email>", and "<password>"
    Then there shall be 3 accounts in the system
    And there shall exist an account with the username "<username>"

    Examples:
      | username   | firstName | lastName | email                | password     |
      | tomholland | Tom       | Holland  | tomholland@email.com | IamSpiderman |
      | chrispratt | Chris     | Pratt    | chrispratt@email.com | starLORD     |


  Scenario Outline: Attempting to create an account but the username or email is already taken
    When an account is created with the following information: "<username>", "<firstName>", "<lastName>", "<email>", and "<password>"
    Then there shall be 2 accounts in the system
    And the error message shall be "<error>"

    Examples:
      | username    | firstName | lastName | email                    | password     | error                  |
      | robsab      | Robert    | Sabourin | robertsabourin@email.com | ECSE428      | Username already taken |
      | tomholland2 | Tom       | Holland  | tomholland@email.com     | IamSpiderman | Email already in use   |
