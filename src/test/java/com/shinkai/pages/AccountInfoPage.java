package com.shinkai.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class AccountInfoPage {
    private final SelenideElement dashboardInfoTitle = $(".block-dashboard-info").$(".block-title");
    private final SelenideElement customerAccountInfo = $(".box-information");
    private final SelenideElement newsletterSubscription = $(".box-newsletter");

    @Step("Verify 'Account Information' title")
    public void verifyPageTitle() {
        dashboardInfoTitle
                .shouldHave(exactText("Account Information"));
    }

    @Step("Verify that customer's name is: [{firstName}] [{lastName}]")
    public AccountInfoPage verifyCustomerName(String firstName, String lastName) {
        customerAccountInfo.shouldHave(text(firstName + " " + lastName));
        return this;
    }

    @Step("Verify that customer's email is: [{email}]")
    public AccountInfoPage verifyCustomerEmail(String email) {
        customerAccountInfo.shouldHave(text(email));
        return this;
    }

    @Step("Verify that customer is subscribed to general subscription")
    public void verifySubscription() {
        newsletterSubscription.shouldHave(text("You are subscribed to \"General Subscription\"."));
    }
}
