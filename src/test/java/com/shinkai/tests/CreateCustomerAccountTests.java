package com.shinkai.tests;

import com.shinkai.generators.CustomerFactory;
import com.shinkai.models.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Create New Customer Account")
public class CreateCustomerAccountTests extends TestBase{

    @Test
    @DisplayName("Successful creation of new customer")
    void registerNewUser() {
        Customer newCustomer = CustomerFactory.getRandomCustomer();

        createAccountPage.openPage()
                .verifyPersonalInfoHasField("First Name")
                .verifyPersonalInfoHasField("Last Name")
                .verifyPersonalInfoHasField("Sign Up for Newsletter")
                .verifySignInInfoHasField("Email")
                .verifySignInInfoHasField("Password")
                .verifySignInInfoHasField("Confirm Password");

        createAccountPage.setFirstName(newCustomer.getFirstName())
                .setLastName(newCustomer.getLastName())
                .setSignUpNewsCheckbox()
                .setEmail(newCustomer.getEmail())
                .setPassword(newCustomer.getPassword())
                .confirmPassword(newCustomer.getPassword());

        createAccountPage.submitAccountCreation()
                .verifyThanksForRegistration();

        accountInfoPage.verifyPageTitle();

        accountInfoPage.verifyCustomerName(newCustomer.getFirstName(), newCustomer.getLastName())
                .verifyCustomerEmail(newCustomer.getEmail())
                .verifySubscription();
    }


    @ValueSource(strings = {"firstname", "lastname", "email_address", "password", })
    @ParameterizedTest(name = "Check that field {0} is required when creating an account")
    void registerNewUserWithMissingField(String field) {
        Customer newCustomer = CustomerFactory.getRandomCustomer();
        createAccountPage.openPage()
                .setFirstName(newCustomer.getFirstName())
                .setLastName(newCustomer.getLastName())
                .setSignUpNewsCheckbox()
                .setEmail(newCustomer.getEmail())
                .setPassword(newCustomer.getPassword())
                .confirmPassword(newCustomer.getPassword());

        createAccountPage.clearFieldValue(field);
        createAccountPage.submitAccountCreation();
        createAccountPage.verifyRequiredFieldMessage(field);
    }


    @CsvSource(value = {
            "Pass12!|Minimum length of this field must be equal or greater than 8 symbols",
            "Пароль123!|Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.",
    }, delimiterString = "|")
    @ParameterizedTest(name = "Check password validation error messages: {0}")
    void registerNewUserWithInvalidPassword(String wrongPassword, String errorMessage) {
        Customer newCustomer = CustomerFactory.getRandomCustomer();
        createAccountPage.openPage();
        createAccountPage
                .setPassword(wrongPassword)
                .confirmPassword(wrongPassword)
                .setLastName(newCustomer.getLastName())
                .setFirstName(newCustomer.getFirstName())
                .setSignUpNewsCheckbox()
                .setEmail(newCustomer.getEmail());
        createAccountPage.submitAccountCreation();
        createAccountPage.verifyPasswordError(errorMessage);
    }
}
