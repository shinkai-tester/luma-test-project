package com.shinkai.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Go to menu item [{menuItem}]")
    public MainPage goToStoreMenu(String menuItem) {
        open("");
        $(byId("store.menu")).find(byText(menuItem)).click();
        return this;
    }

    @Step("Selecting random product category")
    public void selectRndProductCategory() {
        executeJavaScript(
                "var options = arguments[0].querySelectorAll('ul.items li.item a');" +
                        "var randomIndex = Math.floor(Math.random() * options.length);" +
                        "options[randomIndex].click();",
                $(".categories-menu")
        );
    }

    @Step("Verifying cart counter number")
    public void verifyCartCounter(String expectedNumber) {
        $(byText("My Cart")).sibling(0).find(".counter-number")
                .shouldHave(Condition.exactText(expectedNumber), Duration.ofSeconds(5));
    }
}
