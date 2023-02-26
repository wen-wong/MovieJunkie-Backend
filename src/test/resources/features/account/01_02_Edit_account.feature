Feature: Edit user account
  As a user, I would like to edit my account so that I can keep my information relevant and up to date.

  Background:
    Given the following accounts exist in the system:
      | username      | email                       | password   |
      | robsab        | robsab@email.com            | ECSE428    |
      | scarjo        | scarlettjohansson@email.com | BlackW1d0w |
      | morganfreeman | morganfreeman@email.com     | Shawshank  |

  Scenario Outline: Successfully editing account information (first name, last name, email, or password)
    Given "<username>" is logged in
    When the user changes their "<infoType>" to "<newInfo>"
    Then "<username>"'s "<infoType>" shall be "<newInfo>"

    Examples:
      | username      | infoType   | newInfo                    |
      | morganfreeman | email      | morgan.freeman@example.com |
      | robsab        | password   | group13IsTheBest           |

  Scenario Outline: Unsuccessfully changing email because they are already in use
    Given "<username>" is logged in
    When the user changes their "<infoType>" to "<newInfo>"
    Then "<username>"'s "<infoType>" shall be "<oldInfo>"
    And the error message will be "<error>"

    Examples:
      | username | infoType | oldInfo            | newInfo                 | error                  |
      | robsab   | email    | robsab@email.com   | morganfreeman@email.com | Email already in use   |