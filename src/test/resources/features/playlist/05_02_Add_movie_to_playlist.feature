Feature: Add movie to playlist
    As a user, I would like to add a movie to a playlist so that I can make my playlist complete.

    Background:
        Given the following user is logged in:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |
        And the following playlists already exists in the system for the user
            | Summer |
            | Horror |

    Scenario Outline: Successfully adding a movie to a playlist from movie
        When a user selects searches movie and selects movie "<movie>"
        And the user selects add to playlist
        And the user selects playlist "<playlist>" to add movie to
        Then the playlist "<playlist>" contains the movie "<movie>"

        Examples:
            | movie       | playlist |
            | Avatar      | Summer   |
            | The Shining | Horror   |

    Scenario Outline: Succesfully adding a movie to a playlist from playlist
        When a user is on playlist "<playlist>"
        And the user selects add movie
        And the user searches and selects movie "<movie>"
        And the user selects add to playlist
        Then the playlist "<playlist>" contains the movie "<movie>"

        Examples:
            | movie       | playlist |
            | Avatar      | Summer   |
            | The Shining | Horror   |

    Scenario Outline: Failing to add movie to playlist from movie
        When a user selects searches movie and selects movie "<movie>"
        And the user selects add to playlist
        And the user does not select a playlist to add movie to
        Then no playlist contains the movie "<movie>"

        Examples:
            | movie       |
            | Avatar      |
            | The Shining |