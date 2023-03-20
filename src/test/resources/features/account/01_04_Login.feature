Feature: Log in
    As a user, I would like to log in to view my previous accunt activity and created playlists and reviews.

    Background:
        Given the following accounts exist in the system:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |
            | bradpitt | brad@email.com    | Fightclub |
        
    Scenario Outline: Successfuly logging in
        When a user is on the log in page
        And the user enters a valid "<username>", and "<password>"
        Then the system will be logged into the account

        Examples:
            | username | password  |
            | robsabs  | ECSE428   |
            | bradpitt | Fightclub |

    Scenario Outline: Failing to log in because of incorrect username or password
        When a user is on the log in page
        And a user enters an invalid "<username>" or "<password>"
        Then the system will give an error message
        And no account will be logged in

        Examples:
            | username | password  |
            | rob      | ECSE428   |
            | bradpitt | Fightc    |