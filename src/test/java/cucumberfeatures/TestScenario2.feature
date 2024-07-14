@Tag
Feature: Liverpool error validation
  Background:
    Given I landed on liverpool page

  @Regression
  Scenario: Searching for play station - Xbox failure
    Given an user search for "Xbox"
    When all product in catalog entries contains "PLAYSTATION" or "PS4" or "PS5"

