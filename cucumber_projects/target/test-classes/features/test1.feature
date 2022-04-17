Feature: Editing some info in user profile
  Scenario: Login with correct credentials
    Given Test application is started
    And User clicks on login input and send "user"
    And User clicks on password input and send "password"
    Then Success authorization

  Scenario: Editing profile info
    Given Test application is started and user login is successful
    When User clicks on profile
    And User edit contacts
    And Saves new contact info
    Then Successful saving