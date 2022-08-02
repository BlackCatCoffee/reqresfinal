
Feature: get single user
  Scenario: get user using id
    Given the user has an id of 2
    When user calls "getSingleUserAPI" with "GET" http request with expected status 200
    Then the "SINGLE_USER_ID" was 1