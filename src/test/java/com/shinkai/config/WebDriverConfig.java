package com.shinkai.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"})

public interface WebDriverConfig extends Config {
    @Key("browserWithVersion")
    @DefaultValue("chrome,125.0")
    String[] getBrowserAndVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("baseUrl")
    @DefaultValue("https://magento.softwaretestingboard.com")
    String getBaseUrl();

    @Key("remoteDriverUrl")
    String getRemoteUrl();
}

