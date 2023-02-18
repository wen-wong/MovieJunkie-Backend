Feature: Search a movie by title
  As a user, I would like to search movies by title so that I can see information
  on that movie, its ratings, and reviews

  Background:
    Given the following movies exist in the system:
      | Avatar                                |
      | Harry Potter and the Sorcerer's Stone |

  Scenario: Looking for a specific movie
    When the user searches for "Harry Potter and the Sorcerer's Stone"
    Then the system shall return a list of at least 1 movie(s)
    Then the list of movies shall contain "Harry Potter and the Sorcerer's Stone"

  Scenario: Looking for a movie with only 1 keyword
    When the user searches for "avatar"
    Then the system shall return a list of at least 2 movie(s)
    Then the list of movies shall contain "Avatar"
    Then the list of movies shall contain "Avatar: The Way of Water"