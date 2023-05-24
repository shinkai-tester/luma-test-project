package com.shinkai.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Advanced Search")
public class AdvancedSearchTests extends TestBase{

    @CsvSource(value = {
            "Product Name;Jupiter All-Weather Trainer",
            "SKU;MSH11",
    }, delimiterString = ";")
    @ParameterizedTest(name = "Check advanced search separately by {0}")
    void searchByProductNameOrSKU(String field, String searchTerm) {
        advancedSearchPage.openPage()
                .verifySearchFieldAvailable(field)
                .enterSearchTerm(field, searchTerm)
                .clickSearchBtn()
                .selectFirstItem()
                .verifyTextInProductInfo(searchTerm);
    }



    @Test
    @DisplayName("Check advanced search by 2 fields: 'Product Name' and 'SKU'")
    void searchByProductNameAndSKU() {
        String productField = "Product Name";
        String skuField = "SKU";
        String productName = "Orestes Yoga Pant";
        String skuValue = "MP10";

        advancedSearchPage.openPage()
                .verifySearchFieldAvailable(productField)
                .verifySearchFieldAvailable(skuField)
                .enterSearchTerm(productField, productName)
                .enterSearchTerm(skuField, skuValue)
                .clickSearchBtn()
                .selectFirstItem()
                .verifyTextInProductInfo(productName)
                .verifyTextInProductInfo(skuValue);
    }
}
