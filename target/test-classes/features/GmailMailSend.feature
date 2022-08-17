Feature: Gmail Login functionality

Background:
Given The user has launched gmail application in the browser
   
#This is postivie login testcase
Scenario: Check if user is able to login and send email from gmail
#    When The user enters valid <emailid> and <password>
    When The user enters valid emailid and password
    Then Clicks on signin button to login to gmail
    And Check if the user has logged in successfully
    And Compose email with to, subject,  body and send email
    And Check in Sentitem and Inbox
    And Logout of Gmail

#Examples:      |emailid           |password|
#               |gincu926@gmail.com|Password@123|	
      
      
#This is negative login testcase
Scenario: Check if user is unable to login with invalid credentials
#    Given The user has launched gmail application in the browser
    When The user enters invalid emailid 
    Then Clicks on signin button to login to gmail
    And Check if user is unable to login
    And Check if the user is thrown appropriate error message
        
  
