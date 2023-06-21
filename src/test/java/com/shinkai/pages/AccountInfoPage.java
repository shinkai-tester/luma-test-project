package com.shinkai.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountInfoPage {
    private final SelenideElement customerAccountInfo = $(".box-information");

    @Step("Verify 'Account Information' title")
    public void verifyPageTitle() {
        $(".block-dashboard-info").$(".block-title")
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
        $(".box-newsletter").shouldHave(text("You are subscribed to \"General Subscription\"."));
    }
}
