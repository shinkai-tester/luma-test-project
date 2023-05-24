package com.shinkai.pages.components;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsListComponent {
    private final ElementsCollection productItems = $$(".product-items li .product-item-info");

    @Step("Select first product item and click on it")
    public void clickOnFirstProductItem() {
        productItems.first().click();
    }

    @Step("Add product item to Cart")
    public void addToCart() {
        $("[title='Add to Cart']").click();
    }
}
