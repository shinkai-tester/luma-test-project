package com.shinkai.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.shinkai.helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ProductInfoComponent {
    private final SelenideElement sizeOptionRequired = $("#product-options-wrapper").find(".swatch-attribute.size div.mage-error");
    private final SelenideElement colourOptionRequired = $("#product-options-wrapper").find(".swatch-attribute.color div.mage-error");
    private final SelenideElement addToCarBtn = $("#product-addtocart-button");
    private final SelenideElement sizeParentElement = $("[attribute-code='size'] div[role='listbox']");
    private final SelenideElement colourParentElement = $("[attribute-code='color'] div[role='listbox']");

    @Step("Verifying choosing product size before adding to Cart is required")
    public ProductInfoComponent verifyChoosingSizeIsRequired() {
        sizeOptionRequired.shouldHave(exactText("This is a required field."));
        return this;
    }

    @Step("Verifying choosing product colour before adding to Cart is required")
    public void verifyChoosingColourIsRequired() {
        Attach.screenshotAs("This is a required field");
        colourOptionRequired.shouldHave(exactText("This is a required field."));
    }

    @Step("Add product item to Cart")
    public ProductInfoComponent addItemToCart() {
        addToCarBtn.click();
        return this;
    }

    @Step("Select random product size")
    public ProductInfoComponent selectRndSize() {
        executeJavaScript(
                "var options = arguments[0].querySelectorAll('.swatch-option');" +
                        "var randomIndex = Math.floor(Math.random() * options.length);" +
                        "options[randomIndex].click();",
                sizeParentElement
        );
        return this;
    }

    @Step("Select random product colour")
    public ProductInfoComponent selectRndColour() {
        executeJavaScript(
                "var options = arguments[0].querySelectorAll('.swatch-option');" +
                        "var randomIndex = Math.floor(Math.random() * options.length);" +
                        "options[randomIndex].click();",
                colourParentElement
        );
        return this;
    }

    @Step("Verifying message that product is added to Cart")
    public void verifyAddedToCartMessage() {
        String itemName = $("[itemprop='name']").getText();
        $("div.message-success.success.message [data-bind='html: $parent.prepareMessageForHtml(message.text)']")
                .shouldHave(text("You added " + itemName + " to your shopping cart"));
        Attach.screenshotAs("Product added to Cart");
    }
}
