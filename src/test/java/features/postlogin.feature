@this-one
Feature: login for site
  Scenario Outline: user input to login for site
    Given user registration of "<userName>" and "<password>"
    When user calls "postLoginSuccessfulAPI" with "GET" http request with expected status 200
    Then the "USER_LOGIN" was "successful"

    Examples:
      |userName        |password  |
      |morpheus@kdk.com  |drone     |
      |jon@              |handy-man |
