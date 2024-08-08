
@tag
Feature: checkout process
  I want to use this template for my feature file

  @tag1
  Scenario: Successful checkout with existing product in cart
    Given User is on the checkout page
    And User is on login page
    And User choose item
    And User click on cart icon
    When User enters the following shipping details
     | FirstName 	| John 									|     
     | LastName 	| Doe      							|
     | company    | loding      					|
     | street   	| Anystreet   					|
     | city    		| Anycity 							|
     | state   		| Arizona 							|
     | ZipCode 		| 12345   							|
     | Country 		| United States      		|
     | Phone     	| 1234567890						| 
    And User selects the payment method Check  Money order "tablerate_bestway"
    And User confirms billing and shipping address are same
    Then User places the order
    And Order is placed successfully
