package t1.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("Test application is started")
    public void testApplicationIsStarted() {
    }

    @And("User clicks on login input and send {string}")
    public void userClicksOnLoginInputAndSend(String arg0) {
    }

    @And("User clicks on password input and send {string}")
    public void userClicksOnPasswordInputAndSend(String arg0) {
    }

    @Then("Success authorization")
    public void successAuthorization() {
    }

    @Given("Test application is started and user login is successful")
    public void testApplicationIsStartedAndUserLoginIsSuccessful() {
    }

    @When("User clicks on profile")
    public void userClicksOnProfile() {
    }

    @And("User edit contacts")
    public void userEditContacts() {
    }

    @And("Saves new contact info")
    public void savesNewContactInfo() {
    }

    @Then("Successful saving")
    public void successfulSaving() {
    }
}
