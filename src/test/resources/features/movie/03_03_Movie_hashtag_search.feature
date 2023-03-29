Feature: Movie hashtag search
    As a user, I would like to find what movies are associated to specific hashtags so that I can find movies to watch based on my mood or situation.

    Background:
        Given the following hashtags exist:
            | hashtags |
            | Summer   |
            | Horror   |
            | Cozy     |

        And the following movies have been tagged with "Cozy"
            | movies     |
            | Home Alone |
            | Elf        |
            | Toy Story  |

    Scenario Outline: Successfully get movies with a hashtag
        When a user searches hashtag "Cozy"
        Then the system shall return a list with 3 movies
        And the list of movies shall contain "Home Alone", "Elf", and "Toy Story"

    Scenario Outline: Successfully search an empty hashtag
        When a user searches hashtag "Horror"
        Then the system shall return a list with 0 movies

    Scenario Outline: Failure in searching a hashtag because it doesn't exist
        When a user searches hashtag "top50"
        Then the system shall return an error message 
