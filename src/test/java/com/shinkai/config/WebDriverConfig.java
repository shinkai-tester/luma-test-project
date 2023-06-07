package com.shinkai.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"
})
public interface WebDriverConfig extends Config {
    @Key("browserWithVersion")
    @DefaultValue("chrome,113.0")
    String[] getBrowserAndVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("remoteDriverUrl")
    //@DefaultValue("selenoid.autotests.cloud") - set it if it is needed to run the tests using remote Selenoid
    String getRemoteUrl();

    @Key("baseUrl")
    @DefaultValue("https://magento.softwaretestingboard.com")
    String getBaseUrl();

    String env();
}

