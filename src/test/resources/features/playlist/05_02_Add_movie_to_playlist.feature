Feature: Add movie to playlist
    As a user, I would like to add a movie to a playlist so that I can make my playlist complete.

    Background:
        Given the following user is logged in:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |
        And the following playlists already exist in the system for the user
            | Summer |
            | Horror |
        And the following ids of movies exist in the system:
            | 5 |
            | 6 |

    Scenario Outline: Successfully adding a movie to a playlist from movie
        When a user selects searches movie and selects movie with id "<movieid>"
        And the user selects add to playlist
        And the user selects playlist "<playlist>" to add movie to
        Then the playlist "<playlist>" contains the movie with id "<movieid>"

        Examples:
            | movieid | playlist |
            | 6       | Summer   |
            | 5       | Horror   |

    Scenario Outline: Succesfully adding a movie to a playlist from playlist
        When a user is on playlist "<playlist>"
        And the user selects add movie
        And the user searches and selects movie with id "<movieid>"
        And the user selects add to playlist
        Then the playlist "<playlist>" contains the movie with id "<movieid>"

        Examples:
            | movieid | playlist |
            | 5       | Summer   |
            | 6       | Horror   |

    Scenario Outline: Failing to add movie to playlist from movie
        When a user selects searches movie and selects movie with id "<movieid>"
        And the user selects add to playlist
        And the user does not select a playlist to add movie to
        Then no playlist contains the movie with id "<movieid>"

        Examples:
            | movieid |
            | 5       |
            | 6       |
