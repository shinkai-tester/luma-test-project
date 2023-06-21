package com.shinkai.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})

public interface AuthConfig extends Config {
    @Key("usernameSelenoid")
    String usernameSelenoid();

    @Key("passwordSelenoid")
    String passwordSelenoid();
}
