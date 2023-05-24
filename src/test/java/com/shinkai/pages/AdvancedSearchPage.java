package com.shinkai.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.shinkai.helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchPage {
    private final SelenideElement pageTitle = $(".page-title span");
    private final SelenideElement searchFields = $(byText("Search Settings"))
            .ancestor(".fieldset");
    private final ElementsCollection productItems = $$(".product-items li .product-item-info");
    private final SelenideElement productInfo = $(".product-info-main");

    @Step("Open 'Advanced Search' page'")
    public AdvancedSearchPage openPage() {
        open("/catalogsearch/advanced/");
        pageTitle.shouldHave(exactText("Advanced Search"));
        return this;
    }

    @Step("Verify field with name [{fieldName}] on Advanced Search")
    public AdvancedSearchPage verifySearchFieldAvailable(String fieldName) {
        searchFields.shouldHave(text(fieldName));
        return this;
    }

    @Step("Enter searchTerm = [{searchTerm}] in field [{fieldName}]")
    public AdvancedSearchPage enterSearchTerm(String fieldName, String searchTerm) {
        $("[title='" + fieldName + "']").setValue(searchTerm);
        Attach.screenshotAs("SearchTerm(s) is entered");
        return this;
    }

    @Step("Click search button")
    public AdvancedSearchPage clickSearchBtn() {
        $("button.action.search.primary[title='Search']").click();
        return this;
    }

    @Step("Select first product item from search result")
    public AdvancedSearchPage selectFirstItem() {
        productItems.first().click();
        return this;
    }

    @Step("Verify product item has searchTerm = [{searchTerm}]")
    public AdvancedSearchPage verifyTextInProductInfo(String searchTerm) {
        productInfo.shouldHave(text(searchTerm));
        Attach.screenshotAs("Product Info");
        return this;
    }

}
