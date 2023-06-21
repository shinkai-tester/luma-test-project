package com.shinkai.generators;

import com.github.javafaker.Faker;
import com.shinkai.models.Customer;

public class CustomerFactory {
    public static Customer getRandomCustomer() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = firstName + '.' + lastName + "@example.com",
                password = faker.internet().password
                        (8, 20, true, true, true);

        return new Customer()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password);
    }
}
