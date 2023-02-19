Feature: Log in
  As a user, I would like to log into my account so that I can interact with people and movies on the platform

  Background:
    Given the following accounts exist in the system:
      | username   | firstName | lastName | email                   | password   |
      | kreeves    | Keanu     | Reeves   | keanureeves@email.com   | 01MATRIX01 |
      | bradcooper | Bradley   | Cooper   | bradleycooper@email.com | StarIsBorn |

  Scenario: Successfully logging in
    When the user "kreeves" attempts log in with password "01MATRIX01"
    Then user "kreeves" shall be logged in

  Scenario Outline: Unsuccessfully logging in
    When the user "<username>" attempts log in with password "<password>"
    Then user "<username>" shall not be logged in
    Then the error message shall be "<error>"

    Examples:
      | username   | password      | error                        |
      | bradcooper | IgotAhangover | invalid username or password |
      | brdcooper  | StarIsBorn    | invalid username or password |
