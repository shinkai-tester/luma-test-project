package com.shinkai.generators;

import com.github.javafaker.Faker;
import com.shinkai.models.Customer;

public class CustomerDataGenerator {
    public static Customer getRandomCustomerData() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = firstName + '.' + lastName + "@example.com",
                password = faker.internet().password
                        (8, 20, true, true, true);

        return new Customer()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password);
    }
}
