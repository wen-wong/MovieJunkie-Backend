Feature: Create Playlist
    As a user, I would like to create a playlist so that I can keep similar titles in one area.

    Background:
        Given the following user is logged in:
            | username | email             | password  |
            | robsabs  | robsabs@email.com | ECSE428   |

        And the following playlist already exists in the system for the user
            | Summer |

        
    Scenario Outline: Succesfully creating a playlist
        When a playlist "<playlistname>" is created
        Then 2 playlist exists in the system for the user
        And there shall exist a playlist with name "<playlistname>"

        Examples:
            | playlistname |
            | kids movies  |
            | Christmas    |
            | Action       |

    
    Scenario Outline: Unsuccessfully creating a plyalist because the name is taken
        When a playlist "<playlistname>" is created
        Then 1 playlist shall exist in the system
        And the system gives an error message

        Examples :
        | playlistname |
        | Summer       |


    Scenario Outline: Unsuccessfully creating a playlist because no name is given
        When a playist is created with no name
        Then 1 playlist shall exist in the system
        And the system gives an error message