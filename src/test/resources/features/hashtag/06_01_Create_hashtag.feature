Feature: Create hashtag
  As a user, I would like to create hashtag so that I can tag movies with this new hashtag.

  Background:
    Given the following hashtags exist in the system:
      | water       |
      | cozy        |
      | winter      |

  Scenario Outline: Successfully creating a hashtag
    When a hashtag "<hashtagName>" is created
    Then there shall be 4 hashtag(s) in the system
    And there shall exist a hashtag with name "<hashtagName>"

    Examples:
      | hashtagName |
      | fire        |
      | surf & turf |
      | Easter      |

  Scenario Outline: Unsuccessfully creating a hashtag because it already exists
    When a hashtag "<hashtagName>" is created
    Then there shall be 3 hashtag(s) in the system
    And the hashtag error message shall be "Hashtag already exists."

    Examples:
      | hashtagName |
      | water       |
      | cozy        |
      | winter      |