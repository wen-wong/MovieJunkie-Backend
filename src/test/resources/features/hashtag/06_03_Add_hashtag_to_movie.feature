Feature: Add hashtags to movies
  As a user, I would like to add a hashtag to a movie so that users (others and myself)
  can find movies based on the hashtags.

  Background:
    Given the following movie ids exist in the system:
      | 1234567 |
      | 4567890 |
      | 3141592 |
    Given the following hashtags exist in the system:
      | water       |
      | cozy        |
      | winter      |
    Given the "water" hashtag is associated to movie 3141592

  Scenario Outline: Successfully adding a pre-existing hashtag to a pre-existing movie
    When a hashtag "<hashtagName>" is added to movie <movieID>
    Then movie <movieID> shall have <numHashtags> hashtag(s) associated to it
    And movie <movieID> shall have "<hashtagName>" associated to it
    And there shall be 3 hashtag(s) in the system

    Examples:
      | hashtagName | movieID | numHashtags |
      | water       | 1234567 | 1           |
      | cozy        | 4567890 | 1           |
      | winter      | 3141592 | 2           |

  Scenario Outline: Successfully adding a non-existing hashtag to a pre-existing movie
    When a hashtag "<hashtagName>" is added to movie <movieID>
    Then movie <movieID> shall have <numHashtags> hashtag(s) associated to it
    And movie <movieID> shall have "<hashtagName>" associated to it
    And there shall be 4 hashtag(s) in the system

    Examples:
      | hashtagName | movieID | numHashtags |
      | fire        | 1234567 | 1           |
      | kids        | 4567890 | 1           |
      | roadtrip    | 3141592 | 2           |

  Scenario Outline: Successfully adding a pre-existing hashtag to a pre-existing movie
    When a hashtag "<hashtagName>" is added to movie <movieID>
    Then movie <movieID> shall have 1 hashtag(s) associated to it
    And movie <movieID> shall have "<hashtagName>" associated to it
    And there shall be 3 hashtag(s) in the system

    Examples:
      | hashtagName | movieID |
      | water       | 2178281 |
      | cozy        | 1414213 |
      | winter      | 1732050 |