Feature: Reorder movies in a playlist
    As a user, I would like to modify the ordering of movies in a playlist so that others who find my playlist can know the intended ordering.

    Background:
        Given Given the following user is logged in:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |
        And the following playlist already exists in the system for the user
            | playlist |
            | Summer   |
        And the Summer playlist contains the following movies in the followiing order
            | movie        | ordering |
            | Avatar       | 1        |
            | Pulp Fiction | 2        |
            | Scream       | 3        |

    Scenario Outline: Successfully reorder a playlist
        When the user is on playlist "<playlist>"
        And the user selects to rearrange movie "<movie>" to slot "<slot>"
        Then the playlist "<playlist>" contains the movie "<movie>" in slot "<slot>"

        Examples:
            | playlist | movie  | slot |
            | Summer   | Avatar | 2    |


    Scenario Outline: Unsuccessfully reorder a playlist by choosing too far a slot
        When the user is on playlist "<playlist>"
        And the user selects to rearrange movie "<movie>" to slot "<slot>" that is larger than the playlist size
        Then the playlist "<playlist>" does not contain the movie "<movie>" in slot "<slot>"

        Examples:
            | playlist | movie  | slot |
            | Summer   | Avatar | 14   |

