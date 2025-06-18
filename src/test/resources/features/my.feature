Feature: Product search and cart management

  Background:
    Given The user is on the homepage

  Scenario Outline: User searches for a product and manages the cart
    When The user searches for "<product>"
    And Adds the product to the cart
    Then The product should appear in the cart
    When The user removes the product from the cart
    Then The product should no longer be in the cart

    Examples:
      | product         |
      | wireless mouse  |
      | bluetooth earbud|
      | laptop stand    |