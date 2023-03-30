Feature: Reorder movies in a playlist
    As a user, I would like to modify the ordering of movies in a playlist so that others who find my playlist can know the intended ordering.

    Background:
        Given Given the following user is logged in:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |
        And the following playlist already exists in the system for the user
            | playlist |
            | Summer   |
        And the Summer playlist contains the following movies in the following order
            | movie        | ordering |
            | Avatar       | 1        |
            | Pulp Fiction | 2        |
            | Scream       | 3        |
        And the movies can be reordered by one position at a time using the following directions
            | direction |
            | Up        |
            | Down      |


    Scenario Outline: Successfully reorder a playlist by moving a movie one position up
        When the user is on playlist "<playlist>"
        And the user selects to move movie "<movie>" one position in the "<direction>" direction
        Then the playlist "<playlist>" contains the movie "<movie>" in position "<ordering>"

        Examples:
            | playlist | movie        | ordering | direction |
            | Summer   | Pulp Fiction | 1        | Up        |
            | Summer   | Scream       | 2        | Up        |

    Scenario Outline: Successfully reorder a playlist by moving a movie one position down
        When the user is on playlist "<playlist>"
        And the user selects to move movie "<movie>" one position in the "<direction>" direction
        Then the playlist "<playlist>" contains the movie "<movie>" in position "<ordering>"

        Examples:
            | playlist | movie        | ordering | direction |
            | Summer   | Avatar       | 2        | Down      |
            | Summer   | Pulp Fiction | 3        | Down      |


    Scenario Outline: Unsuccessfully reorder a playlist by choosing an invalid direction
        When the user is on playlist "<playlist>"
        And the user selects to move movie "<movie>"
        And the movie selected is at position "<ordering>"
        And the user selects an invalid "<direction>" direction
        Then the playlist "<playlist>" does not change order

        Examples:
            | playlist | movie        | ordering | direction |
            | Summer   | Avatar       | 1        | Up        |
            | Summer   | Scream       | 3        | Down      |