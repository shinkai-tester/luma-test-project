package com.shinkai.pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class ProductsListComponent {

    @Step("Select first product item and click on it")
    public void clickOnFirstProductItem() {
        $$(".product-items li .product-item-info").first().click();
    }
}
