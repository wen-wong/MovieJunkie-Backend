Feature: View movie information
  As a user, I would like to see detailed information about a movie so that I can make
  sure I am looking at the right movie.

  # Data in the test cases is taken from the IMDB website

  Background:
    Given the following movies exist in the system:
      | Avatar                                |
      | Harry Potter and the Sorcerer's Stone |

  Scenario: Details about a movie already in the database
    When the user attempts to see detailed information on "Harry Potter and the Sorcerer's Stone"
    Then the database shall contain 2 movies
    Then the database shall contain "Harry Potter and the Sorcerer's Stone"
    Then the returned information shall contain:
      | movieField   | fieldData                                                                                                                                                 |
      | Summary      | An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world. |
      | Director     | Chris Colombus                                                                                                                                            |
      | Release Year | 2001                                                                                                                                                      |

  Scenario: Details about a movie not in the database
    When the user attempts to see detailed information on "Pulp Fiction"
    Then the database shall contain 3 movies
    Then the database shall contain "Pulp Fiction"
    Then the returned information shall contain:
      | movieField   | fieldData                                                                                                                                       |
      | Summary      | The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption. |
      | Director     | Quentin Tarantino                                                                                                                               |
      | Release Year | 1994                                                                                                                                            |
