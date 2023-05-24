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
    @DefaultValue("1680x1050")
    String getBrowserSize();

    @Key("remoteDriverUrl")
    //@DefaultValue("selenoid.autotests.cloud")
    String getRemoteUrl();

    @Key("baseUrl")
    @DefaultValue("https://magento.softwaretestingboard.com")
    String getBaseUrl();
}

