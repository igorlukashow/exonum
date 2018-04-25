package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ElectionsPage extends TestBase {

    @Step("Set election by title")
    public ElectionsPage setElection(String title) {
        $(byXpath("//tr[@ng-repeat='election in appData.elections']/td[contains(.,'"+title+"')]")).click();
        return this;
    }

    @Step("Click VOTE IN ELECTION button")
    public CandidatesPage clickVoteInElection() {
        $(byXpath("//div[@href='#/elections/candidates']")).click();
        return new CandidatesPage();
    }
}
