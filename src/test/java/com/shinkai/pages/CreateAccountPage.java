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
    private final SelenideElement pageTitle = $(".page-title span");
    private final SelenideElement personalInfo = $("fieldset.create.info");
    private final SelenideElement accountInfo = $("fieldset.create.account");
    private final SelenideElement firstnameField = $(byId("firstname"));
    private final SelenideElement lastnameField = $(byId("lastname"));
    private final SelenideElement emailAddressField = $(byId("email_address"));
    private final SelenideElement passwordField = $(byId("password"));
    private final SelenideElement confirmPasswordField = $(byId("password-confirmation"));
    private final SelenideElement createAccountButton = $("button[title='Create an Account']");
    private final SelenideElement successMessage = $(".page.messages .message-success");
    private final SelenideElement passwordError = $(byId("password-error"));

    @Step("Open 'Create New Customer Account' page")
    public CreateAccountPage openPage() {
        open("/customer/account/create/");
        pageTitle.shouldHave(exactText("Create New Customer Account"));
        return this;
    }

    @Step("Check that 'Personal Information' section has field [{fieldName}]")
    public CreateAccountPage verifyPersonalInfoHasField(String fieldName) {
        personalInfo.shouldHave(text(fieldName));
        return this;
    }

    @Step("Check that 'Sign-in Information' section has field [{fieldName}]")
    public CreateAccountPage verifySignInInfoHasField(String fieldName) {
        accountInfo.shouldHave(text(fieldName));
        return this;
    }

    @Step("Enter customer first name: [{firstName}]")
    public CreateAccountPage setFirstName(String firstName) {
        firstnameField.setValue(firstName);
        return this;
    }

    @Step("Enter customer last name: [{lastName}]")
    public CreateAccountPage setLastName(String lastName) {
        lastnameField.setValue(lastName);
        return this;
    }

    @Step("Select checkbox 'Sign Up for Newsletter'")
    public CreateAccountPage setSignUpNewsCheckbox() {
        isSubscribed.shouldBe(attribute("type", "checkbox"));
        isSubscribed.click();
        return this;
    }

    @Step("Enter customer email: [{email}]")
    public CreateAccountPage setEmail(String email) {
        emailAddressField.setValue(email);
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
        createAccountButton.click();
        return this;
    }

    @Step("Check 'thank you for registering' message")
    public void verifyThanksForRegistration() {
        successMessage
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
        passwordError.shouldHave(text(errorMessage), Duration.ofSeconds(5));
        Attach.screenshotAs("Screenshot showing password error message");
    }
}
