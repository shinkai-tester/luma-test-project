package com.shinkai.pages;

import com.shinkai.helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchPage {

    @Step("Open 'Advanced Search' page'")
    public AdvancedSearchPage openPage() {
        open("/catalogsearch/advanced/");
        $(".page-title span").shouldHave(exactText("Advanced Search"));
        return this;
    }

    @Step("Verify field with name [{fieldName}] on Advanced Search")
    public AdvancedSearchPage verifySearchFieldAvailable(String fieldName) {
        $(byText("Search Settings")).ancestor(".fieldset").shouldHave(text(fieldName));
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
        $$(".product-items li .product-item-info").first().click();
        return this;
    }

    @Step("Verify product item has searchTerm = [{searchTerm}]")
    public AdvancedSearchPage verifyTextInProductInfo(String searchTerm) {
        $(".product-info-main").shouldHave(text(searchTerm));
        Attach.screenshotAs("Product Info");
        return this;
    }

}
