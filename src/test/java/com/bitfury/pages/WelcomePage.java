package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WelcomePage extends TestBase {

    @Step("Open welcome page")
    public WelcomePage openWelcomePage() {
        open(baseUrl);
        return this;
    }

    @Step("Click VOTE IN ELECTION button")
    public ElectionsPage clickVoteInElection() {
        $(byXpath("//div[text()='VOTE IN ELECTION']")).click();
        return new ElectionsPage();
    }

    @Step("Check opened Welcome page")
    public WelcomePage checkWelcomePageOpened() {
        assertThat(url(), is(baseUrl+"/#/welcome"));
        return this;
    }
}
