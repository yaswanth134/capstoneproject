#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: checkout process

  @tag1
  Scenario: Successful checkout with existing product in cart
    Given User is on the checkout page
    And User is on login page
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


