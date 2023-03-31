Feature: Movie hashtag search
    As a user, I would like to find what movies are associated to specific hashtags so that I can find movies to watch based on my mood or situation.

    Background:
        Given the following hashtags exist:
            | hashtags |
            | Summer   |
            | Horror   |
            | Cozy     |

        #Home Alone (771), Elf (10719), Toy Story (862)
        And the following movies have been tagged with "Cozy"
            | movieId |
            | 771     |
            | 10719   |
            | 862     |

    Scenario: Successfully get movies with a hashtag
        When a user searches hashtag "Cozy"
        Then the system shall return a list with 3 movies
        And the list of movies shall contain movie ids <771>, <10719>, and <862>

    Scenario: Successfully search an empty hashtag
        When a user searches hashtag "Horror"
        Then the system shall return a list with 0 movies

    Scenario: Failure in searching a hashtag because it doesn't exist
        When a user searches hashtag "top50"
        Then the system shall return no movies
