Feature: Edit user account
  As a user, I would like to edit my account so that I can keep my information relevant and up to date.

  Background:
    Given the following accounts exist in the system:
      | username      | firstName | lastName  | email                       | password   |
      | robsab        | Robert    | Sabourin  | robsab@email.com            | ECSE428    |
      | scarjo        | Scarlett  | Johansson | scarlettjohansson@email.com | BlackW1d0w |
      | morganfreeman | Morgan    | Freeman   | morganfreeman@email.com     | Shawshank  |

  Scenario Outline: Successfully editing account information (first name, last name, email, or password)
    Given "<username>" is logged in
    When the user changes their "<infoType>" to "<newInfo>"
    Then "<username>"'s "<infoType>" shall be "<newInfo>"

    Examples:
      | username      | infoType   | newInfo                    |
      | robsab        | first name | Bob                        |
      | scarjo        | last name  | Jost                       |
      | morganfreeman | email      | morgan.freeman@example.com |
      | robsab        | password   | group13IsTheBest           |

  Scenario Outline: Successfully changing username
    Given "robsab" is logged in
    When the user changes their "username" to "<newUsername>"
    Then there shall exist an account with the username "<newUsername>"
    And there shall not exist an account with the username "robsab"

    Examples:
      | newUsername    |
      | robertsabourin |
      | robsab123      |

  Scenario Outline: Unsuccessfully changing username or email because they are already in use
    Given "<username>" is logged in
    When the user changes their "<infoType>" to "<newInfo>"
    Then "<username>"'s "<infoType>" shall be "<newInfo>"
    And the error message shall be "<error>"

    Examples:
      | username | infoType | newInfo                 | error                  |
      | robsab   | username | scarjo                  | Username already taken |
      | robsab   | email    | morganfreeman@email.com | Email already in use   |