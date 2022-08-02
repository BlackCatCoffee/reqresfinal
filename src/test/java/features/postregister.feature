Feature: register for site
  Scenario Outline: user input to register for site
    Given user registration of "<first_name>" and "<password>"
    When user calls "postRegisterSuccessfulAPI" with "GET" http request with expected status 200
    Then the API call was successful

    Examples:
      |first_name        |password  |
      |morpheus@kdk.com  |drone     |
      |jon@              |handy-man |