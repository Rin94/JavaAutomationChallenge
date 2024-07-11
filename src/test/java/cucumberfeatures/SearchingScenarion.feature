@Tag
  Feature: Liverpool exercise for catalog page testing
    Background:
      Given I landed on liverpool page

    @Regression
    Scenario Outline: Searching play station
    Given an user search for "Play Station"
    When all product in catalog entries contains "PLAYSTATION" or "PS4" or "PS5"
    And click in the first product
    Then the product name <name> and price <price> are displayed in the product page

      Examples:
      |name|price|
      |Consola PlayStation 5 de 1 TB edición Bundle Spider-Man + audífonos|$10,999|

    @Regression
    Scenario Outline: Searching Smart tv
      Given an user search for "Smart Tv"
      And the user filter by size <size>
      And the user filter by price <price>
      And the user filter by brand <brand>
      When the products are retrieved with <size>, <brand>, <price>
      Then total count in the catalog is <count>

      Examples:
      |size|price|brand|count|
      |55|10000|SONY|3       |

    @Regression
    Scenario Outline: Searching beauty
      Given the user search by <category> and <subCategory>
      And click in view more brands
      When the user filter by brand <brand>
      Then all product contains the same brand <brand>

      Examples:
        |category|subCategory|brand|
        |belleza|Perfumes Hombre|DIOR|







