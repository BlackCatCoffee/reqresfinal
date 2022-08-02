
Feature: Get list of Data from Page
  Scenario: verify list of users
    Given user is on page 2
    When user calls "getListUserAPI" with "GET" http request with expected status 200
    Then the "GET_LIST_PAGE_NUMBER" was 2