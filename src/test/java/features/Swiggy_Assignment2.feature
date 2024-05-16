
Feature: Swiggy_Assignment2
Scenario: Add item to cart and verify it.

Given The user launches the application.
When Enter delivery location "Satara" and Click on search.
Then Search for the product "Pizza".
Then Add the product into cart.
Then Goto cart.
And Verify the prodect is added successfully.

