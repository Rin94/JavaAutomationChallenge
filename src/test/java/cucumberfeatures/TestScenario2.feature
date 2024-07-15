@Tag
Feature: Liverpool error validation
  Background:
    Given I landed on liverpool page

  @Smoke
  Scenario: Searching for play station - Xbox failure
    When an user search for "Xbox"
    Then all product in catalog entries should contains any of the following items
      |PLAYSTATION|
      |PS4|
      |PS5|

  @Smoke
  Scenario: Searching for Xbox - Success
    When an user search for "Xbox"
    Then all product in catalog entries should contains any of the following items
      |XBOX|
      |GAMEPASS|
      |SEAGATE|
      |REFRIGERADOR|