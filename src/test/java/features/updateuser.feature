
Feature: update existing user
  Scenario Outline: user is being updated with data using id
    Given user info of "<first_name>" and "<job>" with an id 2
    When user calls "putUpdateAPI" with "PUT" http request with expected status 200
    Then the "USER_UPDATE" was "successful"

    Examples:
      |first_name|job       |
      |morpheus  |drone     |
      |jon       |handy-man |