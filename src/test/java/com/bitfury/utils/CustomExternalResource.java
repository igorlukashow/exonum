package com.bitfury.utils;

import com.bitfury.pages.EmailSteps;
import com.codeborne.selenide.Configuration;
import org.junit.rules.ExternalResource;

import static com.bitfury.utils.Constants.TIMEOUT_SELENIDE;
import static com.bitfury.utils.PropertiesReader.getProperty;
import static com.bitfury.utils.PropertiesReader.readPropertiesFile;

public class CustomExternalResource extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        readPropertiesFile();
        Configuration.baseUrl = "https://" + getProperty("baseUrl");
        Configuration.timeout = TIMEOUT_SELENIDE;
    }

}
