package com.shinkai.pages;

import com.codeborne.selenide.SelenideElement;
import com.shinkai.helpers.Attach;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateAccountPage {
    private final SelenideElement isSubscribed = $(byId("is_subscribed"));
    private final SelenideElement passwordField = $(byId("password"));
    private final SelenideElement confirmPasswordField = $(byId("password-confirmation"));

    @Step("Open 'Create New Customer Account' page")
    public CreateAccountPage openPage() {
        open("/customer/account/create/");
        $(".page-title span").shouldHave(exactText("Create New Customer Account"));
        return this;
    }

    @Step("Check that 'Personal Information' section has field [{fieldName}]")
    public CreateAccountPage verifyPersonalInfoHasField(String fieldName) {
        $("fieldset.create.info").shouldHave(text(fieldName));
        return this;
    }

    @Step("Check that 'Sign-in Information' section has field [{fieldName}]")
    public CreateAccountPage verifySignInInfoHasField(String fieldName) {
        $("fieldset.create.account").shouldHave(text(fieldName));
        return this;
    }

    @Step("Enter customer first name: [{firstName}]")
    public CreateAccountPage setFirstName(String firstName) {
        $(byId("firstname")).setValue(firstName);
        return this;
    }

    @Step("Enter customer last name: [{lastName}]")
    public CreateAccountPage setLastName(String lastName) {
        $(byId("lastname")).setValue(lastName);
        return this;
    }

    @Step("Enter customer email: [{email}]")
    public CreateAccountPage setEmail(String email) {
        $(byId("email_address")).setValue(email);
        return this;
    }

    @Step("Enter customer password")
    public CreateAccountPage setPassword(String password) {
        passwordField.shouldBe(visible, Duration.ofSeconds(5)).setValue(password);
        passwordField.shouldHave(value(password), Duration.ofSeconds(5));
        return this;
    }

    @Step("Confirm customer password")
    public CreateAccountPage confirmPassword(String password) {
        confirmPasswordField.shouldBe(visible, Duration.ofSeconds(5)).setValue(password);
        confirmPasswordField.shouldHave(value(password), Duration.ofSeconds(5));
        return this;
    }

    @Step("Submit customer account creation")
    public CreateAccountPage submitAccountCreation() {
        $("button[title='Create an Account']").click();
        return this;
    }

    @Step("Check 'thank you for registering' message")
    public void verifyThanksForRegistration() {
        $(".page.messages .message-success")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Thank you for registering with Main Website Store."));
        Attach.screenshotAs("Screenshot verifying that account has been created");
    }

    @Step("Clear value in the field [{fieldName}]")
    public void clearFieldValue(String fieldName) {
        $(byId(fieldName)).shouldBe(visible, Duration.ofSeconds(5)).setValue("");
        $(byId(fieldName)).shouldHave(value(""), Duration.ofSeconds(5));
    }

    @Step("Verify that the field named [{fieldName}] is required when creating an account")
    public void verifyRequiredFieldMessage(String fieldName) {
        $(byId(fieldName)).sibling(0).shouldHave(text("This is a required field."));
        Attach.screenshotAs("Screenshot showing field is required message");
    }

    @Step("Verify password error message: [{errorMessage}]")
    public void verifyPasswordError(String errorMessage) {
        $(byId("password-error")).shouldHave(text(errorMessage), Duration.ofSeconds(5));
        Attach.screenshotAs("Screenshot showing password error message");
    }
}
