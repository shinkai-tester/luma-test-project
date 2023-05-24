package com.shinkai.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.shinkai.config.WebDriverProvider;
import com.shinkai.helpers.Attach;
import com.shinkai.pages.*;
import com.shinkai.pages.components.ProductInfoComponent;
import com.shinkai.pages.components.ProductsListComponent;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    MainPage mainPage = new MainPage();
    ProductsListComponent productsList = new ProductsListComponent();
    ProductInfoComponent productInfo = new ProductInfoComponent();
    CreateAccountPage createAccountPage = new CreateAccountPage();
    AccountInfoPage accountInfoPage = new AccountInfoPage();
    AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();

    @BeforeAll
    static void setUp() {
        WebDriverProvider.configure();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.addVideo();
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
    }
}
