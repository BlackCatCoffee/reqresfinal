@this-one
Feature: create new user
  Scenario Outline: verify that new user is created
    Given user info of "<first_name>" and "<job>"
    When user calls "postCreateAPI" with "CREATE" http request with expected status 201
    Then the "USER_CREATED" was "not null"

    Examples:
    |first_name|job       |
    |morpheus  |leader    |
    |jon       |supervisor|