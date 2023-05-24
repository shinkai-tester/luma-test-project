package com.shinkai.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddInShoppingCartTests extends TestBase {

    @Test
    @DisplayName("Add product item to cart with selected size and colour")
    void addItemToCardWithOptions() {
        mainPage.goToStoreMenu("Women")
                .selectRndProductCategory();

        productsList.clickOnFirstProductItem();

        productInfo.selectRndSize()
                .selectRndColour()
                .addItemToCart()
                .verifyAddedToCartMessage();

        mainPage.verifyCartCounter("1");
    }

    @Test
    @DisplayName("Add product item to cart without selected options")
    void addItemToCardWithoutOptions() {
        mainPage.goToStoreMenu("Women")
                .selectRndProductCategory();

        productsList.clickOnFirstProductItem();

        productInfo.addItemToCart()
                .verifyChoosingSizeIsRequired()
                .verifyChoosingColourIsRequired();
    }
}
